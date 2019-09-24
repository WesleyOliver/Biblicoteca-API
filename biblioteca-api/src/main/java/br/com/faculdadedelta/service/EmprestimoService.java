package br.com.faculdadedelta.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.faculdadedelta.model.Emprestimo;
import br.com.faculdadedelta.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Transactional
	public Emprestimo inserir(Emprestimo emprestimo) {
		emprestimo.setId(null);
		return emprestimoRepository.save(emprestimo);
	}

	public Emprestimo pesquisarPorId(Long id) {
		return emprestimoRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	@Transactional
	public Emprestimo alterar(Emprestimo emprestimo, Long id) {
		// busca no banco de dados para garantir que o genero não seja nulo
		Emprestimo emprestimoPesquisado = pesquisarPorId(id);
		/* copia dos novos dados para que não seja persistido os mesmo dados que ja
		existiam no banco */
		BeanUtils.copyProperties(emprestimo, emprestimoPesquisado, "id");
		return emprestimoRepository.save(emprestimoPesquisado);
	}

	@Transactional
	public void excluir(Long id) {
		emprestimoRepository.deleteById(id);
	}

	public List<Emprestimo> listar() {
		return emprestimoRepository.findAll();
	}

}
