package com.epam.module4.model;

import java.util.HashMap;

public abstract class BankObject {
	public static final String OBJECT_ID = "id";
	public static final String CITY = "city";
	public static final String PHONE = "phone";
	
	protected HashMap<String, Object> properties;
	
	public BankObject(Integer id){
		properties = new HashMap<String, Object>();	
		properties.put(OBJECT_ID, id);
	}
	
	public Integer getId() {
		return (Integer) properties.get(OBJECT_ID);
	}

	public void setId(Integer id) {
		properties.put(OBJECT_ID, id);
	}

}
