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

import br.com.faculdadedelta.model.Endereco;
import br.com.faculdadedelta.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco inserir(@RequestBody @Valid Endereco endereco, HttpServletResponse response) {

		Endereco enderecoCadastrada = enderecoService.inserir(endereco);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getId())
				.toUri();

		response.setHeader(HttpHeaders.LOCATION, uri.toString());

		return enderecoCadastrada;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Endereco> listar() {
		return enderecoService.listar();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Endereco pesquisarPorId(@PathVariable("id") Long id) {
		return enderecoService.pesquisarPorId(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Endereco alterar(@RequestBody @Valid Endereco endereco, @PathVariable("id") Long id) {
		return enderecoService.alterar(endereco, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		enderecoService.excluir(id);
	}
}

