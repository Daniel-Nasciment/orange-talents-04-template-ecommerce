package com.orange.mercado.livre.cadastraOpiniao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.orange.mercado.livre.cadastraProduto.Produto;
import com.orange.mercado.livre.cadastraProduto.ProdutoRepository;
import com.orange.mercado.livre.cadastraUsuario.Usuario;
import com.orange.mercado.livre.cadastraUsuario.UsuarioRepository;
import com.orange.mercado.livre.validator.ExistsId;

public class OpiniaoProdutoForm {

	@Min(1)
	@Max(5)
	@NotNull
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank @Length(max = 500)
	private String descricao;

	@NotNull
	@ExistsId(domainClass = Usuario.class, fieldName = "id")
	private Long usuario;

	@NotNull
	@ExistsId(domainClass = Produto.class, fieldName = "id")
	private Long produto;

	public OpiniaoProdutoForm(@Min(1) @Max(5) @NotNull Integer nota, @NotBlank String titulo,
			@NotBlank String descricao, @NotNull Long usuario, @NotNull Long produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "OpiniaoProdutoForm [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", usuario="
				+ usuario + ", produto=" + produto + "]";
	}

	public OpiniaoProduto converteOpiniao(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository) {
		
		Usuario usuario = usuarioRepository.findById(this.usuario).get();
		Produto produto = produtoRepository.findById(this.produto).get();
		
		return new OpiniaoProduto(this.nota, this.titulo, this.descricao, usuario, produto);
	}

	
	
}
