package com.example.prueba.Seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ConfiguracionSeguridad {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/guest/**").hasRole("GUEST")
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> httpBasic.disable()) 
            .formLogin(form -> form.defaultSuccessUrl("/home", true)) 
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));

        return http.build();
    }
   
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
            .password("{noop}admin123") 
            .roles("ADMIN")
            .build();

        UserDetails guest = User.withUsername("guest")
            .password("{noop}guest123")
            .roles("GUEST")
            .build();

        return new InMemoryUserDetailsManager(admin, guest);
    }
}
