package br.uece.eesdevops.amazingmovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AmazingmoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazingmoviesApplication.class, args);
	}

}
