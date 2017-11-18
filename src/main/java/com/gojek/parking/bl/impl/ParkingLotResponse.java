package com.gojek.parking.bl.impl;

import java.util.List;


public class ParkingLotResponse<T> {
	private List<T> data;
	private List<String> errors;
	private boolean status;
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
	
	

}
