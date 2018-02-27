package com.personaldata;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.junit.Test;

import com.personaldata.model.Person;
import com.personaldata.model.PrivateDataBuilder;
import com.personaldata.model.PublicDataBuilder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class PersonTest {

	@Test
	public void objectToJsonTest() {

		Person p = new Person();

		//Set private data
		p.setPrivateData(
			new PrivateDataBuilder()
			.withFirstName("Mohamed")
			.withLastName("Bourass")
			.withEmail("test@gmail.com")
			.withPostalAddress("1 avenue des cerisiers, 91800 Brunoy, FRANCE")
			.withPhoneNumber("+33678124401")
			.withIPAddress("172.16.254.1")
			.withMacAddress("5E:FF:56:A2:AF:15").build()
			);

		//Set public data		
		p.setPublicData(
				new PublicDataBuilder()
				.withGender("M")
				.withBirthDate("13/10/1983")
				.withBirthLocation("Paris")
				.withNativeLanguage("french")
				.withReligion("")
				.withIQ("")
				.withHeight("1.84")
				.withWeight("93")
				.withShoeSize("45")
				.withDoctor("John Henry")
				.withBpm("65")
				.withBloodPressure("13").build()
			);

		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		String dataJson = xstream.toXML(p);

		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("src/test/java/com/personaldata/mbourass.json"), "utf-8"));
			writer.write(dataJson);
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/*ignore*/
			}
		}
	}
}