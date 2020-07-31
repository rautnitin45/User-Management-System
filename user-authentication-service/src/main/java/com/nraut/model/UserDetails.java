package com.nraut.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Nitin
 *
 */
@Entity
public class UserDetails {

	private Long id;
	private String name;
	private String type;
	
	public UserDetails(Long id, String name, String type) {
	        this.id = id;
	        this.name = name;
	        this.type = type;
	    }
	 
	 public UserDetails() {
	 }
	
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
