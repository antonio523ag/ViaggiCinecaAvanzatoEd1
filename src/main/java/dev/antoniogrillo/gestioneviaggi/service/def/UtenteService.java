package dev.antoniogrillo.gestioneviaggi.service.def;

import dev.antoniogrillo.gestioneviaggi.dto.internal.LoginResponseDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.LoginRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.request.RegistrazioneRequestDTO;
import dev.antoniogrillo.gestioneviaggi.dto.response.UtenteDTO;
import dev.antoniogrillo.gestioneviaggi.entity.Utente;

import java.util.List;

public interface UtenteService {
    LoginResponseDTO login(LoginRequestDTO utente);

    Utente registraUtente(RegistrazioneRequestDTO utente);

    List<UtenteDTO> getUtenti(int numeroPagina);

    Utente registraAdmin(RegistrazioneRequestDTO utente);

    UtenteDTO deleteUtente(long id);
}
