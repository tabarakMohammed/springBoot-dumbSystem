package servicesMethod.servicesFile;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;


public interface StorageService {

	
	void initPathFolder(StorageProperties properties,String Folder);
	String store(MultipartFile file, String FolderName);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);
    
	void deleteOnce(String filename);
	
	void deleteAll();
	

}