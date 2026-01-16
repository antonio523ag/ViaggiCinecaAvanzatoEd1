package dev.antoniogrillo.gestioneviaggi.mapper;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiRecensioneDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiRecensioneGraphQL;
import dev.antoniogrillo.gestioneviaggi.dto.response.RecensioneViaggioDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.RecensioneViaggio;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.handler.GraphQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RecensioneMapper {

    private final UtenteMapper utenteMapper;
    private final MetaMapper metaMapper;

    public RecensioneViaggioDTO toRecensioneViaggioDTO(RecensioneViaggio r){
        return new RecensioneViaggioDTO(r.getId(),
                                       r.getDescrizione(),
                                       r.getVoto(),
                                       r.getDataPartenza(),
                                       r.getDataRitorno(),
                                       utenteMapper.toMiniViaggiatoreDTO(r.getUtente()),
                                       metaMapper.toMetaDTO(r.getMeta()));
    }

    public List<RecensioneViaggioDTO> toRecensioneViaggioDTO(List<RecensioneViaggio> recensioni){
        return recensioni.stream().map(this::toRecensioneViaggioDTO).toList();
    }

    public RecensioneViaggio toRecensioneViaggio(AggiungiRecensioneDTO request, Utente u, Meta m){
        RecensioneViaggio r=new RecensioneViaggio();
        r.setDescrizione(request.testo());
        r.setVoto(request.punteggio());
        r.setDataPartenza(request.dataPartenza());
        r.setDataRitorno(request.dataRitorno());
        r.setUtente(u);
        r.setMeta(m);
        return r;
    }


    public AggiungiRecensioneDTO toAggiungiRecensioneDTO(AggiungiRecensioneGraphQL request) {
        LocalDate dataPartenza=null;
        try {
            dataPartenza=LocalDate.parse(request.dataPartenza());
        }catch (DateTimeException e){
            throw new GraphQLException("la data "+request.dataPartenza()+" non è una data valida", ErrorType.BAD_REQUEST);
        }
        LocalDate dataRitorno=null;
        try {
            dataRitorno=LocalDate.parse(request.dataRitorno());
        }catch (DateTimeException e){
            throw new GraphQLException("la data "+request.dataRitorno()+" non è una data valida", ErrorType.BAD_REQUEST);
        }
        return new AggiungiRecensioneDTO(request.idMeta(),dataPartenza,dataRitorno,request.punteggio(),request.testo());

    }
}
