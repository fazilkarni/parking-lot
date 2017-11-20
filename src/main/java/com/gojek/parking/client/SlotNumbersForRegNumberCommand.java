package com.gojek.parking.client;

import java.util.List;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.vo.Slot;

public class SlotNumbersForRegNumberCommand implements Command {
	private ParkingLot parkingLot;
	public SlotNumbersForRegNumberCommand(ParkingLot parkingLot){
		this.parkingLot = parkingLot;
	}

	@Override
	public void execute(String[] args) {
		ParkingLotResponse<Slot> response = parkingLot.getSlotByRegNumber(args[1]);
		List<Slot> slots = response.getData();
		String del="";
		System.out.println("Output:");
		if(response.isStatus() && slots.size()>0){
			for(Slot slot:slots){
				if(slot!=null){
					System.out.print(del+slot.getSlotNumber());
					if(del==""){
						del=", ";
					}
				}
			}
			
		}else{
			System.out.println("Not found");
		}
	}
}