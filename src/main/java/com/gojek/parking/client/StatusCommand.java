package com.gojek.parking.client;

import java.util.List;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.vo.Slot;

public class StatusCommand implements Command{

	private ParkingLot parkingLot;
	public StatusCommand(ParkingLot parkingLot){
		this.parkingLot = parkingLot;
	}
	
	@Override
	public void execute(String[] args) {
		ParkingLotResponse<Slot> response = parkingLot.getStatus();
		List<Slot> slots = response.getData();
		if(response.isStatus() && slots.size()>0){
			System.out.println("Output (tab delimited output):");
			System.out.println("Slot No\tRegistration No.\tColour");
			for(Slot slot:slots){
				System.out.println(slot.getSlotNumber()+"\t"+ slot.getRegNumber()+"\t"+slot.getColor());
			}
		}else{
			System.out.println("Internall Error. Please try other command.");
		}
		
	}
	
	

}
