package br.com.cotiinformatica.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.dtos.CategoriaRequestDto;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class CategoriaRepository {

	public List<Categoria> ConsultarCategorias() throws Exception {

		var connection = ConnectionFactory.getConnetion();
		var query = "SELECT * FROM CATEGORIA";
		var statement = connection.prepareStatement(query);
		var resultado = statement.executeQuery();

		var lista = new ArrayList<Categoria>();

		while (resultado.next()) {
			var categoria = new Categoria();
			categoria.setId(UUID.fromString(resultado.getString("ID")));
			categoria.setNome(resultado.getString("NOME"));

			lista.add(categoria);

		}
		return lista;
	}

	public Integer ConsultarCategoriaComID(String nome) throws Exception {

		var connection = ConnectionFactory.getConnetion();
		var query = "SELECT nome as qtde FROM CATEGORIA WHERE UPPER(NOME) LIKE UPPER(?)";
		var statement = connection.prepareStatement(query);
		statement.setString(1, nome);
		var resultado = statement.executeQuery();

		System.out.println(resultado);

		if (resultado.next()) {
				return 1;
			
		} else {
			return 0;
		}

	}

	public void CadastrarCategoria(CategoriaRequestDto categoria) throws Exception {

		var connection = ConnectionFactory.getConnetion();
		var query = "INSERT INTO CATEGORIA(ID,NOME) VALUES (?,?)";
		var statement = connection.prepareStatement(query);
		statement.setObject(1, UUID.randomUUID());
		statement.setString(2, categoria.getNome());
		statement.executeUpdate();
	}

}
