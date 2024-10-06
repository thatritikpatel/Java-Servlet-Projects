package utils;

import java.util.Random;

public class OTPGenerator{
	static Random r = new Random();

	public static String newOTP(){
		StringBuffer sb = new StringBuffer();

		for(int i=0;i<6;i++){
			sb.append(r.nextInt(9)+1);
		}

		return sb.toString();
	}
}