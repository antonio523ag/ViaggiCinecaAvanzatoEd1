package dev.antoniogrillo.gestioneviaggi.dto.request;

import java.time.LocalDate;

public record RegistrazioneUtenteGraphQL(String nome, String cognome, String dataNascita, String password, String email) {
}
