package com.gojek.parking.bl;

import java.util.List;

import com.gojek.parking.vo.Slot;


/**
 * This Interface contains all the APIs to required to interact with the parking lot system.
 * @author karni.fazil
 *
 */
public interface ParkingLot {
	
	public List<Slot> createParkingLot(int slots);
	public Slot park(String regNumber,String color);
	public Slot leave(String regNumber);
	public List<Slot> getStatus();
	public List<String> getRegNumbersByColor(String color);
	public Integer getSlotByRegNumber(String regNumber);
	public List<Integer> getSlotNumsByColor(String color);
	
	/* Following are nice to have. These can be used in scenarios where more info is required.*/
	public List<Slot> getAllSlotsByCarColor(String color);
	public List<Slot> getAllSlotsByRegNumber(String regNumber);
	

}
