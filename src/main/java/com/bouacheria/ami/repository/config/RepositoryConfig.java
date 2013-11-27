package com.bouacheria.ami.repository.config;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;



@Configuration
@ImportResource("classpath:repository-config.xml")
public class RepositoryConfig {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ConfigUtil configUtil;

	
	/**
	 * Factory for Spring to configure the EntityManager.
	 * @return LocalContainerEntityManagerFactoryBean 
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaDialect(hibernateJpaDialect());
		entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		entityManagerFactory.setJpaProperties(getJpaProperties());
		entityManagerFactory.setPackagesToScan("com.bouacheria.ami.domain");
		return entityManagerFactory;
	}

	private Properties getJpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.show_sql", Boolean.TRUE);
		//jpaProperties.put("hibernate.format_sql", Boolean.TRUE);
		//jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		//jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		if(configUtil.hasHbm2ddl())
		{
			jpaProperties.put("hibernate.hbm2ddl.auto", configUtil.getHbm2ddl());
		}
		
		return jpaProperties;
	}
	
	@Bean
	public HibernateJpaDialect hibernateJpaDialect() {
		return new HibernateJpaDialect();
	}
	
	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}
	
	@PostConstruct
	public void printConfig()
	{
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> configureed");
		System.out.println(configUtil.toString());
	}
}
