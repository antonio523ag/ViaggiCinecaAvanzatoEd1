package dev.antoniogrillo.gestioneviaggi.facade.impl;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiRecensioneDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.RecensioneViaggioDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.RecensioneViaggio;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.facade.def.RecensioneFacade;
import dev.antoniogrillo.gestioneviaggi.mapper.RecensioneMapper;
import dev.antoniogrillo.gestioneviaggi.service.def.MetaService;
import dev.antoniogrillo.gestioneviaggi.service.def.RecensioneViaggioService;
import dev.antoniogrillo.gestioneviaggi.service.def.UtenteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecensioneFacadeImpl implements RecensioneFacade {

    private final RecensioneViaggioService service;
    private final RecensioneMapper mapper;
    private final UtenteService utenteService;
    private final MetaService metaService;

    @Override
    public RecensioneViaggioDTO getRecensione(long id) {
        RecensioneViaggio r=service.getRecensione(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Recensione non trovata"));
        return mapper.toRecensioneViaggioDTO(r);
    }

    @Override
    public List<RecensioneViaggioDTO> getRecensioni(Utente utente, int numeroPagina) {
        List<RecensioneViaggio> recensioni=service.getRecensioniUtente(utente.getId(),numeroPagina);
        return mapper.toRecensioneViaggioDTO(recensioni);
    }

    @Override
    public List<RecensioneViaggioDTO> getRecensioniUtente(long idUtente, int numeroPagina) {
        List<RecensioneViaggio> recensioni=service.getRecensioniUtente(idUtente,numeroPagina);
        return mapper.toRecensioneViaggioDTO(recensioni);
    }

    @Override
    public List<RecensioneViaggioDTO> getRecensioniMeta(int idMeta, int numeroPagina) {
        List<RecensioneViaggio> recensioni=service.getRecensioniMeta(idMeta,numeroPagina);
        return mapper.toRecensioneViaggioDTO(recensioni);
    }

    @Override
    @Transactional
    public RecensioneViaggio salva(AggiungiRecensioneDTO request, Utente utente) {
        Utente u=utenteService.getUtenteById(utente.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Utente non trovato"));
        Meta m=metaService.getMeta(request.idMeta()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Meta non trovato"));
        RecensioneViaggio r=mapper.toRecensioneViaggio(request,u,m);
        return service.salva(r);
    }

    @Override
    public void elimina(long id) {
        if(!service.elimina(id))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Recensione non trovata");
    }
}
