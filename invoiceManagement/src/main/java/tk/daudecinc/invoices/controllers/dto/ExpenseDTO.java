package tk.daudecinc.invoices.controllers.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import tk.daudecinc.invoices.utils.interfaces.BalanceDocument;

@Data
public class ExpenseDTO implements BalanceDocument{
	
	private Long id;
	
	@NotBlank
	private String description;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date expenseDate;
	
	@NotNull
	private Float amount;

	@Override
	public Date getDocumentDate() {
		return expenseDate;
	}


	@Override
	public Boolean isBalanceDiscount() {
		return true;
	}

	@Override
	public Boolean isSubventionDiscount() {
		return false;
	}

}
