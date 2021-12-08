package tk.daudecinc.balance.controllers.dto;

import lombok.Data;

@Data
public class YearConfigurationDTO {

	private Integer year;
	private Float initialBalance;
	private Float subvention;
}
