package br.com.alura.forum.security.validator.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationErrorOutputDto {

	private List<String> globalErrorMessages = new ArrayList<>();
	private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();
	
	public void addError(String message) {
		globalErrorMessages.add(message);
	}
	
	public void addFielderror(String field, String message) {
		FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, message);
		fieldErrors.add(fieldError);
	}
}
