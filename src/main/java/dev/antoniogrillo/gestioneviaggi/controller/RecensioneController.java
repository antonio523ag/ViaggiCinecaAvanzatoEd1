package dev.antoniogrillo.gestioneviaggi.controller;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiRecensioneDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.RecensioneViaggioDTO;
import dev.antoniogrillo.gestioneviaggi.entity.RecensioneViaggio;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.service.def.RecensioneViaggioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecensioneController {

    private final RecensioneViaggioService service;

    @GetMapping("/all/recensione/{id}")
    public ResponseEntity<RecensioneViaggioDTO> getRecensione(@PathVariable long id){
        return ResponseEntity.ok(service.getRecensione(id));
    }

    @GetMapping("/authorized/recensioni/{numeroPagina}")
    public ResponseEntity<List<RecensioneViaggioDTO>> getRecensioni(@AuthenticationPrincipal Utente utente,@PathVariable int numeroPagina){
        return ResponseEntity.ok(service.getRecensioni(utente,numeroPagina));
    }

    @GetMapping("/authorized/recensioni/idUtente/{idUtente}/{numeroPagina}")
    public ResponseEntity<List<RecensioneViaggioDTO>> getRecensioni(@PathVariable long idUtente,@PathVariable int numeroPagina){
        return ResponseEntity.ok(service.getRecensioniUtente(idUtente,numeroPagina));
    }

    @GetMapping("/authorized/recensioni/idMeta/{idMeta}/{numeroPagina}")
    public ResponseEntity<List<RecensioneViaggioDTO>> getRecensioniMeta(@PathVariable int idMeta,@PathVariable int numeroPagina){
        return ResponseEntity.ok(service.getRecensioniMeta(idMeta,numeroPagina));
    }

    @PostMapping("/authorized/recensione")
    public ResponseEntity<Long> getRecensioni(@RequestBody AggiungiRecensioneDTO request, @AuthenticationPrincipal Utente utente){
        RecensioneViaggio r=service.salva(request,utente);
        return ResponseEntity.ok(r.getId());
    }

    @DeleteMapping("/admin/recensione/{id}")
    public ResponseEntity<RecensioneViaggioDTO> getRecensioni(@PathVariable long id){
        return ResponseEntity.ok(service.elimina(id));
    }




}
