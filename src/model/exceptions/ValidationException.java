package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException{

	// Classe para validar um formulario, ou seja, carregar as mensagens de erro do formulario
	//caso existam
	// Exceção personalizada que vai carregar uma coleção contendo todos os erros possiveis
	
	private static final long serialVersionUID = 1L;
	
	//o primeiro string vai indicar o nome do campo e o segundo a mensagem de erro
	private Map<String, String> errors = new HashMap<>();
	
	public ValidationException(String msg) {
		super(msg);
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	
	//metodo para permitir adicionar um elemento na coleção map
	public void addError(String fieldName, String errorMessage) {
		errors.put(fieldName, errorMessage);
	}
}
