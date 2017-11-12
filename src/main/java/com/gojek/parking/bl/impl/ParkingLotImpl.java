package com.gojek.parking.bl.impl;

import java.util.ArrayList;
import java.util.List;

import com.gojek.parking.bl.ParkingLot;
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
			for(int i=0;i<totalSlots;i++){
				this.slots.add(new Slot(i));
			}
		}
		return this.slots;
	}

	public Slot park(String regNumber, String color) {
		// TODO Auto-generated method stub
		return null;
	}

	public Slot leave(String regNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Slot> getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getRegNumbersByColor(String color) {
		// TODO Auto-generated method stub
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
