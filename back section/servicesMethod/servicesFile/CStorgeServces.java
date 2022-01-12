package servicesMethod.servicesFile;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CStorgeServces implements StorageService {

	private  Path rootLocation;

	
	@Autowired
	public CStorgeServces() {


	}
	
	
	

	@Override
	public void initPathFolder(StorageProperties properties,String Folder) {
		
		properties.setLocation("home/profileImages/"+Folder+"/");
		this.rootLocation = Paths.get(properties.getLocation());
		
	}
	

	

	@Override
	public String store(MultipartFile file, String folderName) throws StorageException {
		
		
		String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
		String filename = StringUtils.cleanPath(folderName+'.'+ext);

		
		try {
			
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
				
			}
			
			if (filename.contains("..")) {
				
				// This is a security check
				throw new StorageException(
						"Cannot store file with relative path outside current directory "
								+ filename);
				
			}
			
		
		///	resize(file, rootLocation.resolve(filename), 400, 400);
			
			
			try (InputStream inputStream = file.getInputStream()) {	
				
				 Image image = ImageIO.read(inputStream);
			    BufferedImage bi = this.createResizedCopy(image, 180, 180, true);
		        ImageIO.write(bi, "jpg", new File(rootLocation.resolve(filename).toString())); 
		        return rootLocation.resolve(filename).toString();
			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}
	

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}
	


	@Override
	public Path load(String filename) {
		

		Path path =  rootLocation.resolve(filename +".jpg").normalize();
//		try {
//			Resource r = new UrlResource(path.toUri());
//
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
        return path;		

	}

	@Override
	public Resource loadAsResource(String filename) {

		return null;
	}

	@Override
	public void deleteAll() {

		FileSystemUtils.deleteRecursively(rootLocation.toFile());

	}

	
	@Override
	public void deleteOnce(String filename) {
		
		FileSystemUtils.deleteRecursively(rootLocation.resolve(filename).toFile());
		
		

		
	}
	
	BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
	  
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		
	    BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
	    Graphics2D g = scaledBI.createGraphics();
	
	    if (preserveAlpha) {
	        g.setComposite(AlphaComposite.Src);
	 
	    }
	    
	    
	    g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
	    g.dispose();
	    return scaledBI;
	}
	
}
