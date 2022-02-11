package tk.daudecinc.balance.utils.zip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface ZipService {
	
	public ByteArrayOutputStream generateZipFile(List<ZipRequestBean> zips) throws IOException;

}
