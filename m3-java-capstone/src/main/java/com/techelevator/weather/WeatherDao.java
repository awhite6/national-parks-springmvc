package com.techelevator.weather;

import java.util.List;

public interface WeatherDao {
	public List<Weather> getAllWeather();
	public List<Weather> getWeatherByParkId(String parkCode);
	
}
