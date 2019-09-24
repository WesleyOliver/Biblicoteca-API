package br.com.faculdadedelta.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.faculdadedelta.model.Genero;
import br.com.faculdadedelta.service.GeneroService;

@RestController
@RequestMapping("/generos")
public class GeneroResource {

	@Autowired
	private GeneroService generoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Genero inserir(@RequestBody @Valid Genero genero, HttpServletResponse response) {

		Genero generoCadastrado = generoService.inserir(genero);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(genero.getId()).toUri();

		response.setHeader(HttpHeaders.LOCATION, uri.toString());

		return generoCadastrado;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Genero> listar(){
		return generoService.listar();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Genero pesquisarPorId(@PathVariable("id") Long id) {
		return generoService.pesquisarPorId(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Genero alterar(@RequestBody @Valid Genero genero, @PathVariable("id") Long id) {
		return generoService.alterar(genero, id);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {		
		generoService.excluir(id);
	}

}
