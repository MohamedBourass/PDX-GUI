package com.personaldata;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.junit.Test;

import com.personaldata.model.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class PersonTest {

	@Test
	public void objectToJsonTest() {

		Person p = new Person();
		
		//Set private data
		p.getPrivateData().setFirstName("Mohamed");
		p.getPrivateData().setLastName("Bourass");
		p.getPrivateData().setEmail("test@gmail.com");
		p.getPrivateData().setPostalAddress("1 avenue des cerisiers, 91800 Brunoy, FRANCE");
		p.getPrivateData().setPhoneNumber("+33678124401");
		p.getPrivateData().setIpAddress("172.16.254.1");
		p.getPrivateData().setMacAddress("5E:FF:56:A2:AF:15");
			
		//Set public data
		p.getPublicData().setGender("M");
		p.getPublicData().setBirthDate("13/10/1983");
		p.getPublicData().setBirthLocation("Paris");
		p.getPublicData().setNativeLanguage("french");
		p.getPublicData().setReligion("");
		p.getPublicData().setIQ("");
		p.getPublicData().setHeight("1.84");
		p.getPublicData().setWeight("93");
		p.getPublicData().setShoeSize("45");
		p.getPublicData().setDoctor("John Henry");
		p.getPublicData().setBpm("65");
		p.getPublicData().setBloodPressure("13");
		
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		String dataJson = xstream.toXML(p);
		//System.out.println(dataJson);
		
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("src/test/java/com/personaldata/mbourass.json"), "utf-8"));
		    writer.write(dataJson);
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
}