package com.example.gerencferramentas.controller;

import com.example.gerencferramentas.model.Usuario;
import com.example.gerencferramentas.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Página de login
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // Ação de login
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String senha,
                        HttpSession session,
                        Model model) {

        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/ferramentas";
        } else {
            model.addAttribute("erro", "E-mail ou senha incorretos.");
            return "login";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
