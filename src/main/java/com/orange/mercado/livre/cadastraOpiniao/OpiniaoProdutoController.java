package com.orange.mercado.livre.cadastraOpiniao;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.mercado.livre.cadastraProduto.ProdutoRepository;
import com.orange.mercado.livre.cadastraUsuario.UsuarioRepository;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoProdutoController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private OpiniaoProdutoRepository opiniaoProdutoRepository;

	@PostMapping
	public String criaOpiniao(@RequestBody @Valid OpiniaoProdutoForm opiniaoProdutoForm) {
		
		OpiniaoProduto opiniaoProduto = opiniaoProdutoForm.converteOpiniao(usuarioRepository, produtoRepository);
		
		opiniaoProdutoRepository.save(opiniaoProduto);
		
		return opiniaoProduto.toString();
	}

	
}
