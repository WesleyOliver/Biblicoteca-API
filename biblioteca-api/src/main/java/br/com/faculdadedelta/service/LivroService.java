package br.com.faculdadedelta.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.faculdadedelta.model.Livro;
import br.com.faculdadedelta.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;

	@Transactional
	public Livro inserir(Livro livro) {
		livro.setId(null);
		return livroRepository.save(livro);
	}

	public Livro pesquisarPorId(Long id) {
		return livroRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	@Transactional
	public Livro alterar(Livro livro, Long id) {
		// busca no banco de dados para garantir que o genero não seja nulo
		Livro livroPesquisado = pesquisarPorId(id);
		/* copia dos novos dados para que não seja persistido os mesmo dados que ja
		existiam no banco */
		BeanUtils.copyProperties(livro, livroPesquisado, "id");
		return livroRepository.save(livroPesquisado);
	}

	@Transactional
	public void excluir(Long id) {
		livroRepository.deleteById(id);
	}

	public List<Livro> listar() {
		return livroRepository.findAll();
	}

}
