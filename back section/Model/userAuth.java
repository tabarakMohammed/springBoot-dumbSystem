package com.example.Uponinon.Model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import validation.usernameNotExistOrIsEnablied;

public class userAuth {


	@NotBlank(message=("notBlank"))
    @Size(min = 1, max = 100,message=("max"))
    @usernameNotExistOrIsEnablied(message=("usernameNotExist"))
	private String username;

	@NotBlank(message=("notBlank"))
    @Size(max = 100,message=("max"))
	@Email(message=("emailNotRight"))
	private String email;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
