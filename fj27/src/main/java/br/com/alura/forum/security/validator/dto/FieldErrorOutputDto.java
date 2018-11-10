package br.com.alura.forum.security.validator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldErrorOutputDto {

	private String field;
	private String message;
	
	public FieldErrorOutputDto(String field, String message) {
		this.field = field;
		this.message = message;
	}
	
	
}
