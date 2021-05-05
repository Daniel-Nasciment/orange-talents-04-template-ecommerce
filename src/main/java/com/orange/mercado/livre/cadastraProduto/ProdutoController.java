package com.orange.mercado.livre.cadastraProduto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.mercado.livre.cadastraCategoria.CategoriaRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//Validação customizada
	
	@InitBinder(value = "produtoForm")
	//Método do Spring MVC que você pode customizar o Controller com coisas adicionais. 
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}

	@PostMapping
	public String cadastraProduto(@RequestBody @Valid ProdutoForm produtoForm) {

		Produto produto = produtoForm.converterProduto(categoriaRepository);

		
		produtoRepository.save(produto);

		return "Produto cadastrado";
	}

}
