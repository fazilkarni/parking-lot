package com.gojek.parking.client;

import java.util.List;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.vo.Slot;

public class RegNumbersForCarsWithColorCommand implements Command {
	private ParkingLot parkingLot;
	public RegNumbersForCarsWithColorCommand(ParkingLot parkingLot){
		this.parkingLot = parkingLot;
	}

	@Override
	public void execute(String[] args) {
		ParkingLotResponse<String> response = parkingLot.getRegNumbersByColor(args[1]);
		List<String> regNumbers = response.getData();
		String del="";
		//System.out.println("Output:");
		if(response.isStatus() && regNumbers.size()>0){
			for(String regNumber:regNumbers){
				System.out.print(del+regNumber);
				if(del==""){
					del=", ";
				};
			}
			System.out.println();
			
		}else{
			System.out.println("No cars with the color "+args[1]+" are parked");
		}
	}
}
