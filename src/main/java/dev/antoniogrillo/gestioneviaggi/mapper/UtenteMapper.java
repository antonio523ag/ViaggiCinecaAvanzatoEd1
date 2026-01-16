package dev.antoniogrillo.gestioneviaggi.mapper;

import dev.antoniogrillo.gestioneviaggi.dto.request.RegistrazioneRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.RegistrazioneUtenteGraphQL;
import dev.antoniogrillo.gestioneviaggi.dto.response.MiniViaggiatoreDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.UtenteDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.handler.GraphQLException;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Component
public class UtenteMapper {

    public UtenteDTO toUtenteDTO(Utente utente){
        return new UtenteDTO(utente.getId(),
                             utente.getNome(),
                             utente.getCognome(),
                             utente.getUsername(),
                             utente.getDataNascita(),
                             utente.getRuolo());
    }

    public List<UtenteDTO> toUtenteDTO(List<Utente> utenti){
        return utenti.stream().map(this::toUtenteDTO).toList();
    }

    public Utente toUtente(RegistrazioneRequestDTO d){
        Utente utente=new Utente();
        utente.setCognome(d.cognome());
        utente.setNome(d.nome());
        utente.setEmail(d.email());
        utente.setPassword(d.password());
        utente.setDataNascita(d.dataNascita());
        return utente;
    }

    public MiniViaggiatoreDTO toMiniViaggiatoreDTO(Utente utente){
        return new MiniViaggiatoreDTO(utente.getEmail(),
                                     utente.getId());
    }

    public RegistrazioneRequestDTO toRegistrazioneRequestDTO(RegistrazioneUtenteGraphQL input) {
        LocalDate dataNascita;
        try{
            dataNascita=LocalDate.parse(input.dataNascita());
        }catch (DateTimeException e){
            throw new GraphQLException("la data "+input.dataNascita()+" non è una data di nascita valida", ErrorType.BAD_REQUEST);
        }
        return new RegistrazioneRequestDTO(input.nome(),input.cognome(),dataNascita,input.password(),input.email());
    }
}
