package dev.antoniogrillo.gestioneviaggi.facade.impl;

import dev.antoniogrillo.gestioneviaggi.dto.request.AggiungiMetaDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.MetaDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.facade.def.MetaFacade;
import dev.antoniogrillo.gestioneviaggi.mapper.MetaMapper;
import dev.antoniogrillo.gestioneviaggi.service.def.MetaService;
import dev.antoniogrillo.gestioneviaggi.service.def.TipologiaMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetaFacadeImpl implements MetaFacade {

    private final MetaService service;
    private final MetaMapper mapper;
    private final TipologiaMetaService tipologiaMetaService;

    @Override
    public List<MetaDTO> getMete(int numeroPagina) {
        List<Meta> metas = service.getMete(numeroPagina);
        return mapper.toMetaDTO(metas);
    }

    @Override
    public List<MetaDTO> getMetePerTipologia(long idTipologia, int numeroPagina) {
        List<Meta> metas = service.getMetePerTipologia(idTipologia, numeroPagina);
        return mapper.toMetaDTO(metas);
    }

    @Override
    public Meta salva(AggiungiMetaDTO request) {
        Optional<Meta> opt=service.getByNome(request.nome());
        if(opt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Meta già esistente");
        }
        List<TipologiaMeta> tipologie=tipologiaMetaService.findAllByIds(request.idTipologie());
        Meta m=new Meta();
        m.setNome(request.nome());
        m.setTipologie(tipologie);
        return service.salva(m);
    }

    @Override
    public void elimina(long id) {
        if(!service.elimina(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Meta non trovata");
    }

    @Override
    public MetaDTO getMeta(long id) {
        Meta meta=service.getMeta(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Meta non trovata"));
        return mapper.toMetaDTO(meta);
    }

    @Override
    public List<MetaDTO> getVisitate(int numeroPagina, Utente utente) {
        List<Meta> mete=service.getMetePerIdUtente(numeroPagina,utente.getId());
        return mapper.toMetaDTO(mete);
    }

    @Override
    public List<MetaDTO> getDaVisitare(int numeroPagina, Utente utente) {
        List<Meta> mete=service.getMeteNonVisitatePerIdUtente(numeroPagina,utente.getId());
        return mapper.toMetaDTO(mete);
    }
}
