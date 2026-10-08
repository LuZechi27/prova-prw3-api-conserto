package br.edu.ifsp.prw3.prova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ProvaApplication {

	static void main(String[] args) {
		SpringApplication.run(ProvaApplication.class, args);
	}

}
