package bordanzi.mongo.api.support;


import java.io.IOException;

import com.mongodb.BasicDBObject;

public class MongoCSVAdd {

	MongoConnection mongoConnect;
	FileManager fileManager;
	
	public MongoCSVAdd (MongoConnection mongoConnectionn, FileManager fileManager){
	
		this.mongoConnect = mongoConnectionn;
		this.fileManager = fileManager;
		
	}
	
	public void parseCSV(String file, String collectionName){
		
		try{
			fileManager.initFile(file);
		}catch(IOException e){
			e.printStackTrace();
		}

		while(fileManager.hasLines()){
				
			String line = fileManager.getNextLine();
			String[] atrr = line.split(",");
			
			BasicDBObject document = new BasicDBObject("ad_placement",atrr[0])
									.append("format_iab", atrr[1])
									.append("gender", atrr[2])
									.append("age", atrr[3])
									.append("scholarity", atrr[4])
									.append("marital", atrr[5])
									.append("income",atrr[6])
									.append("connection", atrr[7])
									.append("browser", atrr[8])
									.append("so", atrr[9])
									.append("interest_id", atrr[10]);
			String cOp = atrr[11];
			
			/*
			if (cOp.equals("C")){
				document.append("clicks", 1);
				document.append("prints", 0);
			}else{
				document.append("clicks", 0);
				document.append("prints", 1);
			}
			*/
			mongoConnect.insertIntoCollection(collectionName, document, cOp);
			
			
		}
		
	}
}
