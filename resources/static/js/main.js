              $("#mypreload").hide();


$( document ).ready(function() {
	  
	  // SUBMIT FORM
	    $("#messageForm").submit(function(event) {
	    // Prevent the form from submitting via the browser.
	    event.preventDefault();
	    var urI = "save";
	    ajaxPost(urI);
	  });
	   
	
	    function ajaxPost(urI){
	      
	     
	    		// PREPARE FORM DATA
	        var formData = {
	        		
	        		messageContent : $("#txtmsg").val(),
	        		id_recive      : $("#id_R").val()
	        	
	              }          
	      // DO POST

	        $.ajax({
	      type : "POST",
	      contentType : "application/json",
	      url : "/api/"+urI,
	      data : JSON.stringify(formData),
	      dataType : 'json',
	    
	      beforeSend: function() {
	    	  
	    	  var bb = document.getElementById('saveBtn');
	    	  bb.disabled= true;
    		  $("#mypreload").show();

           
        
	     
	      },
	      
	      complete: function() {
	    	  
	    	  bb.disabled= false;
              $("#mypreload").hide();

	      },
	    	  
	      success: function (result) {    
	        if(result.status == "Done"){
	        	  $('#msgShow').html("<p> was sent :- " + result.data + "</p>   <br> <br>   <br> <br>    ");
	         
	        }else{
	          $("#msgShow").html("<strong>Error</strong>");
	        }
	        
	        console.log(result);
	        
	      },
	      error: function (e) {
	        alert("Error!")
	        console.log("ERROR: ", e);
	      }
	    } );
	      
	      // Reset FormData after Posting
	      resetData();
	 
	    }
	    
	    function resetData(){
	      $("#txtmsg").val("");
	    }
	})
	
	

			img = new Image();
		
		function preview_image(event) 
	                                	{
		 var reader = new FileReader();
		 reader.onload = function()
		
		 {
		  var output = document.getElementById('output_image');
		  output.src = reader.result;
		  img.src = reader.result;
		 }
		 reader.readAsDataURL(event.target.files[0]);
		}
	
		
		
		
		 function setReport(MessageContent,msgId,idResever)
		 {
			 $("#msgReportID").val(msgId) ;
			 $("#idProfilr").val(idResever);		 
     		 $("#moadelP").text(MessageContent);
			
     		
			 
		 }
	
		 $("#closeReportBtn").click(function(event) {
             $('#idBtn').attr("disabled", false);

		 });
		 // Report button
		    $("#idBtn").click(function(event) {
		    // Prevent the form from submitting via the browser.
		    event.preventDefault();
		    var urI = "report";
		    var messageId = $("#msgReportID").val();
		    var profileId = $("#idProfilr").val();
            var data = {
 	        		
		    		messageId : messageId,
		    		profileId : profileId
 	        	
 	              }     
  
		    var htmlx =  $('#reportBody').text('');
     		 $("#moadelP").text('شكرا جزيلاً');
             $('#idBtn').attr("disabled", true);
		 
           
		    
		 //   alert( messageId + "go head" + profileId);
		    ajaxReport(urI,data,htmlx);

		  });
		    
		   
		    function ajaxReport(urI,data,htmlx){
		    	
		
	 	   
		    
		    $.ajax({
	 	      type : "POST",
	 	      contentType : "application/json",
	 	      url : "/api/"+urI,
	 	      data : JSON.stringify(data),
	 	      dataType : 'json',
	 	    
	 	      success: function (result) {    
	 	      
	 	    	  if(result.status == "Done"){
                  
	 	    		 htmlx


	 	        } else {
	 	        	
	 	        
	 	        }
	 	        
	 	        console.log(result);
	 	        
	 	      },
	 	   
	 	      error: function (e) {
	 	        alert("sorry something went wrong, Error!")
	 	     
	 	      }
	 	  
		    });
	 	      
	 	  
	 	 
		    }
	 	    
		   
		    
		   
		 


