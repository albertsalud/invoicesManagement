package tk.daudecinc.balance.utils.ftp;

import java.io.IOException;
import java.io.InputStream;

public interface FTPOperations {
	
	public void disconnect() throws IOException;
	
	public boolean uploadFile(String destinationDir, String destionationFileName, InputStream is);
	
	public boolean deleteFile(String containingFolder, String fileName);

	public InputStream getFile(String containingFolder, String fileName);
	

}
