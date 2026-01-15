package dev.antoniogrillo.gestioneviaggi.repository;

import dev.antoniogrillo.gestioneviaggi.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Optional<Utente> findByEmailAndPassword(String email, String password);

    Optional<Utente> findByEmail(String email);
}
