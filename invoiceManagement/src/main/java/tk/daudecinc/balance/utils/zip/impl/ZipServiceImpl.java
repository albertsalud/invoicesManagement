package tk.daudecinc.balance.utils.zip.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Component;

import tk.daudecinc.balance.utils.zip.ZipRequestBean;
import tk.daudecinc.balance.utils.zip.ZipService;

@Component
public class ZipServiceImpl implements ZipService {
	
	@Override
	public ByteArrayOutputStream generateZipFile(List<ZipRequestBean> zips) throws IOException {
		ByteArrayOutputStream myBos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(myBos);
		
		for(ZipRequestBean currentRequest : zips) {
			ZipEntry entry = new ZipEntry(currentRequest.getFileName());
			zos.putNextEntry(entry);
			
			byte[] bytes = new byte[1024];
	        int length;
	        while((length = currentRequest.getIn().read(bytes)) >= 0) {
	        	zos.write(bytes, 0, length);
	        }
		}
        
        zos.close();
        return myBos;
	}
}
