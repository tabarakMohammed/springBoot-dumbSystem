package servicesMethod.servicesFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	
	private String location;

	public String getLocation() {
		
		return location;
	}

	public void setLocation(String location) {
	
		this.location = location;
	
		Path uploadPath = Paths.get(location);
	
		if(!Files.exists(uploadPath)) {
		
			try {
				
				Files.createDirectories(uploadPath);
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
