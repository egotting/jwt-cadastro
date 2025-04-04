// package br.com.egotting.simple_api_restful_springboot.config;

// import org.flywaydb.core.Flyway;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import lombok.RequiredArgsConstructor;

// @Configuration
// @RequiredArgsConstructor
// public class FlywayConfig {

// private final org.springframework.core.env.Environment environment;

// @Bean(initMethod = "migrate")
// Flyway flyway() {
// return new Flyway(Flyway.configure()
// .baselineOnMigrate(true)
// .dataSource(
// environment.getRequiredProperty("spring.datasource.url"),
// environment.getRequiredProperty("spring.datasource.username"),
// environment.getRequiredProperty("spring.datasource.password")));
// }
// }
