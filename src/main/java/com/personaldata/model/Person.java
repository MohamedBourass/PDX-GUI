package com.personaldata.model;

/**
 * Model class for a Person.
 *
 * @author Mohamed Bourass
 */
public class Person {
	
	private PrivateData privateData;
	
	private PublicData publicData;
    
    public Person() {
    		privateData = new PrivateData();
    		publicData = new PublicData();
    }

	public PrivateData getPrivateData() {
		return privateData;
	}

	public void setPrivateData(PrivateData privateData) {
		this.privateData = privateData;
	}

	public PublicData getPublicData() {
		return publicData;
	}

	public void setPublicData(PublicData publicData) {
		this.publicData = publicData;
	}
}