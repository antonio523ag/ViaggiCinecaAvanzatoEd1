package dev.antoniogrillo.gestioneviaggi.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDTO {
    private String message;
    private LocalDateTime timestamp;
    private int code;
}
