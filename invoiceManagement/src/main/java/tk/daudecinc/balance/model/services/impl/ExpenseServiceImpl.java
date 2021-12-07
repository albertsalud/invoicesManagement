package tk.daudecinc.balance.model.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.daudecinc.balance.model.entities.Expense;
import tk.daudecinc.balance.model.repositories.ExpenseRepository;
import tk.daudecinc.balance.model.services.ExpenseService;
import tk.daudecinc.balance.utils.ServicesUtils;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private ServicesUtils servicesUtils;
	
	@Override
	public List<Expense> findAllByYear(Integer year) {
		int myYear = servicesUtils.manageYear(year);
		return expenseRepository.findAllByYearOrderByExpenseDateDesc(myYear);
		
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
