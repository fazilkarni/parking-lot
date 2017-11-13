package com.gojek.parking.util;

import java.util.List;

import com.gojek.parking.vo.Slot;

public class Utils {
	
	//TODO make it generic, which takes array and comparator
	public static Slot getFreeSlot(List<Slot> slots){
		Slot freeSlot = null;
		int totalSlots = slots.size();
		for(int i=0;i<totalSlots;i++){
			if(!slots.get(i).getOccupied()){
				freeSlot = slots.get(i);
			}
		}
		return freeSlot;
	}
	
	//TODO try to merge above one and this.
	public static Slot getSlotByRegNumber(List<Slot> slots,String regNumber){
		Slot occupiedSlot = null;
		int totalSlots = slots.size();
		for(int i=0;i<totalSlots;i++){
			if(slots.get(i).getRegNumber()!=null && slots.get(i).getRegNumber().equals(regNumber)){
				occupiedSlot = slots.get(i);
			}
		}
		return occupiedSlot;
	}

}
