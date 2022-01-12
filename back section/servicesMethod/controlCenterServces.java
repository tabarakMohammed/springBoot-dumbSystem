package servicesMethod;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Uponinon.Model.messageReporter;
import com.example.Uponinon.Model.jpa.messageReporterRepo;

@Service
public class controlCenterServces {
	
	@Autowired
	private messageReporterRepo msgReportedRepo;
	
	public  List<messageReporter> getMsgREported(int pageNumber) {
		
		 
	     try {
			Pageable firstPageWithTwoElements = PageRequest.of(pageNumber, 10,Sort.by("id").descending());

			
			 Page<messageReporter> themessageReporter = msgReportedRepo.findAll(firstPageWithTwoElements);

			 List<messageReporter>  listMessage = new  ArrayList<messageReporter>();
			  
			  for (messageReporter messageReporter : themessageReporter.getContent()) {	 
				 
				  listMessage.add(messageReporter);

			    }
			return listMessage;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	     
	     
	     
		
		
	}
	
	
	
	public void backupdata() {}

	
	
	
	
	
}
