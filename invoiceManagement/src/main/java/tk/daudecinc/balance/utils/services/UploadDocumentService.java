package tk.daudecinc.balance.utils.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import tk.daudecinc.balance.utils.ftp.FTPServices;
import tk.daudecinc.balance.utils.ftp.FTPServices.FTPServicesResultBean;
import tk.daudecinc.balance.utils.interfaces.BalanceDocument;

@Component
public class UploadDocumentService {
	
	private Log logger = LogFactory.getLog(UploadDocumentService.class);
	
	@Autowired
	private FTPServices ftpServices;
	
	@Value("${dd5.ftp.documentsRootFolder}")
	private String documentsFolder;
	
	public String uploadDocument(BalanceDocument document) {
		if(Strings.isEmpty(document.getFile().getOriginalFilename())) return null;
		
		String destinationFolder = documentsFolder + "/" + document.getYear() + 
				"/" + document.getDocumentType() + "/";
		
		String destinationName = generateFileName(document.getFile());
		
		FTPServicesResultBean resultBean = ftpServices.saveFile(destinationFolder, 
				destinationName, document.getFile());
		if(!resultBean.isOk()) {
			logger.error("Unable to upload file " + destinationName + " to folder " + resultBean.getDestinationFolder());
			return null;
		}
		
		return destinationName;
	}

	private String generateFileName(MultipartFile file) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");
		return sdf.format(new Date()) + "_" + file.getOriginalFilename();
	}

}
