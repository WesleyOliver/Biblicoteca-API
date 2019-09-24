package br.com.faculdadedelta.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.faculdadedelta.model.Cliente;
import br.com.faculdadedelta.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente inserir(Cliente cliente) {
		cliente.setId(null);
		return clienteRepository.save(cliente);
	}

	public Cliente pesquisarPorId(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	@Transactional
	public Cliente alterar(Cliente cliente, Long id) {
		// busca no banco de dados para garantir que o genero não seja nulo
		Cliente clientePesquisado = pesquisarPorId(id);
		/*
		 * copia dos novos dados para que não seja persistido os mesmo dados que ja
		 * existiam no banco
		 */
		BeanUtils.copyProperties(cliente,  clientePesquisado, "id");
		return clienteRepository.save(clientePesquisado);
	}
	
	@Transactional
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}
	
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}

}
