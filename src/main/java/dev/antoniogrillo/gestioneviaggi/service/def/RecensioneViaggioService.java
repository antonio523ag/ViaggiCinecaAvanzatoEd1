package dev.antoniogrillo.gestioneviaggi.service.def;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiRecensioneDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.RecensioneViaggioDTO;
import dev.antoniogrillo.gestioneviaggi.entity.RecensioneViaggio;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;

import java.util.List;

public interface RecensioneViaggioService {
    RecensioneViaggioDTO getRecensione(long id);

    List<RecensioneViaggioDTO> getRecensioni(Utente utente, int numeroPagina);

    List<RecensioneViaggioDTO> getRecensioniUtente(long idUtente, int numeroPagina);

    List<RecensioneViaggioDTO> getRecensioniMeta(int idMeta, int numeroPagina);

    RecensioneViaggio salva(AggiungiRecensioneDTO request, Utente utente);

    RecensioneViaggioDTO elimina(long id);
}
