package com.example.Uponinon.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
public class profile {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne()
    @JoinColumn(name = "user_id")
	private user user;
	
    private String imageName;
    
        
    @OneToMany( mappedBy = "profile", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<message> message = new HashSet<>();
    

	
	

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

//	public Set<message> getMessage() {
//		return message;
//	}
//
//	public void setMessage(Set<message> message) {
//		this.message = message;
//	}

	
	
	public Set<message> getMessage() {
		return message;
	}

	public void setMessage(Set<message> message) {
		this.message = message;
	}
    
    
    
}

