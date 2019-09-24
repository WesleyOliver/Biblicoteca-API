package br.com.faculdadedelta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadedelta.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
