package dev.antoniogrillo.gestioneviaggi.controller;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiRecensioneDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.RecensioneViaggioDTO;
import dev.antoniogrillo.gestioneviaggi.entity.RecensioneViaggio;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.facade.def.RecensioneFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecensioneController {

    private final RecensioneFacade facade;

    @GetMapping("/all/recensione/{id}")
    public ResponseEntity<RecensioneViaggioDTO> getRecensione(@PathVariable long id){
        return ResponseEntity.ok(facade.getRecensione(id));
    }

    @GetMapping("/authorized/recensioni/{numeroPagina}")
    public ResponseEntity<List<RecensioneViaggioDTO>> getRecensioni(@AuthenticationPrincipal Utente utente,@PathVariable int numeroPagina){
        return ResponseEntity.ok(facade.getRecensioni(utente,numeroPagina));
    }

    @GetMapping("/authorized/recensioni/idUtente/{idUtente}/{numeroPagina}")
    public ResponseEntity<List<RecensioneViaggioDTO>> getRecensioni(@PathVariable long idUtente,@PathVariable int numeroPagina){
        return ResponseEntity.ok(facade.getRecensioniUtente(idUtente,numeroPagina));
    }

    @GetMapping("/authorized/recensioni/idMeta/{idMeta}/{numeroPagina}")
    public ResponseEntity<List<RecensioneViaggioDTO>> getRecensioniMeta(@PathVariable int idMeta,@PathVariable int numeroPagina){
        return ResponseEntity.ok(facade.getRecensioniMeta(idMeta,numeroPagina));
    }

    @PostMapping("/authorized/recensione")
    public ResponseEntity<Long> getRecensioni(@RequestBody AggiungiRecensioneDTO request, @AuthenticationPrincipal Utente utente){
        RecensioneViaggio r= facade.salva(request,utente);
        return ResponseEntity.ok(r.getId());
    }

    @DeleteMapping("/admin/recensione/{id}")
    public ResponseEntity<Void> getRecensioni(@PathVariable long id){
        facade.elimina(id);
        return ResponseEntity.ok().build();
    }




}
