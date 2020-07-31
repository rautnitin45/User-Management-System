package com.nraut;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.nraut.model.UserDetails;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAuthenticationServiceApplicationTests {

	 @Autowired
     private RestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port;      
     }
    
     
	@Test
	public void contextLoads() {
		System.out.println(getRootUrl());
	}

	  /*@Test
	    public void testGetUserById() {
	        UserDetails userDetails = restTemplate.getForObject(getRootUrl() + "/edit/1", UserDetails.class);
	        System.out.println(userDetails.getName());
	        assertNotNull(userDetails);
	    }*/
}
