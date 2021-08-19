package ru.kuryakin.meteo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
public class MeteoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeteoApplication.class, args);
	}

}
