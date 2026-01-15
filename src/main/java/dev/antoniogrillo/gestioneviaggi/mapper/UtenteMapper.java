package dev.antoniogrillo.gestioneviaggi.mapper;

import dev.antoniogrillo.gestioneviaggi.dto.request.RegistrazioneRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.MiniViaggiatoreDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.UtenteDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import org.springframework.stereotype.Component;

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
}
