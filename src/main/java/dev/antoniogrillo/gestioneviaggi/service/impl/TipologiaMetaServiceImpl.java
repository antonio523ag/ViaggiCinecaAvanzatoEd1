package dev.antoniogrillo.gestioneviaggi.service.impl;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiTipologia;
import dev.antoniogrillo.gestioneviaggi.dto.response.TipologiaMetaDTO;
import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import dev.antoniogrillo.gestioneviaggi.mapper.TipologiaMetaMapper;
import dev.antoniogrillo.gestioneviaggi.repository.TipologiaRepository;
import dev.antoniogrillo.gestioneviaggi.service.def.TipologiaMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipologiaMetaServiceImpl implements TipologiaMetaService {

    private final TipologiaRepository repo;
    private final TipologiaMetaMapper mapper;

    @Override
    public List<TipologiaMetaDTO> getTipologie(int numeroPagina) {
        Sort s= Sort.by("nome").ascending();
        Pageable pageable = PageRequest.of(numeroPagina, 10, s);
        List<TipologiaMeta> tipologie=repo.findAll(pageable).getContent();
        return mapper.toTipologiaMetaDTO(tipologie);
    }

    @Override
    public TipologiaMetaDTO aggiungi(AggiungiTipologia request) {
        TipologiaMeta t=repo.findByNome(request.nome()).orElse(mapper.toTipologiaMeta(request));
        t=repo.save(t);
        return mapper.toTipologiaMetaDTO(t);
    }

    @Override
    public TipologiaMetaDTO getTipologia(long id) {
        TipologiaMeta t=repo.findById(id).orElseThrow(()->new RuntimeException("nessuna tipologia con questo id"));
        return mapper.toTipologiaMetaDTO(t);
    }

    @Override
    public List<TipologiaMeta> findAllByIds(List<Long> longs) {
        return repo.findAllById(longs);
    }
}
