package dev.antoniogrillo.gestioneviaggi.service.impl;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiRecensioneDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.RecensioneViaggioDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.RecensioneViaggio;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.mapper.RecensioneMapper;
import dev.antoniogrillo.gestioneviaggi.repository.CriteriaRepository;
import dev.antoniogrillo.gestioneviaggi.repository.MetaRepository;
import dev.antoniogrillo.gestioneviaggi.repository.RecensioneViaggioRepository;
import dev.antoniogrillo.gestioneviaggi.repository.UtenteRepository;
import dev.antoniogrillo.gestioneviaggi.service.def.RecensioneViaggioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecensioneViaggioServiceImpl implements RecensioneViaggioService {

    private final RecensioneViaggioRepository repo;
    private final CriteriaRepository criteriaRepository;
    private final RecensioneMapper mapper;
    private final UtenteRepository utenteRepository;
    private final MetaRepository metaRepository;


    @Override
    public RecensioneViaggioDTO getRecensione(long id) {
        RecensioneViaggio r=repo.findById(id).orElseThrow(()->new RuntimeException("nessuna recensione con questo id"));
        return mapper.toRecensioneViaggioDTO(r);
    }

    @Override
    public List<RecensioneViaggioDTO> getRecensioni(Utente utente, int numeroPagina) {
        return getRecensioniUtente(utente.getId(),numeroPagina);
    }

    @Override
    public List<RecensioneViaggioDTO> getRecensioniUtente(long idUtente, int numeroPagina) {
        List<RecensioneViaggio> l=criteriaRepository.findRecensioniPerIdUtente(idUtente,numeroPagina);
        return mapper.toRecensioneViaggioDTO(l);
    }

    @Override
    public List<RecensioneViaggioDTO> getRecensioniMeta(int idMeta, int numeroPagina) {
        List<RecensioneViaggio> l=criteriaRepository.findRecensioniPerIdMeta(idMeta,numeroPagina);
        return mapper.toRecensioneViaggioDTO(l);
    }

    @Override
    public RecensioneViaggio salva(AggiungiRecensioneDTO request, Utente utente) {
        Utente u=utenteRepository.findById(utente.getId()).orElseThrow(()->new RuntimeException("nessun utente con questo id"));
        Meta m=metaRepository.findById(request.idMeta()).orElseThrow(()->new RuntimeException("nessuna meta con questo id"));
        RecensioneViaggio r=mapper.toRecensioneViaggio(request,u,m);
        return repo.save(r);
    }

    @Override
    @Transactional
    public RecensioneViaggioDTO elimina(long id) {
        RecensioneViaggio r=repo.findById(id).orElseThrow(()->new RuntimeException("nessuna recensione con questo id"));
        repo.delete(r);
        return mapper.toRecensioneViaggioDTO(r);
    }
}
