package com.gojek.parking.client;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.vo.Slot;

public class ParkCommand implements Command{
	
	private ParkingLot parkingLot;
	public ParkCommand(ParkingLot parkingLot){
		this.parkingLot = parkingLot;
	}

	@Override
	public void execute(String[] args) {
		ParkingLotResponse<Slot> response = parkingLot.park(args[1],args[2]);
		if(response.isStatus() && response.getData().size()>0){
			System.out.println("Allocated slot number: "+ response.getData().get(0).getSlotNumber());
		}else if(!response.isStatus()){
			System.out.println(response.getErrors().get(0));
		}
	}

}
