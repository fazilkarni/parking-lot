package com.gojek.parking.client;

/**
 * This is the base interface for the Commands. All the command modeled should implement this interface
 * @author mkarni
 *
 */
public interface Command{
	  public void execute(String[] args);
	}
