package com.orange.mercado.livre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.mercado.livre.form.UsuarioForm;
import com.orange.mercado.livre.model.Usuario;
import com.orange.mercado.livre.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {

		Usuario usuario = usuarioForm.converteUsuario();
		usuarioRepository.save(usuario);
		
		return ResponseEntity.ok("Usuário Cadastrado!");
	}

	
	

	
}
