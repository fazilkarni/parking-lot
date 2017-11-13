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
	public List<Slot> createParkingLot(int totalSlots) {
		if(totalSlots>0 && totalSlots<SLOTS_LIMIT){
			slots = new ArrayList<Slot>(totalSlots);
			for(int i=1;i<=totalSlots;i++){
				this.slots.add(new Slot(i));
			}
		}
		return this.slots;
	}

	public Slot park(String regNumber, String color) {
		Slot slot = null;
		if(Validator.validateRegNumber(regNumber) && Validator.validateColor(color)){
			if(slots==null) return null;
			slot = Utils.getFreeSlot(slots);
			if(slot!=null){
				slot.setRegNumber(regNumber);
				slot.setColor(color);
			}
		}	
		return slot;
	}

	public Slot leave(String regNumber) {
		Slot slot = Utils.getSlotByRegNumber(slots,regNumber);
		if(slot!=null){
			slot.setOccupied(false);
		}
		return slot;
	}

	public List<Slot> getStatus() {
		return slots;
	}

	public List<String> getRegNumbersByColor(String color) {
		
		return null;
	}

	public Integer getSlotByRegNumber(String regNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Integer> getSlotNumsByColor(String color) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Slot> getAllSlotsByCarColor(String color) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Slot> getAllSlotsByRegNumber(String regNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
