package tk.daudecinc.balance.utils.services;

import java.util.List;

import tk.daudecinc.balance.utils.interfaces.BalanceDocument;

public interface BalanceService {
	
	public List<BalanceDocument> findBalanceDocumentsByYear(Integer year);

}
