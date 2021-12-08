package tk.daudecinc.balance.model.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name="im_invoices")
public class Invoice {
	
	@Id
	@SequenceGenerator(name = "invoices_seq",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "invoices_seq")
	private Long id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date invoiceDate;
	
	@Column(nullable = false)
	@Setter(value = AccessLevel.NONE)
	private int year;
	
	@Column(nullable = false, length = 20)
	private String invoiceNumber;
	
	@Column(nullable = false, length = 100)
	private String provider;
	
	@Column(nullable = false)
	private Float amount;
	
	@Column(nullable = false, length = 100)
	private String payer;
	
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	private Boolean balanceDiscount;
	
	@Column(length = 200)
	private String documentName;
	
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
		
		Calendar yearCalendar = Calendar.getInstance();
		yearCalendar.setTime(invoiceDate);
		
		this.year = yearCalendar.get(Calendar.YEAR);
	}
	
	@Column(length = 250)
	private String description;

}
