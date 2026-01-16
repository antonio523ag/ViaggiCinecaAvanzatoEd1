package dev.antoniogrillo.gestioneviaggi.handler;

import dev.antoniogrillo.gestioneviaggi.dto.response.MessageDTO;
import dev.antoniogrillo.gestioneviaggi.mapper.MessageMapper;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomHandler {
    private final MessageMapper mapper;


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<MessageDTO> handlerSQLIntegrityViolation(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mapper.toMessageDTO(e.getMessage(),409));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handlerMethodArgumentNotValid(MethodArgumentNotValidException e){
        Map<String,String> errors=e.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageDTO> handlerConstraintDeclarationException(ConstraintViolationException e){
        return ResponseEntity.badRequest().body(mapper.toMessageDTO(e.getMessage(),400));
    }
}
