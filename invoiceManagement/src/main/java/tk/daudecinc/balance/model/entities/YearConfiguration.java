package tk.daudecinc.balance.model.entities;

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
	private Integer year;
	
	@Column(nullable = false)
	private Float initialBalance;
	
	@Column(nullable = false)
	private Float subvention;

}
