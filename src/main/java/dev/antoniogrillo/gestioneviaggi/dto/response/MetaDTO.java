package dev.antoniogrillo.gestioneviaggi.dto.response;

import java.util.List;

public record MetaDTO(String nome, long id, List<TipologiaMetaDTO> tipologie) {
}
