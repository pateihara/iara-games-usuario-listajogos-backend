package br.com.iaragames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/jogos")
    public String jogos() {
        return "jogos";
    }

    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuarios";
    }
}
