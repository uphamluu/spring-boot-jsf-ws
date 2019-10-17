package com.phamluu.ws;  
  
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/updateHowtoDocument/{id}")
	public void updateHowtoDocument(@PathVariable Integer id){
		mongoservice.updateHowtoDocument(id); 
	}


	public MongoService getMongoservice() {
		return mongoservice;
	}


	public void setMongoservice(MongoService mongoservice) {
		this.mongoservice = mongoservice;
	}
	


	
	
	
	
	
	

}  