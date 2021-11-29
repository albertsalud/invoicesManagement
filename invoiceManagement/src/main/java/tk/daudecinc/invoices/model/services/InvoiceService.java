package tk.daudecinc.invoices.model.services;

import java.util.List;

import tk.daudecinc.invoices.model.entities.Invoice;

public interface InvoiceService {

	public List<Invoice> findAllByYear(Integer year);

	public void save(Invoice invoiceToSave);

	public Invoice findById(Long invoiceId);


}
