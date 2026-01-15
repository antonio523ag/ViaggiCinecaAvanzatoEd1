package dev.antoniogrillo.gestioneviaggi.configuration;

import dev.antoniogrillo.gestioneviaggi.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class SecurityBean {

    private final UtenteRepository repo;

    @Bean
    protected UserDetailsService getService(){
        return u-> Objects.requireNonNull(repo.findByEmail(u).orElse(null));
    }

    @Bean
    protected PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }

    @Bean
    protected AuthenticationProvider getProvider(){
        DaoAuthenticationProvider dap=new DaoAuthenticationProvider(getService());
        dap.setPasswordEncoder(getPasswordEncoder());
        return dap;
    }

}
