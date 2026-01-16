package dev.antoniogrillo.gestioneviaggi.handler;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {

        if (ex instanceof EntityNotFoundException) {
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(ErrorType.NOT_FOUND)
                    .build();
        }


        if (ex instanceof IllegalArgumentException) {
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();
        }
        if(ex instanceof GraphQLException e){
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(e.getCode())
                    .build();
        }

        if(ex instanceof UnexpectedTypeException e){
            return GraphqlErrorBuilder.newError(env)
                    .message(e.getMessage())
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();
        }
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getClass()+" "+ex.getMessage())
                .errorType(ErrorType.INTERNAL_ERROR)
                .build();
    }
}
