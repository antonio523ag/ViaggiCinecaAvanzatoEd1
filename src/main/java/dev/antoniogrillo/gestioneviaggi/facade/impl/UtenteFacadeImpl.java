package dev.antoniogrillo.gestioneviaggi.facade.impl;

import dev.antoniogrillo.gestioneviaggi.dto.internal.LoginResponseDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.LoginRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.RegistrazioneRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.UtenteDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Ruolo;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.facade.def.UtenteFacade;
import dev.antoniogrillo.gestioneviaggi.mapper.UtenteMapper;
import dev.antoniogrillo.gestioneviaggi.service.def.GestoreTokenService;
import dev.antoniogrillo.gestioneviaggi.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteFacadeImpl implements UtenteFacade {

    private final UtenteService service;
    private final UtenteMapper mapper;
    private final GestoreTokenService gestoreTokenService;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        Utente u=service.login(request.username(),request.password()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Credenziali non valide"));
        UtenteDTO uDTO= mapper.toUtenteDTO(u);
        String token=gestoreTokenService.generateToken(u);
        return new LoginResponseDTO(token,uDTO);
    }

    @Override
    public Utente registraUtente(RegistrazioneRequestDTO utente) {
        Utente u=mapper.toUtente(utente);
        u.setRuolo(Ruolo.UTENTE);
        return service.salva(u);
    }

    @Override
    public List<UtenteDTO> getUtenti(int numeroPagina) {
        List<Utente> utenti=service.getUtenti(numeroPagina);
        return mapper.toUtenteDTO(utenti);
    }

    @Override
    public Utente registraAdmin(RegistrazioneRequestDTO utente) {
        Utente u=mapper.toUtente(utente);
        u.setRuolo(Ruolo.ADMIN);
        return service.salva(u);
    }

    @Override
    public void deleteUtente(long id) {
        if(!service.deleteUtente(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato");

    }
}
