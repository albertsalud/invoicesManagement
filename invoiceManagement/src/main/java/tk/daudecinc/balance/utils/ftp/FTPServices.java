package tk.daudecinc.balance.utils.ftp;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Component
public class FTPServices {
	
	@Value("${im.ftp.host}")
	private String hostname;
	
	@Value("${im.ftp.username}")
	private String username;
	
	@Value("${im.ftp.password}")
	private String password;
	
	
	@Getter
	public class FTPServicesResultBean {
		
		private FTPServicesResultBean() {}
		
		private FTPServicesResultBean(String destinationFolder, String destinationName, 
				MultipartFile file) {
			
			this.destinationFolder = destinationFolder;
			this.destinationName = destinationName;
			this.file = file;
		}
		
		private String destinationFolder;
		private String destinationName;
		private String errorMessage;
		private MultipartFile file;
		private boolean ok;
		private InputStream inputStream;
	}
	
	public FTPServicesResultBean saveFile(String destinationFolder, String destinationName, 
			MultipartFile file) {
		
		FTPServicesResultBean result = new FTPServicesResultBean(destinationFolder, destinationName,
				file);
		
		try {
			FTPOperations ftp = new FTPConnection(hostname, username, password);
			result.ok = ftp.uploadFile(destinationFolder, destinationName, file.getInputStream());
			
			ftp.disconnect();
		
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			result.ok = false;
			result.errorMessage = e.getMessage();
		}
		
		return result;
	}

	public FTPServicesResultBean deleteFile(String containingFolder, String fileName) {
		FTPServicesResultBean result = new FTPServicesResultBean(containingFolder, fileName,
				null);
		
		try {
			FTPOperations ftp = new FTPConnection(hostname, username, password);
			result.ok = ftp.deleteFile(containingFolder, fileName);
			
			ftp.disconnect();
		
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			result.ok = false;
			result.errorMessage = e.getMessage();
		}
		
		return result;
		
	}

	public FTPServicesResultBean getFile(String containingFolder, String fileName) {
		FTPServicesResultBean result = new FTPServicesResultBean();
		
		try {
			FTPOperations ftp = new FTPConnection(hostname, username, password);
			InputStream in = ftp.getFile(containingFolder, fileName);
			ftp.disconnect();
			
			result.inputStream = in;
			result.ok = true;
			return result;
		
		} catch (IOException e) {
			result.ok = false;
			result.errorMessage = e.getMessage();
		} 
		return null;
	}

}
