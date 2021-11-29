package tk.daudecinc.invoices.controllers.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class InvoiceDTO {
	
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

}
