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

import br.com.faculdadedelta.model.Editora;
import br.com.faculdadedelta.service.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraResource {

	@Autowired
	private EditoraService editoraService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Editora inserir(@RequestBody @Valid Editora editora, HttpServletResponse response) {

		Editora editoraCadastrada = editoraService.inserir(editora);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(editora.getId())
				.toUri();

		response.setHeader(HttpHeaders.LOCATION, uri.toString());

		return editoraCadastrada;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Editora> listar(){
		return editoraService.listar();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Editora pesquisarPorId(@PathVariable("id") Long id) {
		return editoraService.pesquisarPorId(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Editora alterar(@RequestBody @Valid Editora editora, @PathVariable("id") Long id) {
		return editoraService.alterar(editora, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		editoraService.excluir(id);
	}

}
