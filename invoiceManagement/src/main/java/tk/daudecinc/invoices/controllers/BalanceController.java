package tk.daudecinc.invoices.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BalanceController {
	
	public String home() {
		
		return "balance";
	}

}
