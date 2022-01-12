package com.example.Uponinon.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_forgot_password")
public class userForgotPassword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Size(min= 0 , max = 72)
	private String token;

	@Column(name="token_date")
	private LocalDateTime tokenDate;

	@OneToOne()
	@JoinColumn(name = "user_id")
	private user user;

	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getTokenDate() {
		return tokenDate;
	}

	public void setTokenDate(LocalDateTime tokenDate) {
		this.tokenDate = tokenDate;
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}
	

}
