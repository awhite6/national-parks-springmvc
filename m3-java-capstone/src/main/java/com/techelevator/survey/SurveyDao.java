package com.techelevator.survey;

import java.util.Map;

import com.techelevator.park.Park;

public interface SurveyDao {
	
	public void insertSurveyResult(Survey survey);
	
	public Map<String, Integer> getCountForSurvey(); 

}
