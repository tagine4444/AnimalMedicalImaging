package com.bouacheria.ami.service.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class SHAEncryptorServiceImpl implements EncryptorService {

	@Override
	public String encrypt(String password)
    {
		try 
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
		 
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) 
	        {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        return sb.toString();
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
	
	@Override
	public boolean isValid(String pwd1, String pwd2 ) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(pwd1.getBytes());
        byte[] pwd1Byte = md.digest();
        
        md.update(pwd2.getBytes());
        byte[] pwd2Byte = md.digest();
        
		return MessageDigest.isEqual(pwd1Byte, pwd2Byte);
	}
}
