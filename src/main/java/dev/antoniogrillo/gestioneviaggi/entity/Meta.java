package dev.antoniogrillo.gestioneviaggi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Il nome della meta è obbligatorio")
    private String nome;

    @ManyToMany
    @NotNull(message = "La meta deve avere almeno una tipologia")
    @NotEmpty(message = "La meta deve avere almeno una tipologia")
    @JoinTable(name="meta_tipologie",
                            joinColumns = @JoinColumn(name = "meta_fk",nullable = false),
                            inverseJoinColumns = @JoinColumn(name = "tipologie_fk",nullable = false),
                            uniqueConstraints = @UniqueConstraint(columnNames = {"meta_fk","tipologie_fk"},name = "meta_tipologie_uc"))
    private List<TipologiaMeta> tipologie;

    @OneToMany(mappedBy = "meta")
    private List<RecensioneViaggio> recensioni;
}
