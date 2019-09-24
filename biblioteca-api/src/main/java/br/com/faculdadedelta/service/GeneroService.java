package br.com.faculdadedelta.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.faculdadedelta.model.Genero;
import br.com.faculdadedelta.repository.GeneroRepository;

@Service
public class GeneroService {

	@Autowired
	private GeneroRepository generoRepository;

	@Transactional
	public Genero inserir(Genero genero) {
		genero.setId(null);
		return generoRepository.save(genero);
	}

	public Genero pesquisarPorId(Long id) {
		return generoRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	@Transactional
	public Genero alterar(Genero genero, Long id) {
		// busca no banco de dados para garantir que o genero não seja nulo
		Genero generoPesquisado = pesquisarPorId(id);
		/* copia dos novos dados para que não seja persistido os mesmo dados que ja
		existiam no banco */
		BeanUtils.copyProperties(genero, generoPesquisado, "id");
		return generoRepository.save(generoPesquisado);
	}

	@Transactional
	public void excluir(Long id) {
		generoRepository.deleteById(id);
	}

	public List<Genero> listar() {
		return generoRepository.findAll();
	}

}
