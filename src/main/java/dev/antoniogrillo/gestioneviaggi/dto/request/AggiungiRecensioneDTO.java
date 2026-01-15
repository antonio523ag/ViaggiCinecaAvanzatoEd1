package dev.antoniogrillo.gestioneviaggi.dto.request;

import java.time.LocalDate;

public record AggiungiRecensioneDTO(long idMeta, LocalDate dataPartenza, LocalDate dataRitorno, int punteggio, String testo) {
}
