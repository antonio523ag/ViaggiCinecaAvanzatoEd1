package dev.antoniogrillo.gestioneviaggi.service.impl;

import dev.antoniogrillo.gestioneviaggi.dto.internal.LoginResponseDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.LoginRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.RegistrazioneRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.UtenteDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Ruolo;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.mapper.UtenteMapper;
import dev.antoniogrillo.gestioneviaggi.repository.UtenteRepository;
import dev.antoniogrillo.gestioneviaggi.service.def.GestoreTokenService;
import dev.antoniogrillo.gestioneviaggi.service.def.UtenteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository repo;
    private final GestoreTokenService tokenService;
    private final UtenteMapper mapper;


    @Override
    public LoginResponseDTO login(LoginRequestDTO utente) {
        Utente u=repo.findByEmailAndPassword(utente.username(),utente.password()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"nessun utente con queste credenziali"));
        return new LoginResponseDTO(tokenService.generateToken(u),mapper.toUtenteDTO(u));
    }

    @Override
    public Utente registraUtente(RegistrazioneRequestDTO utente) {
        return registraUtente(utente,Ruolo.UTENTE);
    }

    @Override
    public List<UtenteDTO> getUtenti(int numeroPagina) {
        Sort s= Sort.by("cognome").ascending().and(Sort.by("nome").ascending());
        Pageable p= PageRequest.of(numeroPagina,10,s);
        return mapper.toUtenteDTO(repo.findAll(p).getContent());
    }

    @Override
    public Utente registraAdmin(RegistrazioneRequestDTO utente) {
        return registraUtente(utente,Ruolo.ADMIN);
    }

    @Override
    @Transactional
    public UtenteDTO deleteUtente(long id) {
        Utente u=repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"nessun utente con questo id"));
        repo.delete(u);
        return mapper.toUtenteDTO(u);
    }

    private Utente registraUtente(RegistrazioneRequestDTO request, Ruolo ruolo){
        Utente u=mapper.toUtente(request);
        u.setRuolo(ruolo);
        return repo.save(u);
    }
}
