package dev.antoniogrillo.gestioneviaggi.service.def;

import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Optional;

public interface UtenteService {
    Optional<Utente> login(@NotBlank(message = "l'email deve essere inserita")
                 @Email(message = "deve essere un indirizzo email valido")
                 String email,
                   @NotBlank(message = "la password deve essere inserita")
                 String password);

    Utente salva(@Valid Utente utente);

    List<Utente> getUtenti(@Min(value = 0,message = "numeroPagina non può essere negativo") int numeroPagina);


    boolean deleteUtente(@Min(value = 1,message = "id deve essere maggiore o uguale a 1") long id);

    Optional<Utente> getUtenteById(@Min(value = 1,message = "id deve essere maggiore o uguale a 1") long id);
}
