package tk.daudecinc.balance.model.services;

import java.util.List;

import tk.daudecinc.balance.model.entities.YearConfiguration;

public interface YearConfigurationService {

	public List<YearConfiguration> findAllOrderByYear();
	
	public YearConfiguration findByYear(Integer year);
	
	public void save(YearConfiguration configurationToSave);
}
