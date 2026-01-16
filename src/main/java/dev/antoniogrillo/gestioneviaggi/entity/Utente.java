package dev.antoniogrillo.gestioneviaggi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utente implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;
    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;
    @NotNull(message = "L'email è obbligatoria")
    @Email(message = "Deve essere un indirizzo email valido")
    private String email;
    @NotBlank(message = "La password è obbligatoria")
    private String password;
    @NotNull(message = "La data di nascita è obbligatoria")
    @Past(message = "La data di nascita deve essere nel passato")
    private LocalDate dataNascita;
    @NotNull(message = "Il ruolo è obbligatorio")
    private Ruolo ruolo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ruolo.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
