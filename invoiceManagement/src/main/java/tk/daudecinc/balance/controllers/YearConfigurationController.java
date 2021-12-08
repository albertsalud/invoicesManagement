package tk.daudecinc.balance.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tk.daudecinc.balance.controllers.dto.InvoiceDTO;
import tk.daudecinc.balance.controllers.dto.YearConfigurationDTO;
import tk.daudecinc.balance.model.entities.Invoice;
import tk.daudecinc.balance.model.entities.YearConfiguration;
import tk.daudecinc.balance.model.services.YearConfigurationService;

@Controller
@RequestMapping("/configurations")
public class YearConfigurationController {

	@Autowired
	private YearConfigurationService configurationService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(value = {"", "/"})
	public String listConfigurations(Model model) {
		List<YearConfiguration> configurations = configurationService.findAllOrderByYear();
		model.addAttribute("configurations", 
				configurations.stream()
					.map(configuration -> {
						return modelMapper.map(configuration, YearConfigurationDTO.class);
					})
					.collect(Collectors.toList())
				);
		
		return "configurations_list";
	}
	
	@GetMapping(value = "/new")
	public String getNewYearConfiguration(Model model) {
		YearConfigurationDTO dto = new YearConfigurationDTO();		
		
		return goToYearConfigurationForm(model, dto);
	}

	private String goToYearConfigurationForm(Model model, YearConfigurationDTO dto) {
		model.addAttribute("configuration", dto);
		return "configurations_form";
	}
	
	@PostMapping("/save")
	public String saveYearConfiguration(@Valid @ModelAttribute YearConfigurationDTO dto, 
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			return this.goToYearConfigurationForm(model, dto);
		}
		
		YearConfiguration configurationToSave = modelMapper.map(dto, YearConfiguration.class);
		configurationService.save(configurationToSave);
		
		return this.listConfigurations(model);
	}
	
	@GetMapping("/{year}")
	public String getYearConfiguration(@PathVariable(name = "year") Integer year, Model model) {
		YearConfiguration requestedConfiguration = configurationService.findByYear(year);
		
		YearConfigurationDTO dto = null;
		if(requestedConfiguration == null) {
			model.addAttribute("message", "Requested year configuration doesn't exist!");
			dto = new YearConfigurationDTO();
		
		} else {
			dto = modelMapper.map(requestedConfiguration, YearConfigurationDTO.class);
		}
		
		return goToYearConfigurationForm(model, dto);
		
	}
}
