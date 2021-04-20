package dev.ghimire.services;

import dev.ghimire.daos.ExpenseDaoHibernateImpl;
import dev.ghimire.daos.ExpenseDaoLocalImpl;
import dev.ghimire.entities.Expense;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseServiceTests {
    //switch between using either localDao,PostgresDao,HibernateDao

   // private static ExpenseService expenseService = new ExpenseServiceImpl(ExpenseDaoLocalImpl.expenseDaoLocal);
//    private static ExpenseService expenseService = new ExpenseServiceImpl(ExpenseDaoHibernateImpl.expenseDaoHibernate);
//
//
//    private static Expense testExpense1 = null;
//    private static Expense testExpense2 = null;
//    @Test
//    @Order(1)
//    void register_expense_test_1()
//    {
//        Expense expense1 = new Expense(0,250,"certification",1);
//        Expense expense2 = new Expense(0,500,"work attire",1);
//        Expense e1 = expenseService.registerExpense(expense1);
//        Expense e2 = expenseService.registerExpense(expense2);
//
//        testExpense1=e1;
//        testExpense2=e2;
//
//
//        Assertions.assertNotNull(e1);
//        Assertions.assertNotNull(e2);
//        Assertions.assertEquals(e1.getAmount(),expense1.getAmount());
//
//    }
//
//    @Test
//    @Order(2)
//    void get_all_expense_test_1()
//    {
//        Set<Expense> allExpense = expenseService.getAllExpense();
//        System.out.println(allExpense);
//        Assertions.assertTrue(allExpense.size()>1);
//        Assertions.assertFalse(allExpense.isEmpty());
//    }
//    @Test
//    @Order(3)
//    void get_all_expense_by_employee_id_test_1()
//    {
//        int employeeId = testExpense1.getEmployeeId();
//
//        Set<Expense> allExpense = expenseService.getAllExpenseByEmployeeId(employeeId);
//
//        Assertions.assertFalse(allExpense.isEmpty());
//
//    }
//
//    @Test
//    @Order(4)
//    void get_expense_by_id_test_1()
//    {
//        int testExpenseId = testExpense1.getExpenseId();
//        Expense expense = expenseService.getExpenseById(testExpenseId);
//        Assertions.assertEquals(testExpenseId,expense.getExpenseId());
//    }
//
//    @Test
//    @Order(5)
//    void update_expense_test_1()
//    {
//        //update can be done only by manager and to the field status and decision date, will have that restriction on frontEnd
//        testExpense1.setStatus("approved");
//        //decisionDate will be automatically added at DAO level
//        //testExpense1.setDecisionDate(System.currentTimeMillis());
//        Expense expense = expenseService.updateExpense(testExpense1);
//        Assertions.assertEquals(testExpense1.getStatus(),expense.getStatus());
//        //Assertions.assertEquals(testExpense1.getDecisionDate(),expense.getDecisionDate(),1000);
//    }
//
//    @Test
//    @Order(6)
//    void delete_expense_by_id_test_1()
//    {
//        int id = testExpense2.getExpenseId();
//        boolean deleted = expenseService.deleteExpenseById(id);
//        System.out.println(expenseService.getAllExpense());
//        Assertions.assertTrue(deleted);
//    }


}
