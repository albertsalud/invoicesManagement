package tk.daudecinc.invoices.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "im_year_configuration")
@Data
public class YearConfiguration {
	
	@Id
	private int year;
	
	@Column(nullable = false)
	private Long initialBalance;
	
	@Column(nullable = false)
	private Long subvention;

}
