// package br.com.egotting.simple_api_restful_springboot.config;

// import javax.sql.DataSource;

// import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.transaction.PlatformTransactionManager;
// import
// org.springframework.transaction.annotation.EnableTransactionManagement;

// import jakarta.persistence.EntityManagerFactory;

// @Configuration
// @EnableTransactionManagement
// @EnableJpaRepositories("br.com.egotting.simple_api_restful_springboot.domain.Repositories")
// @EntityScan(basePackages =
// "br.com.egotting.simple_api_restful_springboot.domain.Entity")
// public class JpaConfig {

// @Bean
// LocalContainerEntityManagerFactoryBean
// entityManagerFactory(EntityManagerFactoryBuilder builder,
// DataSource datasource) {
// return builder
// .dataSource(datasource)
// .packages("br.com.egotting.simple_api_restful_springboot.domain")
// .persistenceUnit("myJpaUnit")
// .build();
// }

// @Bean
// PlatformTransactionManager transactionManager(EntityManagerFactory
// entityFactoryManager) {
// return new JpaTransactionManager(entityFactoryManager);
// }
// }
