package dev.antoniogrillo.gestioneviaggi.facade.def;

import dev.antoniogrillo.gestioneviaggi.dto.internal.LoginResponseDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.LoginRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.RegistrazioneRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.UtenteDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;

import java.util.List;

public interface UtenteFacade {
    LoginResponseDTO login(LoginRequestDTO utente);

    Utente registraUtente(RegistrazioneRequestDTO utente);

    List<UtenteDTO> getUtenti(int numeroPagina);

    Utente registraAdmin(RegistrazioneRequestDTO utente);

    void deleteUtente(long id);
}
