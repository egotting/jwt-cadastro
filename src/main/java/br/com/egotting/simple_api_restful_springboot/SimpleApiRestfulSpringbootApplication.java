package br.com.egotting.simple_api_restful_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories(basePackages = "br.com.egotting.simple_api_restful_springboot.domain.Repositories")
public class SimpleApiRestfulSpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SimpleApiRestfulSpringbootApplication.class, args);
	}
}
