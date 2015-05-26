package com.sg.syj.common.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.authentication.encoding.PasswordEncoder;

/****
 * spring security 中使用自定义的加密方式
 * @author czm
 *
 */


public class MyPasswordEncode implements PasswordEncoder{

	@Override
	public String encodePassword(String password, Object salt) {
		String pass=null;
		try {
			pass=Md5encyptUtil.getEncryptedPwd(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return pass;
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		boolean valid=false;
		try {
			valid=Md5encyptUtil.validPassword(rawPass, encPass);
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}		
		if(valid){
			return true;
		}else{
			return false;
		}
	}

}
