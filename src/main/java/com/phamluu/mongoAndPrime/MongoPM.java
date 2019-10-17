package com.phamluu.mongoAndPrime;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.phamluu.mongoAndPrime.entity.Howto;
import com.phamluu.mongoAndPrime.entity.UyenMongoCollection_Table;
import com.phamluu.mongoAndPrime.entity.UyenMongoDatabase;
import com.phamluu.mongoAndPrime.service.MongoService;

@Component("dtMongoPM")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestScoped
public class MongoPM implements Serializable {
     
    private List<UyenMongoCollection_Table> collections;
    private List<UyenMongoDatabase> allDatabases;    
    private List<Howto> howtos;
    
    private UyenMongoCollection_Table selectedCollection;
    
    @Autowired
    private MongoService mongoservice;
    
    @PostConstruct
    public void init() {
    	collections= mongoservice.getAllCollectionOfAllDBs();
    	allDatabases= mongoservice.getAllMongoDatabases();
    	reload();
    }
    
    public void reload() {
    	howtos = mongoservice.retrieveRecords(new UyenMongoCollection_Table("UYEN_DB","HOW_TO"));
    	
    }
    
    
     
    public List<UyenMongoDatabase> getAllDatabases() {
		return allDatabases;
	}

	public void setAllDatabases(List<UyenMongoDatabase> allDatabases) {
		this.allDatabases = allDatabases;
	}
	
	public void initTestData(){
		mongoservice.initTestData();
	}
 
   

    public MongoService getService() {
		return mongoservice;
	}

	public void setService(MongoService service) {
		this.mongoservice = service;
	}

	public List<UyenMongoCollection_Table> getCollections() {
		return collections;
	}

	public UyenMongoCollection_Table getSelectedCollection() {
		return selectedCollection;
	}

	public void setSelectedCollection(UyenMongoCollection_Table selectedCollection) {
		this.selectedCollection = selectedCollection;
	}
	
	public void showRecords(){
		howtos = mongoservice.retrieveRecords(selectedCollection);
		
	}
	
	public boolean isHOW_TO(UyenMongoCollection_Table aColec){
		return Howto.DB_COLLECTION_NAME.equalsIgnoreCase(aColec.getCollectionName_TableN());
		
	}

	public List<Howto> getHowtos() {
		return howtos;
	}

	public void setHowtos(List<Howto> howtos) {
		this.howtos = howtos;
	}
	
	public void onRowCancel(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getId());
        FacesMessage msg = new FacesMessage("Edit Cancelled", "Edit Cancelled");
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void onRowEdit(RowEditEvent event) {
		Howto editingHowto = (Howto)event.getObject();
		
		
		
        FacesMessage msg = new FacesMessage("Howto Edited", "Edited how to id: "+editingHowto.getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void deleteDocument(Integer howtoID) {
		FacesMessage msg = new FacesMessage("Deleted HOW_TO with ID:", "Deleted HOW_TO with ID:" + howtoID);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		mongoservice.deleteDocument(howtoID);
	}
	
	

//	private void setCollections(List<MongoCollection> collections) {
//		this.collections = collections;
//	}
    
     
    
}