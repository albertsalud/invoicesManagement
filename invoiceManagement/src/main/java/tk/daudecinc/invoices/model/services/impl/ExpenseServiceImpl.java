package tk.daudecinc.invoices.model.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import tk.daudecinc.invoices.model.entities.Expense;
import tk.daudecinc.invoices.model.services.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Override
	public List<Expense> findAllByYear(Integer year) {
		Expense fakeExpense = new Expense();
		fakeExpense.setAmount(150f);
		fakeExpense.setDescription("A description");
		fakeExpense.setExpenseDate(new Date());
		
		List<Expense> fakeList = new ArrayList<>();
		fakeList.add(fakeExpense);
		
		return fakeList;
	}

}
