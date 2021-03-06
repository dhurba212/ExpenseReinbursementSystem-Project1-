package dev.ghimire.dao;


import dev.ghimire.daos.ExpenseDAO;
import dev.ghimire.entities.Expense;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class ExpenseDaoTestsMockito {
   private static Set<Expense> expenses = new HashSet<>();

    @Mock
    ExpenseDAO expenseDAO=null;

    @BeforeEach//test that run before each test
    void set_up(){

        Expense e1 = new Expense(0,1000,"rent",1);
        Expense e2 = new Expense(1,100,"food",1);
        Expense e3 = new Expense(2,50,"electricity",1);
        Expense e4 = new Expense(3,1500,"rent",2);
        Expense e5 = new Expense(4,500,"food",2);
        Expense e6 = new Expense(5,100,"electricity",2);

        Set<Expense> allExpense = new HashSet<>();
        allExpense.add(e1);
        allExpense.add(e2);
        allExpense.add(e3);
        allExpense.add(e4);
        allExpense.add(e5);
        allExpense.add(e6);


        Mockito.when(this.expenseDAO.getAllExpense()).thenReturn(allExpense);
        //Mockito.when(this.expenseDAO.createExpense(e5)).thenReturn();


    }
    @Test
    void get_all_expense_test_1()
    {

        Set<Expense> allExpense = expenseDAO.getAllExpense();
        System.out.println(allExpense);
        Assertions.assertEquals(allExpense.size(),6);


    }



}
