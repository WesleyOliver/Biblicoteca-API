package br.com.faculdadedelta.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.faculdadedelta.model.Endereco;
import br.com.faculdadedelta.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional
	public Endereco inserir(Endereco endereco) {
		endereco.setId(null);
		return enderecoRepository.save(endereco);
	}

	public Endereco pesquisarPorId(Long id) {
		return enderecoRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	@Transactional
	public Endereco alterar(Endereco endereco, Long id) {
		// busca no banco de dados para garantir que o genero não seja nulo
		Endereco enderecoPesquisado = pesquisarPorId(id);
		/*
		 * copia dos novos dados para que não seja persistido os mesmo dados que ja
		 * existiam no banco
		 */
		BeanUtils.copyProperties(endereco,  enderecoPesquisado, "id");
		return enderecoRepository.save(enderecoPesquisado);
	}
	
	@Transactional
	public void excluir(Long id) {
		enderecoRepository.deleteById(id);
	}
	
	public List<Endereco> listar(){
		return enderecoRepository.findAll();
	}

}
