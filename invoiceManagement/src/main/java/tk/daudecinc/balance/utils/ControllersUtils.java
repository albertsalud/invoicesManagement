package tk.daudecinc.balance.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import tk.daudecinc.balance.controllers.dto.YearConfigurationDTO;
import tk.daudecinc.balance.model.entities.YearConfiguration;
import tk.daudecinc.balance.model.services.YearConfigurationService;

@Component
public class ControllersUtils {
	
	@Autowired
	private YearConfigurationService configurationService;
	
	@Autowired
	private ModelMapper mapper;

	public void addConfigurationsToModel(Model model) {
		
		List<YearConfiguration> configurations = configurationService.findAllOrderByYear();
		model.addAttribute("existentConfigurations", 
				configurations.stream()
					.map(configuration -> {
						return mapper.map(configuration, YearConfigurationDTO.class);
						})
					.collect(Collectors.toList()));
	}
}
