package br.com.faculdadedelta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadedelta.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
