package com.gojek.parking.util;

import java.util.List;

import com.gojek.parking.bl.impl.ParkingLotResponse;
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

}
