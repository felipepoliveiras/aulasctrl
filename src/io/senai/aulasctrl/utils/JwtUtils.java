package io.senai.aulasctrl.utils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.senai.aulasctrl.models.Usuario;

public class JwtUtils {
	
	public static final String ISSUER = "SENAI Informatica";
	public static final String AUTH_SUBJ = "API Authentication";
	public static final String AUTH_SECRET = "KAJSDLISAD87A80S9DSADNSDASDUASDAISDSJDKHjkashdkjashKDUah";
	
	private static final Algorithm getTokenAutenticacaoAlg() {
		try {
			return Algorithm.HMAC512(AUTH_SECRET);
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static final String gerarTokenAutenticacao(Usuario usuario) {
		return
			JWT
				.create()
				//Dados
				.withIssuedAt(new Date())
				.withExpiresAt(CalendarUtils.add(1, Calendar.DAY_OF_MONTH))
				.withIssuer(ISSUER)
				.withSubject(AUTH_SUBJ)
				//Paylod
				.withClaim("id", usuario.getId())
				.withClaim("nome", usuario.getNome())
				.withClaim("email", usuario.getEmail())
				//Assinatura
				.sign(getTokenAutenticacaoAlg());
	}
	
	public static final Usuario getUsuarioDeTokenAutenticacao(String token) {
		DecodedJWT jwt = JWT.decode(token);
		Usuario usuario = new Usuario();
		usuario.setId(jwt.getClaim("id").asLong());
		usuario.setNome(jwt.getClaim("nome").asString());
		usuario.setEmail(jwt.getClaim("email").asString());
		
		return usuario;
	}
	
	public static final void validarTokenAutenticacao(String token) throws JWTVerificationException{
		
				JWT
					.require(getTokenAutenticacaoAlg())
					.build()
					.verify(token);
	}

}
