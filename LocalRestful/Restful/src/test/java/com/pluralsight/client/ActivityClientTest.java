package com.pluralsight.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pluralsight.model.Activity;
import com.pluralsight.model.ActivitySearch;
import com.pluralsight.model.ActivitySearchType;

public class ActivityClientTest {
	
	
	@Test
	public void testSearchobject(){
		ActivitySearchClient client = new ActivitySearchClient();
		
		List<String> searchValues = new ArrayList<String>();
		searchValues.add("Biking");
		searchValues.add("Cycling");
		
		ActivitySearch search = new ActivitySearch();
		search.setDescriptions(searchValues);
		search.setDurationFrom(30);
		search.setDurationTo(60);
		search.setSearchType(ActivitySearchType.SEARCH_BY_DESCRIPTION);
		
		List<Activity> activities = client.search(search);
		
		System.out.println(activities);
		
		assertNotNull(activities);
		
	}
	
	
	@Test
	public void testSearch(){
		ActivitySearchClient client = new ActivitySearchClient();
		
		String param = "description";
		List<String> searchValues = new ArrayList<String>();
		searchValues.add("Swimming");
		searchValues.add("Running");
		
		String secondParam = "DurationFrom";
		int durationFrom = 30;
		
		String thridParam = "DurationTo";
		int durationTo = 55;
		
		List<Activity> activities = client.search(param, searchValues, secondParam, durationFrom, thridParam, durationTo);
		
		System.out.println(activities);
		
		assertNotNull(activities);
	}
	
	
	@Test
	public void testDelete(){
		
		ActivityClient client = new ActivityClient();
		client.delete("1234");
	}
	
	
	@Test
	public void testPut(){
		
		Activity activity = new Activity();
		activity.setDescription("Put@Running");
		activity.setDuration(30);
		
		ActivityClient client = new ActivityClient();
		activity = client.update(activity);

		assertNotNull(activity);
	}
	
	//Test for Post
	@Test
	public void testCreate() {
		ActivityClient client = new ActivityClient();
		
		Activity activity = new Activity();
		activity.setDescription("Post@Swimming");
		activity.setDuration(90);
		
		activity = client.create(activity);
		
		assertNotNull(activity);
	}

	@Test
	public void testGet() {
		
		ActivityClient client = new ActivityClient();
		Activity activity = client.get("1243");
		
		assertNotNull(activity);
	}
	
	@Test
	public void testGetList(){
		ActivityClient client = new ActivityClient();
		List<Activity> activities = client.get();
		
		assertNotNull(activities);
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetWithBadReq(){
		ActivityClient client = new ActivityClient();
		client.get("123");
	}

}
