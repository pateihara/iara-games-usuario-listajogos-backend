package br.com.iaragames.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("br.com.iaragames.beans")  // Certifica que o Spring encontra as entidades
@EnableJpaRepositories("br.com.iaragames.dao") // Certifica que encontra os repositórios
@ComponentScan(basePackages = "br.com.iaragames") // Garante que escaneia todos os componentes
public class LojaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LojaApplication.class, args);
    }
}
