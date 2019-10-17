package com.phamluu.mongoAndPrime.entity;

import java.io.Serializable;

public class UyenMongoCollection_Table implements Serializable {
	private String dbName;
	private String collectionName_TableN;
	

    public UyenMongoCollection_Table(String dbName, String name) {
    	this.dbName = dbName;
    	this.collectionName_TableN = name;
	}
    
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
 
    public UyenMongoCollection_Table() {}

	public String getCollectionName_TableN() {
		return collectionName_TableN;
	}

	public void setCollectionName_TableN(String collectionName) {
		this.collectionName_TableN = collectionName;
	}

}