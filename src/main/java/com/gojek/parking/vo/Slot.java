package com.gojek.parking.vo;

/**
 * This
 * @author karni.fazil
 *TODO: Add parking and leaving timestamp.
 */
public class Slot {
	private int slotNumber;
	private String regNumber;
	private String color;
	private boolean occupied;
	
	public Slot(){
		
	}
	public Slot(int slotNumber){
		this.slotNumber = slotNumber;
		
	}
	public int getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Boolean getOccupied() {
		return occupied;
	}
	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}
	

}
