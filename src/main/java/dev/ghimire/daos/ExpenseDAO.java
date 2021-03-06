package dev.ghimire.daos;

import dev.ghimire.entities.Expense;

import java.util.List;
import java.util.Set;


public interface ExpenseDAO {
    Expense createExpense(Expense expense);

    Set<Expense> getAllExpense();

    Expense getExpenseById(int id);

    Expense updateExpense(Expense expense);

    boolean deleteExpenseById(int id);


}
