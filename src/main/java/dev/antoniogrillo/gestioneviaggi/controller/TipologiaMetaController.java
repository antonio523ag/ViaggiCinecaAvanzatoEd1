package dev.antoniogrillo.gestioneviaggi.controller;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiTipologia;
import dev.antoniogrillo.gestioneviaggi.dto.response.TipologiaMetaDTO;
import dev.antoniogrillo.gestioneviaggi.service.def.TipologiaMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TipologiaMetaController {

    private final TipologiaMetaService service;

    @GetMapping("/authorized/tipologie/{numeroPagina}")
    public ResponseEntity<List<TipologiaMetaDTO>> getTipologie(@PathVariable int numeroPagina){
        List<TipologiaMetaDTO> t=service.getTipologie(numeroPagina);
        return ResponseEntity.ok(t);
    }

    @GetMapping("/authorized/tipologia/{id}")
    public ResponseEntity<TipologiaMetaDTO> getTipologia(@PathVariable long id){
        return ResponseEntity.ok(service.getTipologia(id));
    }

    @PostMapping("/admin/tipologie")
    public ResponseEntity<TipologiaMetaDTO> aggiungi(@RequestBody AggiungiTipologia request){
        TipologiaMetaDTO t=service.aggiungi(request);
        return ResponseEntity.ok(t);
    }




}
