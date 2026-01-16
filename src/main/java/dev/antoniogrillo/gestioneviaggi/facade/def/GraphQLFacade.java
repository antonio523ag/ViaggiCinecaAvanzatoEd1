package dev.antoniogrillo.gestioneviaggi.facade.def;

import dev.antoniogrillo.gestioneviaggi.dto.request.*;
import dev.antoniogrillo.gestioneviaggi.dto.response.LoginGraphResponse;
import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.RecensioneViaggio;
import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;

import java.util.List;

public interface GraphQLFacade {
    List<RecensioneViaggio> getRecensioniUtente(Utente utente);

    List<Meta> getMete(TipologiaMeta tipologiaMeta);

    Utente getUtente(RecensioneViaggio recensioneViaggio);

    Meta getMeta(RecensioneViaggio recensioneViaggio);

    List<TipologiaMeta> getTipologieMete(Meta meta);

    List<RecensioneViaggio> getRecensioniMete(Meta meta);

    List<Meta> getMete(int numeroPagina);

    List<Meta> getMetePerTipologia(long idTipologia, int numeroPagina);

    Meta aggiungiMeta(AggiungiMetaDTO request);

    boolean elimina(long id);

    Meta getMeta(long id);

    List<Meta> getVisitate(int numeroPagina, Utente utente);

    List<Meta> getDaVisitare(int numeroPagina, Utente utente);

    RecensioneViaggio getRecensione(long id);

    List<RecensioneViaggio> getRecensioni(Utente utente, int numeroPagina);

    List<RecensioneViaggio> getRecensioniUtente(long idUtente, int numeroPagina);

    List<RecensioneViaggio> getRecensioniMeta(int idMeta, int numeroPagina);

    RecensioneViaggio aggiungiRecensione(AggiungiRecensioneGraphQL request, Utente utente);

    List<TipologiaMeta> getTipologie(int numeroPagina);

    TipologiaMeta getTipologia(long id);

    TipologiaMeta aggiungiTipologia(AggiungiTipologiaDTO input);

    LoginGraphResponse login(LoginRequestDTO input);

    Utente registraUtente(RegistrazioneUtenteGraphQL input);

    List<Utente> getUtenti(int numeroPagina);

    Utente registraAdmin(RegistrazioneUtenteGraphQL input);

    boolean deleteUtente(long id);
}
