package utils;

import java.util.*;

public class RandomCodeGenerator{
	public static String activationCode(){
		String r1 = Long.toString(new Random().nextLong());
		String r2 = Long.toString(new Date().getTime());
		String activationCode = r1+"_"+r2;

		return activationCode;
	}
}