package com.techelevator.weather;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDao implements WeatherDao {
	
	private JdbcTemplate jdbcTemplate; 

	@Autowired 
	public JDBCWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	@Override
	public List<Weather> getAllWeather() {
		List<Weather> weatherList = new ArrayList<Weather>();
		String sql = "SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Weather weather = mapRowToWeather(results);
			weatherList.add(weather);
		}
		
		return weatherList;
	}

	@Override
	public List<Weather> getWeatherByParkId(String parkCode) {
		List<Weather> weatherList = new ArrayList<Weather>();
		String sql = "SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather WHERE parkcode = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);
		while (results.next()) {
			Weather weather = mapRowToWeather(results);
			weatherList.add(weather);
		}
		
		return weatherList;	
	}
	
	private Weather mapRowToWeather(SqlRowSet result) {
		Weather weather = new Weather();
		
		weather.setParkCode(result.getString("parkcode"));
		weather.setFiveDayForecastValue(result.getInt("fivedayforecastvalue"));
		weather.setLow(result.getInt("low"));
		weather.setHigh(result.getInt("high"));
		weather.setForecast(result.getString("forecast"));
		weather.setWeatherAdvisory();
		
		return weather;
	}
	
}
