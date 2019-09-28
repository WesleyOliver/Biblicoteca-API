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

import br.com.faculdadedelta.model.Emprestimo;
import br.com.faculdadedelta.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoResource {
	
	@Autowired
	private EmprestimoService emprestimoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_INCLUIR_EMPRESTIMO')")
	public Emprestimo inserir(@RequestBody @Valid Emprestimo emprestimo, HttpServletResponse response) {

		Emprestimo emprestimoCadastrado = emprestimoService.inserir(emprestimo);

		// devolve para a tela a url do recurso cadastrado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(emprestimo.getId()).toUri();

		response.setHeader(HttpHeaders.LOCATION, uri.toString());

		return emprestimoCadastrado;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_LISTAR_EMPRESTIMO')")
	public List<Emprestimo> listar() {
		return emprestimoService.listar();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_POR_ID_EMPRESTIMO')")
	public Emprestimo pesquisarPorId(@PathVariable("id") Long id) {
		return emprestimoService.pesquisarPorId(id);

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_ALTERAR_EMPRESTIMO')")
	public Emprestimo alterar(@RequestBody @Valid Emprestimo emprestimo, @PathVariable("id") Long id) {
		return emprestimoService.alterar(emprestimo, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_EXCLUIR_EMPRESTIMO')")
	public void excluir(@PathVariable("id") Long id) {
		emprestimoService.excluir(id);
	}

}
