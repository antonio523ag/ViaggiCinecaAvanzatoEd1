package dev.antoniogrillo.gestioneviaggi.service.impl;

import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import dev.antoniogrillo.gestioneviaggi.repository.TipologiaRepository;
import dev.antoniogrillo.gestioneviaggi.service.def.TipologiaMetaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipologiaMetaServiceImpl implements TipologiaMetaService {

    private final TipologiaRepository repo;

    @Override
    public List<TipologiaMeta> getTipologie(int numeroPagina) {
                Sort s= Sort.by("nome").ascending();
        Pageable pageable = PageRequest.of(numeroPagina, 10, s);
        return repo.findAll(pageable).getContent();
    }

    @Override
    @Transactional
    public TipologiaMeta aggiungi(TipologiaMeta request) {
        TipologiaMeta t=repo.findByNome(request.getNome()).orElse(null);
        if(t!=null)return t;
        return repo.save(request);
    }

    @Override
    public Optional<TipologiaMeta> getTipologiaById(long id) {
        return repo.findById(id);
    }

    @Override
    public List<TipologiaMeta> findAllByIds(List<Long> longs) {
        return repo.findAllById(longs);
    }

}
