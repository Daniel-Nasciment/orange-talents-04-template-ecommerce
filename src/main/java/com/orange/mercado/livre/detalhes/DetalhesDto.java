package com.orange.mercado.livre.detalhes;

import java.math.BigDecimal;
import java.util.Set;
import java.util.SortedSet;

import com.orange.mercado.livre.cadastraProduto.Produto;

public class DetalhesDto {

	private String nome;
	private BigDecimal valor;
	private String descricao;
	private Set<DetalheCaracteristica> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;

	public DetalhesDto(Produto produto) {
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.descricao = produto.getDescricao();
		this.caracteristicas = produto.mapCaracteristicas(DetalheCaracteristica::new);

		/*
		 * PODERIA RESOLVER DESSA FORMA, MAS SERÁ REALIZADO DA SEGUINTE FORMA:
		 * 
		 * Aqui eu retornaria a coleção inteira e com isso eu possibilito a manipulação
		 * do objeto.
		 * 
		 * 
		 * this.caracteristicas = produto.getCaracteristicas().stream()
		 * .map(caracteristica -> new DetalhesProdutoCaracteristicaDto(caracteristica));
		 */

		this.linksImagens = produto.mapLinksImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());

	}
	
	//Sempre lembrar dos getters na classe de DTO para o Jackson serializar meu objeto

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public Set<DetalheCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public SortedSet<String> getPerguntas() {
		return perguntas;
	}

}