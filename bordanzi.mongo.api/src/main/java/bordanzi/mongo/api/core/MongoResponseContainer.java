package bordanzi.mongo.api.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MongoResponseContainer {

	private List<MongoResponse> responses;
	private Iterator iterador;
	
	public MongoResponseContainer(){
		this.responses=new ArrayList<MongoResponse>();
		this.iterador = responses.iterator();
	}
	
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
	
	public boolean poseeSiguiente(){
		return iterador.hasNext();
	}
	
	public MongoResponse obtenerSiguiente(){
		
		return (MongoResponse)iterador.next();
	}
	
	
}
