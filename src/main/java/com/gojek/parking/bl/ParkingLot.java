package com.gojek.parking.bl;

import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.vo.Slot;




/**
 * This Interface contains all the APIs to required to interact with the parking lot system.
 * @author karni.fazil
 *
 */
public interface ParkingLot {
	
	public ParkingLotResponse<Slot> createParkingLot(int slots);
	public ParkingLotResponse<Slot>  park(String regNumber,String color);
	public ParkingLotResponse<Slot>  leave(String regNumber);
	public ParkingLotResponse<Slot>  leave(Integer slotNumber);
	public ParkingLotResponse<Slot>  getStatus();
	public ParkingLotResponse<String>  getRegNumbersByColor(String color);
	public ParkingLotResponse<Integer>  getSlotNumsByColor(String color);
	public ParkingLotResponse<Integer>  getSlotNumsByregNumber(String regNumber);
	public ParkingLotResponse<Slot>  getSlotByRegNumber(String regNumber);

}
