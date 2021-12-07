package tk.daudecinc.balance.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.daudecinc.balance.model.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	public List<Invoice> findAllByYearOrderByInvoiceDateDesc(int myYear);

}
