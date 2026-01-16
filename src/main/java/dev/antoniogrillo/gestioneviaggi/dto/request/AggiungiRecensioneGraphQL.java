package dev.antoniogrillo.gestioneviaggi.dto.request;

public record AggiungiRecensioneGraphQL(long idMeta, String dataPartenza, String dataRitorno, int punteggio, String testo) {
}
