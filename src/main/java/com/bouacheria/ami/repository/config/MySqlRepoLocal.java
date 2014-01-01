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

import com.bouacheria.ami.constants.AMIConstants;
import com.jolbox.bonecp.BoneCPDataSource;

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

		logger.info("====>dburl:  " +url);
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(pwd);
		dataSource.setPartitionCount(3);
		dataSource.setAcquireIncrement(5);
		dataSource.setMaxConnectionsPerPartition(20);
		dataSource.setMinConnectionsPerPartition(5);
		dataSource.setStatementsCacheSize(5);		
		
		System.out.println("====>dburl:  datasource done" );
		return dataSource;
	}
	
	
}
