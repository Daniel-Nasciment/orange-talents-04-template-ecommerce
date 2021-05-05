package com.orange.mercado.livre.cadastraUsuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
