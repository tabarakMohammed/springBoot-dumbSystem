package servicesMethod;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Uponinon.Model.role;
import com.example.Uponinon.Model.user;
//import com.example.Uponinon.Model.users_roles;
import com.example.Uponinon.Model.jpa.roleJpa;
import com.example.Uponinon.Model.jpa.userJpa;
//import com.example.Uponinon.Model.jpa.userRolesJpa;

@Service
public class userServces {

	@Autowired
	userJpa UJ;
	@Autowired
	roleJpa RJ;
	
	
	public String createUser(user user) {
	
		Date date = new Date();
	
		
		try {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String passEnco = encoder.encode(user.getPassword());
		user.setPassword(passEnco);
		
		
		
//		
		role myRole = RJ.findByAuthority("USER");	
		Set<role> roleSet = new HashSet<role>();
		roleSet.add(myRole);
		user.setRoles(roleSet);
		user.setRegisterDate(date);
		UJ.save(user);
		
		
//        users_roles myAuth = new users_roles();
//        myAuth.setRole_id(myRole.getId());
//        myAuth.setUser_id(user.getId());
//		URJ.save(myAuth);
		
		
		return "done";
		
		}catch (Exception e) {
			// TODO: handle exception
			return "someError"+e.getMessage();
		}
	}
	
	
	
	public String resetPassword(user user, String password) {
		return null;	
	}
	
	
	  public String ChangeRole(user user, String Role) {
		  
		  
				try {
					
						role myRole = RJ.findByAuthority(Role);
						System.out.println(myRole.getAuthority());
						role myRole1 = RJ.findByAuthority("USER");	
						
						Set<role> roleSet = new HashSet<role>();
						
						roleSet.add(myRole);
						roleSet.add(myRole1);

						user.getRoles().clear();
						user.setRoles(roleSet);
						UJ.save(user);
					  
					  return "";
				} catch (Exception e) {
					
					return e.toString();
					// TODO: handle exception
				}
	    }
		
	  
	  
		
	  public String deleteRole(user user) {
		  
				try {
					     
						role myRole = RJ.findByAuthority("USER");	
						Set<role> roleSet = new HashSet<role>();
						roleSet.add(myRole);
						user.getRoles().clear();
						user.setRoles(roleSet);
						UJ.save(user);
					  
					  return "";
				} catch (Exception e) {
					
					return e.toString();
					// TODO: handle exception
				}
	    }
		
	  
	  public List<user> getDisableUser() {
          
		  List<user> users = UJ.disabledUsers();
		  
		  
		  return users;
	  }
	  
		 public List<user> getAllUser() {		          
				  List<user> users = UJ.findAll();					  
				  return users;
			  }
	  
	  
	  public void deleteUsers( String username) {
		  
		   Long id = UJ.findByUsername(username).getId();
		   UJ.deleteById(id);
		
	  }
	  
	  
}
