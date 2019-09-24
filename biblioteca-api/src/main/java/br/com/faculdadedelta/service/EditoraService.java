package br.com.faculdadedelta.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.faculdadedelta.model.Editora;
import br.com.faculdadedelta.repository.EditoraRepository;

@Service
public class EditoraService {
	
	@Autowired
	private EditoraRepository editoraRepository;
	
	@Transactional
	public Editora inserir(Editora editora) {
		editora.setId(null);
		return editoraRepository.save(editora);
	}
	
	public Editora pesquisarPorId(Long id) {
		return editoraRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	@Transactional
	public Editora alterar(Editora editora, Long id) {
		// busca no banco de dados para garantir que o genero não seja nulo
		Editora editoraPesqusada = pesquisarPorId(id);
		/* copia dos novos dados para que não seja persistido os mesmo dados que ja
		existiam no banco */
		BeanUtils.copyProperties(editora, editoraPesqusada, "id");
		return editoraRepository.save(editoraPesqusada);
	}
	
	@Transactional
	public void excluir(Long id) {
		editoraRepository.deleteById(id);
	}
	
	public List<Editora> listar(){
		return editoraRepository.findAll();
	}

}