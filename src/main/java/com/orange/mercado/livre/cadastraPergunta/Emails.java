package com.orange.mercado.livre.cadastraPergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Emails {

	@Autowired
	// 1
	private Mailer mailer;

	// 1
	public void novaPergunta(@NotNull @Valid PerguntaProduto pergunta) {
		mailer.send("<html>...</html>", "Nova pergunta...", pergunta.getInteressada().getEmail(),
				"novapergunta@nossomercadolivre.com", pergunta.getDonoProduto().getEmail());
	}

}