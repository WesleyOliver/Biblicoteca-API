package br.com.faculdadedelta.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // controle para escutar todas a exceções lançadas
@Order(Ordered.HIGHEST_PRECEDENCE) // da prioridade na execussão das clases
public class ApiException extends ResponseEntityExceptionHandler {
	
	// trata erro de pesquisa de recurso que não existe
	@ExceptionHandler({ EmptyResultDataAccessException.class }) // escuta somente essa exceção
	public ResponseEntity<Object> handlerEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String mensagemUsuario = "Recurso não encontrado!";
		String mensagemDesenvolvedor = ex.toString();
		List<ErroDetalhe> erros = Arrays.asList(new ErroDetalhe(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	// cria a lista de erros dos campos abrigatórios
	private List<ErroDetalhe> criarListaErros(BindingResult bindingResult){
		List<ErroDetalhe> erros = new ArrayList<>();
		
		// Laço for each
		bindingResult.getFieldErrors().forEach((fieldErro) -> erros.add(
				new ErroDetalhe(fieldErro.getDefaultMessage(),
				fieldErro.toString())));
		
		// Laço for tradicional
		/* for(FieldError fieldError : bindingResult.getFieldErrors()) {
			erros.add(new ErroDetalhe(fieldError.getDefaultMessage(),  fieldError.toString()));
		} */
		return erros;		
	}
	
	// consome e valida a lista de erros de compos abrigatórios
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<ErroDetalhe> erros = criarListaErros(ex.getBindingResult());
		
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		String mensagemUsuario = "Falha ao realizar operação, tente novamente mais tarde!";
		String mensagemDesenvolvedor = ex.toString();
		List<ErroDetalhe> erros = Arrays.asList(new ErroDetalhe(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

 	public static class ErroDetalhe {
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;

		public ErroDetalhe(String mensagemUsuario, String mensagemDesenvolvedor) {
			super();
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}

	}

}
