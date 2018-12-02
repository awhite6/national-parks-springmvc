package com.techelevator.weather;

import org.springframework.context.annotation.Scope;

public class Weather {
	private String parkCode;
	private int fiveDayForecastValue;
	private int low;
	private int high;
	private String forecast;
	private String weatherAdvisory = "";
	private String degreeType = "F";
	
	public String getParkCode() {
		return parkCode;
	}
	
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}
	
	public int getLow() {
		return low;
	}
	
	public void setLow(int low) {
		this.low = low;
	}
	
	public int getHigh() {
		return high;
	}
	
	public void setHigh(int high) {
		this.high = high;
	}
	
	public String getForecast() {
		return forecast;
	}
	
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	public String getWeatherAdvisory() {
		return weatherAdvisory;
	}
	
	public String getDegreeType() {
		return degreeType;
	}
	
	private int getDifferenceBetweenHighAndLow() {
		int difference;
		
		if (low < 0) {
			difference = high + low;
		} else {
			difference = high - low;
		}
		
		return difference;
	}
	
	public void setDegreeType(String changeTo) {
		double newHigh = high;
		double newLow = low;
				
		if (degreeType.equalsIgnoreCase("F") && !changeTo.equals("F")) {
			newHigh = ((high - 32) * 5) / 9;
			newLow = ((low - 32) * 5) / 9;
			degreeType = "C";
		} else if (degreeType.equalsIgnoreCase("C") && !changeTo.equals("C")) {
			newHigh = (((9.0/5.0)*high) + 32);
			newLow = (((9/5.0)*low) + 32);
			degreeType = "F";
		}
		
		high = (int) newHigh;
		low = (int) newLow;
	}
	
	
	
	public void setWeatherAdvisory() {
		
		if (forecast.equals("snow")) {
			weatherAdvisory += "Don't forget your snowshoes! ";
		} 
		
		if (forecast.equals("rain")) {
			weatherAdvisory += "It's a wet one out there don't forget your rain gear and waterproof shoes! ";
		}
		
		if (forecast.equals("thunderstorms")) {
			weatherAdvisory += "Watch out for the thunder. Seek shelter and avoid hiking on exposed ridges. ";
		}
		
		if (forecast.equals("sunny")) {
			weatherAdvisory += "Its a sunny one out there don't forget your sunblock! ";
		}
		
		if (high > 75) {
			weatherAdvisory += "It's a scorcher out there bring an extra gallon of water. ";
		}
		
		if (getDifferenceBetweenHighAndLow() > 20) {
			weatherAdvisory += "Wear breathable layers. ";
		}
		
		if (low < 20) {
			weatherAdvisory += "Beware of frigid temperatures. Bundle up! ";
		}
		
	}
	
	
}
