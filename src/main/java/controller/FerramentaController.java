package com.example.gerencferramentas.controller;

import com.example.gerencferramentas.model.Ferramenta;
import com.example.gerencferramentas.model.Usuario;
import com.example.gerencferramentas.service.FerramentaService;
import jakarta.servlet.http.HttpSession;
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

    private boolean adicionarUsuarioNaModel(HttpSession session, Model model) {
        Object usuarioObj = session.getAttribute("usuarioLogado");
        if (usuarioObj != null) {
            Usuario usuario = (Usuario) usuarioObj;
            model.addAttribute("usuarioNome", usuario.getNome());
            return true;
        } else {
            model.addAttribute("usuarioNome", "Visitante");
            return false;
        }
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {
        if (!adicionarUsuarioNaModel(session, model)) {
            return "redirect:/login";
        }
        String filtroNome = (String) session.getAttribute("filtroNome");
        List<Ferramenta> ferramentas;
        if (filtroNome != null && !filtroNome.isEmpty()) {
            ferramentas = ferramentaService.buscarPorNome(filtroNome);
            model.addAttribute("filtroNome", filtroNome);
        } else {
            ferramentas = ferramentaService.listarTodas();
            model.addAttribute("filtroNome", "");
        }
        model.addAttribute("ferramentas", ferramentas);
        return "ferramentas/listar_bootstrap";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String nome, Model model, HttpSession session) {
        if (!adicionarUsuarioNaModel(session, model)) {
            return "redirect:/login";
        }
        if (nome != null && !nome.isEmpty()) {
            session.setAttribute("filtroNome", nome);
        }
        String filtroNome = (String) session.getAttribute("filtroNome");
        List<Ferramenta> resultados;
        if (filtroNome != null && !filtroNome.isEmpty()) {
            resultados = ferramentaService.buscarPorNome(filtroNome);
        } else {
            resultados = ferramentaService.listarTodas();
        }
        model.addAttribute("ferramentas", resultados);
        model.addAttribute("filtroNome", filtroNome != null ? filtroNome : "");
        return "ferramentas/listar_bootstrap";
    }

    @GetMapping("/limparFiltro")
    public String limparFiltro(HttpSession session) {
        session.removeAttribute("filtroNome");
        return "redirect:/ferramentas";
    }

    @GetMapping("/nova")
    public String nova(Model model, HttpSession session) {
        if (!adicionarUsuarioNaModel(session, model)) {
            return "redirect:/login";
        }
        model.addAttribute("ferramenta", new Ferramenta());
        return "ferramentas/form_bootstrap";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Ferramenta ferramenta,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model,
                         HttpSession session) {
        if (!adicionarUsuarioNaModel(session, model)) {
            return "redirect:/login";
        }
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
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!adicionarUsuarioNaModel(session, model)) {
            return "redirect:/login";
        }
        Ferramenta f = ferramentaService.buscarPorId(id);
        if (f == null) {
            redirectAttributes.addFlashAttribute("msg", "Ferramenta não encontrada!");
            return "redirect:/ferramentas";
        }
        model.addAttribute("ferramenta", f);
        return "ferramentas/form_bootstrap";
    }

    @PostMapping("/excluir/{id}")
    public String excluirPost(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpSession session, Model model) {
        if (!adicionarUsuarioNaModel(session, model)) {
            return "redirect:/login";
        }
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
    public String excluirGet(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpSession session, Model model) {
        return excluirPost(id, redirectAttributes, session, model);
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!adicionarUsuarioNaModel(session, model)) {
            return "redirect:/login";
        }
        Ferramenta f = ferramentaService.buscarPorId(id);
        if (f == null) {
            redirectAttributes.addFlashAttribute("msg", "Ferramenta não encontrada!");
            return "redirect:/ferramentas";
        }
        model.addAttribute("ferramenta", f);
        return "ferramentas/detalhes_bootstrap";
    }
}
