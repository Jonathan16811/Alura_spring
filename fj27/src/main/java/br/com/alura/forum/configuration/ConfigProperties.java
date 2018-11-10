package br.com.alura.forum.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties
public class ConfigProperties {
	
	@Value("${alura.forum.jwt.secret}")
	private String secret;
	
	@Value("${alura.forum.jwt.expiration}")
	private long expirationInMillis;

}
