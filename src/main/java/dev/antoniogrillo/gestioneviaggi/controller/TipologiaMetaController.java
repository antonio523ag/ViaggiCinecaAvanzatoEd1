package dev.antoniogrillo.gestioneviaggi.controller;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiTipologiaDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.TipologiaMetaDTO;
import dev.antoniogrillo.gestioneviaggi.facade.def.TipologiaMetaFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TipologiaMetaController {

    private final TipologiaMetaFacade facade;

    @GetMapping("/authorized/tipologie/{numeroPagina}")
    public ResponseEntity<List<TipologiaMetaDTO>> getTipologie(@PathVariable int numeroPagina){
        List<TipologiaMetaDTO> t= facade.getTipologie(numeroPagina);
        return ResponseEntity.ok(t);
    }

    @GetMapping("/authorized/tipologia/{id}")
    public ResponseEntity<TipologiaMetaDTO> getTipologia(@PathVariable long id){
        return ResponseEntity.ok(facade.getTipologia(id));
    }

    @PostMapping("/admin/tipologie")
    public ResponseEntity<TipologiaMetaDTO> aggiungi(@RequestBody AggiungiTipologiaDTO request){
        TipologiaMetaDTO t= facade.aggiungi(request);
        return ResponseEntity.ok(t);
    }




}
