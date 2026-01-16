package dev.antoniogrillo.gestioneviaggi.facade.impl;

import dev.antoniogrillo.gestioneviaggi.dto.request.*;
import dev.antoniogrillo.gestioneviaggi.dto.response.LoginGraphResponse;
import dev.antoniogrillo.gestioneviaggi.entity.*;
import dev.antoniogrillo.gestioneviaggi.facade.def.GraphQLFacade;
import dev.antoniogrillo.gestioneviaggi.handler.GraphQLException;
import dev.antoniogrillo.gestioneviaggi.mapper.RecensioneMapper;
import dev.antoniogrillo.gestioneviaggi.mapper.TipologiaMetaMapper;
import dev.antoniogrillo.gestioneviaggi.mapper.UtenteMapper;
import dev.antoniogrillo.gestioneviaggi.service.def.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GraphQLFacadeImpl implements GraphQLFacade {

    private final UtenteService utenteService;
    private final MetaService metaService;
    private final RecensioneViaggioService recensioneViaggioService;
    private final TipologiaMetaService tipologiaMetaService;
    private final RecensioneMapper recensioneMapper;
    private final TipologiaMetaMapper tipologiaMetaMapper;
    private final GestoreTokenService gestoreTokenService;
    private final UtenteMapper utenteMapper;


    @Override
    public List<RecensioneViaggio> getRecensioniUtente(Utente utente) {
        Utente u=utenteService.getUtenteById(utente.getId()).orElseThrow(() -> new GraphQLException("Utente non trovato", ErrorType.NOT_FOUND));
        return u.getRecensioni();
    }

    @Override
    public List<Meta> getMete(TipologiaMeta tipologiaMeta) {
        TipologiaMeta t=tipologiaMetaService.getTipologiaById(tipologiaMeta.getId()).orElseThrow(() -> new GraphQLException("Tipologia non trovata", ErrorType.NOT_FOUND));
        return t.getMete();
    }

    @Override
    public Utente getUtente(RecensioneViaggio recensioneViaggio) {
        RecensioneViaggio r=recensioneViaggioService.getRecensione(recensioneViaggio.getId()).orElseThrow(() -> new GraphQLException("Recensione non trovata", ErrorType.NOT_FOUND));
        return r.getUtente();
    }

    @Override
    public Meta getMeta(RecensioneViaggio recensioneViaggio) {
        RecensioneViaggio r=recensioneViaggioService.getRecensione(recensioneViaggio.getId()).orElseThrow(() -> new GraphQLException("Recensione non trovata", ErrorType.NOT_FOUND));
        return r.getMeta();
    }

    @Override
    public List<TipologiaMeta> getTipologieMete(Meta meta) {
        Meta m=metaService.getMeta(meta.getId()).orElseThrow(() -> new GraphQLException("Meta non trovata", ErrorType.NOT_FOUND));
        return m.getTipologie();
    }

    @Override
    public List<RecensioneViaggio> getRecensioniMete(Meta meta) {
        Meta m=metaService.getMeta(meta.getId()).orElseThrow(() -> new GraphQLException("Meta non trovata", ErrorType.NOT_FOUND));
        return m.getRecensioni();
    }

    @Override
    public List<Meta> getMete(int numeroPagina) {
        return metaService.getMete(numeroPagina);
    }

    @Override
    public List<Meta> getMetePerTipologia(long idTipologia, int numeroPagina) {
        return metaService.getMetePerTipologia(idTipologia,numeroPagina);
    }

    @Override
    @Transactional
    public Meta aggiungiMeta(AggiungiMetaDTO request) {
        Optional<Meta> opt=metaService.getByNome(request.nome());
        if(opt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Meta già esistente");
        }
        List<TipologiaMeta> tipologie=tipologiaMetaService.findAllByIds(request.idTipologie());
        Meta m=new Meta();
        m.setNome(request.nome());
        m.setTipologie(tipologie);
        return metaService.salva(m);
    }

    @Override
    public boolean elimina(long id) {
        return metaService.elimina(id);
    }

    @Override
    public Meta getMeta(long id) {
        return metaService.getMeta(id).orElseThrow(() -> new GraphQLException("Meta non trovata", ErrorType.NOT_FOUND));
    }

    @Override
    public List<Meta> getVisitate(int numeroPagina, Utente utente) {
        return metaService.getMetePerIdUtente(numeroPagina,utente.getId());
    }

    @Override
    public List<Meta> getDaVisitare(int numeroPagina, Utente utente) {
        return metaService.getMeteNonVisitatePerIdUtente(numeroPagina,utente.getId());
    }

    @Override
    public RecensioneViaggio getRecensione(long id) {
        return recensioneViaggioService.getRecensione(id).orElseThrow(() -> new GraphQLException("Recensione non trovata", ErrorType.NOT_FOUND));
    }

    @Override
    public List<RecensioneViaggio> getRecensioni(Utente utente, int numeroPagina) {
        return recensioneViaggioService.getRecensioniUtente(utente.getId(),numeroPagina);
    }

    @Override
    public List<RecensioneViaggio> getRecensioniUtente(long idUtente, int numeroPagina) {
        return recensioneViaggioService.getRecensioniUtente(idUtente,numeroPagina);
    }

    @Override
    public List<RecensioneViaggio> getRecensioniMeta(int idMeta, int numeroPagina) {
        return recensioneViaggioService.getRecensioniMeta(idMeta,numeroPagina);
    }

    @Override
    public RecensioneViaggio aggiungiRecensione(AggiungiRecensioneGraphQL request, Utente utente) {
        AggiungiRecensioneDTO a=recensioneMapper.toAggiungiRecensioneDTO(request);
        Utente u=utenteService.getUtenteById(utente.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Utente non trovato"));
        Meta m=metaService.getMeta(request.idMeta()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Meta non trovato"));
        RecensioneViaggio r=recensioneMapper.toRecensioneViaggio(a,u,m);
        return recensioneViaggioService.salva(r);
    }

    @Override
    public List<TipologiaMeta> getTipologie(int numeroPagina) {
        return tipologiaMetaService.getTipologie(numeroPagina);
    }

    @Override
    public TipologiaMeta getTipologia(long id) {
        return tipologiaMetaService.getTipologiaById(id).orElseThrow(() -> new GraphQLException("Tipologia non trovata", ErrorType.NOT_FOUND));
    }

    @Override
    public TipologiaMeta aggiungiTipologia(AggiungiTipologiaDTO input) {
        TipologiaMeta t=tipologiaMetaMapper.toTipologiaMeta(input);
        return tipologiaMetaService.aggiungi(t);
    }

    @Override
    public LoginGraphResponse login(LoginRequestDTO input) {
        Utente u=utenteService.login(input.username(),input.password()).orElseThrow(()->new GraphQLException("Credenziali non valide", ErrorType.NOT_FOUND));
        String token=gestoreTokenService.generateToken(u);
        return new LoginGraphResponse(token,u);
    }

    @Override
    public Utente registraUtente(RegistrazioneUtenteGraphQL input) {
        RegistrazioneRequestDTO utente=utenteMapper.toRegistrazioneRequestDTO(input);
        Utente u=utenteMapper.toUtente(utente);
        u.setRuolo(Ruolo.UTENTE);
        return utenteService.salva(u);
    }

    @Override
    public List<Utente> getUtenti(int numeroPagina) {
        return utenteService.getUtenti(numeroPagina);
    }

    @Override
    public Utente registraAdmin(RegistrazioneUtenteGraphQL input) {
        RegistrazioneRequestDTO utente=utenteMapper.toRegistrazioneRequestDTO(input);
        Utente u=utenteMapper.toUtente(utente);
        u.setRuolo(Ruolo.ADMIN);
        return utenteService.salva(u);
    }

    @Override
    public boolean deleteUtente(long id) {
        return utenteService.deleteUtente(id);
    }
}
