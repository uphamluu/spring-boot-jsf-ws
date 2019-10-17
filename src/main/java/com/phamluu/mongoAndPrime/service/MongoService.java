package com.phamluu.mongoAndPrime.service;

import java.util.List;

import com.phamluu.mongoAndPrime.entity.Howto;
import com.phamluu.mongoAndPrime.entity.UyenMongoCollection_Table;
import com.phamluu.mongoAndPrime.entity.UyenMongoDatabase;

//import com.phamluu.mongoAndPrime.entity.Howto;
//import com.phamluu.mongoAndPrime.entity.UyenMongoCollection_Table;
//import com.phamluu.mongoAndPrime.entity.UyenMongoDatabase;

public interface MongoService {
	public void addHowtoDocument();
	public List<UyenMongoDatabase>  getAllMongoDatabases();
	public List<UyenMongoCollection_Table> getAllCollectionOfAllDBs();
	public List<Howto> retrieveRecords(UyenMongoCollection_Table uyenMongoCollection_Table);
	public void initTestData();
	public void deleteDocument(Integer howtoID);

}
