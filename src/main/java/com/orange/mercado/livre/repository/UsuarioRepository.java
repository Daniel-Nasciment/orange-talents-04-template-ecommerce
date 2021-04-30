package com.orange.mercado.livre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orange.mercado.livre.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
