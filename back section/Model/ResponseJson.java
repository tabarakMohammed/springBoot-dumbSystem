package com.example.Uponinon.Model;

public class ResponseJson {

	 private String status;
	  private String data;
	  
	  public ResponseJson(String status, String data){
		    this.status = status;
		    this.data = data;
		    
		  }
		 
		  public String getStatus() {
		    return status;
		  }
		 
		  public void setStatus(String status) {
		    this.status = status;
		  }
		 
		  public Object getData() {
		    return data;
		  }
		 
		  public void setData(String data) {
		    this.data = data;
		  }
}
