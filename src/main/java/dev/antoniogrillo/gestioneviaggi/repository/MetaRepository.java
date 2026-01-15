package dev.antoniogrillo.gestioneviaggi.repository;

import dev.antoniogrillo.gestioneviaggi.entity.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetaRepository extends JpaRepository<Meta, Long> {
    Optional<Meta> findByNome(String nome);
}
