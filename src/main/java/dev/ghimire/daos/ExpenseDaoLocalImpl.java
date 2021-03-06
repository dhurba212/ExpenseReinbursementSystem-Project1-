package dev.ghimire.daos;

import dev.ghimire.entities.Expense;
import org.apache.log4j.Logger;

import java.util.*;

public class ExpenseDaoLocalImpl implements ExpenseDAO {
    public static ExpenseDaoLocalImpl expenseDaoLocal= new ExpenseDaoLocalImpl();

    private static int idMaker=0;
    private  static Map<Integer,Expense> allExpense =  new HashMap<>();

    private static Logger logger = Logger.getLogger(ExpenseDaoLocalImpl.class);
    @Override
    public Expense createExpense(Expense expense) {
        expense.setExpenseId(++idMaker);
        allExpense.put(expense.getExpenseId(), expense);

        Expense createdExpense = allExpense.get(expense.getExpenseId());
        if(createdExpense!=null)
        {
            logger.info("A new expense was created");
            return createdExpense;
        }
        else
        {
            logger.error("Wasn't able to create an expense");
            return null;
        }
    }

    @Override
    public Set<Expense> getAllExpense() {
        Set<Expense> expenses = new HashSet<>(allExpense.values());

        return expenses;
    }

    @Override
    public Expense getExpenseById(int id) {
        Expense expense = allExpense.get(id);

        return expense;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        expense.setDecisionDate(System.currentTimeMillis());
        int id = expense.getExpenseId();

        allExpense.put(id,expense);
        return allExpense.get(id);
    }

    @Override
    public boolean deleteExpenseById(int id) {
        Expense expense = allExpense.remove(id);
        if(expense == null)
        {
            return false;
        }
        return true;
    }
}
