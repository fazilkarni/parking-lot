package com.gojek.parking.bl;

import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.vo.Slot;




/**
 * This Interface contains all the APIs to required to interact with the parking lot system.
 * @author karni.fazil
 *
 */
public interface ParkingLot {
	
	public ParkingLotResponse<Slot,String> createParkingLot(int slots);
	public ParkingLotResponse<Slot,String>  park(String regNumber,String color);
	public ParkingLotResponse<Slot,String>  leave(String regNumber);
	public ParkingLotResponse<Slot,String>  getStatus();
	public ParkingLotResponse<Slot,String>  getRegNumbersByColor(String color);
	public ParkingLotResponse<Slot,String>  getSlotByRegNumber(String regNumber);
	public ParkingLotResponse<Slot,String>  getSlotNumsByColor(String color);
	
	/* Following are nice to have. These can be used in scenarios where more info is required.*/
	public ParkingLotResponse<Slot,String>  getAllSlotsByCarColor(String color);
	public ParkingLotResponse<Slot,String>  getAllSlotsByRegNumber(String regNumber);
	

}
