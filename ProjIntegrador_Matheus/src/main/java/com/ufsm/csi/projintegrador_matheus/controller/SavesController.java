package com.ufsm.csi.projintegrador_matheus.controller;

import com.ufsm.csi.projintegrador_matheus.dao.SavesDao;
import com.ufsm.csi.projintegrador_matheus.model.Saves;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("saves")
public class SavesController {

    @GetMapping("/LerSaves/{id}/{console}")
    @ResponseBody
    public ArrayList<Saves> pegarSaves(@PathVariable String id,@PathVariable String console){
        System.out.println(id+' '+console);
        int idCerto = Integer.parseInt(id);
        ArrayList<Saves> listSaves = new SavesDao().LerSaves(idCerto,console);


        if(listSaves.isEmpty()){
            System.out.println("retorno nulo");
            return null;
        }else {
            return listSaves;
        }
    }

    @GetMapping("/PegarSave/{save}")
    @ResponseBody
    public Saves pegarUmSave(@PathVariable String save){
        System.out.println(save);
        int idCerto = Integer.parseInt(save);
         Saves save1 = new SavesDao().LerUmSave(idCerto);


        if(save1 == null){
            System.out.println("retorno nulo");
            return null;
        }else {
            return save1;
        }
    }


}
