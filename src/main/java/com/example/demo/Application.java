package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.demo.model.User;
import com.example.demo.service.UserHttpClient;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Application has started successfully!");
    }

    @Bean
    UserHttpClient userHttpClient() {
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(UserHttpClient.class);
    }

    @Bean
    CommandLineRunner runner(UserHttpClient userHttpClient) {
        return args -> {
            List<User> users = userHttpClient.findAll();
            System.out.println(users);
        };
    }

}
