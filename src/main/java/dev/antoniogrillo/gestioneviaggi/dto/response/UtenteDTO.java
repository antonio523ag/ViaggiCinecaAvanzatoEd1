package dev.antoniogrillo.gestioneviaggi.dto.response;

import dev.antoniogrillo.gestioneviaggi.entity.Ruolo;

import java.time.LocalDate;

public record UtenteDTO(long id, String nome, String cognome, String email, LocalDate dataNascita, Ruolo ruolo) {
}
