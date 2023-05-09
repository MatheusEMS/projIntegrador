package com.ufsm.csi.projintegrador_matheus.service;

import com.ufsm.csi.projintegrador_matheus.dao.UsuarioDao;
import com.ufsm.csi.projintegrador_matheus.model.Usuario;

public class UsuarioService {
    public Usuario autenticado(String email, String senha){

        // dao = new UsuarioDao();
        // Usuario u = dao.getUsuario(email);

        Usuario usuario = new UsuarioDao().getUsuario(email);

        try{
            if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)){
                System.out.println("usuario:"+usuario);
                return usuario;
            }
            return null;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean criarConta(Usuario usuario)
    {
        System.out.println("nome:"+usuario.getNome()+" email:"+usuario.getEmail() +" senha:"+usuario.getSenha());
        if(usuario.getNome() !=null && usuario.getEmail() != null && usuario.getSenha() != null)
        {
            System.out.println("service 1");
            new UsuarioDao().UsuarioInsert(usuario);
            if(usuario == null){
                return false;
            }
            return true;

        }else{
            return false;
        }
    }
}
