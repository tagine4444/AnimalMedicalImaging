package com.bouacheria.ami.service.encryption;

import java.security.NoSuchAlgorithmException;

public interface EncryptorService {

	String encrypt(String password);

	boolean isValid(String pwd1, String pwd2) throws NoSuchAlgorithmException;

}
