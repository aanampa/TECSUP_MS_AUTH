package com.tecsup.auth.repository;

import com.tecsup.auth.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    // Buscamos solo por login
    Optional<Usuario> findByLogin(String login);

    boolean existsByLogin(String login);
}