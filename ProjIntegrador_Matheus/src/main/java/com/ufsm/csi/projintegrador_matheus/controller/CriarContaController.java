package com.ufsm.csi.projintegrador_matheus.controller;

import com.ufsm.csi.projintegrador_matheus.controller.security.LoginController;
import com.ufsm.csi.projintegrador_matheus.model.Usuario;
import com.ufsm.csi.projintegrador_matheus.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("CriarConta")
public class CriarContaController {

    @PostMapping("/addConta")
    @ResponseBody
    public Usuario addADM(@RequestBody Usuario usuario, RedirectAttributes attributes, String nome, String email, String senha){


        //restriçoes do cadastro , perguntar ex:tamanhoEmail > 5
        if(usuario == null){
            return null;
        }

        int tamanhoNome;
        int tamanhoEmail;
        int tamanhoSenha;

        if(usuario.getNome() == null || usuario.getEmail() == null || usuario.getSenha() == null){
            if(usuario.getNome() == null) {
                System.out.println("entrou aqui");
                usuario.setNome(null);
            }
            if (usuario.getEmail() == null) {
                usuario.setEmail(null);
            }
            if(usuario.getSenha() == null) {
                usuario.setSenha(null);
            }
            tamanhoNome = 0;
            tamanhoEmail = 0;
            tamanhoSenha = 0;
        }else {
            tamanhoNome = usuario.getNome().length();
            tamanhoEmail = usuario.getEmail().length();
            tamanhoSenha = usuario.getSenha().length();
        }








        //System.out.println("nome:"+usuario.getNome().length()+" email:"+usuario.getEmail() +" senha:"+usuario.getSenha());

        if(tamanhoNome < 3) {
            System.out.println("entrou aqui");
            usuario.setNome(null);
        }
        if (tamanhoEmail < 6) {
            usuario.setEmail(null);
        }
        if(tamanhoSenha < 3) {
            usuario.setSenha(null);
        }

        boolean verifica;
        if(tamanhoNome > 2  && tamanhoEmail > 5 && tamanhoSenha > 2){
            verifica = new UsuarioService().criarConta(usuario);
        }else{
            System.out.println(usuario.getNome());
            return usuario;
        }
        //o certo é verifica != false;
        if(verifica != false){
            System.out.println("true");
            return usuario;
        }else{
            System.out.println("false");
            return null;
        }

    }
}
