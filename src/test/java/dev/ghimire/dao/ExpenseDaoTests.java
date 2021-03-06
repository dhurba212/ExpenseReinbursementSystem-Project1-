package dev.ghimire.dao;

import dev.ghimire.daos.ExpenseDAO;
import dev.ghimire.daos.ExpenseDaoHibernateImpl;
import dev.ghimire.daos.ExpenseDaoLocalImpl;
import dev.ghimire.daos.ExpenseDaoPostgresImpl;
import dev.ghimire.entities.Expense;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDaoTests {

    //ExpenseDAO expenseDAO = new ExpenseDaoLocalImpl();
    ExpenseDAO expenseDAO = new ExpenseDaoHibernateImpl();
    private static Expense expenseTest = null;
    private static Expense expenseTest2 = null;

    @Test
    @Order(1)
    void create_expense_test_1()
    {
        Expense e1 = new Expense(0,100,"travel",1);
        Expense e2 = new Expense(0,200,"certification",1);
        Expense expense = expenseDAO.createExpense(e1);
        Expense expense2 = expenseDAO.createExpense(e2);
        expenseTest = expense;
        expenseTest2 = expense2;
        Assertions.assertEquals(expense.getAmount(),100);

    }

    @Test
    @Order(2)
    void get_expense_by_id_test_1()
    {
        Expense expense = expenseDAO.getExpenseById(expenseTest.getExpenseId());
        Assertions.assertEquals(expense.getExpenseId(),expenseTest.getExpenseId());
        Assertions.assertNotNull(expense);
    }

    @Test
    @Order(3)
    void get_all_expense_test_1()
    {
        Set<Expense> allExpense = expenseDAO.getAllExpense();
        System.out.println(allExpense);
        Assertions.assertFalse(allExpense.isEmpty());
    }

    @Test
    @Order(4)
    void update_expense_test_1()
    {
        expenseTest.setAmount(500);
        expenseTest.setReason("food");
        expenseTest.setDecisionDate(System.currentTimeMillis());

        Expense expense = expenseDAO.updateExpense(expenseTest);
        Assertions.assertEquals(expense.getAmount(),expenseTest.getAmount());
        Assertions.assertEquals(expense.getReason(),expenseTest.getReason());
    }

    @Test
    @Order(5)
    void delete_expense_by_id_test_1()
    {
        boolean deleted = expenseDAO.deleteExpenseById(expenseTest2.getExpenseId());
        Assertions.assertTrue(deleted);



    }
}
