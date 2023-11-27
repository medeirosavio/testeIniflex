package io.github.medeirosavio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.medeirosavio", "springfox.documentation"})
public class testeIniflex {
    public static void main(String[] args) {
        SpringApplication.run(testeIniflex.class,args);
    }

    @Bean
    public CommandLineRunner initialization(){
        return args -> {
            System.out.println("API OK");
        };
    }
}
