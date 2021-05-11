package com.orange.mercado.livre.detalhes;

import com.orange.mercado.livre.cadastraProduto.CaracteristicaProduto;

public class DetalheCaracteristica {

	private String nome;
	private String descricao;

	public DetalheCaracteristica(CaracteristicaProduto caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
