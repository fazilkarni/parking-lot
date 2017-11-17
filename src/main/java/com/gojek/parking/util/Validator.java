package com.gojek.parking.util;

import java.util.List;
import java.util.regex.Pattern;
/**
 * This class is used for various validations such as Registration number, color etc.
 * @author mkarni
 *
 */
public class Validator {
	private static String REG_NUM_PATTERN = "[A-Z]{2}-[0-9]{2}-[A-Z]{1}-[0-9]{4}";
	private static enum COLORS {RED,BLUE,WHITE,GREEN,GREY,BLACK,YELLOW,PINK,MAROON};
	/**
	 * This method does the validation of given RegNumber string. Here Current just checks for the pattern [a-zA-Z]{2}-[0-9]{2}-[a-zA-Z]{1}-[0-9]{6}
	 * This can be enhanced to add more validations here.
	 * @param regNumber
	 * @return
	 */
	public static boolean validateRegNumber(String regNumber, List<String> errorMessages){
		
		boolean matched = Pattern.matches(REG_NUM_PATTERN, regNumber.toUpperCase());
		if(!matched){
			errorMessages.add("Registration number "+regNumber+" is not valid");
		}
		return matched;
	}
	
	public static boolean validateColor(String color,List<String> errorMessages){
		boolean matched = false;
		for(COLORS colorEnum:COLORS.values()){
			if(colorEnum.toString().equalsIgnoreCase(color)) {
				matched = true;
				break;
			}
		}
		
		if(!matched){
			errorMessages.add(color+" is not a valid colour");
		}

		return matched;
	}
}
