package tk.daudecinc.balance.utils.interfaces;

import java.util.Date;

public interface BalanceDocument {

	public Date getDocumentDate();
	
	public String getDescription();
	
	public Boolean isBalanceDiscount();
	
	public Boolean isSubventionDiscount();
	
	public Float getAmount();
	
	public String getDocumentType();
}
