package bordanzi.mongo.api.support;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoConnection {
	
	private MongoClient cliente;
	private DB base;
	
	public MongoConnection(String ip, Integer port, String dbname) throws UnknownHostException{
		this.cliente = new MongoClient(ip,port);
		this.base = cliente.getDB(dbname);
	}
	
	public MongoConnection(Integer port, String dbName) throws UnknownHostException{
		this.cliente = new MongoClient("localhost",port);
		this.base = cliente.getDB(dbName);
	}
	
	public DBCollection mongoGetCollection(String name){
		return base.getCollection(name);
	}
	
	public void insertIntoCollection(String collectionName, BasicDBObject document, String cOp){
		
		DBCollection collection = base.getCollection(collectionName);
		
		if (cOp.equals("C")){
			BasicDBObject upDoc = new BasicDBObject("$inc",new BasicDBObject("clicks",1));
			collection.update(document, upDoc, true, false);
		}else{
			BasicDBObject upDoc = new BasicDBObject("$inc",new BasicDBObject("prints",1));
			collection.update(document,upDoc, true, false);
		}
	}
	
	public DBCursor mongoQuery(String collectionName, BasicDBObject query){
		
		DBCursor cursor = base.getCollection(collectionName).find(query);
		
		return cursor;
	}
	
}
