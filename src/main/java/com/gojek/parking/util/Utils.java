package com.gojek.parking.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotImpl;
import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.client.Command;
import com.gojek.parking.client.CreateParkingLotCommand;
import com.gojek.parking.client.LeaveCommand;
import com.gojek.parking.client.ParkCommand;
import com.gojek.parking.client.RegNumbersForCarsWithColorCommand;
import com.gojek.parking.client.SlotNumbersForCarsWithColorCommand;
import com.gojek.parking.client.StatusCommand;
import com.gojek.parking.vo.Slot;

public class Utils {
	
	//TODO make it generic, which takes array and comparator
	public static Slot getFreeSlot(List<Slot> slots){
		Slot freeSlot = null;
		for (Slot slot : slots) {
			if(!slot.getOccupied()){
				freeSlot = slot;break;
			}
		}
		return freeSlot;
	}
	
	//TODO try to merge above one and this.
	public static Slot getSlotByRegNumber(List<Slot> slots,String regNumber){
		Slot occupiedSlot = null;
		
		for (Slot slot : slots) {
			if(slot.getRegNumber()!=null && slot.getRegNumber().equals(regNumber)){
				occupiedSlot = slot; break;
			}
		}
		return occupiedSlot;
	}
	
	public static <T> ParkingLotResponse<T> constructResponse(List<T> data,List<String> errorMessages){
			//TODO: Send only the clone of the data. Dont send data as it might be corrupted from the client side.
			ParkingLotResponse<T>  response = new ParkingLotResponse<T>();
			response.setData(data);
			response.setStatus(data!=null?true:false);
			response.setErrors(errorMessages);
		    return response;
	}
	
	public static Map<String, Command> initAvailableCommands(){
		Map<String, Command> availableCommands = new HashMap<String,Command>();
		ParkingLot parkingLot = new ParkingLotImpl();
		
		availableCommands.put("create_parking_lot", new CreateParkingLotCommand(parkingLot));
		availableCommands.put("park", new ParkCommand(parkingLot));
		availableCommands.put("leave", new LeaveCommand(parkingLot));
		availableCommands.put("status", new StatusCommand(parkingLot));
		availableCommands.put("registration_numbers_for_cars_with_colour", new RegNumbersForCarsWithColorCommand(parkingLot));
		availableCommands.put("slot_numbers_for_cars_with_colour", new SlotNumbersForCarsWithColorCommand(parkingLot));
		
		
		return availableCommands;
	}

}
