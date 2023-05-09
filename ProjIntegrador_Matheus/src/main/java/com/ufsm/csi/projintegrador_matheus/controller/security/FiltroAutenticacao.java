package com.ufsm.csi.projintegrador_matheus.controller.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FiltroAutenticacao extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String url = request.getRequestURI();
        System.out.println("filtro para requisicao"+ url);

        try {
            //ver a mudança, em teoria coloca oq precisa de login para acessar
            //|| !url.contains("jogos")
            if(!url.contains("login") ){
                if(!url.contains("CriarConta")) {
                    if(!url.contains("jogos")) {
                        if(!url.contains("saves")) {
                            if(!url.contains("home")) {

                            String token = request.getHeader("Authorization");
                            System.out.println("token: " + token);
                            String username = new JWTUtil().getUsernameToken(token);
                            System.out.println("username:" + username);
                            System.out.println("token expirado?" + new JWTUtil().isTokenExpirado(token));

                            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                                if (!new JWTUtil().isTokenExpirado(token)) {
                                    UsernamePasswordAuthenticationToken authenticationToken =
                                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                }


                            }
                        }
                    }
                    }
                }
            }
        }catch (ExpiredJwtException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token invalido");
        }




        filterChain.doFilter(request,response);
    }
}
