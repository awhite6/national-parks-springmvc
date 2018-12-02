package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.park.JDBCParkDao;
import com.techelevator.park.Park;

public class JDBCParkDaoIntegrationTest extends DAOIntegrationTest {

	private JDBCParkDao jdbcParkDao; 
	private JdbcTemplate template; 
	private String sql; 
	
	@Before 
	public void setUp() {
		jdbcParkDao = new JDBCParkDao(super.getDataSource()); 
		this.template = new JdbcTemplate(super.getDataSource());
		sql = "INSERT INTO park VALUES ('CC', 'camp cope', 'New Hampshire', '10', '10', '1', '2', 'forest', '1999', "
				+ "'1', 'qwe', 'qwee', 'a cool parl', '45', '2') ";
		template.update(sql);
	
	}
	
	@Test 
	public void check_for_all_parks() {		
		
	List<Park> parks = jdbcParkDao.getallPark(); 
	Assert.assertTrue(parks.size() > 0); 
	}
	
	@Test 
	public void check_for_park_by_id() {
		List<Park> park = jdbcParkDao.getParkById("CC"); 
		Assert.assertTrue(park.size() > 0);;
	}
	
	
	
}
