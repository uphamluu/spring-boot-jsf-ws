package com.phamluu;  
  
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phamluu.mongoAndPrime.service.MongoService;

@RestController  
public class HowtoWS {  
	
	 @Autowired
	 private MongoService mongoservice;
	
	
	@RequestMapping("/addHowtoDocument")
	public void addHowtoDocument(){
		
		mongoservice.addHowtoDocument(); 

	}


	public MongoService getMongoservice() {
		return mongoservice;
	}


	public void setMongoservice(MongoService mongoservice) {
		this.mongoservice = mongoservice;
	}
	


	
	
	
	
	
	

}  