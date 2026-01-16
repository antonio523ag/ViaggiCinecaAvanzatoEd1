package dev.antoniogrillo.gestioneviaggi.service.def;

import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface TipologiaMetaService {
    List<TipologiaMeta> getTipologie(@Min(value = 0,message = "numeroPagina non può essere negativo") int numeroPagina);

    TipologiaMeta aggiungi(@Valid TipologiaMeta request);

    Optional<TipologiaMeta> getTipologiaById(@Min(value = 1,message = "id deve essere maggiore o uguale a 1") long id);

    List<TipologiaMeta> findAllByIds(@NotNull(message = "Lista di id non può essere nulla") @NotEmpty(message = "Lista di id non può essere vuota") List<Long> longs);

}
