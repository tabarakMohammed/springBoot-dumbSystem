package com.example.Uponinon.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;


@Entity
@Table(name = "messages")
public class message {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NumberFormat
    private Long id_sender;
    
	@NumberFormat
    private Long id_recive;
	
    private Date messsageDate;

 
    @NotBlank(message=("notBlank"))
    @Size(max = 300, message=("max"))
	private String messageContent;
	
	@ManyToOne()
    @JoinColumn(name = "profile", nullable = true)
	private profile profile;

	
	/**
	 * 
	 * A Foreign key refering com.dataORM.dataORM.model.profile
	 *  from com.dataORM.dataORM.model.message
	 *  has the wrong number of column. should be 0

	 */
	
	
	
	public Long getId() {
		return id;
	}
	
	   public Long getId_recive() {
			return id_recive;
		}

	

		public void setId_recive(long id_recive) {
			this.id_recive = id_recive;
		}
	
	
	public long getId_sender() {
		return id_sender;
	}

	public void setId_sender(long id_sender) {
		this.id_sender = id_sender;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public profile getProfile() {
		return profile;
	}

	public void setProfile(profile profile) {
		this.profile = profile;
	}

	public String getMesssageDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
		return formatter.format(messsageDate);
	}

	public void setMesssageDate(Date messsageDate) {
		
		this.messsageDate = messsageDate;
	}
	
	
	
	
	
}

