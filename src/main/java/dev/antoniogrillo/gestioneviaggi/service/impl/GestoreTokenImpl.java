package dev.antoniogrillo.gestioneviaggi.service.impl;

import dev.antoniogrillo.gestioneviaggi.entity.Ruolo;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.repository.UtenteRepository;
import dev.antoniogrillo.gestioneviaggi.service.def.GestoreTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class GestoreTokenImpl implements GestoreTokenService {

    private final UtenteRepository repo;

    @Value("${custom.jwt.secret}")
    private String propertyKey;

    @Value("${custom.jwt.expiration}")
    private long expiration;

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(propertyKey));
    }



    @Override
    public Utente getUtenteByToken(String token) {
        if(!isValid(token))return null;
        String username=getSubject(token);
        return repo.findByEmail(username).orElse(null);
    }

    @Override
    public String generateToken(Utente utente) {
        return Jwts.builder()
                .claims()
                    .add("ruolo",utente.getRuolo())
                    .add("dati a caso","bla bla bla")
                    .subject(utente.getUsername())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + expiration))
                .and()
                .signWith(getKey())
                .compact();
    }

    private Claims getClaims(String token){
        return (Claims)Jwts.parser().verifyWith(getKey()).build()
                .parse(token).getPayload();
    }

    private String getSubject(String token){
        return getClaims(token).getSubject();
    }

    private Date getExpiration(String token){
        return getClaims(token).getExpiration();
    }

    private Ruolo getRuolo(String token){
        return Ruolo.valueOf(getClaims(token).get("ruolo").toString());
    }

    private boolean isValid(String token){
        try{
            return getExpiration(token).after(new Date());
        }catch (JwtException e){
            return false;
        }
    }
}
