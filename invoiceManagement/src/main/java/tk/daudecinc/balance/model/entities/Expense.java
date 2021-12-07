package tk.daudecinc.balance.model.entities;

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

import lombok.Data;

@Data
@Entity
@Table(name = "im_expenses")
public class Expense {
	
	@Id
	@SequenceGenerator(name = "expenses_seq",
		allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "expenses_seq")
	private Long id;
	
	@Column(nullable = false, length = 200)
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date expenseDate;
	
	@Column(nullable = false)
	private Float amount;
	

}
