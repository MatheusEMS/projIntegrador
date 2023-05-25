package com.ufsm.csi.projintegrador_matheus.controller;
import com.ufsm.csi.projintegrador_matheus.dao.SavesDao;
import com.ufsm.csi.projintegrador_matheus.model.Caminho;
import com.ufsm.csi.projintegrador_matheus.model.Saves;
import com.ufsm.csi.projintegrador_matheus.model.SavesReq;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/upload")
public class UploadController {
    String caminhoGlobal = "";

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

        saves.setArquivo(caminhoGlobal);

        System.out.println("caminho: "+saves.getArquivo());


        verifica = new SavesDao().cadastrarSave(saves);

        System.out.println(verifica);

        if(verifica == false){
            return saves;
        }else {
            return null;
        }
    }

    @PostMapping("/CadastrarImagem")
    public Boolean test(@ModelAttribute MultipartFile file){
        System.out.println("leu imagem");
        ;
        String folder = "src/main/java/com/ufsm/csi/projintegrador_matheus/fileSaves";
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(folder,file.getOriginalFilename());
            Files.write(path,bytes);
            String caminho = folder + file.getOriginalFilename();

            caminhoGlobal = caminho;
            System.out.println("certo " + caminho);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("erro");
            return null;
        }

    }


    @PostMapping("/CadastrarJogo")
    public SavesReq cadastroJogo(@ModelAttribute MultipartFile file, @RequestParam String nomeJogo, @RequestParam String idSave){
        System.out.println("entrou cadastrar");
        System.out.println(file.getOriginalFilename());
        System.out.println(nomeJogo);
        System.out.println(idSave);

        return null;
    }


}

