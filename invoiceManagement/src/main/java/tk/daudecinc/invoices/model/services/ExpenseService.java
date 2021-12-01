package tk.daudecinc.invoices.model.services;

import java.util.List;

import tk.daudecinc.invoices.model.entities.Expense;

public interface ExpenseService {

	public List<Expense> findAllByYear(Integer year);

}
