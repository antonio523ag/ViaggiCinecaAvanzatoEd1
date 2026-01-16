package dev.antoniogrillo.gestioneviaggi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class TipologiaMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Il nome della tipologia è obbligatorio")
    @Column(unique = true,nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "tipologie")
    private List<Meta> mete;

}
