package com.orange.mercado.livre.cadastraProduto;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// Verificando se a classe que veio é igual a ProdutoForm ou se é classe filha
		return ProdutoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		//Qualquer lógica de validação só pode ser feita se não houver erro 
		
		if (errors.hasErrors()) {
			return;
		}

		
		
		ProdutoForm form = (ProdutoForm) target;
		Set<String> nomesIguais = form.buscaCaracteristicasIguais();
		if (!nomesIguais.isEmpty()) {
			//(parameter, errorCode, message)
			errors.rejectValue("caracteristicas", null, "Olha, você tem caracteristicas iguais " + nomesIguais);
		}
	}

}
