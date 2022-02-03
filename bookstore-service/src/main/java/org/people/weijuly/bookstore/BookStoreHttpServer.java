package org.people.weijuly.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BookStoreHttpServer {

    @Autowired
    HttpHandler handler;
    WebServer webServer;
    @Value("${server.http.port}")
    private Integer port;

    @PostConstruct
    public void start() {
        ReactiveWebServerFactory factory = new NettyReactiveWebServerFactory(port);
        webServer = factory.getWebServer(handler);
        webServer.start();
    }

    @PreDestroy
    public void stop() {
        webServer.stop();
    }
}
