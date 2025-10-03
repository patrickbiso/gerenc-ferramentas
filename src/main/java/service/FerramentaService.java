package com.example.gerencferramentas.service;

import com.example.gerencferramentas.model.Ferramenta;
import com.example.gerencferramentas.repository.FerramentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FerramentaService {

    @Autowired
    private FerramentaRepository ferramentaRepository;

    public List<Ferramenta> listarTodas() {
        return ferramentaRepository.findAll();
    }

    public Ferramenta salvar(Ferramenta ferramenta) {
        return ferramentaRepository.save(ferramenta);
    }

    public Ferramenta buscarPorId(Long id) {
        return ferramentaRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        ferramentaRepository.deleteById(id);
    }

    public List<Ferramenta> buscarPorNome(String nome) {
        return ferramentaRepository.findByNomeContainingIgnoreCase(nome);
    }
}
