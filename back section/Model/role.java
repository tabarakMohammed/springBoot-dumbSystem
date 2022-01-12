package com.example.Uponinon.Model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;




@Entity
@Table(name = "role")
public class role {
	
    @Column(name = "role_id")
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String authority;

  //  @ManyToMany(mappedBy = "user_role")
    //private ArrayList<user> users = new ArrayList<user>();
    
    
    

    
//	public ArrayList<user> getUsers() {
//		return users;
//	}
//
//	public void setUsers(ArrayList<user> users) {
//		this.users = users;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	
}
    
    
    
    
    
    