package dev.antoniogrillo.gestioneviaggi.dto.internal;

import dev.antoniogrillo.gestioneviaggi.dto.response.UtenteDTO;
public record LoginResponseDTO(String token,UtenteDTO utente){
}
