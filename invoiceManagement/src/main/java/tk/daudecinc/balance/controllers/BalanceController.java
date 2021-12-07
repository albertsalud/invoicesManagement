package tk.daudecinc.balance.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tk.daudecinc.balance.controllers.dto.YearConfigurationDTO;
import tk.daudecinc.balance.model.entities.YearConfiguration;
import tk.daudecinc.balance.model.services.YearConfigurationService;
import tk.daudecinc.balance.utils.interfaces.BalanceDocument;
import tk.daudecinc.balance.utils.services.BalanceService;

@Controller
@RequestMapping("/")
public class BalanceController {
	
	@Autowired
	private YearConfigurationService configurationService;
	
	@Autowired
	private BalanceService balanceService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping(value = {"", "/"})
	public String home(@RequestParam(required = false) Integer year, Model model) {
		List<BalanceDocument> documents = balanceService.findBalanceDocumentsByYear(year);
		
		model.addAttribute("balanceDocuments", documents);
		
		YearConfiguration yc = configurationService.findByYear(year);
		model.addAttribute("configuration", mapper.map(yc, YearConfigurationDTO.class));
		
		return "balance";
	}

}
