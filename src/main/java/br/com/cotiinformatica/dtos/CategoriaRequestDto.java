package br.com.cotiinformatica.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaRequestDto {
	
	@Size(min = 8, max= 50)
	private String nome;

}
