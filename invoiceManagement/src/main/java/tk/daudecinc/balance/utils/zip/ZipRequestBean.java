package tk.daudecinc.balance.utils.zip;

import java.io.InputStream;

import lombok.Data;

@Data
public class ZipRequestBean {
	
	private InputStream in;
	private String fileName;

}
