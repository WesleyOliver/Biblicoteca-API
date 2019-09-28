package br.com.faculdadedelta.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

import br.com.faculdadedelta.model.Cliente;
import br.com.faculdadedelta.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)	
	@PreAuthorize("hasAuthority('ROLE_INCLUIR_CLIENTE')")
	public Cliente inserir(@RequestBody @Valid Cliente cliente, HttpServletResponse response) {
		
		Cliente clienteCadastrado = clienteService.inserir(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
				
				response.setHeader(HttpHeaders.LOCATION, uri.toString());
				
				return clienteCadastrado;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_LISTAR_CLIENTE')")
	public List<Cliente> listar(){
		return clienteService.listar();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_POR_ID_CLIENTE')")
	public Cliente pesquisarPorId(@PathVariable("id") Long id) {
		return clienteService.pesquisarPorId(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_ALTERAR_CLIENTE')")
	public Cliente alterar(@RequestBody @Valid Cliente cliente, @PathVariable("id") Long id) {
		return clienteService.alterar(cliente, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_EXCLUIR_CLIENTE')")
	public void excluir(@PathVariable("id") Long id) {
		clienteService.excluir(id);
	}

}
