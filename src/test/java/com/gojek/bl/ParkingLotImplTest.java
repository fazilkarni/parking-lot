package com.gojek.bl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gojek.bl.data.ParkingLotDataProvider;
import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotImpl;
import com.gojek.parking.vo.Slot;

public class ParkingLotImplTest {
	
	@Test(dataProvider = "createParkingLotDataProvider", dataProviderClass = ParkingLotDataProvider.class)
	
	public void createParkingSlotsTest(Object[] data) {
	int expectedslots;	
	System.out.println(data);
	int totalslot = Integer.parseInt(data[0].toString());
	ParkingLot parking = new ParkingLotImpl();
	List<Slot> slots = parking.createParkingLot(totalslot);
		if(totalslot>0 && totalslot<1000000){
			expectedslots = Integer.parseInt(data[1].toString());
			assertEquals(slots.size(),expectedslots);
		}else
			assertTrue(slots==null);

	}
}
