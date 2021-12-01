package tk.daudecinc.invoices.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.invoices.model.entities.YearConfiguration;
import tk.daudecinc.invoices.model.repositories.YearConfigurationRepository;
import tk.daudecinc.invoices.model.services.YearConfigurationService;

@Service
public class YearConfigurationServiceImpl implements YearConfigurationService{
	
	@Autowired
	private YearConfigurationRepository yearRepository;

	@Override
	public List<YearConfiguration> findAllOrderByYear() {
		return yearRepository.findAllByOrderByYearDesc();
	}

	@Override
	public YearConfiguration findByYear(Integer year) {
		return yearRepository.findById(year).orElse(null);
	}
	
	@Override
	public void save(YearConfiguration configurationToSave) {
		this.yearRepository.save(configurationToSave);
	}

}
