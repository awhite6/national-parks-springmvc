package com.techelevator.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCParkDao implements ParkDao {
	
	private JdbcTemplate jdbcTemplate; 
	
	@Autowired 
	public JDBCParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	@Override
	public List<Park> getallPark() {
		List<Park> parks = new ArrayList<Park>(); 
		
		String sql = "SELECT  parkCode, \n" + 
				"	parkName, \n" + 
				"	state, \n" + 
				"	acreage, \n" + 
				"	elevationInFeet, \n" + 
				"	milesOfTrail, \n" + 
				"	numberOfCampsites, \n" + 
				"	climate, \n" + 
				"	yearFounded, \n" + 
				"	annualVisitorCount, \n" + 
				"	inspirationalQuote, \n" + 
				"	inspirationalQuoteSource, \n" + 
				"	parkDescription, \n" + 
				"	entryFee, \n" + 
				"	numberOfAnimalSpecies FROM park"; 
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql); 
		
		while(result.next()) {
		Park park = mapRowToPark(result); 
		parks.add(park); 
		
		}
				
		return parks;
	}

	@Override
	public List<Park> getParkById(String parkCode) {
		List<Park> parks = new ArrayList<Park>(); 
		
		String sql = "SELECT  parkCode, \n" + 
				"	parkName, \n" + 
				"	state, \n" + 
				"	acreage, \n" + 
				"	elevationInFeet, \n" + 
				"	milesOfTrail, \n" + 
				"	numberOfCampsites, \n" + 
				"	climate, \n" + 
				"	yearFounded, \n" + 
				"	annualVisitorCount, \n" + 
				"	inspirationalQuote, \n" + 
				"	inspirationalQuoteSource, \n" + 
				"	parkDescription, \n" + 
				"	entryFee, \n" + 
				"	numberOfAnimalSpecies FROM park WHERE parkcode = ?";
		
	SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkCode); 
		
	while(result.next()) {
		Park park = mapRowToPark(result); 
		parks.add(park); 
		
		}
		return parks;
	}

	private Park mapRowToPark(SqlRowSet row) {
		Park park = new Park(); 
		park.setParkCode(row.getString("parkcode"));
		park.setParkName(row.getString("parkname"));
		park.setState(row.getString("state"));
		park.setAcreage(row.getInt("acreage"));
		park.setElevationInFeet(row.getInt("elevationinfeet"));
		park.setMilesOfTrail((int)row.getDouble("milesoftrail"));
		park.setNumberOfCampsites(row.getInt("numberofcampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearfounded"));
		park.setAnnualVisitorCount(row.getInt("annualvisitorcount")); 
		park.setInspirationalQuote(row.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(row.getString("inspirationalquotesource"));
		park.setParkDescription(row.getString("parkdescription"));
		park.setEntryFee(row.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(row.getInt("numberofanimalspecies"));
		
		return park; 
		
	}
	
}
