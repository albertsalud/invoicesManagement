package tk.daudecinc.balance.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.daudecinc.balance.model.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	public List<Expense> findAllByYearOrderByExpenseDateDesc(int myYear);

}
