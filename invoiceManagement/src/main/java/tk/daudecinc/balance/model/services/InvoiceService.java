package tk.daudecinc.balance.model.services;

import java.io.ByteArrayOutputStream;
import java.util.List;

import tk.daudecinc.balance.model.entities.Invoice;

public interface InvoiceService {

	public List<Invoice> findAllByYear(Integer year);

	public void save(Invoice invoiceToSave);

	public Invoice findById(Long invoiceId);

	public void deleteInvoice(Long invoiceId);

	public ByteArrayOutputStream downloadAllInvoices(Integer year);

}
