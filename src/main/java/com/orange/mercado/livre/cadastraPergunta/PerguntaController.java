package com.orange.mercado.livre.cadastraPergunta;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.mercado.livre.cadastraProduto.ProdutoRepository;
import com.orange.mercado.livre.cadastraUsuario.UsuarioRepository;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerguntaProdutoRepository perguntaProdutoRepository;

	@PostMapping
	public String criaPerguntas(@RequestBody @Valid PerguntaProdutoForm perguntaProdutoForm) {

		PerguntaProduto perguntaproduto = perguntaProdutoForm.converterPergunta(usuarioRepository, produtoRepository);

		perguntaProdutoRepository.save(perguntaproduto);

		System.out.println("Aqui o e-mail com a perguta foi enviada");

		return "Pergunta cadastrada com sucesso!";
	}

}
