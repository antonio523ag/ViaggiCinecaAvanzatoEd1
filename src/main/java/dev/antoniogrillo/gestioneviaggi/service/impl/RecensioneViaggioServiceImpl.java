package dev.antoniogrillo.gestioneviaggi.service.impl;

import dev.antoniogrillo.gestioneviaggi.entity.RecensioneViaggio;
import dev.antoniogrillo.gestioneviaggi.repository.CriteriaRepository;
import dev.antoniogrillo.gestioneviaggi.repository.RecensioneViaggioRepository;
import dev.antoniogrillo.gestioneviaggi.service.def.RecensioneViaggioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecensioneViaggioServiceImpl implements RecensioneViaggioService {

    private final RecensioneViaggioRepository repo;
    private final CriteriaRepository criteriaRepository;

    @Override
    public Optional<RecensioneViaggio> getRecensione(long id) {
        return repo.findById(id);
    }

    @Override
    public List<RecensioneViaggio> getRecensioniUtente(long idUtente, int numeroPagina) {
        return criteriaRepository.findRecensioniPerIdUtente(idUtente,numeroPagina);
    }

    @Override
    public List<RecensioneViaggio> getRecensioniMeta(int idMeta, int numeroPagina) {
        return criteriaRepository.findRecensioniPerIdMeta(idMeta,numeroPagina);
    }

    @Override
    public RecensioneViaggio salva(RecensioneViaggio request) {
        return repo.save(request);
    }

    @Override
    @Transactional
    public boolean elimina(long id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
