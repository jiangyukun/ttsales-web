/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename TestCode.java
 * @package cn.ttsales.work.web
 * @author dandyzheng
 * @date 2014年12月3日
 */
package cn.ttsales.work.web.bus;

import java.util.Random;

/**
 * @author dandyzheng
 *
 */
public class TestVerifyCode {

	/**
	 * @param args
	 * @author dandyzheng
	 * @date 2014年12月3日
	 * @see
	 */
	public static void main(String[] args) {
		Random rd = new Random();
		String n = "";
		int num1;
		int numA;
		do {
			num1 = Math.abs(rd.nextInt()) % 10 + 48;
			numA = Math.abs(rd.nextInt())%26 + 65;
			num1 = rd.nextInt(2) == 0 ? num1 : numA;
			char numr = (char) num1;
			String dn = Character.toString(numr);
			n += dn;
		} while (n.length() < 10);

		System.out.println(n);
	}

}
