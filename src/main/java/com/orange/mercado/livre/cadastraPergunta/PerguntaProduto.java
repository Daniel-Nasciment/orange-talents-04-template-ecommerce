package com.orange.mercado.livre.cadastraPergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.orange.mercado.livre.cadastraProduto.Produto;
import com.orange.mercado.livre.cadastraUsuario.Usuario;

@Entity
public class PerguntaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotNull
	private LocalDateTime instPerguntado = LocalDateTime.now();

	@ManyToOne
	@NotNull
	private Produto produto;

	@ManyToOne
	@NotNull
	private Usuario usuario;

	@Deprecated
	public PerguntaProduto() {

	}

	public PerguntaProduto(@NotBlank String titulo, @NotNull Produto produto, @NotNull Usuario usuario) {
		super();
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "PerguntaProduto [id=" + id + ", titulo=" + titulo + ", instPerguntado=" + instPerguntado + ", produto="
				+ produto + ", usuario=" + usuario + "]";
	}
	
	public Usuario getInteressada() {
		return usuario;
	}

	public Usuario getDonoProduto() {
		return produto.getDono();
	}

}
