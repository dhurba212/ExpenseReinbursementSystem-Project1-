package dev.ghimire.controllers;

import com.google.gson.Gson;
import dev.ghimire.daos.EmployeeDaoHibernateImpl;
import dev.ghimire.daos.ExpenseDaoHibernateImpl;
import dev.ghimire.entities.Employee;
import dev.ghimire.entities.Expense;
import dev.ghimire.services.EmployeeService;
import dev.ghimire.services.EmployeeServiceImpl;
import dev.ghimire.services.ExpenseService;
import dev.ghimire.services.ExpenseServiceImpl;
import io.javalin.http.Handler;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

public class ExpenseController {
    private static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoHibernateImpl());
    private static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoHibernateImpl());

    public Handler createExpenseLambda = ctx ->
    {
        Gson gson = new Gson();
        Expense expense = gson.fromJson(ctx.body(),Expense.class);
        Expense newExpense = new Expense(0,expense.getAmount(),expense.getReason(),expense.getEmployeeId());

        System.out.println(newExpense);
        int employeeId = expense.getEmployeeId();
        System.out.println(employeeId);
        Employee employee = employeeService.getEmployeeById(employeeId);
        System.out.println(employee);
        if(employee!=null)
        {
            Expense registeredExpense = expenseService.registerExpense(newExpense);

            String registeredExpenseJson = gson.toJson(registeredExpense);
            ctx.result(registeredExpenseJson);
            ctx.status(201);

        }
        else
        {
            ctx.result("Employee with id "+employeeId+" doesn't exist");
            ctx.status(200);
        }
    };

    public Handler getAllExpensesLambda = ctx -> {

        String s = ctx.queryParam("status","no");
        String status=s.toLowerCase();
        System.out.println(status);
        Gson gson = new Gson();



            if(status.equals("pending"))
            {
                Set<Expense> allPendingExpenses = expenseService.allPendingExpenses();
                String allPendingExpenseJson = gson.toJson(allPendingExpenses);
                ctx.result(allPendingExpenseJson);
                ctx.status(200);

            }
            else if(status.equals("approved"))
            {
                Set<Expense> allApprovedExpenses = expenseService.allApprovedExpenses();
                String allApprovedExpenseJson = gson.toJson(allApprovedExpenses);
                ctx.result(allApprovedExpenseJson);
                ctx.status(200);
            }
            else if(status.equals("denied"))
            {
                Set<Expense> allDeniedExpenses = expenseService.allDeniedExpenses();
                String allDeniedExpenseJson = gson.toJson(allDeniedExpenses);
                ctx.result(allDeniedExpenseJson);
                ctx.status(200);
            }
            else if(status.equals("no"))
            {
            Set<Expense> allExpenses = expenseService.getAllExpense();
            String allExpensesJson = gson.toJson(allExpenses);
            ctx.result(allExpensesJson);
            ctx.status(200);
            }

            else
            {
                ctx.result("Can only search for pending,approved and denied");
            }
    };


    public Handler updateExpenseLambda = ctx ->
    {
        Gson gson =  new Gson();
        int expenseId = Integer.parseInt(ctx.pathParam("id"));
        //expense pulled from database
        Expense expenseFromDb = expenseService.getExpenseById(expenseId);
        if(expenseFromDb!=null)
        {
            //expense sent by user to update
            Expense expenseFromUser = gson.fromJson(ctx.body(),Expense.class);
            //changing 3 fields in expenses pulled from database for the expense id sent by user and
            //made changes to decisionDate, status and managerId
            //doing so doesn't allow user to change anything else beside 3 required fields.
            expenseFromDb.setDecisionDate(System.currentTimeMillis());
            expenseFromDb.setStatus(expenseFromUser.getStatus());
            expenseFromDb.setManagerId(expenseFromDb.getManagerId());

            //finally putting back the expenseFromDb with 3 changes back to database
            Expense updatedExpense = expenseService.updateExpense(expenseFromDb);

            String updatedExpenseJson = gson.toJson(updatedExpense);
            ctx.result(updatedExpenseJson);
            ctx.status(200);
        }
        else
        {
            ctx.result("Expense with expense id "+expenseId+" doesn't exist");
            ctx.status(200);
        }

    };

        public Handler getAllExpensesByEmployeeIdLambda = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Gson gson = new Gson();

        Employee employee = employeeService.getEmployeeById(id);
        if(employee!=null)
        {
            Set<Expense> expenses = expenseService.getAllExpenseByEmployeeId(id);
            if(expenses.isEmpty())
            {
                ctx.result("Employee with id "+id+" doesn't have any submitted expenses");
            }
            else
            {
                String expensesJson = gson.toJson(expenses);
                ctx.result(expensesJson);
            }

        }
        else
        {
            ctx.result("Employee with emoloyeee id "+id+" doesn't exist");
        }
        ctx.status(200);


    };

















}
