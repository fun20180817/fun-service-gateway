package com.fun.util;

import org.apache.commons.codec.binary.Base64;

/**
 * ClassName: BaseUtils <br/>
 * date: 2018年7月26日 下午4:52:26 <br/>
 * 
 * @author lishuai11
 * @version
 * @since JDK 1.8
 */
public class BaseUtils {

	/**
	 * 
	 * encode:(加密). <br/> 
	 * 
	 * @author lishuai11 
	 * @param string
	 * @return 
	 * @since JDK 1.8
	 */
	public static String encode(String string) {
		byte[] b = string.getBytes();
		Base64 base64 = new Base64();
		b = base64.encode(b);
		String s = new String(b);
		return s;
	}

	/**
	 * 
	 * decode:(解密). <br/> 
	 * 
	 * @author lishuai11 
	 * @param string
	 * @return 
	 * @since JDK 1.8
	 */
	public static String decode(String string) {
		byte[] b = string.getBytes();
		Base64 base64 = new Base64();
		b = base64.decode(b);
		String s = new String(b);
		return s;
	}
}
