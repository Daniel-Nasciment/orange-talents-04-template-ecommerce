package com.orange.mercado.livre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@ManyToOne
	private Categoria catMae;

	@Deprecated
	public Categoria() {

	}

	public Categoria(@NotBlank String nome) {
		super();
		this.nome = nome;
	}

	public void setMae(Categoria categoriaMae) {
		this.catMae = categoriaMae;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", catMae=" + catMae + "]";
	}

}
