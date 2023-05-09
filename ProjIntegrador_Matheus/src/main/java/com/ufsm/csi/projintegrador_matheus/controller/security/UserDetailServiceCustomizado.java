package com.ufsm.csi.projintegrador_matheus.controller.security;

import com.ufsm.csi.projintegrador_matheus.dao.UsuarioDao;
import com.ufsm.csi.projintegrador_matheus.model.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceCustomizado implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username:"+ username);

        Usuario usuario = new UsuarioDao().getUsuario(username);



        if(usuario == null){
            throw new UsernameNotFoundException("Usuário ou senha incorretos");
        }else {
            String cripto = new BCryptPasswordEncoder().encode(usuario.getSenha());
            UserDetails user = User.withUsername(usuario.getEmail()).password(cripto).authorities(usuario.getAdmin()).build();

            return user;
        }

    }
}
