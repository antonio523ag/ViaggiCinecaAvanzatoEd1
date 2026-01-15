package dev.antoniogrillo.gestioneviaggi.mapper;

import dev.antoniogrillo.gestioneviaggi.dto.response.MetaDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.TipologiaMetaDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MetaMapper {

    private final TipologiaMetaMapper tipologiaMetaMapper;

    public MetaDTO toMetaDTO(Meta meta){
        List<TipologiaMetaDTO> l=tipologiaMetaMapper.toTipologiaMetaDTO(meta.getTipologie());
        return new MetaDTO(meta.getNome(),meta.getId(),l);
    }

    public List<MetaDTO> toMetaDTO(List<Meta> metas){
        return metas.stream().map(this::toMetaDTO).toList();
    }
}
