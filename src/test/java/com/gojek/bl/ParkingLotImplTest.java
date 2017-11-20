package com.gojek.bl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gojek.bl.data.ParkingLotDataProvider;
import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotImpl;
import com.gojek.parking.bl.impl.ParkingLotResponse;
import com.gojek.parking.vo.Slot;

public class ParkingLotImplTest {
	private ParkingLot parking;
	private ParkingLot parkingForStatus;
	private Boolean[] parkingLotCurrentStatus;

	@BeforeMethod
	public void setUp() {
		// System.out.println("created");
		parking = ParkingLotImpl.getInstance();
		parkingForStatus = parking;
	}

	//TODO: try to initialize this object when when required. Make it group specific.
	@BeforeClass
	public void beforeTest() {
		//parkingForStatus = ParkingLotImpl.getInstance();

	}

	@Test(dataProvider = "createParkingLotDataProvider", dataProviderClass = ParkingLotDataProvider.class)
	public void createParkingSlotsTest(Object[] data) {
		int expectedslots;
		System.out.println(data);
		int totalslot = Integer.parseInt(data[0].toString());
		ParkingLotResponse<Slot> response = parking.createParkingLot(totalslot);
		if (totalslot > 0 && totalslot < 99999) {
			expectedslots = Integer.parseInt(data[1].toString());
			assertTrue(response.isStatus());
			assertEquals(response.getData().size(), expectedslots);

		} else {
			assertFalse(response.isStatus());
			assertEquals(response.getData(), null);
			assertTrue(response.getErrors() != null);
			assertTrue(response.getErrors().get(0)
					.equals("Total slots to be created should be more than 0 and less than max limit " + 99999));
		}

	}

	@Test(dataProvider = "parkDataProvider", dataProviderClass = ParkingLotDataProvider.class)
	public void parkTest(Object[] data) {
		// This is the dependency.
		parking.createParkingLot(1);
		ParkingLotResponse<Slot> response = parking.park((String) data[0], (String) data[1]);
		if (data[2] != null && ((String) data[2]).contains("occupied=true")) {
			assertTrue(response != null);
			assertTrue(response.isStatus());
			assertTrue(response.getData().get(0).getOccupied());
			assertTrue(response.getData().get(0).getColor().equals((String) data[1]));
			assertTrue(response.getData().get(0).getRegNumber().equals((String) data[0]));
			assertTrue(response.getData().get(0).getSlotNumber() > 0);
		} else {
			assertTrue(response != null);
			assertFalse(response.isStatus());
			assertTrue(response.getErrors().get(0).equals("Registration number " + (String) data[0] + " is not valid"));
		}
	}

	@Test(dataProvider = "leaveDataProvider", dataProviderClass = ParkingLotDataProvider.class)
	public void leaveTest(Object[] data) {

		// This is the prerequisite.
		parking.createParkingLot(1);
		parking.park("KA-51-ZA-1990", "GREEN");
		ParkingLotResponse<Slot> response = parking.leave((String) data[0]);
		if (data[1] != null && ((String) data[1]).contains("occupied=false")) {
			assertTrue(response != null);
			assertTrue(response.isStatus());
			assertFalse(response.getData().get(0).getOccupied());
			assertTrue(response.getData().get(0).getColor() == null);
			assertTrue(response.getData().get(0).getRegNumber() == null);
		} else {
			assertTrue(response != null);
			assertFalse(response.isStatus());
			assertTrue(response.getErrors().get(0).equals((String) data[1]));
		}
	}

