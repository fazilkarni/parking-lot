package com.gojek.parking.client;

import java.util.List;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotResponse;

public class SlotNumbersForCarsWithColorCommand implements Command {
	private ParkingLot parkingLot;
	public SlotNumbersForCarsWithColorCommand(ParkingLot parkingLot){
		this.parkingLot = parkingLot;
	}

	@Override
	public void execute(String[] args) {
		ParkingLotResponse<Integer> response = parkingLot.getSlotNumsByColor(args[1]);
		List<Integer> regNumbers = response.getData();
		String del="";
		//System.out.println("Output:");
		if(response.isStatus() && regNumbers.size()>0){
			for(Integer regNumber:regNumbers){
				System.out.print(del+regNumber);
				if(del==""){
					del=", ";
				};
			}
			System.out.println("");
			
		}else{
			System.out.println("No cars with the color "+args[1]+" are parked");
		}
	}
}