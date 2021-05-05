package com.orange.mercado.livre.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.orange.mercado.livre.cadastraUsuario.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	// Como ejetar uma propriedade que está no application properties?
	// Ambos foram declarados no APP PROP para não ficar espalhados no código

	@Value("${jwt.expiration}")
	private String expiration;

	@Value("${jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		// Esse authentication.getPrincipal() recupera o usuário logado

		Date hoje = new Date();

		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("API do mercado livre").setSubject(logado.getId().toString())
				.setIssuedAt(hoje).setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Long getIdUsuario(String token) {
		// Devolve o objeto do TOKEN
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();

		// Converto a STRING para long
		// Subject = Usuário
		return Long.parseLong(claims.getSubject());

	}

}