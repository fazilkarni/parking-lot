package com.gojek.parking.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.util.Utils;
import com.gojek.parking.util.Validator;
import com.gojek.parking.vo.Slot;

/**
 * This is the implementation of ParkingLot interface.
 * 
 * @author karni.fazil TODO: think of how to indicate user about error
 *         conditions.
 */

public class ParkingLotImpl implements ParkingLot {
	private int SLOTS_LIMIT = 99999;
	private List<Slot> slots;
	private volatile static ParkingLot _instance;
	/**
	 * This the is the private constructor to make ParkingLot a singleton object.
	 */
	private ParkingLotImpl(){
		
	}
	
	/**
	 * This method to returns singleton ParkingLot object.
	 * @return
	 */
	public static ParkingLot getInstance() {
		//System.out.println("ParkingLot accessed.");
		if (_instance == null) {
			synchronized (ParkingLot.class) {
				if (_instance == null) {
					_instance = new ParkingLotImpl();
				}
			}
		}
		return _instance;
	}

	/**
	 * This method initializes the slots equal to the totalSlots passed as an
	 * argument.
	 */
	public ParkingLotResponse<Slot> createParkingLot(int totalSlots) {

		if (totalSlots > 0 && totalSlots < SLOTS_LIMIT) {
			slots = new ArrayList<Slot>(totalSlots);
			for (int i = 1; i <= totalSlots; i++) {
				this.slots.add(new Slot(i));
			}
			return Utils.constructResponse(slots, null);
		} else {
			List<String> errorMessages = new ArrayList<String>();
			// Construct error response. Move this string to properties file
			errorMessages.add("Total slots to be created should be more than 0 and less than max limit " + SLOTS_LIMIT);
			return Utils.constructResponse(null, errorMessages);
		}
	}

	/**
	 * This method parking car in PL and updates the status appropriately.
	 */
	public ParkingLotResponse<Slot> park(String regNumber, String color) {
		Slot slot = null;
		List<String> errorsMessages = new ArrayList<String>();
		if (Validator.validateRegNumber(regNumber, errorsMessages) && Validator.validateColor(color, errorsMessages)) {
			if (slots == null) {
				return Utils.constructResponse(null, errorsMessages);
			} else if(Validator.isDuplicateRegNumbers(regNumber,slots)) {
				errorsMessages.add("Duplicate resigster number");
				return Utils.constructResponse(null, errorsMessages);
			}else{
				slot = Utils.getFreeSlot(slots);
				if (slot != null) {
					slot.setRegNumber(regNumber);
					slot.setColor(color);
					slot.setOccupied(true);
					List<Slot> slots = new ArrayList<Slot>();
					slots.add(slot);
					return Utils.constructResponse(slots, null);
				} else {
					errorsMessages.add("Sorry, parking lot is full");
					return Utils.constructResponse(null, errorsMessages);
				}
			}
		} else {
			return Utils.constructResponse(null, errorsMessages);
		}
	}

	/**
	 * This method method removes the car from the parking lot by regNumber and updates the
	 * status appropriately in Parking lot.
	 */
	public ParkingLotResponse<Slot> leave(String regNumber) {
		Slot slot = null;
		List<Slot> VaccantSlots = null;
		List<String> errorMessages = new ArrayList<String>();
		if (Validator.validateRegNumber(regNumber, errorMessages)) {
			slot = Utils.getSlotByRegNumber(slots, regNumber);
			if (slot != null && slot.getOccupied()) {
				slot.setOccupied(false);
				slot.setColor(null);
				slot.setRegNumber(null);
			} else {
				errorMessages.add("No such registration numbered car parked.");
			}
		} else {
			errorMessages = new ArrayList<String>();
			errorMessages.add("Registration number " + regNumber + " is invalid");
		}
		// Construct and return the response.
		if (slot != null) {
			VaccantSlots = new ArrayList<Slot>();
			VaccantSlots.add(slot);
		}
		return Utils.constructResponse(VaccantSlots, errorMessages);
	}
	
	/**
	 * This method method removes the car from the parking lot by slot number and updates the
	 * status appropriately in Parking lot.
	 */
	public ParkingLotResponse<Slot> leave(Integer slotNumber) {
		Slot slot = null;
		List<Slot> VaccantSlots = null;
		List<String> errorMessages = new ArrayList<String>();
		if (this.slots!=null && this.slots.size()>= slotNumber) {
			slot = slots.get(slotNumber);
			if(slot.getOccupied()) {
				slot.setOccupied(false);
				slot.setColor(null);
				slot.setRegNumber(null);
				VaccantSlots = new ArrayList<Slot>();
				VaccantSlots.add(slot);
			} else {
				errorMessages.add("No car parked at the slot number "+ ++slotNumber); // +1 is required as arrays start from 0 so it was reduced by 1. So while sending it back to client increment it by 1
			}
		}else {
			errorMessages = new ArrayList<String>();
			errorMessages.add("Slot number " + slotNumber + " is invalid");
		}
		// Construct and return the response. 
		
		return Utils.constructResponse(VaccantSlots, errorMessages);
	}

	/**
	 * This method returns the current status of the parking lot, such as which
	 * slots occupied and which are not.
	 */
	public ParkingLotResponse<Slot> getStatus() {
		return Utils.constructResponse(slots, null);
	}

	/**
	 * 
	 */
	public ParkingLotResponse<String> getRegNumbersByColor(String color) {

		List<String> collect = slots.stream().filter(slot -> slot.getColor()!=null && slot.getColor().toString().equals(color))
				.map(Slot::getRegNumber).collect(Collectors.toList());
		return Utils.constructResponse(collect, null);

	}

	public ParkingLotResponse<Integer> getSlotNumsByColor(String color) {
		List<Integer> collect = slots.stream().filter(slot -> slot.getColor()!=null && slot.getColor().toString().equals(color))
				.map(Slot::getSlotNumber).collect(Collectors.toList());
		return Utils.constructResponse(collect, null);
	}

	public ParkingLotResponse<Slot> getSlotByRegNumber(String regNumber) {
		List<Slot> collect = new ArrayList<Slot>();
		collect.add(Utils.getSlotByRegNumber(slots, regNumber));
		return Utils.constructResponse(collect, null);
	}

	public ParkingLotResponse<Slot> getAllSlotsByCarColor(String color) {
		List<Slot> filteredSlotsByColor = slots.stream() // convert list to stream
				.filter(slot -> slot.getColor()!=null && slot.getColor().toString().equals(color)) //collect slots having car with  car color equals to "color"
				.collect(Collectors.toList());
		return Utils.constructResponse(filteredSlotsByColor, null);
	}

	public ParkingLotResponse<Slot> getAllSlotsByRegNumber(String regNumber) {
		List<Slot> filteredSlotsbyRegNum = slots.stream() // convert list to stream
				.filter(slot -> slot.getRegNumber()!=null && slot.getRegNumber().toString().equals(regNumber)) // collect slots having car with registration number regNumber
				.collect(Collectors.toList());
		return Utils.constructResponse(filteredSlotsbyRegNum, null);
	}

	public ParkingLotResponse<Integer> getSlotNumsByregNumber(String regNumber) {
		List<Integer> filteredSlotsbyRegNum = slots.stream() // convert list to stream
				.filter(slot -> slot.getRegNumber()!=null && slot.getRegNumber().toString().equals(regNumber))// collect slots having car with registration number regNumber
				.map(Slot::getSlotNumber)
				.collect(Collectors.toList());
		return Utils.constructResponse(filteredSlotsbyRegNum, null);
	}
	
	

}
