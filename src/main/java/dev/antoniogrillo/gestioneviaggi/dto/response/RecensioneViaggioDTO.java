package dev.antoniogrillo.gestioneviaggi.dto.response;
import java.time.LocalDate;

public record RecensioneViaggioDTO(long id, String testo, int punteggio, LocalDate dataPartenza, LocalDate dataRitorno, MiniViaggiatoreDTO viaggiatore,
                                   MetaDTO meta) {
}
