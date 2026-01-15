package dev.antoniogrillo.gestioneviaggi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

    @ManyToMany
    @JoinTable(name="meta_tipologie",
                            joinColumns = @JoinColumn(name = "meta_fk",nullable = false),
                            inverseJoinColumns = @JoinColumn(name = "tipologie_fk",nullable = false),
                            uniqueConstraints = @UniqueConstraint(columnNames = {"meta_fk","tipologie_fk"},name = "meta_tipologie_uc"))
    private List<TipologiaMeta> tipologie;

    @OneToMany(mappedBy = "meta")
    private List<RecensioneViaggio> recensioni;
}
