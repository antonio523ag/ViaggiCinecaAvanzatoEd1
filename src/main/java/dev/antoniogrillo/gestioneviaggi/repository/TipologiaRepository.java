package dev.antoniogrillo.gestioneviaggi.repository;

import dev.antoniogrillo.gestioneviaggi.entity.TipologiaMeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipologiaRepository extends JpaRepository<TipologiaMeta, Long> {
    Optional<TipologiaMeta> findByNome(String nome);
}
