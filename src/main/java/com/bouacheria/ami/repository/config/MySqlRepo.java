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
@Profile(AMIConstants.PROFILE_DEFAULT)
public class MySqlRepo {

	private static final Logger logger = LoggerFactory.getLogger(MySqlRepoLocal.class);
	
	@Resource(name = "amiProperties")
	private Properties amiProperties;
	
	@Bean
	public DataSource dataSource(){
		
		String user = (String)amiProperties.get("db.user");
		String pwd  = (String)amiProperties.get("db.pwd");
		String url  = (String)amiProperties.get("db.url");
		
		logger.info("====>dburl:  " +url);
		
		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(pwd);
//		dataSource.setPartitionCount(1);
		dataSource.setAcquireIncrement(2);
		dataSource.setMaxConnectionsPerPartition(10);
		dataSource.setMinConnectionsPerPartition(2);
		dataSource.setStatementsCacheSize(2);		
		
		return dataSource;
	}
	
}
