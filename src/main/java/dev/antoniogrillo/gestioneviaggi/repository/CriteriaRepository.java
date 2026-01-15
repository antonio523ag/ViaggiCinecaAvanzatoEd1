package dev.antoniogrillo.gestioneviaggi.repository;

import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.RecensioneViaggio;
import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CriteriaRepository {

    private final EntityManager manager;

    public List<Meta> findMetePerTipologia(long idTipologia, int numeroPagina) {
        List<Param<Long>> params=List.of(new Param<>("TipologiaMeta","id",idTipologia,true));
        return findMete(params,numeroPagina);
    }

    public List<Meta> findMeteVisitate(long id, int numeroPagina) {
        List<Param<Long>> params=List.of(new Param<>("utente","id",id,true));
        return findMete(params,numeroPagina);
    }

    public List<RecensioneViaggio> findRecensioniPerIdUtente(long idUtente, int numeroPagina) {
        List<Param<Long>> params=List.of(new Param<>("utente","id",idUtente,true));
        return findRecensioni(params,numeroPagina);
    }

    public List<RecensioneViaggio> findRecensioniPerIdMeta(int idMeta, int numeroPagina) {
        List<Param<Integer>> params=List.of(new Param<>("meta","id",idMeta,true));
        return findRecensioni(params,numeroPagina);
    }

    public List<Meta> findMeteDaVisitare(long id, int numeroPagina) {
        List<Param<Long>> params=List.of(new Param<>("utente","id",id,false));
        return findMete(params,numeroPagina);
    }



    private <P> List<RecensioneViaggio> findRecensioni(List<Param<P>> params,int numeroPagina){
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<RecensioneViaggio> query=builder.createQuery(RecensioneViaggio.class);
        Root<RecensioneViaggio> root=query.from(RecensioneViaggio.class);
        Join<RecensioneViaggio, Utente> utenti=root.join("utente");
        Join<RecensioneViaggio, Meta> mete=root.join("meta");
        Join<Meta, TipologiaMeta> tipologie=mete.join("tipologie");
        List<Predicate> predicate=new ArrayList<>();
        for(Param<?> p:params){
            Path<?> p1=switch (p.className().trim().toLowerCase()){
                case "recensioneviaggio"-> root.get(p.parameterName());
                case "utente"-> utenti.get(p.parameterName());
                case "meta"-> mete.get(p.parameterName());
                case "tipologiameta"-> tipologie.get(p.parameterName());
                default->throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Errore nel filtro, nome classe non presente");
            };
            Predicate p2=p.equals()?builder.equal(p1,p.parameterValue):builder.notEqual(p1,p.parameterValue);
            predicate.add(p2);
        }
        query.orderBy(builder.asc(root.get("dataPartenza")));
        query.select(root).where(predicate.toArray(new Predicate[0]));
        return manager.createQuery(query).setFirstResult(numeroPagina*10).setMaxResults(10).getResultList();
    }

    private<P> List<Meta> findMete(List<Param<P>> params,int numeroPagina){
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<Meta> query=builder.createQuery(Meta.class);
        Root<Meta> root=query.from(Meta.class);
        Join<Meta, TipologiaMeta> tipologie=root.join("tipologie");
        Join<Meta, RecensioneViaggio> recensioni=root.join("recensioni");
        Join<RecensioneViaggio, Utente> utenti=recensioni.join("utente");
        List<Predicate> predicate=new ArrayList<>();
        for(Param<?> p:params){

            Path<?> p1=switch (p.className().trim().toLowerCase()){
                case "meta"-> root.get(p.parameterName());
                case "tipologiameta"-> tipologie.get(p.parameterName());
                case "recensioneviaggio"-> recensioni.get(p.parameterName());
                case "utente"-> utenti.get(p.parameterName());
                default->throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Errore nel filtro, nome classe non presente");
            };
            Predicate p2=p.equals()?builder.equal(p1,p.parameterValue):builder.notEqual(p1,p.parameterValue);
            predicate.add(p2);
        }
        query.orderBy(builder.asc(root.get("nome")));
        query.select(root).where(predicate.toArray(new Predicate[0]));
        return manager.createQuery(query).setFirstResult(numeroPagina*10).setMaxResults(10).getResultList();
    }




    private record Param<P>(String className,String parameterName,P parameterValue,boolean equals){}
}
