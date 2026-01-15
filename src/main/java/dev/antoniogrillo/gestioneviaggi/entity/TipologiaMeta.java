package dev.antoniogrillo.gestioneviaggi.entity;

import jakarta.persistence.*;
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
    @Column(unique = true,nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "tipologie")
    private List<Meta> mete;

}
