package dev.ghimire.services;

import dev.ghimire.entities.Expense;
import java.util.Set;

public interface ExpenseService {

    Expense registerExpense(Expense expense);

    Set<Expense> getAllExpense();

    Set<Expense> allPendingExpenses();

    Set<Expense> allDeniedExpenses();

    Set<Expense> allApprovedExpenses();

    Set<Expense> getAllExpenseByEmployeeId(int employeeId);

    Expense getExpenseById(int expenseId);

    Expense updateExpense(Expense expense);

    boolean deleteExpenseById(int expenseId);



}
