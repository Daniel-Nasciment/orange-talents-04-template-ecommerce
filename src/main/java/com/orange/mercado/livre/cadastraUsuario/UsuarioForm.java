package com.orange.mercado.livre.cadastraUsuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.orange.mercado.livre.validator.UniqueValue;

public class UsuarioForm {

	@Email
	@NotBlank
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;
	@Length(min = 6)
	@NotBlank
	private String senha;

	public UsuarioForm(@Email @NotBlank String email, @NotBlank String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public Usuario converteUsuario() {

		String hashSenha = new BCryptPasswordEncoder().encode(senha);

		return new Usuario(this.email, hashSenha);
	}

}
