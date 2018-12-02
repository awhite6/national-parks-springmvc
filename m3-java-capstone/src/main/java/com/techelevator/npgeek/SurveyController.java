package com.techelevator.npgeek;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.park.Park;
import com.techelevator.park.ParkDao;
import com.techelevator.survey.Survey;
import com.techelevator.survey.SurveyDao;

@Controller 
public class SurveyController {
	
	@Autowired 
	private ParkDao parkDao;
	
	@Autowired
	private SurveyDao surveyDao;
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String displayHomePage(ModelMap map, Model model) {
		
		if ( !model.containsAttribute("survey")) {
			model.addAttribute("survey", new Survey());
		}
		
		List<Park> parks = parkDao.getallPark();
		map.addAttribute("pageTitle", "SURVEY");
		map.addAttribute("parks", parks);
		
		return "survey";
	}
	
	@RequestMapping(path="/handleSurvey", method=RequestMethod.POST)
	public String handleSurvey(@Valid @ModelAttribute("survey") Survey survey,
			BindingResult result,
			RedirectAttributes attr, ModelMap map) {
						
		if (result.hasErrors()) {
			List<Park> parks = parkDao.getallPark();
			map.addAttribute("parks", parks);
			return "survey";
		}
		
		surveyDao.insertSurveyResult(survey);
		return "redirect:/surveyResults";
	}
	
	@RequestMapping(path="/surveyResults", method=RequestMethod.GET)
	public String displayFavoriteParks(ModelMap map) {
		Map<String, Integer> parkCodesFromSurvey = surveyDao.getCountForSurvey();
		Queue<Park> parksFromSurvey = new LinkedList<Park>();
		
		populateQueueWithParks(parkCodesFromSurvey, parksFromSurvey);
		
		map.addAttribute("pageTitle", "SURVEY RESULTS");
		map.addAttribute("parkCodesFromSurvey", parkCodesFromSurvey);
		map.addAttribute("parksFromSurvey", parksFromSurvey);
		
		return "favoriteParks";
	}
	
	private void populateQueueWithParks(Map<String, Integer> parkCodesFromSurvey, Queue<Park>parksFromSurvey) {
		for (String s : parkCodesFromSurvey.keySet()) {
			Park park = parkDao.getParkById(s).get(0);
			parksFromSurvey.add(park);
		}	
	}
	
	

}