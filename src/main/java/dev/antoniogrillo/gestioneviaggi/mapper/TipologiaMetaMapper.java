package dev.antoniogrillo.gestioneviaggi.mapper;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiTipologiaDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.TipologiaMetaDTO;
import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipologiaMetaMapper {

    public TipologiaMetaDTO toTipologiaMetaDTO(TipologiaMeta tipologiaMeta) {
        return new TipologiaMetaDTO(tipologiaMeta.getId(), tipologiaMeta.getNome());
    }

    public List<TipologiaMetaDTO> toTipologiaMetaDTO(List<TipologiaMeta> tipologie) {
        return tipologie.stream().map(this::toTipologiaMetaDTO).toList();
    }

    public TipologiaMeta toTipologiaMeta(AggiungiTipologiaDTO request) {
        TipologiaMeta t=new TipologiaMeta();
        t.setNome(request.nome());
        return t;
    }
}
