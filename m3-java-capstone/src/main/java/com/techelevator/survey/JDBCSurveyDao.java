package com.techelevator.survey;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

//import com.techelevator.park.String;
import com.techelevator.park.ParkDao;

@Component
public class JDBCSurveyDao implements SurveyDao {

	private JdbcTemplate jdbcTemplate; 

	@Autowired 
	public JDBCSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}

	@Override
	public void insertSurveyResult(Survey survey) {
		String sql = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) VALUES (? , ? , ? , ?); "; 
		jdbcTemplate.update(sql, survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel()); 
	
	}


	@Override
	public Map<String, Integer> getCountForSurvey() {
		Map<String, Integer> surveyCount = new LinkedHashMap<String, Integer>();
		String sql = "SELECT parkcode, COUNT(parkcode)  FROM survey_result GROUP BY parkcode ORDER BY count DESC, parkcode;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while(results.next()) {
			surveyCount.put(results.getString("parkcode"), results.getInt("count"));
		}
		
		return surveyCount;
	}
	
	
	public List<Survey> getAllSurveys(){
		List<Survey> surveys = new ArrayList<Survey>(); 
		String sql = "SELECT * FROM survey_result";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql); 
		
		while(result.next()) {
			Survey survey = new Survey(); 
			
			survey.setParkCode(result.getString("parkcode")); 
			survey.setEmailAddress(result.getString("emailaddress")); 
			survey.setState(result.getString("state")); 
			survey.setActivityLevel(result.getString("activitylevel")); 
			surveys.add(survey); 
			
		}
		return surveys; 
	}

}
