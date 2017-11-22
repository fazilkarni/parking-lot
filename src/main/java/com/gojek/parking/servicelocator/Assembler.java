package com.gojek.parking.servicelocator;

/**
 * This class is used to configure or load service locator with the services.
 * @author mkarni
 *
 */
public class Assembler {
    private static final Assembler instance = new Assembler();
 
    private Assembler() {
    }
 
    public static Assembler getInstance() {
        return instance;
    }
 
    public void configure() {
    	//Here we configure the service locator. load it with all the available services.
        //ServiceLocator.getInstance().loadService("ParkingLotService", ParkingLotImpl.getInstance());
       
    }
}
