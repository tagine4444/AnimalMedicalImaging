package com.bouacheria.ami.repository.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.bouacheria.ami.constants.AMIConstants;

@Configuration
@ComponentScan(basePackages="com.bouacheria.ami.repository" )
@Profile(AMIConstants.PROFILE_LOCAL)
public class MySqlRepoLocal {

	private static final Logger logger = LoggerFactory.getLogger(MySqlRepoLocal.class);
	
	@Resource(name = "amiProperties")
	private Properties amiProperties;
	
	@Bean
	public DataSource dataSource(){
		
		String user = (String)amiProperties.get("local.db.user");
		String pwd  = (String)amiProperties.get("local.db.pwd");
		String url  = (String)amiProperties.get("local.db.url");
		
		System.out.println("====>dburl:  " +url);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl(LoginController.getDbUrl());
//		dataSource.setUrl("jdbc:mysql://localhost:3306/amischema");
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(pwd);
		return dataSource;
	}
	
	
}
