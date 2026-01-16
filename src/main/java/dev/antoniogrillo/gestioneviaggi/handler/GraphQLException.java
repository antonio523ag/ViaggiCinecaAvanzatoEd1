package dev.antoniogrillo.gestioneviaggi.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.graphql.execution.ErrorType;

@Getter
@Setter
public class GraphQLException extends RuntimeException{
    private ErrorType code;
    public GraphQLException(String message, ErrorType code) {
        super(message);
        this.code = code;
    }
}
