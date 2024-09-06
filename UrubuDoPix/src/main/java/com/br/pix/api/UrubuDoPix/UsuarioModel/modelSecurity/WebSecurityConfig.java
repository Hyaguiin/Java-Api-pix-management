package com.br.pix.api.UrubuDoPix.UsuarioModel.modelSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/pix").permitAll()  // Permite acesso público a este endpoint
                    .anyRequest().authenticated()  // Requer autenticação para todas as outras URLs
            )
            .formLogin()  // Ativa a autenticação baseada em formulário
            .and()
            .httpBasic();  // Ativa a autenticação básica

        return http.build();
    }
}
