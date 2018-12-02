package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.park.Park;
import com.techelevator.park.ParkDao;
import com.techelevator.weather.Weather;
import com.techelevator.weather.WeatherDao;

@Controller 
@SessionAttributes({"parkCode", "temperatureScale"})
public class ParkController {
	
	@Autowired 
	private ParkDao parkDao;
	
	@Autowired 
	private WeatherDao weatherDao;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String redirectToHome() {
		return "redirect:/home";
	}
	
	@RequestMapping(path="/home", method=RequestMethod.GET)
	public String displayHomePage(ModelMap map) {
		List<Park> parks = parkDao.getallPark(); 
		map.addAttribute("parks", parks); 
		return "homePage"; 
	}
	
	@RequestMapping(path="/parkDetails", method=RequestMethod.GET)
	public String displayParkDetails(ModelMap map, HttpServletRequest request) {
		String parkCode = determineParkCode(map.get("parkCode"), request, map);
		String temperatureScale = determineTemperatureType(map.get("temperatureScale"));
	
		Park park = parkDao.getParkById(parkCode).get(0);
		
		List<Weather> weatherList = generateWeatherList(parkCode, temperatureScale);
		
		map.addAttribute("weatherList", weatherList);
		map.addAttribute("park", park);
		map.addAttribute("temperatureScale", temperatureScale);
		
		return "parkDetails"; 
	}
	
	
	@RequestMapping(path="/changeMyTempTypeUrl", method=RequestMethod.POST)
	public String changeTempOnPage(ModelMap map, Weather weather) {
		String temperatureScale = weather.getDegreeType();
		String parkCode = weather.getParkCode();
		
		map.addAttribute("parkCode", parkCode);
		map.addAttribute("temperatureScale", temperatureScale);
		
		return "redirect:/parkDetails";
	}
	
	private List<Weather> generateWeatherList(String parkCode, String temperatureScale) {
		List<Weather> weatherList = weatherDao.getWeatherByParkId(parkCode);
		for (int i=0; i < weatherList.size(); i++) {
			weatherList.get(i).setDegreeType(temperatureScale);
		}
		
		return weatherList;
	}
	
	private String determineTemperatureType(Object ob) {
		if (comingFromHomePage(ob)) {
			return "F";
		} else {
			return (String) ob;
		}
	}
	
	private String determineParkCode(Object ob, HttpServletRequest request, ModelMap map) {
		if (request.getParameter("parkcode") != null) {
		    return request.getParameter("parkcode");
		} else {
			return (String) map.get("parkCode");
		}
	}
	
	private boolean comingFromHomePage(Object ob) {
		if (ob != null) {
			return false;
		} else {
			return true;
		}
				
	}

}
