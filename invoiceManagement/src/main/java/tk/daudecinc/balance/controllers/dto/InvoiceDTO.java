package tk.daudecinc.balance.controllers.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import tk.daudecinc.balance.utils.interfaces.BalanceDocument;

@Data
public class InvoiceDTO implements BalanceDocument{
	
	private Long id;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date invoiceDate;
	
	@NotBlank
	private String provider;
	
	@NotBlank
	private String invoiceNumber;
	
	@NotNull
	private Float amount;
	
	@NotBlank
	private String payer;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date paymentDate;
	private Boolean balanceDiscount;
	
	private String description;
	
	@Override
	public Date getDocumentDate() {
		return invoiceDate;
	}
	
	@Override
	public Boolean isBalanceDiscount() {
		return balanceDiscount;
	}
	
	@Override
	public Boolean isSubventionDiscount() {
		return true;
	}

	@Override
	public String getDocumentType() {
		return "Factura";
	}

}
