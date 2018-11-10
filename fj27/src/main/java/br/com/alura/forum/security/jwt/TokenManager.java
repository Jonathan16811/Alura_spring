package br.com.alura.forum.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.alura.forum.configuration.ConfigProperties;
import br.com.alura.forum.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {

	private static final String CANAL = "Alura Forúm API";

	@Autowired
	private ConfigProperties configProperties;

	public String generateToken(Authentication authentication) {

		User user = (User) authentication.getPrincipal();

		final Date now = new Date();
		final Date expiration = new Date(now.getTime() + configProperties.getExpirationInMillis());

		return Jwts.builder().setIssuer(CANAL).setSubject(Long.toString(user.getId())).setIssuedAt(now)
				.setExpiration(expiration).signWith(SignatureAlgorithm.HS256, configProperties.getSecret()).compact();

	}

	public boolean isValid(String jwt) {
		try {
			Jwts.parser().setSigningKey(configProperties.getSecret()).parseClaimsJws(jwt);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}

	}

	public Long getUserIdFromToken(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(configProperties.getSecret()).parseClaimsJws(jwt).getBody();
		return Long.parseLong(claims.getSubject());

	}
}
