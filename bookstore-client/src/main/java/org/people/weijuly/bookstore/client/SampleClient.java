package org.people.weijuly.bookstore.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.SneakyThrows;
import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.model.BooksModel;
import org.people.weijuly.bookstore.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.io.IOException;

@Component
public class SampleClient {

    @Autowired
    private ResourceUtil resourceUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${graphql.server.url}")
    private String url;

    public void call() {
        try {
            String query = resourceUtil.getResourceAsString("query/searchBookByTag.graphqls");
            GraphQLRequest request = GraphQLRequest.builder().query(query).build();
            GraphQLResponse response = graphQLWebClient().post(request).block();
            BooksModel booksModel = response.get("searchBooksByTag", BooksModel.class);
            for (BookModel bookModel : booksModel.getBooks()) {
                System.out.println(bookModel.getId());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Bean
    public GraphQLWebClient graphQLWebClient() throws SSLException {
        return GraphQLWebClient.newInstance(webClient(), objectMapper);
    }

    private WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl(url)
                .clientConnector(new ReactorClientHttpConnector(httpClient()))
                .build();
    }

    private HttpClient httpClient() {
        return HttpClient
                .create()
                .secure(t -> t.sslContext(noVerifySSLContext()));
    }

    @SneakyThrows
    private SslContext noVerifySSLContext() {
        return SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
    }
}
