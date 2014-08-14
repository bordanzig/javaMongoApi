package bordanzi.mongo.api.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import bordanzi.mongo.api.support.MongoConnection;
import bordanzi.mongo.api.core.MongoResponse;
import bordanzi.mongo.api.core.MongoResponseContainer;

import com.google.common.base.Optional;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;






@Path("/mongo/query")
@Produces(MediaType.APPLICATION_JSON)
public class MongoResource {
	
	private MongoConnection mongoConnection;
	private String mongoCollectionName;
	
	public MongoResource(MongoConnection conection, String collection){
		this.mongoConnection = conection;
		this.mongoCollectionName = collection;
		
	}
	
	@GET
	public MongoResponseContainer response(@QueryParam("ad_placement") Optional<String> ad_placement,
								  		   @QueryParam("format_iab") Optional<String> format_iab,
								  		   @QueryParam("gender") Optional<String> gender,
								  		   @QueryParam("age") Optional<String> age,
								  		   @QueryParam("scholarity") Optional<String> scholarity,
								  		   @QueryParam("marital") Optional<String> marital,
								  		   @QueryParam("income") Optional<String> income,
								  		   @QueryParam("connection") Optional<String> connection,
								  		   @QueryParam("browser") Optional<String> browser,
								  		   @QueryParam("so") Optional<String> so,
								  		   @QueryParam("interest_id") Optional<String> interest_id){
		
		BasicDBObject query = new BasicDBObject("ad_placement", ad_placement)
							  .append("format_iab", format_iab)
							  .append("gender", gender)
							  .append("age", age)
							  .append("scholarity", scholarity)
							  .append("marital", marital)
							  .append("income",income)
							  .append("connection",connection)
							  .append("browser", browser)
							  .append("so",so)
							  .append("interest_id", interest_id);
		
		DBCursor cursorRespuesta =  mongoConnection.mongoQuery(mongoCollectionName, query);
		
		MongoResponseContainer contenedor = new MongoResponseContainer();
		
		while(cursorRespuesta.hasNext()){
			
			BasicDBObject respuestaSiguiente =(BasicDBObject) cursorRespuesta.next();
			MongoResponse objetoRespuesta = new MongoResponse(respuestaSiguiente);
			
			contenedor.agregarResponse(objetoRespuesta);
		}
		
		cursorRespuesta.close();
		return contenedor;
							  
	}
	
}