package tk.daudecinc.invoices.model.services;

import java.util.List;

import tk.daudecinc.invoices.model.entities.YearConfiguration;

public interface YearConfigurationService {

	public List<YearConfiguration> findAllOrderByYear();
	
	public YearConfiguration findByYear(Integer year);
	
	public void save(YearConfiguration configurationToSave);
}
