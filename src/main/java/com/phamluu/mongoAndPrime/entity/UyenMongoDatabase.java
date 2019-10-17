package com.phamluu.mongoAndPrime.entity;

import java.io.Serializable;

public class UyenMongoDatabase implements Serializable {
		private String databaseName_schemaName;
	    
	    public UyenMongoDatabase(String name) {
			this.databaseName_schemaName = name;
		}

		
		public String getName() {
			return databaseName_schemaName;
		}

		public void setName(String name) {
			this.databaseName_schemaName = name;
		}
	     
}
