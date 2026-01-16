package dev.antoniogrillo.gestioneviaggi.facade.impl;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiTipologiaDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.TipologiaMetaDTO;
import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import dev.antoniogrillo.gestioneviaggi.facade.def.TipologiaMetaFacade;
import dev.antoniogrillo.gestioneviaggi.mapper.TipologiaMetaMapper;
import dev.antoniogrillo.gestioneviaggi.service.def.TipologiaMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipologiaMetaFacadeImpl implements TipologiaMetaFacade {

    private final TipologiaMetaService service;
    private final TipologiaMetaMapper mapper;

    @Override
    public List<TipologiaMetaDTO> getTipologie(int numeroPagina) {
        List<TipologiaMeta> tipologie=service.getTipologie(numeroPagina);
        return mapper.toTipologiaMetaDTO(tipologie);
    }

    @Override
    public TipologiaMetaDTO getTipologia(long id) {
        TipologiaMeta t=service.getTipologiaById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tipologia non trovata"));
        return mapper.toTipologiaMetaDTO(t);
    }

    @Override
    public TipologiaMetaDTO aggiungi(AggiungiTipologiaDTO request) {
        TipologiaMeta t=mapper.toTipologiaMeta(request);
        t=service.aggiungi(t);
        return mapper.toTipologiaMetaDTO(t);
    }
}
