package com.example.gerencferramentas.controller;

import com.example.gerencferramentas.model.Ferramenta;
import com.example.gerencferramentas.service.FerramentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (result.hasErrors()) {

            return "ferramentas/form_bootstrap";
        }
        try {
            ferramentaService.salvar(ferramenta);
            redirectAttributes.addFlashAttribute("msg", "Ferramenta salva com sucesso!");
        } catch (DataIntegrityViolationException ex) {

            model.addAttribute("ferramenta", ferramenta);
            model.addAttribute("msg", "Erro: código duplicado ou violação de integridade.");
            return "ferramentas/form_bootstrap";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msg", "Erro ao salvar: " + ex.getMessage());
        }
        return "redirect:/ferramentas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Ferramenta f = ferramentaService.buscarPorId(id);
        if (f == null) {
            redirectAttributes.addFlashAttribute("msg", "Ferramenta não encontrada!");
            return "redirect:/ferramentas";
        }
        model.addAttribute("ferramenta", f);
        return "ferramentas/form_bootstrap";
    }


    @PostMapping("/excluir/{id}")
    public String excluirPost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Ferramenta f = ferramentaService.buscarPorId(id);
        if (f == null) {
            redirectAttributes.addFlashAttribute("msg", "Ferramenta não encontrada!");
        } else {
            ferramentaService.excluir(id);
            redirectAttributes.addFlashAttribute("msg", "Ferramenta excluída com sucesso!");
        }
        return "redirect:/ferramentas";
    }


    @GetMapping("/excluir/{id}")
    public String excluirGet(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        return excluirPost(id, redirectAttributes);
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String nome, Model model) {
        List<Ferramenta> resultados = ferramentaService.buscarPorNome(nome);
        model.addAttribute("ferramentas", resultados);
        return "ferramentas/listar_bootstrap";
    }


    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Ferramenta f = ferramentaService.buscarPorId(id);
        if (f == null) {
            redirectAttributes.addFlashAttribute("msg", "Ferramenta não encontrada!");
            return "redirect:/ferramentas";
        }
        model.addAttribute("ferramenta", f);
        return "ferramentas/detalhes_bootstrap";
    }
}
