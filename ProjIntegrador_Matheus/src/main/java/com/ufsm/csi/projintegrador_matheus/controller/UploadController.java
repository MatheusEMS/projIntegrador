package com.ufsm.csi.projintegrador_matheus.controller;

import com.ufsm.csi.projintegrador_matheus.dao.SavesDao;
import com.ufsm.csi.projintegrador_matheus.model.Saves;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping("/CadastrarSave")
    public Saves cadastroSave(@RequestBody Saves saves, @RequestParam String idConsole, @RequestParam String idJogo, @RequestParam String idUsuario){
        Boolean verifica = false;

        int IdConsoleCerto = Integer.parseInt(idConsole);
        int IdJogoCerto = Integer.parseInt(idJogo);
        int IdUsuarioCerto = Integer.parseInt(idUsuario);

        saves.setIdConsole(IdConsoleCerto);
        saves.setIdJogo(IdJogoCerto);
        saves.setIdUsuario(IdUsuarioCerto);


        System.out.println("id console :"+saves.getIdConsole());
        System.out.println("id jogo :"+saves.getIdJogo());
        System.out.println("id user :"+saves.getIdUsuario());

        //verifica = new SavesDao().cadastrarSave(saves);

        System.out.println(verifica);

        if(verifica == false){
            return saves;
        }else {
            return null;
        }
    }
}
