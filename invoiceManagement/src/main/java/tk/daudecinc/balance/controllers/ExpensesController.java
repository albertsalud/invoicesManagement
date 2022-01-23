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
import org.springframework.web.bind.annotation.RequestParam;

import tk.daudecinc.balance.controllers.dto.ExpenseDTO;
import tk.daudecinc.balance.model.entities.Expense;
import tk.daudecinc.balance.model.services.ExpenseService;
import tk.daudecinc.balance.utils.ControllersUtils;
import tk.daudecinc.balance.utils.YearHolder;
import tk.daudecinc.balance.utils.services.UploadDocumentService;

@Controller
@RequestMapping("/expenses")
public class ExpensesController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ControllersUtils controllersUtils;
	
	@Autowired
	private UploadDocumentService uploadDocumentService;
	
	@Autowired
	private YearHolder yearHolder;

	@GetMapping(value = {"/", ""})
	public String listExpenses(@RequestParam(required = false) Integer year,
			Model model) {
		yearHolder.setYear(year);
		
		List<Expense> expenses = expenseService.findAllByYear(yearHolder.getYear());
		
		model.addAttribute("expensesList", 
			expenses.stream()
				.map(expense -> {
					return mapper.map(expense, ExpenseDTO.class);
				})
				.collect(Collectors.toList())
				);
		
		controllersUtils.addConfigurationsToModel(model);
		
		return "expenses_list";
	}
	
	@GetMapping(value="/new")
	public String newExpenseForm(Model model) {
		ExpenseDTO dto = new ExpenseDTO();
		
		return goToExpensesForm(model, dto);
	}

	private String goToExpensesForm(Model model, ExpenseDTO dto) {
		model.addAttribute("expenseFormDTO", dto);
		return "expenses_form";
	}
	
	@PostMapping("/save")
	public String saveInvoice(@Valid @ModelAttribute ExpenseDTO dto, 
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			return this.goToExpensesForm(model, dto);
		}
		
		Expense expenseToSave = mapper.map(dto, Expense.class);
		dto.setYear(expenseToSave.getYear());
		
		String newDocumentName = uploadDocumentService.uploadDocument(dto);
		if(newDocumentName != null) {
			expenseToSave.setDocumentName(newDocumentName);
		}
		
		expenseService.save(expenseToSave);
		
		return this.listExpenses(null, model);
	}
	
	@GetMapping("/{id}")
	public String getExpense(@PathVariable(name = "id") Long expenseId, Model model) {
		Expense requestedExpense = expenseService.findById(expenseId);
		
		ExpenseDTO dto = null;
		if(requestedExpense == null) {
			model.addAttribute("message", "Requested expense doesn't exist!");
			dto = new ExpenseDTO();
		
		} else {
			dto = mapper.map(requestedExpense, ExpenseDTO.class);
		}
		
		return goToExpensesForm(model, dto);
		
	}
	
	@GetMapping("/delete")
	public String deleteExpense(@RequestParam(name = "expense", required = true) Long expenseId, Model model) {
		expenseService.deleteExpense(expenseId);
		
		return this.listExpenses(null, model);
	}
}
