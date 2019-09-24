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

import br.com.faculdadedelta.model.Autor;
import br.com.faculdadedelta.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorResource {

	@Autowired
	private AutorService autorService;

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)	
	public Autor inserir(@RequestBody @Valid Autor autor, HttpServletResponse response) {
		
		Autor autorCadastrado = autorService.inserir(autor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(autor.getId())
				.toUri();
				
				response.setHeader(HttpHeaders.LOCATION, uri.toString());
				
				return autorCadastrado;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Autor> listar(){
		return autorService.listar();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Autor pesquisarPorId(@PathVariable("id") Long id) {
		return autorService.pesquisarPorId(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Autor alterar(@RequestBody @Valid Autor autor, @PathVariable("id") Long id) {
		return autorService.alterar(autor, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		autorService.excluir(id);
	}

}