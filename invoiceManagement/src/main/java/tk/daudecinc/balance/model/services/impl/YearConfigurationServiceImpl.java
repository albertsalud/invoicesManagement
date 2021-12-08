package tk.daudecinc.balance.model.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.balance.model.entities.YearConfiguration;
import tk.daudecinc.balance.model.repositories.YearConfigurationRepository;
import tk.daudecinc.balance.model.services.YearConfigurationService;

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
		int myYear = manageYear(year);
		return yearRepository.findById(myYear).orElse(null);
	}
	
	private int manageYear(Integer year) {
		return year == null ?
				LocalDate.now().getYear() : year.intValue();
	}
	
	@Override
	public void save(YearConfiguration configurationToSave) {
		this.yearRepository.save(configurationToSave);
	}

}
