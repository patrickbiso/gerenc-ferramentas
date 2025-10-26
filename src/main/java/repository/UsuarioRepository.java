package com.example.gerencferramentas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gerencferramentas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmailAndSenha(String email, String senha);
}
