package tk.daudecinc.invoices.utils.services;

import java.util.List;

import tk.daudecinc.invoices.utils.interfaces.BalanceDocument;

public interface BalanceService {
	
	public List<BalanceDocument> findBalanceDocumentsByYear(Integer year);

}
