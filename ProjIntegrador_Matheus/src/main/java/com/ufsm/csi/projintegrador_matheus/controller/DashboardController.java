package com.ufsm.csi.projintegrador_matheus.controller;

import com.ufsm.csi.projintegrador_matheus.dao.JogosDao;
import com.ufsm.csi.projintegrador_matheus.dao.SavesDao;
import com.ufsm.csi.projintegrador_matheus.model.Jogos;
import com.ufsm.csi.projintegrador_matheus.model.Saves;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/home")
public class DashboardController {

    @GetMapping("/teste")
    public String teste(){
        return "feito";
    }

    @GetMapping("/LerSaves")
    @ResponseBody
    public ArrayList<Saves> pegarSaves(){
        ArrayList<Saves> listSaves = new SavesDao().LerSavesHome();
        System.out.println("lerSaves");

        if(listSaves.isEmpty()){
            System.out.println("retorno nulo");
            return null;
        }else {
            return listSaves;
        }
    }

    @GetMapping("/PegarNome/{idjogo}")
    @ResponseBody
    public Jogos pegarSaves(@PathVariable String idjogo){
        int idCerto = Integer.parseInt(idjogo);

        Jogos jogos = new JogosDao().PegarNome(idCerto);


        if(jogos == null){
            System.out.println("retorno nulo");
            return null;
        }else {
            System.out.println(jogos.getNomeJogo());
            return jogos;
        }
    }



}
