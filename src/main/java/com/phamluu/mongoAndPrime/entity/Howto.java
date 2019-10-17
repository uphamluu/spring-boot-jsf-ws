package com.phamluu.mongoAndPrime.entity;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

public class Howto implements Serializable {
	
	public static final String DB_COLLECTION_NAME = "HOW_TO";
	public static final String FIELD_NAME_ID = "HOW_ID";
	public static final String FIELD_NAME_TITLE = "TITLE";
	public static final String FIELD_NAME_TAGS = "TAGS";
	public static final String FIELD_NAME_CONTENT = "CONTENT";
	public static final String FIELD_NAME_CREATED_DATE = "CREATED_DATE";
	public static final String FIELD_NAME_CHANGED_DATE = "CHANGED_DATE";
	
	private String internalID;
	private Integer id;
	private String title;
	private String tags;
	private String content;
	private Date createdDate;
	private Date changedDate;
	
	public Howto(String internalID, Integer id, String title, String tags, String content, Date createdDate, Date changedDate) {
		super();
		this.internalID = internalID;
		this.id = id;
		this.title = title;
		this.tags = tags;
		this.content = content;
		this.createdDate = createdDate;
		this.changedDate = changedDate;
	}
	
	public Howto(Object internalID, Object id, Object title, Object tags, Object content) {
		super();
		this.internalID = ((ObjectId) internalID).toString();
		this.id = (Integer)id;
		this.title = (String)title;
		this.tags = (String)tags;
		this.content = (String)content;
//		this.createdDate = createdDate;
//		this.changedDate = changedDate;
 
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}

	public String getInternalID() {
		return internalID;
	}

	public void setInternalID(String internalID) {
		this.internalID = internalID;
	}
	
	
	
	
	
	
	
	
	
//	data.append("HOW_ID", i);
//	data.append("TITLE", "EXAMPLE Of TITLE");
//	data.append("TAGS", "LIST OF TAG: JAVA, SQL, ORACLE, TOMCAT, JBOSSs");
//	data.append("CONTENT", "Full content");
//	data.append("created_date", cal.getTime());
//	data.append("changed_date", cal.getTime());
	

}
