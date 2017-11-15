package com.gojek.parking.bl.impl;

import java.util.ArrayList;
import java.util.List;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.util.Utils;
import com.gojek.parking.util.Validator;
import com.gojek.parking.vo.Slot;

/**
 * This is the implementation of ParkingLot interface.
 * @author karni.fazil
 *TODO: think of how to indicate user about error conditions.
 */


public class ParkingLotImpl implements ParkingLot{
	private int SLOTS_LIMIT=99999;
	private List<Slot> slots;
	
	public ParkingLotResponse<Slot,String>  createParkingLot(int totalSlots) {
		ParkingLotResponse<Slot,String>  response = new ParkingLotResponse<Slot,String>();
		if(totalSlots>0 && totalSlots<SLOTS_LIMIT){
			slots = new ArrayList<Slot>(totalSlots);
			for(int i=1;i<=totalSlots;i++){
				this.slots.add(new Slot(i));
			}
		    response.setData(slots);
			response.setStatus(true);
		}else{
			//Construct error response.
			response.setStatus(false);
			ArrayList<String> errorMessages = new ArrayList<String>();
			errorMessages.add("Total slots to be created should be more than 0 and less than max limit "+SLOTS_LIMIT);
		}
		return response;
	}

	public ParkingLotResponse<Slot,String> park(String regNumber, String color) {
		Slot slot = null;
		ParkingLotResponse<Slot,String>  response = new ParkingLotResponse<Slot,String>();
		if(Validator.validateRegNumber(regNumber,response) && Validator.validateColor(color,response)){
			
			if(slots==null){
				response.setStatus(false);
				return response;
			}else{
				slot = Utils.getFreeSlot(slots);
				if(slot!=null){
					slot.setRegNumber(regNumber);
					slot.setColor(color);
					slot.setOccupied(true);
					List<Slot> data = new ArrayList<Slot>();
					data.add(slot);
					response.setData(data);
					response.setStatus(true);
				}
			}
		}	
		return response;
	}

	public ParkingLotResponse<Slot,String> leave(String regNumber) {
		Slot slot = Utils.getSlotByRegNumber(slots,regNumber);
		if(slot!=null){
			slot.setOccupied(false);
		}
		ParkingLotResponse<Slot,String>  response = new ParkingLotResponse<Slot,String>();
		List<Slot> slots = new ArrayList<Slot>();
		slots.add(slot);
		response.setData(slots);
		response.setStatus(true);
		return response;
	}

	public ParkingLotResponse<Slot,String> getStatus() {
		return null;
	}

	public ParkingLotResponse<Slot,String> getRegNumbersByColor(String color) {
		
		return null;
	}

	public ParkingLotResponse<Slot,String> getSlotByRegNumber(String regNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public ParkingLotResponse<Slot,String> getSlotNumsByColor(String color) {
		// TODO Auto-generated method stub
		return null;
	}

	public ParkingLotResponse<Slot,String> getAllSlotsByCarColor(String color) {
		// TODO Auto-generated method stub
		return null;
	}

	public ParkingLotResponse<Slot,String> getAllSlotsByRegNumber(String regNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
