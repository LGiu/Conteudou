package br.com.conteudou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ConteudouApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConteudouApplication.class, args);
    }

}
