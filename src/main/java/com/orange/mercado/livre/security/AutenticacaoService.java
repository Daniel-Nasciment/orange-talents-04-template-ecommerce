package com.orange.mercado.livre.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.orange.mercado.livre.cadastraUsuario.Usuario;
import com.orange.mercado.livre.cadastraUsuario.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	// No Spring ele faz a consulta por e-mail e faz a checagem da senha em memória

	@Autowired
	private UsuarioRepository usuarioRepository;

	/*
	 * Como o repository faz a consulta no banco, e o service valida o email.
	 * Preciso ter um metodo que busque um usuário por email e que seja Opcional
	 * caso não localize no banco. Sendo assim crio uma repository de Usuário e crio
	 * um método de buscar por email
	 * 
	 * @Autowired no repository DENTRO do service de autenticacao
	 * 
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}

		// Exception do String caso não encontre o email
		throw new UsernameNotFoundException("Dados inválidos");
	}

}