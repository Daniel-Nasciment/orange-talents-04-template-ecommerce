package com.orange.mercado.livre.cadastraPergunta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.orange.mercado.livre.cadastraProduto.Produto;
import com.orange.mercado.livre.cadastraProduto.ProdutoRepository;
import com.orange.mercado.livre.cadastraUsuario.Usuario;
import com.orange.mercado.livre.cadastraUsuario.UsuarioRepository;
import com.orange.mercado.livre.validator.ExistsId;

public class PerguntaProdutoForm {

	@NotBlank
	private String titulo;

	@NotNull
	@ExistsId(domainClass = Usuario.class, fieldName = "id")
	private Long usuario;

	@NotNull
	@ExistsId(domainClass = Produto.class, fieldName = "id")
	private Long produto;

	public PerguntaProdutoForm(@NotBlank String titulo, @NotNull Long usuario, @NotNull Long produto) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Long getUsuario() {
		return usuario;
	}

	public Long getProduto() {
		return produto;
	}

	@Override
	public String toString() {
		return "PerguntaProdutoForm [titulo=" + titulo + ", usuario=" + usuario + "]";
	}

	public PerguntaProduto converterPergunta(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository, Usuario interessada) {

		Produto produto = produtoRepository.findById(this.produto).get();

		return new PerguntaProduto(this.titulo, produto, interessada);
	}

}
