package com.personaldata.model;

public class PrivateDataBuilder {
		
    private String firstName;
    private String lastName; 
    private String postalAddress;
    private String phoneNumber;
    private String email;
    private String ipAddress;
    private String macAddress;
    
    public PrivateDataBuilder withFirstName(String firstName) {
    		this.firstName = firstName;
    		return this;
    }
    
    public PrivateDataBuilder withLastName(String lastName) {
    		this.lastName = lastName;
    		return this;
    }
    
    public PrivateDataBuilder withPostalAddress(String postalAddress) {
    		this.postalAddress = postalAddress;
    		return this;
    }
    
    public PrivateDataBuilder withPhoneNumber(String phoneNumber) {
    		this.phoneNumber = phoneNumber;
    		return this;
    }
    
    public PrivateDataBuilder withEmail(String email) {
    		this.email = email;
    		return this;
    }
	    
    public PrivateDataBuilder withIPAddress(String ipAddress) {
    		this.ipAddress = ipAddress;
    		return this;
    }
    
    public PrivateDataBuilder withMacAddress(String macAddress) {
    		this.macAddress = macAddress;
    		return this;
    }
	
    public PrivateData build() {
		PrivateData p = new PrivateData();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setPostalAddress(postalAddress);
		p.setPhoneNumber(phoneNumber);
		p.setEmail(email);
		p.setIpAddress(ipAddress);
		p.setMacAddress(macAddress);
		return p;
	}
}