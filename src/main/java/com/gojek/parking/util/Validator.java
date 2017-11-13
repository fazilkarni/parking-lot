package com.gojek.parking.util;

import java.util.regex.Pattern;
/**
 * This class is used for various validations such as Registration number, color etc.
 * @author mkarni
 *
 */
public class Validator {
	private static String REG_NUM_PATTERN = "[a-zA-Z]{2}-[0-9]{2}-[a-zA-Z]{1}-[0-9]{4}";
	private static enum COLORS {RED,BLUE,WHITE,GREEN,GREY,BLACK,YELLOW,PINK,MAROON};
	/**
	 * This method does the validation of given RegNumber string. Here Current just checks for the pattern [a-zA-Z]{2}-[0-9]{2}-[a-zA-Z]{1}-[0-9]{6}
	 * This can be enhanced to add more validations here.
	 * @param regNumber
	 * @return
	 */
	public static boolean validateRegNumber(String regNumber){
		return Pattern.matches(REG_NUM_PATTERN, regNumber);
	}
	
	public static boolean validateColor(String color){
		
		for(COLORS colorEnum:COLORS.values()){
			if(colorEnum.toString().equalsIgnoreCase(color)) return true;
		}
		return false;
	}
	
	public static void main(String args[]){
		System.out.println(validateColor("RED"));
	}
}
