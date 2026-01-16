package dev.antoniogrillo.gestioneviaggi.service.def;

import dev.antoniogrillo.gestioneviaggi.entity.RecensioneViaggio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface RecensioneViaggioService {
    Optional<RecensioneViaggio> getRecensione(@Min(value = 1,message = "id deve essere maggiore o uguale a 1") long id);

    List<RecensioneViaggio> getRecensioniUtente(@Min(value = 1,message = "id deve essere maggiore o uguale a 1") long idUtente,@Min(value = 0,message = "numeroPagina deve essere maggiore o uguale a 0") int numeroPagina);

    List<RecensioneViaggio> getRecensioniMeta(@Min(value = 1,message = "id deve essere maggiore o uguale a 1") int idMeta,@Min(value = 0,message = "numeroPagina deve essere maggiore o uguale a 0") int numeroPagina);

    RecensioneViaggio salva(@Valid RecensioneViaggio request);

    boolean elimina(@Min(value = 1,message = "id deve essere maggiore o uguale a 1") long id);
}
