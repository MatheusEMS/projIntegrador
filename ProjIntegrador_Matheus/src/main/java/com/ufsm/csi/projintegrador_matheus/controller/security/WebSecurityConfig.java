package com.ufsm.csi.projintegrador_matheus.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    private UserDetailsService userDetailsService;



    @Autowired
    public void configureAutenticacao(AuthenticationManagerBuilder auth) throws Exception{
        System.out.println("#### configurando o autentication manager ####");
        auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*@Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }*/

    @Bean
    public FiltroAutenticacao filtroAutenticacao() throws Exception{
        return new FiltroAutenticacao();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
//trocar

        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()
                   .requestMatchers(HttpMethod.POST,"/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/home/LerSaves").permitAll()
                        .requestMatchers(HttpMethod.GET,"/home/PegarNome/{idjogo}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/CriarConta/addConta").permitAll()
                        .requestMatchers(HttpMethod.POST,"/conta/editar").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers(HttpMethod.GET,"/conta/pegarConta/{id}").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers(HttpMethod.GET,"/saves/LerSaves/{id}/{console}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/saves/PegarSave/{save}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/requisicao/LerRequisicoes").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/requisicao/deletar/{id}").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/jogos/Lerjogos/{console}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/upload/CadastrarJogo").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/upload/CadastrarSave").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers(HttpMethod.POST,"/upload/CadastrarImagem").hasAnyAuthority("ADMIN","USER")
                        .anyRequest().denyAll();

                //authenticationProvider(this.authenticationProvider()).authorizeRequests()
        //.and().formLogin();

        http.addFilterBefore(this.filtroAutenticacao(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



    @Bean
    public CorsFilter corsFilter() {
        System.out.println("configurando cors ....");
        final var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

}
