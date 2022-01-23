package tk.daudecinc.balance.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.daudecinc.balance.model.entities.YearConfiguration;
import tk.daudecinc.balance.model.services.YearConfigurationService;

@NoArgsConstructor
public class YearHolder {

	private Integer year;
	
	@Autowired
	@Getter(value = AccessLevel.NONE)
	@Setter(value=AccessLevel.NONE)
	private YearConfigurationService configurationService;
	
	public Integer getYear() {
		if(year == null) setYear();
		
		return year;
	}

	private void setYear() {
		List<YearConfiguration> configurations = configurationService.findAllOrderByYear();
		if(configurations != null && !configurations.isEmpty()) {
			year = configurations.get(0).getYear();
		}
	}
	
	public void setYear(Integer year) {
		if(year != null) this.year = year;
	}
}
