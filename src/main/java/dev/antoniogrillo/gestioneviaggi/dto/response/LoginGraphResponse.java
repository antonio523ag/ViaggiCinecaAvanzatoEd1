package dev.antoniogrillo.gestioneviaggi.dto.response;

import dev.antoniogrillo.gestioneviaggi.entity.Utente;

public record LoginGraphResponse(String token, Utente utente ) {
}
