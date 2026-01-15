package dev.antoniogrillo.gestioneviaggi.service.impl;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiMetaDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.MetaDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.mapper.MetaMapper;
import dev.antoniogrillo.gestioneviaggi.repository.CriteriaRepository;
import dev.antoniogrillo.gestioneviaggi.repository.MetaRepository;
import dev.antoniogrillo.gestioneviaggi.service.def.MetaService;
import dev.antoniogrillo.gestioneviaggi.service.def.TipologiaMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetaServiceImpl implements MetaService {

    private final MetaRepository repository;
    private final MetaMapper mapper;
    private final CriteriaRepository criteriaRepository;
    private final TipologiaMetaService tipologiaMetaService;

    @Override
    public List<MetaDTO> getMete(int numeroPagina) {
        Sort s= Sort.by("nome").ascending();
        Pageable pageable = PageRequest.of(numeroPagina, 10, s);
        List<Meta> metas=repository.findAll(pageable).getContent();
        return mapper.toMetaDTO(metas);
    }

    @Override
    public List<MetaDTO> getMetePerTipologia(long idTipologia, int numeroPagina) {
        List<Meta> mete=criteriaRepository.findMetePerTipologia(idTipologia,numeroPagina);
        return mapper.toMetaDTO(mete);
    }

    @Override
    public Meta salva(AggiungiMetaDTO request) {
        Meta m= repository.findByNome(request.nome()).orElse(null);
        if(m!=null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nome meta già esistente");
        List<TipologiaMeta> tipologie=tipologiaMetaService.findAllByIds(request.idTipologie());
        if(tipologie.isEmpty())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tipologie non valide");
        m=new Meta();
        m.setNome(request.nome());
        m.setTipologie(tipologie);
        return repository.save(m);
    }

    @Override
    public void elimina(long id) {
        repository.deleteById(id);

    }

    @Override
    public MetaDTO getMeta(long id) {
        Meta m=repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"nessuna meta con questo id"));
        return mapper.toMetaDTO(m);
    }

    @Override
    public List<MetaDTO> getVisitate(int numeroPagina, Utente utente) {
        List<Meta> mete=criteriaRepository.findMeteVisitate(utente.getId(),numeroPagina);
        return mapper.toMetaDTO(mete);
    }

    @Override
    public List<MetaDTO> getDaVisitare(int numeroPagina, Utente utente) {
        List<Meta> mete=criteriaRepository.findMeteDaVisitare(utente.getId(),numeroPagina);
        return mapper.toMetaDTO(mete);
    }
}
