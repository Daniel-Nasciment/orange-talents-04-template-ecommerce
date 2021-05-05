package com.orange.mercado.livre.cadastraCategoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.orange.mercado.livre.validator.ExistsId;
import com.orange.mercado.livre.validator.UniqueValue;

public class CategoriaForm {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	@Positive
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCatMae;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdCatMae(Long idCatMae) {
		this.idCatMae = idCatMae;
	}

	public Categoria converterCategoria(CategoriaRepository categoriaRepository) {

		Categoria categoria = new Categoria(this.nome);

		if (idCatMae != null) {
			Categoria categoriaMae = categoriaRepository.findById(idCatMae).get();
			categoria.setMae(categoriaMae);
		}

		return categoria;
	}
}
