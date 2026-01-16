package dev.antoniogrillo.gestioneviaggi.controller;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiMetaDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.MetaDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.facade.def.MetaFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MetaController {

    private final MetaFacade facade;

    @GetMapping("/all/mete/{numeroPagina}")
    public ResponseEntity<List<MetaDTO>> getMete(@PathVariable int numeroPagina){
        return ResponseEntity.ok(facade.getMete(numeroPagina));
    }

    @GetMapping("/all/mete/tipologia/{idTipologia}/{numeroPagina}")
    public ResponseEntity<List<MetaDTO>> getMetePerTipologia(@PathVariable long idTipologia,@PathVariable int numeroPagina){
        return ResponseEntity.ok(facade.getMetePerTipologia(idTipologia,numeroPagina));
    }

    @PostMapping("/admin/meta")
    public ResponseEntity<Long> creaMeta(@RequestBody AggiungiMetaDTO request){
        Meta m= facade.salva(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(m.getId());
    }

    @DeleteMapping("/admin/meta/{id}")
    public ResponseEntity<Void> eliminaMeta(@PathVariable long id){
        facade.elimina(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/meta/{id}")
    public ResponseEntity<MetaDTO> getMeta(@PathVariable long id){
        return ResponseEntity.ok(facade.getMeta(id));
    }

    @GetMapping("/authorized/meta/getVisitate/{numeroPagina}")
    public ResponseEntity<List<MetaDTO>> getVisitate(@PathVariable int numeroPagina, @AuthenticationPrincipal Utente utente){
        return ResponseEntity.ok(facade.getVisitate(numeroPagina,utente));
    }

    @GetMapping("/authorized/meta/daVisitare/{numeroPagina}")
    public ResponseEntity<List<MetaDTO>> getDaVisitare(@PathVariable int numeroPagina, @AuthenticationPrincipal Utente utente){
        return ResponseEntity.ok(facade.getDaVisitare(numeroPagina,utente));
    }

}
