package dev.antoniogrillo.gestioneviaggi.service.def;


import dev.antoniogrillo.gestioneviaggi.entity.Utente;

public interface GestoreTokenService {

    Utente getUtenteByToken(String token);
    String generateToken(Utente utente);
}
