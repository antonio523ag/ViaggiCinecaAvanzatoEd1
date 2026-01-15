package dev.antoniogrillo.gestioneviaggi.service.def;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiTipologia;
import dev.antoniogrillo.gestioneviaggi.dto.response.TipologiaMetaDTO;
import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;

import java.util.List;

public interface TipologiaMetaService {
    List<TipologiaMetaDTO> getTipologie(int numeroPagina);

    TipologiaMetaDTO aggiungi(AggiungiTipologia request);

    TipologiaMetaDTO getTipologia(long id);

    List<TipologiaMeta> findAllByIds(List<Long> longs);
}
