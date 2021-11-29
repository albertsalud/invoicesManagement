package tk.daudecinc.invoices.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.daudecinc.invoices.model.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	public List<Invoice> findAllByYearOrderByInvoiceDateDesc(int myYear);

}
