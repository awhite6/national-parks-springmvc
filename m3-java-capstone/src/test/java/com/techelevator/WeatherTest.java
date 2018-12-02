package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.weather.Weather;


public class WeatherTest {
	Weather weather;
	
	@Before
	public void setUp() {
		weather = new Weather();
		weather.setHigh(104);
		weather.setLow(20);
	}
	
	@Test
	public void check_fahrenheit_to_celcius() {
		weather.setDegreeType("F");
		Assert.assertEquals(40, weather.getHigh());
	}
	
	@Test
	public void check_celcius_to_fahrenheit() {
		weather.setDegreeType("F");
		weather.setDegreeType("C");
		Assert.assertEquals(104, weather.getHigh());
	}
}
