package dev.antoniogrillo.gestioneviaggi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "La data di partenza è obbligatoria")
    @Past(message = "La data di partenza deve essere nel passato")
    private LocalDate dataPartenza;
    @NotNull(message = "La data di ritorno è obbligatoria")
    @Past(message = "La data di ritorno deve essere nel passato")
    private LocalDate dataRitorno;
    @Size(min = 1,max = 5,message = "Il voto deve essere compreso tra 1 e 5")
    private int voto;
    @NotBlank(message = "La descrizione della recensione è obbligatoria")
    @Lob
    private String descrizione;
    @ManyToOne
    @JoinColumn(name = "utente_fk",nullable = false)
    @NotNull(message = "L'utente è obbligatorio")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "meta_fk",nullable = false)
    @NotNull(message = "La meta è obbligatoria")
    private Meta meta;

}
