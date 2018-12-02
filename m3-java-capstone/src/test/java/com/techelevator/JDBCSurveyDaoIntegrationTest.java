package com.techelevator;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.survey.JDBCSurveyDao;
import com.techelevator.survey.Survey;

public class JDBCSurveyDaoIntegrationTest extends DAOIntegrationTest {
	
	private JDBCSurveyDao surveyDao; 
	private JdbcTemplate template; 
	private String sql; 
	
	@Before 
	public void setUp() {
		surveyDao = new JDBCSurveyDao(super.getDataSource()); 	
		this.template = new JdbcTemplate(super.getDataSource());
		String sql = "DELETE FROM survey_result"; 
		template.update(sql); 
		
		Survey survey = new Survey(); 
		survey.setParkCode("CC");
		survey.setEmailAddress("haha");
		survey.setState("ohio");
		survey.setActivityLevel("lazy");
		surveyDao.insertSurveyResult(survey);

	}
	
	@Test 
	public void check_insert_survey() {
		List<Survey> surveys; 		
		surveys = surveyDao.getAllSurveys(); 
		
		Assert.assertTrue(surveys.size() > 0);	
		
	}
	
	@Test
	public void check_survey_count() {
		
		Map<String, Integer> surveyCount = surveyDao.getCountForSurvey();
		Assert.assertTrue(surveyCount.size() > 0);
		
	}

}
