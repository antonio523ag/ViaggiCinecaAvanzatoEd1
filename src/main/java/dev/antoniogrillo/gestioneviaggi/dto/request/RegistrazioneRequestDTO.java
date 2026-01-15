package dev.antoniogrillo.gestioneviaggi.dto.request;

import java.time.LocalDate;

public record RegistrazioneRequestDTO(String nome, String cognome, LocalDate dataNascita, String password, String email) {
}
