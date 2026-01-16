package dev.antoniogrillo.gestioneviaggi.mapper;

import dev.antoniogrillo.gestioneviaggi.dto.response.MessageDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageMapper {

    public MessageDTO toMessageDTO(String message,int code){
        MessageDTO dto = new MessageDTO();
        dto.setMessage(message);
        dto.setTimestamp(LocalDateTime.now());
        dto.setCode(code);
        return dto;
    }
}
