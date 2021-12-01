package tk.daudecinc.invoices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tk.daudecinc.invoices.model.services.YearConfigurationService;
import tk.daudecinc.invoices.utils.interfaces.BalanceDocument;
import tk.daudecinc.invoices.utils.services.BalanceService;

@Controller
@RequestMapping("/")
public class BalanceController {
	
	@Autowired
	private YearConfigurationService configurationService;
	
	@Autowired
	private BalanceService balanceService;
	
	@GetMapping(value = {"", "/"})
	public String home(@RequestParam(required = false) Integer year, Model model) {
		List<BalanceDocument> documents = balanceService.findBalanceDocumentsByYear(year);
		
		model.addAttribute("balanceDocuments", documents);
		model.addAttribute("configuration", configurationService.findByYear(year));
		
		return "balance";
	}

}
