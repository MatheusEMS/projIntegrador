package com.ufsm.csi.projintegrador_matheus.controller;

import com.ufsm.csi.projintegrador_matheus.model.Usuario;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String inicio(Model model){
        System.out.println("redirecionando .....");
        model.addAttribute("usuario",new Usuario());
        return "login";
    }
}