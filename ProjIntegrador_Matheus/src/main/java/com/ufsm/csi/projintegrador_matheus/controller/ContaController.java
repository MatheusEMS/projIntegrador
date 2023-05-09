package com.ufsm.csi.projintegrador_matheus.controller;

import com.ufsm.csi.projintegrador_matheus.dao.UsuarioDao;
import com.ufsm.csi.projintegrador_matheus.model.Usuario;
import com.ufsm.csi.projintegrador_matheus.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("conta")
public class ContaController {

    @PostMapping("/editar")
    @ResponseBody
    public boolean gravar(@RequestBody Usuario usuario, RedirectAttributes attributes, HttpServletRequest req){
        //if(req.getSession().getAttribute("adm_logado")!= null) {

        System.out.println(" nome:" + usuario.getNome() + "id " + usuario.getId());

            System.out.println("entrou no editar time");
        int tamanhoNome;
        tamanhoNome = usuario.getNome().length();

        if(tamanhoNome > 2){
            new UsuarioDao().Editar(usuario);
        }else{
            return false;
        }
        return true;
        //RedirectView redirectView = new RedirectView("/conta", true);
        //return redirectView;
        //}else{
        //System.out.println("nao logado");
        //return redirectViewErro;
        //}
    }

    @GetMapping("/excluir/{id}")
    @ResponseBody
    public void excluir(@PathVariable String id,RedirectAttributes attributes, HttpServletRequest req){
        RedirectView redirectView = new RedirectView("/times/Vertimes",true);


        System.out.println("id para excluir " + id);
        int idConta = Integer.parseInt(id);

        //new UsuarioDao().excluir(idConta);

        attributes.addFlashAttribute("texto","Time excluido com sucesso");
    }

    @GetMapping("/pegarConta/{id}")
    @ResponseBody
    public Usuario pegarConta(@PathVariable String id){
        int idConta = Integer.parseInt(id);
        Usuario usuario1 = new UsuarioDao().getUsuariobyId(idConta);
        System.out.println("pegarConta");
        return  usuario1;
    }
}
