package org.people.weijuly.bookstore.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.SneakyThrows;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.Properties;
import java.util.Scanner;

@Configuration
public class BookStoreComponentProvider {

    @Autowired
    private ObjectMapper mapper;

    @Value("${graphql.server.url}")
    private String url;

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public GraphQLWebClient graphQLWebClient() throws SSLException {
        return GraphQLWebClient.newInstance(webClient(), mapper);
    }

    @Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine engine = new VelocityEngine();
        engine.init(properties());
        return engine;
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

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("resource.loader", "classpath");
        properties.put("classpath.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return properties;
    }

}
