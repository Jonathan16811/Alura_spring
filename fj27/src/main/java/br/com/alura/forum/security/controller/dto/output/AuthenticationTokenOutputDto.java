package br.com.alura.forum.security.controller.dto.output;

import lombok.Getter;

@Getter
public class AuthenticationTokenOutputDto {

	private String tokenType;
	private String token;

	public AuthenticationTokenOutputDto(String tokenType, String token) {
		super();
		this.tokenType = tokenType;
		this.token = token;
	}
}