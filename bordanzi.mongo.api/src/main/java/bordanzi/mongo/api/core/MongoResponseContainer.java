package bordanzi.mongo.api.core;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MongoResponseContainer {

	private List<MongoResponse> responses;
	
	
	@JsonProperty
	public List<MongoResponse> getResponses() {
		return responses;
	}

	@JsonProperty
	public void setResponses(List<MongoResponse> responses) {
		this.responses = responses;
	}
	
	
	public void agregarResponse(MongoResponse response){
		this.responses.add(response);
	}
	
	
}
