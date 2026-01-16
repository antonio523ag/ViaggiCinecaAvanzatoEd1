package dev.antoniogrillo.gestioneviaggi.service.impl;

import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import dev.antoniogrillo.gestioneviaggi.repository.CriteriaRepository;
import dev.antoniogrillo.gestioneviaggi.repository.MetaRepository;
import dev.antoniogrillo.gestioneviaggi.service.def.MetaService;
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
public class MetaServiceImpl implements MetaService {

    private final MetaRepository repository;
    private final CriteriaRepository criteriaRepository;

    @Override
    public List<Meta> getMete(int numeroPagina) {
        Sort s= Sort.by("nome").ascending();
        Pageable pageable = PageRequest.of(numeroPagina, 10, s);
        return repository.findAll(pageable).getContent();

    }

    @Override
    public List<Meta> getMetePerTipologia(long idTipologia, int numeroPagina) {
        return criteriaRepository.findMetePerTipologia(idTipologia,numeroPagina);
    }

    @Override
    public Meta salva(Meta m) {
        return repository.save(m);
    }

    @Override
    @Transactional
    public boolean elimina(long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Meta> getMeta(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Meta> getMetePerIdUtente(int numeroPagina, long idUtente) {
        return criteriaRepository.findMeteVisitate(idUtente,numeroPagina);
    }

    @Override
    public List<Meta> getMeteNonVisitatePerIdUtente(int numeroPagina, long idUtente) {
        return criteriaRepository.findMeteDaVisitare(idUtente,numeroPagina);
    }

    @Override
    public Optional<Meta> getByNome(String nome) {
        return repository.findByNome(nome);
    }
}
