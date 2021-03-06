package dev.ghimire.daos;

import dev.ghimire.entities.Expense;
import dev.ghimire.utils.ConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ExpenseDaoPostgresImpl implements ExpenseDAO{
    private static Logger logger = Logger.getLogger(ExpenseDaoPostgresImpl.class);

    @Override
    public Expense createExpense(Expense expense) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "insert into expense(amount,reason,submitted_date,status,employee_id) values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1,expense.getAmount());
            ps.setString(2,expense.getReason());
            ps.setDouble(3,expense.getSubmittedDate());
            ps.setString(4,expense.getStatus());
            ps.setInt(5,expense.getEmployeeId());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int id = rs.getInt("expense_id");

            expense.setExpenseId(id);
            logger.info("Expense created successfully");
            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Wasn't able to create new expense");
            return null;
        }

    }

    @Override
    public Set<Expense> getAllExpense() {
        Set<Expense> allExpense = new HashSet<>();

        try(Connection conn = ConnectionUtil.getConnection())
        {
            String sql = "select * from expense";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();




            while(rs.next())
            {

                Expense expense = new Expense();
                expense.setExpenseId(rs.getInt("expense_id"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setReason(rs.getString("reason"));
                expense.setSubmittedDate(rs.getLong("submitted_date"));
                expense.setStatus(rs.getString("status"));
                expense.setDecisionDate(rs.getLong("decision_date"));
                expense.setEmployeeId(rs.getInt("employee_id"));
                expense.setManagerId(rs.getInt("manager_id"));

                allExpense.add(expense);


            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("wasn't able to get all the expense");

        }
        return allExpense;
    }

    @Override
    public Expense getExpenseById(int id) {

        try(Connection conn = ConnectionUtil.getConnection())
        {
            String sql = "select * from expense where expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            rs.next();

            Expense expense = new Expense();
            expense.setExpenseId(rs.getInt("expense_id"));
            expense.setAmount(rs.getDouble("amount"));
            expense.setReason(rs.getString("reason"));
            expense.setSubmittedDate(rs.getLong("submitted_date"));
            expense.setStatus(rs.getString("status"));
            expense.setDecisionDate(rs.getLong("decision_date"));
            expense.setEmployeeId(rs.getInt("employee_id"));
            expense.setManagerId(rs.getInt("manager_id"));

            logger.info("Expense with the id "+id+" was returned succesfully");
            return expense;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("Wasn't able to return the expense with id "+id);
            return null;
        }

    }

    @Override
    public Expense updateExpense(Expense expense) {

        try(Connection conn = ConnectionUtil.getConnection())
        {
            String sql = "update expense set amount=?,reason=?,status=?,decision_date=?,manager_id=? where expense_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1,expense.getExpenseId());
            ps.setString(2, expense.getReason());
            ps.setString(3, expense.getStatus());
            ps.setLong(4,expense.getDecisionDate());
            ps.setInt(5,expense.getManagerId());
            ps.setInt(6,expense.getExpenseId());

            int update = ps.executeUpdate();
            if(update==1)
            {
                return expense;
            }
            else{
                return null;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("Wasn't able to update the expense with expense Id "+expense.getExpenseId());
            return null;
        }

    }

    @Override
    public boolean deleteExpenseById(int id) {

        try(Connection conn = ConnectionUtil.getConnection())
        {
            String sql = "delete from expense where expense_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,id);
            int delete = ps.executeUpdate();

            if(delete==1)
            {
                logger.info("Was able to delete the expense with expense Id "+id);
                return true;
            }
            else
            {
                logger.error("Wasn't able to delete the expense with expense Id "+id);
                return false;
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("Server Error, Wasn't able to delete the expense with expense Id "+id);
            return false;
        }

    }
}
