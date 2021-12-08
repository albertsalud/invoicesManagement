package tk.daudecinc.balance.controllers.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import tk.daudecinc.balance.utils.interfaces.BalanceDocument;

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
	
	private int year;
	
	private MultipartFile expenseFile;
	
	private String documentName;

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


	@Override
	public String getDocumentType() {
		return "Gasto";
	}


	@Override
	public MultipartFile getFile() {
		return this.getExpenseFile();
	}

}
