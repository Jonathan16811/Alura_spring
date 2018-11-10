package br.com.alura.forum.security.controller.dto.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInputDto {

	private String email;
	private String password;
	
	public UsernamePasswordAuthenticationToken build() {
		return new UsernamePasswordAuthenticationToken(this.email, this.password);
	}
}
