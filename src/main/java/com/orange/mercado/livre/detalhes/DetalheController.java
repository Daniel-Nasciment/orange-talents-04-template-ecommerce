package com.orange.mercado.livre.detalhes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.orange.mercado.livre.cadastraProduto.Produto;
import com.orange.mercado.livre.cadastraProduto.ProdutoRepository;

@RestController
public class DetalheController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/detalhes/{id}")
	public DetalhesDto detalhaProd(@PathVariable("id") Long id) {

		/*
		 * Erro 500 caso não encontre um ID não existente:
		 * 
		 * Poderia me defender desse erro, porém só devo fazer isso visto que o projeto tenha a
		 * extrema necessidade de suportar valores errados na URL.
		 */
		
		Produto produto = produtoRepository.findById(id).get();

		return new DetalhesDto(produto);
	}

}
