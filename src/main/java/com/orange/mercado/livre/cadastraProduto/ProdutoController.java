package com.orange.mercado.livre.cadastraProduto;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.mercado.livre.cadastraCategoria.CategoriaRepository;
import com.orange.mercado.livre.cadastraUsuario.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UploaderFake uploaderFake;

	// Validação customizada

	@InitBinder(value = "produtoForm")
	// Método do Spring MVC que você pode customizar o Controller com coisas
	// adicionais.
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}

	@PostMapping
	public String cadastraProduto(@RequestBody @Valid ProdutoForm produtoForm) {

		Produto produto = produtoForm.converterProduto(categoriaRepository);

		produtoRepository.save(produto);

		return "Produto cadastrado";
	}

	// UPLOAD - multipart/form-data é suportado pelo spring MVC

	@PostMapping(value = "/{id}/imagens")
	public String adicionaImagens(@PathVariable("id") Long id, @Valid ImagemForm imagemForm) {
		/*
		 * 1) enviar imagens para o local onde elas vão ficar 2) pegar os links de todas
		 * as imagens 3) associar esses links com o produto em questao 4) preciso
		 * carregar o produto 5) depois que associar eu preciso atualizar a nova versão
		 * do produto
		 */

		Produto produto = produtoRepository.findById(id).get();

		Set<String> links = uploaderFake.envia(imagemForm.getImagens());
		produto.associaImagens(links);

		produtoRepository.save(produto);

		return produto.toString();

	}

}
