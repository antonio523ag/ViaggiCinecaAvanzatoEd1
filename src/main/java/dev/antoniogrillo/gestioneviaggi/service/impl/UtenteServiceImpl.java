package dev.antoniogrillo.gestioneviaggi.service.impl;

import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import dev.antoniogrillo.gestioneviaggi.repository.UtenteRepository;
import dev.antoniogrillo.gestioneviaggi.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository repo;

    @Override
    public Optional<Utente> login(String email, String password) {
        return repo.findByEmailAndPassword(email,password);
    }

    @Override
    public Utente salva(Utente utente) {
        return repo.save(utente);
    }

    @Override
    public List<Utente> getUtenti(int numeroPagina) {
        Sort s= Sort.by("cognome").ascending().and(Sort.by("nome").ascending());
        Pageable p= PageRequest.of(numeroPagina,10,s);
        return repo.findAll(p).getContent();
    }

    @Override
    public boolean deleteUtente(long id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Utente> getUtenteById(long id) {
        return repo.findById(id);
    }
}
