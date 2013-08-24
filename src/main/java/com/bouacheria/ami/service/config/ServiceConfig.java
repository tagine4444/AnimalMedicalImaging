package com.bouacheria.ami.service.config;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:ami.properties")
@ImportResource({"classpath:property-resources.xml"})
public class ServiceConfig {


	@Bean
	@Resource(name = "entityManagerFactory")
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}
}