	@Test(dependsOnMethods = "statusPrerequisite", dataProvider = "statusPrerequisite", dataProviderClass = ParkingLotDataProvider.class)
	public void statusTest(Object[] data) {
		ParkingLotResponse<Slot> response = parkingForStatus.getStatus();
		List<Slot> slots= response.getData();
		assertTrue(slots!=null);
		assertTrue(slots.size()>0);
		
		//This loop checks is the PL status from the application is same as the parkingLotCurrentStatus, computed by the test method.
		for(Slot slot:slots){
			assertTrue(slot.getOccupied() == parkingLotCurrentStatus[slot.getSlotNumber()-1]);
		}
	}

	
	@Test(dataProvider = "statusPrerequisite", dataProviderClass = ParkingLotDataProvider.class)
	public void statusPrerequisite(Object[] data) {
		String[] inputTokens;
		ParkingLotResponse<Slot> response = null;
		switch ((String) data[1]) {
		case "createParkingLot":
			parkingForStatus.createParkingLot(Integer.parseInt((String) data[0]));
			parkingLotCurrentStatus = new Boolean[Integer.parseInt((String) data[0])];
			break;
		case "park":
			inputTokens = ((String) data[0]).split(":");
			response = parkingForStatus.park(inputTokens[0], inputTokens[1]);
			if(response!=null&&response.getData()!=null && response.getData().get(0).getSlotNumber()>0){
				parkingLotCurrentStatus[response.getData().get(0).getSlotNumber()-1]=true;
			}
			break;
		case "leave":
			response = parkingForStatus.leave((String) data[0]);
			if(response!=null&&response.getData()!=null && response.getData().get(0).getSlotNumber()>0){
				parkingLotCurrentStatus[response.getData().get(0).getSlotNumber()-1]=false;
			}
		}
	}
	
	@Test(dependsOnMethods = "statusPrerequisite", dataProvider = "regNumbersForCarsWithColorDataProvider", dataProviderClass = ParkingLotDataProvider.class)
	public void registrationNumbersForCarsWithColour(Object[] data) {
		ParkingLotResponse<String> response = parkingForStatus.getRegNumbersByColor((String)data[0]);
		assertTrue(response.isStatus());
		//Parse expected output to compare with the one received from the API
		String[] expectedRegNumbers = ((String)data[1]).split(":");
		//Construct the map of expected regNumbers.
		Set<String> expectedRegNumbersSet = new HashSet<String>();
		for(String token:expectedRegNumbers){
			expectedRegNumbersSet.add(token);
		}
		//Iterate over the registration numbers received from the API and compare with the expected ones.
		List<String> regNumbersFromAPI = response.getData();
		for(String regNumber:regNumbersFromAPI){
			assertTrue(expectedRegNumbersSet.contains(regNumber));
		}
	}
	
	@Test(dependsOnMethods = "statusPrerequisite", dataProvider = "slotNumbersForCarsWithColorDataProvider", dataProviderClass = ParkingLotDataProvider.class)
	public void slotNumbersForCarsWithColour(Object[] data) {
		ParkingLotResponse<Integer> response = parkingForStatus.getSlotNumsByColor((String)data[0]);
		assertTrue(response.isStatus());
		//Parse expected output to compare with the one received from the API
		String[] expectedRegNumbers = ((String)data[1]).split(":");
		//Construct the map of expected regNumbers.
		Set<String> expectedRegNumbersSet = new HashSet<String>();
		for(String token:expectedRegNumbers){
			expectedRegNumbersSet.add(token);
		}
		//Iterate over the registration numbers received from the API and compare with the expected ones.
		List<Integer> regNumbersFromAPI = response.getData();
		for(Integer regNumber:regNumbersFromAPI){
			Boolean result = expectedRegNumbersSet.contains(regNumber.toString());
			assertTrue(result);
		}
	}
	
	@Test(dependsOnMethods = "statusPrerequisite", dataProvider = "slotNumbersForRegNumberDataProvider", dataProviderClass = ParkingLotDataProvider.class)
	public void slotNumbersForRegistrationNumber(Object[] data) {
		ParkingLotResponse<Integer> response = parkingForStatus.getSlotNumsByregNumber((String)data[0]);
		assertTrue(response.isStatus());
		//Parse expected output to compare with the one received from the API
		String[] expectedRegNumbers = ((String)data[1]).split(":");
		//Construct the map of expected regNumbers.
		Set<String> expectedRegNumbersSet = new HashSet<String>();
		for(String token:expectedRegNumbers){
			expectedRegNumbersSet.add(token);
		}
		//Iterate over the registration numbers received from the API and compare with the expected ones.
		List<Integer> regNumbersFromAPI = response.getData();
		for(Integer regNumber:regNumbersFromAPI){
			Boolean result = expectedRegNumbersSet.contains(regNumber.toString());
			assertTrue(result);
		}
	}
}
