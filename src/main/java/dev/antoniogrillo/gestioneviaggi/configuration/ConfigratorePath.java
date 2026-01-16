package dev.antoniogrillo.gestioneviaggi.configuration;

import dev.antoniogrillo.gestioneviaggi.entity.Ruolo;
import dev.antoniogrillo.gestioneviaggi.filter.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ConfigratorePath {
    private final JWTFilter jwtFilter;
    private final AuthenticationProvider provider;

    @Bean
    protected SecurityFilterChain chain(HttpSecurity http){
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(a->a
                        .requestMatchers("/all/**").permitAll()
                        .requestMatchers("/authorized/**").authenticated()
                        .requestMatchers("/admin/**").hasRole(Ruolo.ADMIN.name())
                        .requestMatchers("/graphql").permitAll()
                        .anyRequest().permitAll()
                ).authenticationProvider(provider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
