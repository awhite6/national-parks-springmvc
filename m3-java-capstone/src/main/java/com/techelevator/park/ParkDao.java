package com.techelevator.park;

import java.util.List;

public interface ParkDao {
	public List<Park> getallPark(); 
	
	public List<Park> getParkById(String parkCode); 
	
}
