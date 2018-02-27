package com.personaldata.model;

public class PublicDataBuilder {
    
	private String gender;
	private String birthDate;
	private String birthLocation;
	private String nativeLanguage;
	private String religion;
	private String IQ;
	private String height;
	private String weight;
	private String shoeSize;
	private String doctor;
	private String bpm;
	private String bloodPressure;
    
    
    public PublicDataBuilder withGender(String gender) {
    		this.gender = gender;
    		return this;
    }
    
    public PublicDataBuilder withBirthDate(String birthDate) {
    		this.birthDate = birthDate;
    		return this;
    }
    
    public PublicDataBuilder withBirthLocation(String birthLocation) {
    		this.birthLocation = birthLocation;
    		return this;
    }
    
    public PublicDataBuilder withNativeLanguage(String nativeLanguage) {
    		this.nativeLanguage = nativeLanguage;
    		return this;
    }
    
    public PublicDataBuilder withReligion(String religion) {
    		this.religion = religion;
    		return this;
    }
	    
    public PublicDataBuilder withIQ(String IQ) {
    		this.IQ = IQ;
    		return this;
    }
    
    public PublicDataBuilder withHeight(String height) {
    		this.height = height;
    		return this;
    }
	
    public PublicDataBuilder withWeight(String weight) {
		this.weight = weight;
		return this;
    }
    
    public PublicDataBuilder withShoeSize(String shoeSize) {
		this.shoeSize = shoeSize;
		return this;
    }
    
    public PublicDataBuilder withDoctor(String doctor) {
		this.doctor = doctor;
		return this;
    }
    
    public PublicDataBuilder withBpm(String bpm) {
		this.bpm = bpm;
		return this;
    }
    
    public PublicDataBuilder withBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
		return this;
    }
	
    public PublicData build() {
    		PublicData p = new PublicData();
		p.setGender(gender);
		p.setBirthDate(birthDate);
		p.setBirthLocation(birthLocation);
		p.setNativeLanguage(nativeLanguage);
		p.setReligion(religion);
		p.setIQ(IQ);
		p.setHeight(height);
		p.setWeight(weight);
		p.setShoeSize(shoeSize);
		p.setDoctor(doctor);
		p.setBpm(bpm);
		p.setBloodPressure(bloodPressure);
		return p;
	}
}