package tk.daudecinc.balance.model.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.balance.model.entities.Expense;
import tk.daudecinc.balance.model.repositories.ExpenseRepository;
import tk.daudecinc.balance.model.services.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Override
	public List<Expense> findAllByYear(Integer year) {
		int myYear = manageYear(year);
		return expenseRepository.findAllByIdOrderByExpenseDateDesc((long) myYear);
		
	}

	private int manageYear(Integer year) {
		return year == null ?
				LocalDate.now().getYear() : year.intValue();
	}
	
	@Override
	public void save(Expense expenseToSave) {
		expenseRepository.save(expenseToSave);
		
	}

	@Override
	public Expense findById(Long expenseId) {
		return Optional.of(expenseRepository.findById(expenseId).get()).orElse(null);
	}

}
