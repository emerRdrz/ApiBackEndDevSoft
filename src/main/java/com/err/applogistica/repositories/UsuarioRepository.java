package com.err.applogistica.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.err.applogistica.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEnableAndEmail(Boolean enable, String email);
}
