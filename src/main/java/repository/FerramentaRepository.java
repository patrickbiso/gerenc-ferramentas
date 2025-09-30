package com.example.gerencferramentas.repository;

import com.example.gerencferramentas.model.Ferramenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface FerramentaRepository extends JpaRepository<Ferramenta, Long> {
    Optional<Ferramenta> findByCodigo(String codigo);

    List<Ferramenta> findByNomeContainingIgnoreCase(String nome);

}