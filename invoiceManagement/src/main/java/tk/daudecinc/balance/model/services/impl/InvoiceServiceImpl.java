package tk.daudecinc.balance.model.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.balance.model.entities.Invoice;
import tk.daudecinc.balance.model.repositories.InvoiceRepository;
import tk.daudecinc.balance.model.services.InvoiceService;
import tk.daudecinc.balance.utils.ServicesUtils;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ServicesUtils servicesUtils;

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

}
