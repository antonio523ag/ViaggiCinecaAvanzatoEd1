package dev.antoniogrillo.gestioneviaggi.controller;

import dev.antoniogrillo.gestioneviaggi.dto.internal.LoginResponseDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.LoginRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.RegistrazioneRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.UtenteDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.facade.def.UtenteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UtenteController {

    private final UtenteFacade facade;

    @PostMapping("/all/login")
    public ResponseEntity<UtenteDTO> login(@RequestBody LoginRequestDTO utente){
        LoginResponseDTO l= facade.login(utente);
        return ResponseEntity.status(HttpStatus.OK).header("Authorization",l.token()).body(l.utente());
    }

    @PostMapping("/all/registra")
    public ResponseEntity<Long> registraUtente(@RequestBody RegistrazioneRequestDTO utente){
        Utente u= facade.registraUtente(utente);
        return ResponseEntity.status(HttpStatus.CREATED).body(u.getId());
    }

    @GetMapping("/authorized/utenti/getAll/{numeroPagina}")
    public ResponseEntity<List<UtenteDTO>> getUtenti(@PathVariable int numeroPagina){
        List<UtenteDTO> utenti= facade.getUtenti(numeroPagina);
        return ResponseEntity.status(HttpStatus.OK).body(utenti);
    }

    @PostMapping("/admin/registra")
    public ResponseEntity<Long> registraAdmin(@RequestBody RegistrazioneRequestDTO utente){
        Utente u= facade.registraAdmin(utente);
        return ResponseEntity.status(HttpStatus.CREATED).body(u.getId());
    }

    @DeleteMapping("/admin/utente/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable long id){
        facade.deleteUtente(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }






}
