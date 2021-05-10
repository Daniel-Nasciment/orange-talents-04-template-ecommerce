package com.orange.mercado.livre.cadastraOpiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.orange.mercado.livre.cadastraProduto.Produto;
import com.orange.mercado.livre.cadastraUsuario.Usuario;

@Entity
public class OpiniaoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	private String descricao;

	@ManyToOne
	@NotNull
	private Usuario usuario;

	@ManyToOne
	@NotNull
	private Produto produto;

	@Deprecated
	public OpiniaoProduto() {

	}

	public OpiniaoProduto(@NotNull Integer nota, @NotBlank String titulo, @NotBlank String descricao,
			@NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "OpiniaoProduto [id=" + id + ", nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao
				+ ", usuario=" + usuario + ", produto=" + produto + "]";
	}

}
