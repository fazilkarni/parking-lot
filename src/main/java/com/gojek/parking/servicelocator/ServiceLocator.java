package com.gojek.parking.servicelocator;

import java.util.HashMap;
import java.util.Map;

import com.gojek.parking.bl.ParkingLot;
import com.gojek.parking.bl.impl.ParkingLotImpl;
import com.gojek.parking.constants.Constants;

public class ServiceLocator {

	private final Map<String, Object> services = new HashMap<String, Object>();

	private volatile static ServiceLocator _instance;

	/**
	 * This the is the private constructor to make ServiceLocator a singleton object
	 * object.
	 */
	private ServiceLocator() {

	}

	/**
	 * This method to returns singleton ParkingLot object.
	 * 
	 * @return
	 */
	public static ServiceLocator getInstance() {
		if (_instance == null) {
			synchronized (ParkingLot.class) {
				if (_instance == null) {
					_instance = new ServiceLocator();
					//Load serviceLocator with services. Public method is provided if required to load the services from the other objects.
					_instance.loadService(Constants.PARKINGLOTSERVICE, ParkingLotImpl.getInstance());
				}
			}
		}
		return _instance;
	}

	void loadService(String key, Object service) {
		services.put(key, service);
	}

	public Object getService(String key) {
		return services.get(key);
	}

}
