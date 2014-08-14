package bordanzi.mongo.api;


import java.net.UnknownHostException;

import bordanzi.mongo.api.configuration.MongoConfig;
import bordanzi.mongo.api.resources.MongoResource;
import bordanzi.mongo.api.support.FileManager;
import bordanzi.mongo.api.support.MongoCSVAdd;
import bordanzi.mongo.api.support.MongoConnection;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MongoApiApplication extends Application<MongoConfig>{
	
	public static void main(String[] args) throws Exception{
		new MongoApiApplication().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<MongoConfig> bootstrap){
		
	}
	
	@Override
	public void run(MongoConfig configuration, Environment environment){
		
		try {
			MongoConnection mongoConnection = new MongoConnection(configuration.getIp(),configuration.getPort(),configuration.getDbName());
			FileManager fileManager = new FileManager();
			MongoCSVAdd mongoCSV = new MongoCSVAdd(mongoConnection,fileManager);
			
			mongoCSV.parseCSV(configuration.getFileName(), configuration.getCollectionName());
			
			final MongoResource resource = new MongoResource(mongoConnection,configuration.getCollectionName());
			
			environment.jersey().register(resource);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
