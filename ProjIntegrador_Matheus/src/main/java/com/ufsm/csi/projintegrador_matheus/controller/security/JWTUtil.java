package com.ufsm.csi.projintegrador_matheus.controller.security;

import com.ufsm.csi.projintegrador_matheus.model.Usuario;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
    public static final long TEMPO_VIDA = Duration.ofSeconds(99000).toMillis();

    public String geraToken(Usuario usuario){
        final Map<String, Object> claims = new HashMap<>();
        claims.put("sub",usuario.getEmail());
        claims.put("permissoes", usuario.getAdmin());

        return Jwts.builder().setClaims(claims).
                setExpiration(new Date(System.currentTimeMillis()+this.TEMPO_VIDA)).
                signWith(SignatureAlgorithm.HS256,"Vasco").
                compact();

    }
    public String getUsernameToken(String token){
        if(token != null){
            return parseToken(token).getSubject();
        }else {
            return null;
        }
    }

    public boolean isTokenExpirado(String token){
        return this.parseToken(token).getExpiration().before(new Date());
    }

    private Claims parseToken(String token){
        return Jwts.parser().setSigningKey("Vasco").parseClaimsJws(token.replace("Bearer","")).getBody();
    }
}
