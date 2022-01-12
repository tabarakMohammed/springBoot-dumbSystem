

		
 
 
 $("input[type='checkbox']").change(function(event) {
	    if(this.checked) {
     //Do stuff
 	 $('#btnCreate').attr("disabled", false);
 } else {
 	 $('#btnCreate').attr("disabled", true);

 	
 } });


 
 
 
