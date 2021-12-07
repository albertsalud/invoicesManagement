package tk.daudecinc.balance.model.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.balance.model.entities.Invoice;
import tk.daudecinc.balance.model.repositories.InvoiceRepository;
import tk.daudecinc.balance.model.services.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public List<Invoice> findAllByYear(Integer year) {
		int myYear = manageYear(year);
		return invoiceRepository.findAllByYearOrderByInvoiceDateDesc(myYear);
	}

	private int manageYear(Integer year) {
		return year == null ?
				LocalDate.now().getYear() : year.intValue();
	}

	@Override
	public void save(Invoice invoiceToSave) {
		invoiceRepository.save(invoiceToSave);
		
	}

	@Override
	public Invoice findById(Long invoiceId) {
		return Optional.of(invoiceRepository.findById(invoiceId)).get().orElse(null);
	}

}
