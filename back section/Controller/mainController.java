
package com.example.Uponinon.Controller;

import java.io.IOException;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Uponinon.Model.message;
import com.example.Uponinon.Model.profile;
import com.example.Uponinon.Model.user;
import com.example.Uponinon.Model.jpa.messageJpa;
import com.example.Uponinon.Model.jpa.profileJpa;
import com.example.Uponinon.Model.jpa.userJpa;


import servicesMethod.servicesFile.StorageFileNotFoundException;
import servicesMethod.servicesFile.StorageProperties;
import servicesMethod.servicesFile.StorageService;

@Validated
@Controller
@RequestMapping("/")
public class mainController {
	@Autowired
   private userJpa userRepo;
   
	
	@Autowired
    private profileJpa pRepo;
	@Autowired
	private StorageService sr;
	@Autowired
	private messageJpa Mjpa;
	
	String imgPath;
	
	
	
	   @ExceptionHandler(javax.validation.ConstraintViolationException.class)
	    public String constraintViolationException(HttpServletResponse response) throws IOException {
		   return "welcome";
		   
	    }
	   
	
	
	@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msgs", "hello");
        
       return "welcome";
     // return"email-simple";
     //  return "createProfile";
	}
    
 
	@GetMapping("about")
    public String about( ) {        
       return "about";
	}

    
   
    @GetMapping(value = "search/{id}/{PN}")
    public String getUserProfile(@PathVariable("id") Long THid, @PathVariable("PN") int pageNumber,Model model){
  
    	
    try {
		Optional<user> userProfile = this.userRepo.findById(THid);
		user user = userProfile.get();
		Optional<user> op = Optional.of(user);
		imgPath = pRepo.findByUser(op).getImageName(); 
		//String mypath = "/"+user.getUsername()+"/"+user.getUsername()+".jpg";
		  
		
		      Pageable firstPageWithTwoElements = PageRequest.of(pageNumber, 5,Sort.by("id").descending());
		 
			  profile pp = pRepo.findByUser(op);
			   
			   Optional<profile> opw = Optional.of(pp);		  
			  Page<message> list1 = Mjpa.findAllByProfile(opw, firstPageWithTwoElements);

			  
		
		model.addAttribute("totalPage", list1.getTotalPages());
		model.addAttribute("thisPage", pageNumber);
		model.addAttribute("totalElement", list1.getTotalElements());

		model.addAttribute("messageList", list1);
		model.addAttribute("username", user.getUsername());
		model.addAttribute("id_recive", user.getId());
		model.addAttribute("message", new message());
		model.addAttribute("imageUrl", imgPath);
		  return "profiles";
		  
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
        return "welcome";

	}
    
    }
    
   
  
    @GetMapping("welcome")
    public String home(Model model) {
        return "welcome";
    }
    
    @GetMapping("search")
    public  String search(Model model,  @NotBlank  @Size(max=10)  @RequestParam("username")  String username ) {

   
    	try {
    		
    	user userObject = userRepo.findByUsername(username);
		
    	Long id = userObject.getId();
			
       		message msg = new message();
       		

		    model.addAttribute("message", msg);


			return "redirect:search/" + id +"/"+0;
			
    	} catch (Exception e) {
			// TODO: handle exception
    		// add message to search page about user not exist
			return "redirect:welcome";
			

		}
    	
		}

    @GetMapping("createProfile")
    public String CreateProfile(  Model model, 
    	    final RedirectAttributes redirectAttributes)
    {
    	
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       	 user userObj = userRepo.findByUsername(authentication.getName());
       	 
        
       	 if( pRepo.existsCarByUserId(userObj.getId())) {
       		 
     
       		
       		 return "redirect:search/" + userObj.getId() + "/" + 0;
       		
       		 
       	 } else {
       		 
			model.addAttribute("profile", new profile() );
			
	        return "createProfile";
       	 }
    }


    @PostMapping("/saveProfile")
	public String saveProfile(@Valid profile userpro, BindingResult bindingResult) {

   	
     
	 if(bindingResult.hasErrors()){
		 return"createProfile";
		 }
	 
	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
   	 user userObj = userRepo.findByUsername(authentication.getName());
     
	    userpro.setUser(userObj);
        
	    pRepo.save(userpro);
	
		return"redirect:/welcome";
		
   	 
	}
    
    @GetMapping("uploadForm")
    public String uploadForm( ) {
      
        return "uploadForm";
    }
    
	
	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes,Model model) {
		
		//Check Image Size
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String userName = authentication.getName();
	     StorageProperties ss = new StorageProperties();
         sr.initPathFolder(ss, userName);
		  sr.store(file,userName);
		  String mypath = "/"+userName+"/"+userName+".jpg";
		  
		  user userObj = userRepo.findByUsername(authentication.getName());
		         Optional<user> op = Optional.of(userObj);
		        
	      profile noExits = pRepo.findByUser(op);
	      noExits.setImageName(mypath);
		  pRepo.save(noExits);
		
		 
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		this.imgPath = mypath;
		
		 return "redirect:search/"+userObj.getId() +"/"+0;
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
    
 
	


}
