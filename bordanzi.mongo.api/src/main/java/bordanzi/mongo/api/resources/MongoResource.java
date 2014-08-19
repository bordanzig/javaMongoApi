package bordanzi.mongo.api.resources;


import java.util.concurrent.atomic.AtomicLong;

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






@Path("/mongo")
@Produces(MediaType.APPLICATION_JSON)
public class MongoResource {
	
	private final MongoConnection mongoConnection;
	private final String mongoCollectionName;
	private final AtomicLong counter;
	
	public MongoResource(MongoConnection conection, String collection){
		this.mongoConnection = conection;
		this.mongoCollectionName = collection;
		this.counter = new AtomicLong();
		
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
		
		BasicDBObject query = new BasicDBObject();
							if(ad_placement.isPresent()){
								query.append("ad_placement", ad_placement.get());
							}
							if(format_iab.isPresent()){
								query.append("format_iab", format_iab.get());
							}	
							if(gender.isPresent()){
								query.append("gender", gender.get());
							}	
							if(age.isPresent()){	
								query.append("age", age.get());
							}	
							if(scholarity.isPresent()){	
								query.append("scholarity", scholarity.get());
							}	
							if(marital.isPresent()){	
								query.append("marital", marital.get());
							}	
							if(income.isPresent()){	
								query.append("income",income.get());
							}	
							if(connection.isPresent()){	
								query.append("connection",connection.get());
							}	
							if(browser.isPresent()){	
								query.append("browser", browser.get());
							}	
							if(so.isPresent()){	
								query.append("so",so.get());
							}
							if(interest_id.isPresent()){	
								query.append("interest_id", interest_id.get());
							}
		DBCursor cursorRespuesta =  mongoConnection.mongoQuery(mongoCollectionName, query);	
		
		MongoResponseContainer contenedor = new MongoResponseContainer();
		
		
		while(cursorRespuesta.hasNext()){
			BasicDBObject respuestaSiguiente =(BasicDBObject) cursorRespuesta.next();
			MongoResponse objetoRespuesta = new MongoResponse(respuestaSiguiente, counter.incrementAndGet());
			
			contenedor.agregarResponse(objetoRespuesta);
		}
		cursorRespuesta.close();
		
		return contenedor;
							  
	}
	
	@GET
	@Path("clicks-for")
	public MongoResponse sumResponse(@QueryParam("ad_placement") Optional<String> ad_placement,
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
		
		MongoResponseContainer contenedor = response(ad_placement, format_iab,
													 gender, age, scholarity,
													 marital, income, connection,
													 browser, so, interest_id);
		
		Integer clicks=0;
		Integer prints=0;
		
		while (contenedor.poseeSiguiente()){
			MongoResponse respuesta = contenedor.obtenerSiguiente();
			clicks = clicks + respuesta.getClicks();
			prints = prints + respuesta.getPrints();
		}
		
		MongoResponse sumResponse = new MongoResponse(clicks, prints, ad_placement.get(), format_iab.get(),
													  gender.get(), age.get(), scholarity.get(), marital.get(), income.get(),
													  connection.get(), browser.get(), so.get(), interest_id.get());
		return sumResponse;
	}
}