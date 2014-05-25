package org.rib.tasklist.rest;

import java.util.Arrays;




import org.junit.Before;
import org.junit.Test;
import org.rib.tasklist.api.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class UserTest {

	private HttpHeaders headers = new HttpHeaders();	
	private RestTemplate template;
	
	@Before
	public void before() {
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    template = new RestTemplate();
	    
	    // Set the request factory. 
	    // IMPORTANT: This section I had to add for POST request. Not needed for GET
	    // template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

	    // Add converters
	    // Note I use the Jackson Converter, I removed the http form converter 
	    // because it is not needed when posting String, used for multipart forms.
	    // template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	}
	
	
	@Test
	public void thatUserCanBeAddedAndRead() {
		
		
		User user = new User("beat", "system");	
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
	    xstream.setMode(XStream.NO_REFERENCES);
	    
	    String userJson = xstream.toXML(user);
	    
	    
	    userJson = "{\"createdAt\":\"2014-05-25T14:49:06.738\",\"changedAt\":\"2014-05-25T14:49:06.738\",\"createdBy\":\"system\",\"name\":\"beat\"}";
		HttpEntity<String> requestEntity = new HttpEntity<String>(userJson, headers);
		
		ResponseEntity<String> entity = template.postForEntity("http://localhost:8080/tasklist/users", requestEntity, String.class);
		
		String path = entity.getHeaders().getLocation().getPath();
        System.out.println(path);
        System.out.println(entity.getStatusCode());
	}
	
	@Test
	public void thatUserCanBeAddedV2() {
		
		User user = new User("beat", "system");		
		User createdUser = template.postForObject("http://localhost:8080/tasklist/users", user, User.class);
				
        System.out.println(createdUser);        
	}
	
	@Test
	public void thatUserCanBeRead() {
						
		String userStr = template.getForObject("http://localhost:8080/tasklist/users/1401017989471", String.class);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"org.rib.tasklist.api.User\": ").append(userStr).append("}");
		
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
	    xstream.setMode(XStream.NO_REFERENCES);
	    User user = (User) xstream.fromXML(sb.toString());
		
        System.out.println(user);    
              
	}
	
	
	
	
}
