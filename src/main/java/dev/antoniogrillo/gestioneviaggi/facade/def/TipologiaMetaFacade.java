package dev.antoniogrillo.gestioneviaggi.facade.def;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiTipologia;
import dev.antoniogrillo.gestioneviaggi.dto.response.TipologiaMetaDTO;

import java.util.List;

public interface TipologiaMetaFacade {
    List<TipologiaMetaDTO> getTipologie(int numeroPagina);

    TipologiaMetaDTO getTipologia(long id);

    TipologiaMetaDTO aggiungi(AggiungiTipologia request);
}
