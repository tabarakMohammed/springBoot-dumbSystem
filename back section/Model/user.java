package com.example.Uponinon.Model;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import validation.checkUsername;
import validation.emailExist;

@Entity
@Table(name = "users")
public class user {
	
   

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message=("notBlank"))
    @Column(unique = true)
    @Size(max = 100,message=("max"))
    @checkUsername(message=("nameTaken"))
    private String username;

    
    @NotBlank(message=("notBlank"))
    @Size(max = 50,message=("max"))
    @Column(unique = true)
    @Email(message=("emailNotRight"))
    @emailExist(message=("emailTaken"))
    private String email;
    
    @NotBlank(message=("notBlank"))
    @Size(max = 30,message=("max"))
    private String firstName;
   
    @NotBlank(message=("notBlank"))
    @Size(max = 30,message=("max"))
    private String lastName;
    
    @NotBlank(message=("notEmpty"))
    private String password;
   
    
    private Boolean isEnabled = false;
	
    private Date registerDate;


	@OneToOne(mappedBy = "user",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
    private profile profile;
	
	@OneToOne(mappedBy = "user",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
    private userForgotPassword us;
    
 
	@OneToOne(mappedBy = "user",
	cascade = CascadeType.ALL)
	private userVerifyEmail user;
	


	@ManyToMany( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<role> roles = new HashSet<>();
 

	public Set<role> getRoles() {
		return roles;
	}

	public void setRoles(Set<role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		
//		if (password == null || password == "")
//		this.password = password;
//		
//		else
//			
//		if(password != null || password != "") {
//	   PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	   this.password = encoder.encode(password);
//		} 
		
		this.password = password;
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
   
    
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

    
}
