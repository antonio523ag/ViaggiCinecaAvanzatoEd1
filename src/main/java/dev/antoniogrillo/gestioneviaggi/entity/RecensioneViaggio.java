package dev.antoniogrillo.gestioneviaggi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class RecensioneViaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate dataPartenza;
    private LocalDate dataRitorno;
    private int voto;
    private String descrizione;
    @ManyToOne
    @JoinColumn(name = "utente_fk",nullable = false)
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "meta_fk",nullable = false)
    private Meta meta;

}
