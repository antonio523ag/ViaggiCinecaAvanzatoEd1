package dev.antoniogrillo.gestioneviaggi.dto.request;

import java.util.List;

public record AggiungiMetaDTO(String nome, List<Long> idTipologie) {
}
