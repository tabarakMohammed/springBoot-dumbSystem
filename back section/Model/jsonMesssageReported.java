package com.example.Uponinon.Model;

public class jsonMesssageReported {

	

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getId_sender() {
		return id_sender;
	}


	public void setId_sender(Long id_sender) {
		this.id_sender = id_sender;
	}


	public Long getId_recive() {
		return id_recive;
	}


	public void setId_recive(Long id_recive) {
		this.id_recive = id_recive;
	}


	public String getMessageContent() {
		return messageContent;
	}


	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}


	public String getReportedUserNameProfile() {
		return ReportedUserNameProfile;
	}


	public void setReportedUserNameProfile(String UserNameProfile) {
		this.ReportedUserNameProfile = UserNameProfile;
	}

	
	

	public String getMesssageDate() {
		return messsageDate;
	}


	public void setMesssageDate(String string) {
		this.messsageDate = string;
	}




	private Long id;
	
    private Long id_sender;
   
	
    private Long id_recive;
 
 
	private String messageContent;
	
	
	private String ReportedUserNameProfile;
	
	private String messsageDate;
	
	
}
