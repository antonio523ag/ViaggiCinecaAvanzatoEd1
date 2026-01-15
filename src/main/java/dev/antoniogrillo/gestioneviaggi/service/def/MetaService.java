package dev.antoniogrillo.gestioneviaggi.service.def;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiMetaDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.MetaDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;

import java.util.List;

public interface MetaService {
    List<MetaDTO> getMete(int numeroPagina);

    List<MetaDTO> getMetePerTipologia(long idTipologia, int numeroPagina);

    Meta salva(AggiungiMetaDTO request);

    void elimina(long id);

    MetaDTO getMeta(long id);

    List<MetaDTO> getVisitate(int numeroPagina, Utente utente);

    List<MetaDTO> getDaVisitare(int numeroPagina, Utente utente);
}
