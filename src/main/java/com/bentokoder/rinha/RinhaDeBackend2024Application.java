package com.bentokoder.rinha;

import com.bentokoder.rinha.entidade.Cliente;
import com.bentokoder.rinha.repositorio.ClienteRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RinhaDeBackend2024Application {

	public static void main(String[] args) {
		SpringApplication.run(RinhaDeBackend2024Application.class, args);
	}

	@Bean
	CommandLineRunner run(ClienteRepositorio repositorio){
		return (arg) -> {
			repositorio.save(new Cliente(1, 100000, 0));
		};
	}

}
