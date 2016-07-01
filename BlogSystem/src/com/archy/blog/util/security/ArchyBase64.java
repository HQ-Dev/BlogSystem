package com.archy.blog.util.security;

public class ArchyBase64 {

	public static String encode(String content) {
		byte[] encodeBytes = java.util.Base64.getEncoder().encode(content.getBytes());
		return  new String(encodeBytes);
	}
	
	public static String decode(String content) {
		byte[] decodeBytes = java.util.Base64.getDecoder().decode(content.getBytes());
		return new String(decodeBytes);
	}
}
