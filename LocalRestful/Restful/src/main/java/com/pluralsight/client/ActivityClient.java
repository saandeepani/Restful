package com.pluralsight.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pluralsight.model.Activity;

public class ActivityClient {

	Client client;

	public ActivityClient() {
		client = ClientBuilder.newClient();
	}

//	public Activity get(String id) {
//
//		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
//		
//		Activity responce = target.path("activities/" + id).request().get(Activity.classReturn type);//V0 get responce in Activity format
//		//String responce = target.path("activities/" + id).request().get(String.class/*Return type*/);//V1 it responceResponce: {"desc":"Swimming","duration":55,"id":"1234","user":{"userId":1111,"userName":"Kalyani"}} in XML String
//		//String responce = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).get(String.class/*Return type*/);//V2 it responce in JSON
//		System.out.println("Responce: "+responce);
//		return responce;
//
//	}


		public Activity get(String id) {

		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		
		Response responce = target.path("activities/" + id).request().get(Response.class);
		
		if(responce.getStatus() != 200){
			throw new RuntimeException(responce.getStatus() + ": there was an error on server.");
			
		}
		
		return responce.readEntity(Activity.class);

	}


	
	public List<Activity> get() {
		
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		//List<Activity> responce = target.path("activities" ).request().get(List.class); // you get Exception # Message body provider not found exception
																						// it Broke parsing to fix this wrap it with new genericType	
		List<Activity> responce = target.path("activities" ).request().get(new GenericType<List<Activity>>(){});
		
		return responce;
	}

	public Activity create(Activity activity) {
		//http://localhost:8080/exercise-services/webapi/activities/activity
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		
		Response response = target.path("activities/activity")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(activity, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
		
		return response.readEntity(Activity.class);
	}

	public Activity update(Activity activity) {
		
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		Response response = target.path("activities/"+ activity.getId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(activity, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
		
		return response.readEntity(Activity.class);
	}

	public void delete(String activityId) {
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		Response response = target.path("activities/"+ activityId).request(MediaType.APPLICATION_JSON).delete();
		
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
		
	}

}
