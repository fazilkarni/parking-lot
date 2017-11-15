package com.gojek.parking.bl.impl;

import java.util.List;

import com.gojek.parking.vo.Slot;

public class ParkingLotResponse<T,V> {
	private List<T> data;
	private List<V> errors;
	private boolean status;
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public List<V> getErrors() {
		return errors;
	}
	public void setErrors(List<V> errors) {
		this.errors = errors;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
	
	

}
