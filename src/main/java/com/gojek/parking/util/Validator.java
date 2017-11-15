package com.gojek.parking.util;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.vo.Slot;
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
	public static boolean validateRegNumber(String regNumber, ParkingLotResponse<Slot,String> response){
		
		boolean matched = Pattern.matches(REG_NUM_PATTERN, regNumber);
		if(!matched){
			setErrorMessage(response, "Registration number "+regNumber+" is not valid");
		}
		return matched;
	}
	
	public static boolean validateColor(String color,ParkingLotResponse<Slot,String> response){
		boolean matched = false;
		for(COLORS colorEnum:COLORS.values()){
			if(colorEnum.toString().equalsIgnoreCase(color)) {
				matched = true;
				break;
			}
		}
		
		if(!matched){
			setErrorMessage(response, color+" is not a valid colour");
		}

		return matched;
	}
	
	private static void setErrorMessage(ParkingLotResponse<Slot,String> response, String errorMessage){
		response.setStatus(false);
		if(response.getErrors()==null)
			response.setErrors((new ArrayList<String>()));
		response.getErrors().add(errorMessage);
	}
}
