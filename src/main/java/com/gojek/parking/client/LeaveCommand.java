package com.gojek.parking.client;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.vo.Slot;

/**
 * This class models/encapsulates the leave parking lot command.
 * @author mkarni
 *
 */
public class LeaveCommand implements Command{
	
	private ParkingLot parkingLot;
	public LeaveCommand(ParkingLot parkingLot){
		this.parkingLot = parkingLot;
	}
	
	@Override
	public void execute(String[] args) {
		if(args.length<2){
			System.out.println("slot number is missing");
		}else if(Integer.parseInt(args[1])<1){
			System.out.println("slot number is invalid");
		}else{
			ParkingLotResponse<Slot> response = parkingLot.leave(Integer.parseInt(args[1])-1);//This needs to be changed to parking Lot id;
			if(response.isStatus() && response.getData().size()>0){
				System.out.println("Slot number "+ response.getData().get(0).getSlotNumber()+" is free ");
			}else{
				System.out.println(response.getErrors().get(0));//Here better iterate over the erros and print them all.
			}
		}
		
	}

}
