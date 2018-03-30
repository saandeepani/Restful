package com.pluralsight.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.pluralsight.model.Activity;
import com.pluralsight.model.ActivitySearch;

public class ActivitySearchClient {
	
	private Client client;
	
	
	public ActivitySearchClient(){
		client = ClientBuilder.newClient();
	}
	
	public List<Activity> search (String param, List<String> searchvalues, String secondParam, int durationFrom, String thridParam, int durationTo){
		
		URI uri = UriBuilder.fromUri("http://localhost:8080/exercise-services/webapi")
					.path("search/activities")
					.queryParam(param, searchvalues)
					.queryParam(secondParam, durationFrom)
					.queryParam(thridParam, durationTo)
					.build();
		
		WebTarget target = client.target(uri);
		
		List<Activity> responce = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List <Activity>>() {});
		
		System.out.println(responce);
		
		return responce;
		
		
	}

	public List<Activity> search(ActivitySearch search) {
		URI uri = UriBuilder.fromUri("http://localhost:8080/exercise-services/webapi")
				.path("search/activities")
				.build();
		
		WebTarget target = client.target(uri);
		
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(search, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() != 200){
			throw new RuntimeException(response.getStatus()+ ":There is an error in server.");
			
		}
		
		return response.readEntity(new GenericType<List<Activity>>(){});
	}

}
