package com.example.gerencferramentas.service;

import com.example.gerencferramentas.model.Ferramenta;
import com.example.gerencferramentas.repository.FerramentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FerramentaService {

    @Autowired
    private FerramentaRepository ferramentaRepository;


    public List<Ferramenta> listarTodas() {
        return ferramentaRepository.findAll();
    }


    public Optional<Ferramenta> buscarPorId(Long id) {
        return ferramentaRepository.findById(id);
    }


    public Optional<Ferramenta> buscarPorCodigo(String codigo) {
        return ferramentaRepository.findByCodigo(codigo);
    }


    public List<Ferramenta> buscarPorNome(String nome) {
        return ferramentaRepository.findByNomeContainingIgnoreCase(nome);
    }


    public Ferramenta salvar(Ferramenta ferramenta) {
        return ferramentaRepository.save(ferramenta);
    }

    public void deletar(Long id) {
        ferramentaRepository.deleteById(id);
    }
}
