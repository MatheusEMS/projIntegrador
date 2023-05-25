package com.ufsm.csi.projintegrador_matheus.controller;

import com.ufsm.csi.projintegrador_matheus.dao.JogosDao;
import com.ufsm.csi.projintegrador_matheus.dao.UsuarioDao;
import com.ufsm.csi.projintegrador_matheus.model.Jogos;
import com.ufsm.csi.projintegrador_matheus.model.SavesReq;
import com.ufsm.csi.projintegrador_matheus.model.Usuario;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@RequestMapping("/jogos")
public class JogosController {

    @GetMapping("/Lerjogos/{console}")
    @ResponseBody
    public ArrayList<Jogos> pegarConta(@PathVariable String console){
        ArrayList<Jogos> listJogos = new JogosDao().LerJogos(console);

        if(listJogos.isEmpty()){
            System.out.println("retorno nulo");
            return null;
        }else {
            return listJogos;
        }

    }


}
