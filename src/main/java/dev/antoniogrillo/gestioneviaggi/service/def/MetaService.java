package dev.antoniogrillo.gestioneviaggi.service.def;

import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface MetaService {
    List<Meta> getMete(@Min(message = "Numero pagina non può essere negativo",value = 0) int numeroPagina);

    List<Meta> getMetePerTipologia(@Min(message = "Id tipologia non può essere negativo",value = 1) long idTipologia,@Min(message = "Numero pagina non può essere negativo",value = 0) int numeroPagina);

    Meta salva(@Valid Meta request);

    boolean elimina(@Min(message = "Id non può essere negativo",value = 1) long id);

    Optional<Meta> getMeta(@Min(message = "Id non può essere negativo",value = 1) long id);

    List<Meta> getMetePerIdUtente(@Min(message = "Numero pagina non può essere negativo",value = 0) int numeroPagina,@Min(message = "Id utente non può essere negativo",value = 1) long idUtente);
    List<Meta> getMeteNonVisitatePerIdUtente(@Min(message = "Numero pagina non può essere negativo",value = 0) int numeroPagina,@Min(message = "Id utente non può essere negativo",value = 1) long idUtente);

    Optional<Meta> getByNome(@NotBlank(message = "Nome non può essere vuoto") String nome);
}
