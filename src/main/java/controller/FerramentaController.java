package com.example.gerencferramentas.controller;

import com.example.gerencferramentas.model.Ferramenta;
import com.example.gerencferramentas.service.FerramentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ferramentas")
public class FerramentaController {

    @Autowired
    private FerramentaService ferramentaService;


    @GetMapping
    public String listarFerramentas(Model model) {
        List<Ferramenta> ferramentas = ferramentaService.listarTodas();
        model.addAttribute("ferramentas", ferramentas);
        return "ferramentas/listar"; // view
    }


    @GetMapping("/nova")
    public String novaFerramentaForm(Model model) {
        model.addAttribute("ferramenta", new Ferramenta());
        return "ferramentas/form";
    }


    @PostMapping("/salvar")
    public String salvarFerramenta(@ModelAttribute Ferramenta ferramenta) {
        ferramentaService.salvar(ferramenta);
        return "redirect:/ferramentas";
    }


    @GetMapping("/editar/{id}")
    public String editarFerramenta(@PathVariable Long id, Model model) {
        Optional<Ferramenta> ferramenta = ferramentaService.buscarPorId(id);
        if (ferramenta.isPresent()) {
            model.addAttribute("ferramenta", ferramenta.get());
            return "ferramentas/form";
        } else {
            return "redirect:/ferramentas";
        }
    }


    @GetMapping("/excluir/{id}")
    public String excluirFerramenta(@PathVariable Long id) {
        ferramentaService.deletar(id);
        return "redirect:/ferramentas";
    }


    @GetMapping("/buscar")
    public String buscarPorNome(@RequestParam String nome, Model model) {
        List<Ferramenta> ferramentas = ferramentaService.buscarPorNome(nome);
        model.addAttribute("ferramentas", ferramentas);
        return "ferramentas/listar";
    }
}
