package br.com.faculdadedelta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadedelta.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	//optional evita null point exception
	Optional<Usuario> findByEmail(String email);

}
