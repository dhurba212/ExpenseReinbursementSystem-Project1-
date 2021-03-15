package dev.ghimire.services;

import dev.ghimire.daos.ExpenseDAO;
import dev.ghimire.entities.Employee;
import dev.ghimire.entities.Expense;
import dev.ghimire.exception.MoneyOutOfBoundException;
import dev.ghimire.exception.ReasonNotSpecifiedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpenseServiceImpl implements ExpenseService{

    private static ExpenseDAO expenseDAO = null;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO)
    {
        this.expenseDAO=expenseDAO;
    }


    @Override
    public Expense registerExpense(Expense expense) {
        if(expense.getAmount()<0)
        {
            throw new MoneyOutOfBoundException();
        }
        if(expense.getReason()==null ||expense.getReason().isEmpty())
        {
            throw new ReasonNotSpecifiedException();
        }
        Expense createdExpense = expenseDAO.createExpense(expense);
        return createdExpense;

    }

    @Override
    public Set<Expense> getAllExpense() {
        Set<Expense> allExpense = expenseDAO.getAllExpense();
        return allExpense;
    }

    @Override
    public Set<Expense> getAllExpenseByEmployeeId(int employeeId) {
        Set<Expense> allExpense = expenseDAO.getAllExpense();
        Set<Expense> allExpenseByEmployeeId = new HashSet<>();

        for(Expense e:allExpense)
        {
            if(e.getEmployeeId() == employeeId)
            {
                allExpenseByEmployeeId.add(e);
            }
        }

        return allExpenseByEmployeeId;
    }

    @Override
    public Expense getExpenseById(int expenseId) {
        Expense expense = expenseDAO.getExpenseById(expenseId);
        return expense;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        if(expense.getAmount()<0)
        {
            throw new MoneyOutOfBoundException();
        }
        Expense updatedExpense = expenseDAO.updateExpense(expense);

        return updatedExpense;
    }


    @Override
    public boolean deleteExpenseById(int expenseId) {
        boolean deleted = expenseDAO.deleteExpenseById(expenseId);
        return deleted;
    }

    @Override
    public Set<Expense> allPendingExpenses()
    {
        Set<Expense> expenses = expenseDAO.getAllExpense();
        Set<Expense> pendingExpenses = new HashSet<>();
        for(Expense e: expenses)
        {
            if(e.getStatus().equals("pending"))
            {
                pendingExpenses.add(e);
            }
        }
        return pendingExpenses;
    }

    @Override
    public Set<Expense> allApprovedExpenses()
    {
        Set<Expense> expenses = expenseDAO.getAllExpense();
        Set<Expense> approvedExpenses = new HashSet<>();
        for(Expense e: expenses)
        {
            if(e.getStatus().equals("approved"))
            {
                approvedExpenses.add(e);
            }
        }
        return approvedExpenses;
    }

    @Override
    public Set<Expense> allDeniedExpenses()
    {
        Set<Expense> expenses = expenseDAO.getAllExpense();
        Set<Expense> deniedExpenses = new HashSet<>();
        for(Expense e: expenses)
        {
            if(e.getStatus().equals("denied"))
            {
                deniedExpenses.add(e);
            }
        }
        return deniedExpenses;
    }


}
