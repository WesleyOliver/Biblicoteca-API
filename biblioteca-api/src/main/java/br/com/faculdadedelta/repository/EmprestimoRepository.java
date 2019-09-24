package br.com.faculdadedelta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadedelta.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

}
