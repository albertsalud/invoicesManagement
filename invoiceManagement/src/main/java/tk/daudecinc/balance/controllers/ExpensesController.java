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

@Controller
@RequestMapping("/expenses")
public class ExpensesController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private ModelMapper mapper;

	@GetMapping(value = {"/", ""})
	public String listExpenses(@RequestParam(required = false) Integer year,
			Model model) {
		List<Expense> expenses = expenseService.findAllByYear(year);
		
		model.addAttribute("expensesList", 
			expenses.stream()
				.map(expense -> {
					return mapper.map(expense, ExpenseDTO.class);
				})
				.collect(Collectors.toList())
				);
		
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
}
