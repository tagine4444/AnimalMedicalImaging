package com.bouacheria.ami.repository.config;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.bouacheria.ami.constants.AMIConstants;

@Component
public class ConfigUtil {
	
	
//	public final static boolean LOCAL_LOAD_DATA = false;
//	public final static boolean PROD_LOAD_DATA  = true;
	//public final static boolean LOCAL_EMAIL_ENABLED = false;
	
	@Autowired
	private Environment env;

	@Resource(name = "amiProperties")
	private Properties amiProperties;
	
	
	public Properties amiProperties() {
		return amiProperties;
	}

	
	public boolean hasHbm2ddl()
	{
		String hbm2ddl = getHbm2ddl();
		if(hbm2ddl!=null && hbm2ddl.length()>0 )
		{
			return true;
		}
		return false;
	}
	
	public String getHbm2ddl()
	{
		return env.getProperty("hbm2ddl");
	}
	
	public String getLocalEmailOn()
	{
		return env.getProperty("localemailon");
	}
	
	public boolean isLocalEmailOn()
	{
		String localEmailOn = getLocalEmailOn();
		return Boolean.parseBoolean(localEmailOn);
	}
	
	
	
	public String getActiveProfile()
	{
		return env.getProperty(AMIConstants.SPRING_ACT_PROFILE);
	}
	
	public boolean isLocalProfile()
	{
		String profile = env.getProperty(AMIConstants.SPRING_ACT_PROFILE);
		if(AMIConstants.PROFILE_LOCAL.equals(profile))
		{
			return true;
		}
		return false;
		
	}
	public boolean isEmailEnabled()
	{
		//set default for prod, should always be true in prod
		if(isLocalProfile())
		{
			return isLocalEmailOn();
			//return LOCAL_EMAIL_ENABLED;
		}
		return true;
	}
	
	
	public boolean isLoadData()
	{
		if(!hasHbm2ddl())
		{
			return false;
		}
		
		if( getHbm2ddl().toLowerCase().contains("create"))
		{
			return true;
		}
		return false;
		
			
//		if(isLocalProfile())
//		{
//			return LOCAL_LOAD_DATA ;
//		}
//		
//		return PROD_LOAD_DATA;
	}


	public String getAmiEmail() {
		return env.getProperty("ami.email");
		//return email;
	}
	
	public String getPdfFilesPath()
	{
		return this.amiProperties.getProperty("upload.pdftmp");
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("["+AMIConstants.SPRING_ACT_PROFILE+":"+ getActiveProfile()+" ]\n");
		sb.append("[Is Email Enabled :"+ isEmailEnabled()+" ]\n");
		sb.append("[Is Load Data     :"+ isLoadData()+" ]\n");
		sb.append("[Hbm2ddl          :"+ getHbm2ddl()+" ]\n");
		sb.append("[AMI Email        :"+ getAmiEmail()+" ]\n");
		
		return sb.toString();
	
	}

}
