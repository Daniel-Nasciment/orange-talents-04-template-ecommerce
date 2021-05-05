package com.orange.mercado.livre.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.orange.mercado.livre.cadastraUsuario.Usuario;
import com.orange.mercado.livre.cadastraUsuario.UsuarioRepository;

//Essa extenssão OncePerRequestFilter é chamado uma unica vez a cada requisição
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	// Essa classe faz a autenticação do token

	// Aqui a injeção de dependencia é feita através do construtor
	// No meu ConfigurationSecurity é passado como parametro o tokenService

	private TokenService tokenService;

	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		super();
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);

		if (valido) {
			autenticarCliente(token);
		}

		filterChain.doFilter(request, response);
	}

	// Posso recuperar o ID do cliente pelo TOKEN
	// Aqui estou forçando a autenticação
	private void autenticarCliente(String token) {
		// Método comentado na classe service
		Long idUsuario = tokenService.getIdUsuario(token);

		Usuario usuario = usuarioRepository.findById(idUsuario).get();

		// usuario, senha, perfis de acesso
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
				usuario.getAuthorities());

		// Considere que está autenticado
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {

		// get header e o tipo do cabeçalho que nesse caso é o Authorization

		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		// substring do 7 até o tamanho da tring = começa a pegar a partit do 7

		return token.substring(7, token.length());

	}

}