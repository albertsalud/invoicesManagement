package tk.daudecinc.balance.model.services;

import java.util.List;

import tk.daudecinc.balance.model.entities.Expense;

public interface ExpenseService {

	public List<Expense> findAllByYear(Integer year);

}
