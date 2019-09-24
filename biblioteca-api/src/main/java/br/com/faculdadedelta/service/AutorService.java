package br.com.faculdadedelta.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.faculdadedelta.model.Autor;
import br.com.faculdadedelta.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;

	@Transactional
	public Autor inserir(Autor autor) {
		autor.setId(null);
		return autorRepository.save(autor);
	}

	public Autor pesquisarPorId(Long id) {
		return autorRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	@Transactional
	public Autor alterar(Autor autor, Long id) {
		// busca no banco de dados para garantir que o genero não seja nulo
		Autor autorPesquisado = pesquisarPorId(id);
		/*
		 * copia dos novos dados para que não seja persistido os mesmo dados que ja
		 * existiam no banco
		 */
		BeanUtils.copyProperties(autor,  autorPesquisado, "id");
		return autorRepository.save(autorPesquisado);
	}
	
	@Transactional
	public void excluir(Long id) {
		autorRepository.deleteById(id);
	}
	
	public List<Autor> listar(){
		return autorRepository.findAll();
	}

}