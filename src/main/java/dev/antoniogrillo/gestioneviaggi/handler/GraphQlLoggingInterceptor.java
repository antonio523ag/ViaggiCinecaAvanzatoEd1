package dev.antoniogrillo.gestioneviaggi.handler;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GraphQlLoggingInterceptor implements WebGraphQlInterceptor {

   @Override
    public Mono<WebGraphQlResponse> intercept(
            WebGraphQlRequest request,
            Chain chain) {

        return chain.next(request)
                .doOnNext(response -> {
                    if (!response.getErrors().isEmpty()) {
                        response.getErrors().forEach(e ->System.out.println(e.getMessage())

                        );
                    }
                });
    }
}
