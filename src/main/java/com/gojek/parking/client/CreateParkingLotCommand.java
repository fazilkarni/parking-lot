package com.gojek.parking.client;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.vo.Slot;

public class CreateParkingLotCommand implements Command {
	private ParkingLot parkingLot;
	public CreateParkingLotCommand(ParkingLot parkingLot){
		this.parkingLot = parkingLot;
	}

	@Override
	public void execute(String[] args) {
		ParkingLotResponse<Slot> response = parkingLot.createParkingLot(Integer.parseInt(args[1]));
		if(response.isStatus() && response.getData().size()>0){
			System.out.println("Created a parking lot with "+ response.getData().size() +" slots");
		}else{
			System.out.println("Internall Error. Please try other command.");
		}
	}
}
