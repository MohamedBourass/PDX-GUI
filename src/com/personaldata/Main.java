package com.personaldata;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.personaldata.model.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class Main {

	public static void main(String[] args) {

		Person me = new Person();
		me.setFirstName("Mohamed");
		me.setLastName("Bourass");
		
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		String dataJson = xstream.toXML(me);
		//System.out.println(dataJson);
		
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("filename.txt"), "utf-8"));
		    writer.write(dataJson);
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
		
		
	}

}
