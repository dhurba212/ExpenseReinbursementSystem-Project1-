package dev.ghimire.daos;

import dev.ghimire.entities.Expense;
import dev.ghimire.utils.HibernateUtil;
import org.apache.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaQuery;


public class ExpenseDaoHibernateImpl implements ExpenseDAO{
    //creating an object og ExpenseDaoHibernateImpl so i can use it for dependency injection in service method
    public static ExpenseDaoHibernateImpl expenseDaoHibernate = new ExpenseDaoHibernateImpl();

    private static Logger logger = Logger.getLogger(ExpenseDaoHibernateImpl.class);


    @Override
    public Expense createExpense(Expense expense) {

        try
        {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();

            session.getTransaction().begin();
            session.save(expense);
            session.getTransaction().commit();

            session.close();
            return expense;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            logger.error("Wasn't able to create an expense");
            return null;
        }

    }

    @Override
    public Set<Expense> getAllExpense() {

        
        try
        {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();


            Criteria criteria = session.createCriteria(Expense.class);
            Set<Expense>allExpenses = new HashSet<>(criteria.list());
            
            session.close();
            return allExpenses;

        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            logger.error("wasn't able to get expenses");
            Set<Expense> expenses = new HashSet<>();
            return expenses;
        }


    }

    @Override
    public Expense getExpenseById(int id) {
        try
        {

            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();

            Expense expense = session.get(Expense.class,id);


            session.close();
            return expense;
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
            logger.error("wasn't able to get expense with that id");
            return null;
        }

    }

    @Override
    public Expense updateExpense(Expense expense) {
        try
        {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();

            session.getTransaction().begin();
            session.update(expense);
            session.getTransaction().commit();

            session.close();
            return expense;

        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            logger.error("Wasn't able to update the expense");
            return null;
        }



    }

    @Override
    public boolean deleteExpenseById(int id) {
        try
        {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();

            session.getTransaction().begin();
            session.delete(this.getExpenseById(id));
            session.getTransaction().commit();

            session.close();
            return true;
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
            logger.error("wasn't able to delete expense by id");
            return false;
        }

    }
}
