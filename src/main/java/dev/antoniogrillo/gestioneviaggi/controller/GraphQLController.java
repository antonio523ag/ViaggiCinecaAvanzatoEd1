package dev.antoniogrillo.gestioneviaggi.controller;

import dev.antoniogrillo.gestioneviaggi.dto.request.*;
import dev.antoniogrillo.gestioneviaggi.dto.response.LoginGraphResponse;
import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.RecensioneViaggio;
import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.facade.def.GraphQLFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GraphQLController {

    private final GraphQLFacade facade;

    //Query

    //--Meta
    @QueryMapping
    public List<Meta> getMete(@Argument int numeroPagina){
        return facade.getMete(numeroPagina);
    }

    @QueryMapping
    public List<Meta> getMetePerTipologia(@Argument long idTipologia,@ Argument int numeroPagina){
        return facade.getMetePerTipologia(idTipologia,numeroPagina);
    }

    @QueryMapping
    public Meta getMeta(@Argument long id){
        return facade.getMeta(id);
    }

    @PreAuthorize( "hasAnyRole('ADMIN','UTENTE')")
    @QueryMapping
    public List<Meta> getVisitate(@Argument int numeroPagina, @AuthenticationPrincipal Utente utente){
        return facade.getVisitate(numeroPagina,utente);
    }

    @PreAuthorize( "isAuthenticated()")
    @QueryMapping
    public List<Meta> getDaVisitare(@Argument int numeroPagina, @AuthenticationPrincipal Utente utente){
        return facade.getDaVisitare(numeroPagina,utente);
    }

    //--RecensioneViaggio
    @QueryMapping
    public RecensioneViaggio getRecensione(@Argument long id){
        return facade.getRecensione(id);
    }

    @PreAuthorize( "isAuthenticated()")
    @QueryMapping
    public List<RecensioneViaggio> getRecensioni(@AuthenticationPrincipal Utente utente,@Argument int numeroPagina){
        return facade.getRecensioni(utente,numeroPagina);
    }

    @PreAuthorize( "isAuthenticated()")
    @QueryMapping
    public List<RecensioneViaggio> getRecensioniUtente(@Argument long idUtente,@Argument int numeroPagina){
        return facade.getRecensioniUtente(idUtente,numeroPagina);
    }

    @PreAuthorize( "isAuthenticated()")
    @QueryMapping
    public List<RecensioneViaggio> getRecensioniMeta(@Argument int idMeta,@Argument int numeroPagina){
        return facade.getRecensioniMeta(idMeta,numeroPagina);
    }

    //--TipologiaMeta
    @PreAuthorize( "isAuthenticated()")
    @QueryMapping
    public List<TipologiaMeta> getTipologie(@Argument int numeroPagina){
        return facade.getTipologie(numeroPagina);
    }

    @PreAuthorize( "isAuthenticated()")
    @QueryMapping
    public TipologiaMeta getTipologia(@Argument long id){
        return facade.getTipologia(id);
    }

    //--Utente
    @QueryMapping
    public LoginGraphResponse login(@Argument LoginRequestDTO input){
        return facade.login(input);
    }

    @PreAuthorize( "isAuthenticated()")
    @QueryMapping
    public List<Utente> getUtenti(@Argument int numeroPagina){
        return facade.getUtenti(numeroPagina);
    }


    //Mutation

    //--Meta
    @PreAuthorize( "hasRole('ADMIN')")
    @MutationMapping
    public Long creaMeta(@Argument AggiungiMetaDTO input){
        Meta m= facade.aggiungiMeta(input);
        return m.getId();
    }

    @PreAuthorize( "hasRole('ADMIN')")
    @MutationMapping
    public boolean eliminaMeta(@Argument long id){
        return facade.elimina(id);
    }

    //--RecensioneViaggio
    @PreAuthorize( "isAuthenticated()")
    @MutationMapping
    public long aggiungiRecensione(@Argument AggiungiRecensioneGraphQL input, @AuthenticationPrincipal Utente utente){
        RecensioneViaggio r= facade.aggiungiRecensione(input,utente);
        return r.getId();
    }

    @PreAuthorize( "hasRole('ADMIN')")
    @MutationMapping
    public boolean cancellaRecensione(@PathVariable long id){
        return facade.elimina(id);
    }

    //--TipologiaMeta
    @PreAuthorize( "hasRole('ADMIN')")
    @MutationMapping
    public Long aggiungiTipologia(@Argument AggiungiTipologiaDTO input){
        TipologiaMeta t= facade.aggiungiTipologia(input);
        return t.getId();
    }

    //--Utente
    @QueryMapping
    public Long registraUtente(@Argument RegistrazioneUtenteGraphQL input){
        Utente u= facade.registraUtente(input);
        return u.getId();
    }

    @PreAuthorize( "hasRole('ADMIN')")
    @MutationMapping
    public Long registraAdmin(@Argument RegistrazioneUtenteGraphQL input){
        Utente u= facade.registraAdmin(input);
        return u.getId();
    }

    @PreAuthorize( "hasRole('ADMIN')")
    @MutationMapping
    public boolean deleteUtente(@PathVariable long id){
        return facade.deleteUtente(id);

    }




    //Mapping relazioni
    //--Utente
    @SchemaMapping
    public List<RecensioneViaggio> getRecensioniUtente(Utente utente){
        return facade.getRecensioniUtente(utente);
    }

    //--TipologiaMeta
    @SchemaMapping
    public List<Meta> getMete(TipologiaMeta tipologiaMeta){
        return facade.getMete(tipologiaMeta);
    }
    //--RecensioneViaggio
    @SchemaMapping
    public Utente getUtente(RecensioneViaggio recensioneViaggio){
        return facade.getUtente(recensioneViaggio);
    }

    @SchemaMapping
    public Meta getMeta(RecensioneViaggio recensioneViaggio){
        return facade.getMeta(recensioneViaggio);
    }

    //--Meta
    @SchemaMapping
    public List<TipologiaMeta> getTipologieMete(Meta meta){
        return facade.getTipologieMete(meta);
    }

    @SchemaMapping
    public List<RecensioneViaggio> getRecensioniMete(Meta meta){
        return facade.getRecensioniMete(meta);
    }

}
