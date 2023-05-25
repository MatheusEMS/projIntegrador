package com.ufsm.csi.projintegrador_matheus.controller;
import com.ufsm.csi.projintegrador_matheus.dao.SavesDao;
import com.ufsm.csi.projintegrador_matheus.model.Saves;
import com.ufsm.csi.projintegrador_matheus.model.SavesReq;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("requisicao")
public class RequisicaoController {

    @GetMapping("/LerRequisicoes")
    @ResponseBody
    public ArrayList<SavesReq> LerReqSaves(){

        System.out.println("entrou req");
        ArrayList<SavesReq> saves = new SavesDao().LerReqSaves();


        if(saves == null){
            System.out.println("retorno nulo");
            return null;
        }else {
            return saves;
        }
    }

    @GetMapping("/deletar/{id}")
    @ResponseBody
    public Boolean DeletarReqSaves(@PathVariable String id){

        Boolean verif = null;
        System.out.println("entrou delet req");
        int idSave = Integer.parseInt(id);
        verif = new SavesDao().DeletarReqSaves(idSave);


        if(verif == true){
            return true;
        }else {
            return false;
        }
    }
}
