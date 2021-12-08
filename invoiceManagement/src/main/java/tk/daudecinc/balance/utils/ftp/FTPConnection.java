package tk.daudecinc.balance.utils.ftp;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPConnection implements FTPOperations{

	private int port = 21;	// FTP standard port
	
	private FTPClient ftpClient;
	
	public FTPConnection(String server, String username, String password) throws IOException {
		ftpClient = new FTPClient();
		System.out.print("Connecting to " + server+ ":" + port + "... ");
		ftpClient.connect(server, port);
		System.out.println("Connected!");
		
		System.out.print("Login with username " + username + "... ");
		System.out.println(ftpClient.login(username, password));
		
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
		ftpClient.enterLocalPassiveMode();
	}
	
	public boolean uploadFile(String destinationDir, String destionationFileName, InputStream is) {
		System.out.println("Uploading " + destionationFileName + " to " + destinationDir + "... ");
			
		try {
			manageWorkingDirectory(destinationDir);
			return ftpClient.storeFile(destionationFileName, is);
		
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private void manageWorkingDirectory(String destinationDir) throws IOException {
		if(!ftpClient.changeWorkingDirectory(destinationDir) &&
				!ftpClient.makeDirectory(destinationDir)) {
			throw new IOException("Unable to create destination directory!");
		}		
	}

	public void disconnect() throws IOException {
		System.out.print("Disconnecting FTP client... ");
		ftpClient.disconnect();
		System.out.println("Disconected!");
	}

	@Override
	public boolean deleteFile(String containingFolder, String fileName) {
		System.out.println("Deleting " + fileName + " from " + containingFolder + "... ");
		
		try {
			manageWorkingDirectory(containingFolder);
			return ftpClient.deleteFile(fileName);
		
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public InputStream getFile(String containingFolder, String fileName) {
		System.out.println("Getting " + fileName + " from " + containingFolder + "... ");
		
		try {
			manageWorkingDirectory(containingFolder);
			InputStream is = ftpClient.retrieveFileStream(fileName);
//			if(!ftpClient.completePendingCommand()) throw new IOException();
			
			return is;
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}