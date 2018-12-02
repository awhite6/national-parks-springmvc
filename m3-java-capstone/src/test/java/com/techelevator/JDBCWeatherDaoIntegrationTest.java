package com.techelevator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.survey.JDBCSurveyDao;
import com.techelevator.survey.Survey;
import com.techelevator.weather.JDBCWeatherDao;
import com.techelevator.weather.Weather;

public class JDBCWeatherDaoIntegrationTest extends DAOIntegrationTest {
	
	private JDBCWeatherDao weatherDao; 
	private JdbcTemplate template; 
	private String sql; 
	
	@Before 
	public void setUp() {
		weatherDao = new JDBCWeatherDao(super.getDataSource()); 	
		this.template = new JdbcTemplate(super.getDataSource());
		
		String sql = "DELETE FROM weather"; 
		template.update(sql); 
		
//		Weather weather = new Weather();
//		weather.setParkCode("MGM");
//		weather.setFiveDayForecastValue(1);
//		weather.setLow(1);
//		weather.setHigh(12);
//		weather.setForecast("uhh son");
		
		sql = "INSERT INTO weather VALUES ('GNP', '1', '10', '17', 'uhh son');";
		template.update(sql);
	}
	
	@Test
	public void check_for_all_weather() {
		List<Weather> weather = weatherDao.getAllWeather();
		Assert.assertTrue(weather.size() > 0);
	}
	
	@Test
	public void check_for_weather_by_park_code() {
		List<Weather> weather = weatherDao.getWeatherByParkId("GNP");
		Assert.assertTrue(weather.size() > 0);
	}
}
