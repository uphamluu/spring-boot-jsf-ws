package com.phamluu.mongoAndPrime.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.phamluu.mongoAndPrime.entity.Howto;
import com.phamluu.mongoAndPrime.entity.UyenMongoCollection_Table;
import com.phamluu.mongoAndPrime.entity.UyenMongoDatabase; 

@Component
//public class MongoServiceImpl {
public class MongoServiceImpl implements MongoService{
	private static String DB_NAME="UYEN_DB";
//	private Mongo mongoConnection; 
	private MongoClient mongoClient;
	
	private void initDBconnection(){
//		if (mongoConnection == null) {
//			mongoConnection = new Mongo("localhost", 27017);
//		}
		
		if (mongoClient == null) {
			mongoClient = new MongoClient("localhost", 27017);
		}
//		
	}
	
	 private MongoCollection<Document> init2(){
	    	initDBconnection();
	    	MongoCredential credential; 
	        credential = MongoCredential.createCredential("sampleUser", DB_NAME,"password".toCharArray());
	        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
	        MongoCollection<Document> collection = database.getCollection("HOW_TO");
	        return collection;
	    }
	
	public List<UyenMongoDatabase>  getAllMongoDatabases(){
		initDBconnection();
		List<UyenMongoDatabase>  rs= new ArrayList<>();
//		List<String> dbs = mongoConnection.getDatabaseNames();
		List<String> dbs = mongoClient.getDatabaseNames();
		for(String db : dbs){
			rs.add(new UyenMongoDatabase(db));
		}
		return rs;
	}
	
	public List<UyenMongoCollection_Table>  getAllCollectionOfAllDBs(){
		List<UyenMongoDatabase> allDBs= getAllMongoDatabases();
		List<UyenMongoCollection_Table> result = new ArrayList<>();
		for(UyenMongoDatabase aMongoDb : allDBs){
			result.addAll(getAllCollectionOfOneDB(aMongoDb));
		}
    	
    	return result;
		
	}
	
	
	
    public List<UyenMongoCollection_Table>  getAllCollectionOfOneDB(UyenMongoDatabase mongoDb){
    	initDBconnection();
//		DB db = mongoConnection.getDB(mongoDb.getName());
		DB db = mongoClient.getDB(mongoDb.getName());
		Set<String> tables = db.getCollectionNames();
		List<UyenMongoCollection_Table> result= new ArrayList<>();

		for(String coll : tables){
			result.add(new UyenMongoCollection_Table(mongoDb.getName(),coll));
		}
    	
    	return result;
    }
    
    public List<Howto> retrieveRecords(UyenMongoCollection_Table selectedCollection){
    	List<Howto> result = new ArrayList<>();
		if (selectedCollection != null) {

			initDBconnection();
			DB db = mongoClient.getDB(selectedCollection.getDbName());
			DBCollection collection = db.getCollection(selectedCollection.getCollectionName_TableN());
			
			DBCursor cursor = collection.find();
			if (cursor.size() > 0) {
				while (cursor.hasNext()) {
					BasicDBObject record = (BasicDBObject) cursor.next();
					result.add(new Howto(record.get("_id"), 
							record.get(Howto.FIELD_NAME_ID), 
							record.get(Howto.FIELD_NAME_TITLE),
							record.get(Howto.FIELD_NAME_TAGS),
							record.get(Howto.FIELD_NAME_CONTENT)
							
							));
					;
				}
			}
		}
		return result;
    	
    }
    
    public void initTestData(){
    	initDBconnection();
    	DB db = mongoClient.getDB(DB_NAME);
    	DBCollection collection = db.getCollection(Howto.DB_COLLECTION_NAME);
    	
    	DBCursor cursor = collection.find();
		if (cursor.size() == 0) {

			List<DBObject> list = new ArrayList<DBObject>();

			Calendar cal = Calendar.getInstance();

			for (int i = 1; i <= 5; i++) {

				BasicDBObject data = new BasicDBObject();
				data.append("HOW_ID", i);
				data.append("TITLE", "EXAMPLE Of TITLE");
				data.append("TAGS", "LIST OF TAG: JAVA, SQL, ORACLE, TOMCAT, JBOSSs");
				data.append("CONTENT", "Full content");
				data.append("created_date", cal.getTime());
				data.append("changed_date", cal.getTime());

				// +1 day
				cal.add(Calendar.DATE, 1);

				list.add(data);

			}

			collection.insert(list);
		}
    	
    }
    
    public void updateData(Howto tobeUpdated){
    	initDBconnection();
    	DB db = mongoClient.getDB(DB_NAME);
    	DBCollection collection = db.getCollection(Howto.DB_COLLECTION_NAME);
    	
//    	 MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
//    	 MongoDatabase database = mongo.getDatabase(DB_NAME);
//    	 MongoCollection<Document> collection = database.getCollection(Howto.DB_COLLECTION_NAME);
    	
//    	collection.updateOne(Filters.eq("id", tobeUpdated.getId()), Updates.set("likes", 150)); 
//    	collection.update(query, update)
    	
    }
    
   
    
    public void deleteDocument(Integer id){
    	 MongoCollection<Document> collection = init2();
//    	collection.deleteOne(Filters.eq("HOW_ID", id));
    	collection.deleteMany(Filters.eq("HOW_ID", id));
    	
        System.out.println("Document deleted successfully...");
    	
    	
    }
    
    
public void addHowtoDocument(){
		
		// Creating a Mongo client 
	      MongoClient mongo = new MongoClient( "localhost" , 27017 ); 

	      // Creating Credentials 
	      MongoCredential credential; 
	      credential = MongoCredential.createCredential("sampleUser", "myDb", 
	         "password".toCharArray()); 
	      System.out.println("Connected to the database successfully");  
	      
	      // Accessing the database 
	      MongoDatabase database = mongo.getDatabase("UYEN_DB"); 

	      // Retrieving a collection
	      MongoCollection<Document> collection = database.getCollection("HOW_TO");
	      System.out.println("Collection sampleCollection selected successfully");
	      Calendar cal = Calendar.getInstance();

	      Document document = new Document(); 
	    		  
	      
	      document.append("HOW_ID", (new Long(collection.count()+2)).intValue() );
	      document.append("TITLE", "EXAMPLE Of TITLE");
	      document.append("TAGS", "LIST OF TAG: JAVA, SQL, ORACLE, TOMCAT, JBOSSs");
	      document.append("CONTENT", "Full content");
	      document.append("created_date", cal.getTime());
	      document.append("changed_date", cal.getTime());
	      
	      
	      collection.insertOne(document); 
	      System.out.println("Document inserted successfully");   

	}
    
    
}