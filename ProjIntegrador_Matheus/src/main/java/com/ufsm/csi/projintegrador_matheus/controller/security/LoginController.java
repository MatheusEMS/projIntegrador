package com.ufsm.csi.projintegrador_matheus.controller.security;

import com.ufsm.csi.projintegrador_matheus.dao.UsuarioDao;
import com.ufsm.csi.projintegrador_matheus.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<Object> autenticado(@RequestBody Usuario usuario){
        System.out.println("User:"+usuario.getEmail());
        System.out.println("Senha:"+usuario.getSenha());
        System.out.println("id:"+usuario.getId());
        System.out.println("admin:"+usuario.getAdmin());

        //como logar com a senha encriptada no banco

        try {
            final Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getEmail(),usuario.getSenha()));

            if(authenticate.isAuthenticated()){
                //colocamos nossa instancia de autenticado no contexto do spring security
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                System.out.println("Gerando token");
                String token = new JWTUtil().geraToken(usuario);

                usuario.setToken(token);
                usuario.setSenha(" ");


                //adm.getPermissao();


                System.out.println("Está autenticado");
                Usuario usuarioReal = new UsuarioDao().getUsuario(usuario.getEmail());
                usuarioReal.setToken(token);
                return new ResponseEntity<>(usuarioReal, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Usuário ou senha incorretos ---",HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Usuário ou senha incorretos ====",HttpStatus.BAD_REQUEST);
    }
}
