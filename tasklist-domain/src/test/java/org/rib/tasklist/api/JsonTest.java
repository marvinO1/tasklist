package org.rib.tasklist.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

@RunWith(JUnit4.class)
public class JsonTest {

	
	@Test
	public void toJson() {
		
		User user = new User("hossa", "system");	
		user.setId("123");
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
	    xstream.setMode(XStream.NO_REFERENCES);
	    
	    System.out.println(xstream.toXML(user));				
	}
	
	@Test
	public void fromJson() {
		
		String jsonStr = "{\"org.rib.tasklist.api.User\": {\"createdAt\": \"2014-05-25T13:26:11.941\",\"changedAt\": \"2014-05-25T13:26:11.941\",\"createdBy\": \"system\",\"name\": \"hossa\" }}";		
		
		 XStream xstream = new XStream(new JettisonMappedXmlDriver());
	     xstream.setMode(XStream.NO_REFERENCES);
	     User user = (User) xstream.fromXML(jsonStr);
	     System.out.println(user);		
	}	
}
