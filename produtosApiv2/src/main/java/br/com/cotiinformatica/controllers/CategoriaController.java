package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.CategoriaRequestDto;
import br.com.cotiinformatica.dtos.CategoriaResponseDto;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/categorias")

public class CategoriaController {

	private ModelMapper mapper = new ModelMapper();

	@GetMapping("/consultar")
	public List<CategoriaResponseDto> consultar() {

		try {
			var categoriaRepository = new CategoriaRepository();
			var categorias = categoriaRepository.ConsultarCategorias();

			var response = new ArrayList<CategoriaResponseDto>();

			for (var categoria : categorias) {
				response.add(mapper.map(categoria, CategoriaResponseDto.class));
			}
			return response;

		} catch (

		Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@PostMapping("cadastrar")
	public ResponseEntity<String> cadastrar(@RequestBody CategoriaRequestDto categoriaDTO) {

		try {
			var categoriaRepository = new CategoriaRepository();
			var validacao = categoriaRepository.ConsultarCategoriaComID(categoriaDTO.getNome());
			
			if (validacao == 1) {
				categoriaRepository.CadastrarCategoria(categoriaDTO);
				return ResponseEntity.status(HttpStatus.CREATED).body("CATEGORIA CADASTRADA");
			} else   {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe uma caegoria com esse nome.");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FALHA AO CADASTRAR.");
		}

	}

}
