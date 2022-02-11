package tk.daudecinc.balance.model.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tk.daudecinc.balance.model.entities.Invoice;
import tk.daudecinc.balance.model.repositories.InvoiceRepository;
import tk.daudecinc.balance.model.services.InvoiceService;
import tk.daudecinc.balance.utils.ServicesUtils;
import tk.daudecinc.balance.utils.ftp.FTPServices;
import tk.daudecinc.balance.utils.ftp.FTPServices.FTPServicesResultBean;
import tk.daudecinc.balance.utils.zip.ZipRequestBean;
import tk.daudecinc.balance.utils.zip.ZipService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ServicesUtils servicesUtils;
	
	@Autowired
	private FTPServices ftpServices;
	
	@Autowired
	private ZipService zipService;
	
	@Value("${dd5.ftp.documentsRootFolder}")
	private String DOCUMENTS_FOLDER;

	@Override
	public List<Invoice> findAllByYear(Integer year) {
		int myYear = servicesUtils.manageYear(year);
		return invoiceRepository.findAllByYearOrderByInvoiceDateDesc(myYear);
	}

	@Override
	public void save(Invoice invoiceToSave) {
		invoiceRepository.save(invoiceToSave);
		
	}

	@Override
	public Invoice findById(Long invoiceId) {
		return Optional.of(invoiceRepository.findById(invoiceId)).get().orElse(null);
	}

	@Override
	public void deleteInvoice(Long invoiceId) {
		invoiceRepository.deleteById(invoiceId);
		
	}

	@Override
	public ByteArrayOutputStream downloadAllInvoices(Integer year) {
		List<Invoice> invoices = invoiceRepository.findAllByYearOrderByInvoiceDateDesc(year);
		
		return generateZipFile(year, invoices);
		
	}

	private ByteArrayOutputStream generateZipFile(Integer year, List<Invoice> invoices) {
		List<ZipRequestBean> zips = new ArrayList<>();
		for(Invoice currentInvoice : invoices.stream()
				.filter(i -> Strings.isNotEmpty(i.getDocumentName()))
				.collect(Collectors.toList())) {
			FTPServicesResultBean result = ftpServices.getFile(DOCUMENTS_FOLDER + "/" + year + "/Factura/", currentInvoice.getDocumentName());
			addNewZipRequestBean(zips, result, currentInvoice.getDocumentName());
		}
		
		try {
			return zipService.generateZipFile(zips);
		
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void addNewZipRequestBean(List<ZipRequestBean> zips, FTPServicesResultBean result, String documentName) {
		if(!result.isOk()) {
			System.err.println(result.getErrorMessage());
			return;
		}
		
		ZipRequestBean zipRequest = new ZipRequestBean();
		zipRequest.setFileName(documentName);
		zipRequest.setIn(result.getInputStream());
		
		zips.add(zipRequest);
	}

}
