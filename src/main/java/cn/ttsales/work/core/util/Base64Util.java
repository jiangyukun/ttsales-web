package cn.ttsales.work.core.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	
	public static String EncoderBase64(String src) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(src.getBytes());
	}
	
	public static String DecoderBase64(String src) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return new String(decoder.decodeBuffer(src));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Base64Util.EncoderBase64("u_b_i=66ec20bbe3284983ac8c04c6803a14aa&v=11"));
	}
}
