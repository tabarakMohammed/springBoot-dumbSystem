package com.example.Uponinon.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Uponinon.Model.ResponseJson;
import com.example.Uponinon.Model.jsonMesssageReported;
import com.example.Uponinon.Model.message;
import com.example.Uponinon.Model.messageReporter;
import com.example.Uponinon.Model.user;
import com.example.Uponinon.Model.jpa.messageJpa;
import com.example.Uponinon.Model.jpa.userJpa;

import servicesMethod.controlCenterServces;
import servicesMethod.userServces;

@RestController
@RequestMapping("/CC")
public class ControlCenterAPI {

	@Autowired
	 private userJpa userRepo;
	
	@Autowired
	private messageJpa MessageRepo;
	

	@Autowired
	private userServces nUserServces;
	
	@Autowired
	private controlCenterServces CCS;
	
	@GetMapping("/getAllDisableUser")
	 public @ResponseBody List<user> getAllDisable( ) {
		
		 try {

		 return nUserServces.getDisableUser();
		 } catch(Exception e) {
		 return null;
		 }
		
	}
	 
	 
	 @GetMapping("/getAllUser")
	 public @ResponseBody List<user> getAllUser( ) {
		
		 try {

		 return nUserServces.getAllUser();
		 } catch(Exception e) {
		 return null;
		 }
		
	}
	
	
	@GetMapping("/deleteUnactiveUser")
	 public @ResponseBody ResponseJson deleteUnactiveUser( @RequestParam String username) {
		
		try {
			nUserServces.deleteUsers(username);
			ResponseJson Response = new ResponseJson("ok" , " was deleted");
			return Response;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseJson Response = new ResponseJson("error" , e.getMessage());
			return Response;
		}
		
	}
	
	
	
	 @GetMapping(value ="/ChangeRole")
	 public @ResponseBody ResponseJson changeRole( @NotBlank  @Size(max=10) @RequestParam  String username,@NotBlank
			 @RequestParam Integer roleId ) {
		
		  if(roleId > 5 || 0 > roleId){
			  ResponseJson Response = new ResponseJson("Error" , "input its wrong");
				 return Response;
		  }else {
	    		 
		 String Role = "USER";
		 
		  switch (roleId) {
		  
			 	case 1:  Role = "USER";
	                  break;
			 	case 2:  Role = "ADMIN";
                       break;
			 	case 3:  Role = "manager";
                       break;
			 	case 4:  Role = "CREATOR";
                       break;
			 	case 5:  Role = "EDITOR";
                       break;
		  }
		 
		 try {
			user User = userRepo.findByUsername(username);
		    nUserServces.ChangeRole(User, Role);
		 
		 } catch (Exception e) {
			 ResponseJson Response = new ResponseJson("Error" , "the user is not defened");
			 return Response;
		}
		 
		
		 ResponseJson Response = new ResponseJson("ok" , "the job was done successfully" + username + Role );
		 return Response;
		  }  
	 }
	 
	 
	 
	 
	 @GetMapping(value ="/deleteRole")
	 public @ResponseBody ResponseJson deleteRole(@NotBlank  @Size(max=10) @RequestParam  String username) {
		
		
		 
		 try {
			user User = userRepo.findByUsername(username);
		    nUserServces.deleteRole(User);
		
		 } catch (Exception e) {
			
			 ResponseJson Response = new ResponseJson("Error" , "the user is not defened");
			 return Response;
		}
		
		 ResponseJson Response = new ResponseJson("ok" , "the job was done successfully" + username  );
		 return Response;
	 }
	 
	 
	 
	 
	 
	 
	 @GetMapping(value ="/ShowReportMsg")
	 public @ResponseBody List<jsonMesssageReported> ShowReportMsg( @RequestParam int pageNumber) {
	 
	 
		  List<jsonMesssageReported> listMessage = new ArrayList<jsonMesssageReported>();	 
		  List<messageReporter>  listReported =  CCS.getMsgREported(pageNumber);
		
		  for (messageReporter messageReporter : listReported) {	 
			  jsonMesssageReported objR = new jsonMesssageReported();
			  Optional<message> msg  = MessageRepo.findById(messageReporter.getMessageId());		   
			  objR.setId(msg.get().getId());
			  objR.setId_recive(msg.get().getId_recive());
			  objR.setId_sender(msg.get().getId_sender());
			  objR.setMessageContent(msg.get().getMessageContent());
			  objR.setReportedUserNameProfile(msg.get().getProfile().getUser().getUsername());		
			
			  try {
				objR.setMesssageDate( msg.get().getMesssageDate() );
			    } catch (Exception e) {
				objR.setMesssageDate( "null" );
			     }
			
			  listMessage.add(objR);			 
		    }

		 return listMessage;
	 }
	 
	 
	 @GetMapping(value ="/editingReportMsg")
	 public @ResponseBody ResponseJson EditReportMsg( 
			 @RequestParam long msgId,
			 @RequestParam String newMessage,
			 @RequestParam String username)  {
	 
		 
    
		 try {
			
			  MessageRepo.messsagesReportedUpdate(newMessage  + username, msgId); 
			
			 ResponseJson Response = new ResponseJson("good" , "good Job thank");
			 return Response;
			
		} catch (Exception e) {
			ResponseJson Response = new ResponseJson("error" , e.getMessage());
			//e.printStackTrace();
			 return Response;
		}

		 
	 }
	 
	 

	
}
