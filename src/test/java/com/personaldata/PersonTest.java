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

		Person me = new Person();
		me.setFirstName("Mohamed");
		me.setLastName("Bourass");
		me.setEmail("test@gmail.com");
		me.setPostalAddress("1 avenue des cerisiers, 91800 Brunoy, FRANCE");
		me.setPhoneNumber("+33678124401");
		me.setIpAddress("172.16.254.1");
		me.setMacAddress("5E:FF:56:A2:AF:15");
		
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		String dataJson = xstream.toXML(me);
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