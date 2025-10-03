package com.example.gerencferramentas.controller;

import com.example.gerencferramentas.model.Ferramenta;
import com.example.gerencferramentas.service.FerramentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ferramentas")
public class FerramentaController {

    @Autowired
    private FerramentaService ferramentaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ferramentas", ferramentaService.listarTodas());
        return "ferramentas/listar_bootstrap";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("ferramenta", new Ferramenta());
        return "ferramentas/form_bootstrap";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Ferramenta ferramenta,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "ferramentas/form_bootstrap";
        }
        ferramentaService.salvar(ferramenta);
        redirectAttributes.addFlashAttribute("msg", "Ferramenta salva com sucesso!");
        return "redirect:/ferramentas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Ferramenta ferramenta = ferramentaService.buscarPorId(id);
        if (ferramenta == null) {
            redirectAttributes.addFlashAttribute("msg", "Ferramenta não encontrada!");
            return "redirect:/ferramentas";
        }
        model.addAttribute("ferramenta", ferramenta);
        return "ferramentas/form_bootstrap";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Ferramenta ferramenta = ferramentaService.buscarPorId(id);
        if (ferramenta == null) {
            redirectAttributes.addFlashAttribute("msg", "Ferramenta não encontrada!");
        } else {
            ferramentaService.excluir(id);
            redirectAttributes.addFlashAttribute("msg", "Ferramenta excluída com sucesso!");
        }
        return "redirect:/ferramentas";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String nome, Model model) {
        List<Ferramenta> resultados = ferramentaService.buscarPorNome(nome);
        model.addAttribute("ferramentas", resultados);
        return "ferramentas/listar_bootstrap";
    }
}
