package com.orange.mercado.livre.cadastraPergunta;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.mercado.livre.cadastraProduto.ProdutoRepository;
import com.orange.mercado.livre.cadastraUsuario.Usuario;
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
	
	@Autowired
	private Emails emails;	

	@PostMapping
	public String criaPerguntas(@RequestBody @Valid PerguntaProdutoForm perguntaProdutoForm) {
		
		Usuario interessada = usuarioRepository.findByEmail("daniel@email.com").get();

		PerguntaProduto perguntaproduto = perguntaProdutoForm.converterPergunta(usuarioRepository, produtoRepository, interessada);

		perguntaProdutoRepository.save(perguntaproduto);

		/* System.out.println("Aqui o e-mail com a perguta foi enviada"); */
		
		
		emails.novaPergunta(perguntaproduto);

		return "Pergunta cadastrada com sucesso!";
	}

}
