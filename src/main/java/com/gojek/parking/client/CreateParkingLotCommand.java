package com.gojek.parking.client;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.vo.Slot;

/**
 * This class models/encapsulates the create parking lot command.
 * @author mkarni
 *
 */
public class CreateParkingLotCommand implements Command {
	private ParkingLot parkingLot;
	public CreateParkingLotCommand(ParkingLot parkingLot){
		this.parkingLot = parkingLot;
	}

	@Override
	public void execute(String[] args) {
		if(args.length<2){
			System.out.println("number of slots argument is missing. ");
		}else{
			ParkingLotResponse<Slot> response = parkingLot.createParkingLot(Integer.parseInt(args[1]));
			if(response.isStatus() && response.getData().size()>0){
				System.out.println("Created a parking lot with "+ response.getData().size() +" slots");
			}else{
				System.out.println("Internall Error. Please try other command.");
			}
		}
	}
}
