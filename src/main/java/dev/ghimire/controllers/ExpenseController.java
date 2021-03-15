package dev.ghimire.controllers;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import dev.ghimire.daos.EmployeeDaoHibernateImpl;
import dev.ghimire.daos.ExpenseDaoHibernateImpl;
import dev.ghimire.daos.ManagerDaoHibernateImpl;
import dev.ghimire.entities.Employee;
import dev.ghimire.entities.Expense;
import dev.ghimire.entities.Manager;
import dev.ghimire.exception.*;
import dev.ghimire.services.*;
import dev.ghimire.utils.JwtUtil;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

public class ExpenseController {
    Logger logger = Logger.getLogger(ExpenseController.class);

    private static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoHibernateImpl());
    private static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoHibernateImpl());
    private static ManagerService managerService = new ManagerServiceImpl(new ManagerDaoHibernateImpl());

    public Handler createExpenseLambda = ctx ->
    {
        try
        {
            String token = ctx.header("Authorization");
            if(token==null)
            {
                throw new NotAuthorizedException();

            }


            DecodedJWT jwt = JwtUtil.verifyJwtToken(token);
            System.out.println(jwt);
            String role= jwt.getClaim("role").asString();
            int jwtId = jwt.getClaim("id").asInt();


            if(!role.equals("employee"))
            {
                throw new NotAuthorizedException();

            }


            Gson gson = new Gson();
            Expense expense = gson.fromJson(ctx.body(),Expense.class);

            if(jwtId !=expense.getEmployeeId())
            {
                throw new NotAuthorizedToCreateExpenseForOtherEmployee();
            }

            Expense newExpense = new Expense(0,expense.getAmount(),expense.getReason(),expense.getEmployeeId());

            int employeeId = expense.getEmployeeId();
            Employee employee = employeeService.getEmployeeById(employeeId);
            System.out.println(employee);

            if(employee==null)
            {
                throw new NotAnEmployeeException();
            }

            Expense registeredExpense = expenseService.registerExpense(newExpense);

            String registeredExpenseJson = gson.toJson(registeredExpense);
            ctx.result(registeredExpenseJson);
            ctx.status(201);



        }
        catch(NotAuthorizedException e)
        {
            logger.info("Not Authorized to create an Expense");
            ctx.result("Only employees are allowed to register Expenses");
            ctx.status(401);
        }
        catch (NotAuthorizedToCreateExpenseForOtherEmployee e)
        {
            logger.info("attempted to create expense for other employee");
            ctx.result("Not authorized to create Expense for other employee");
            ctx.status(403);
        }
        catch (NotAnEmployeeException e)
        {

                logger.info("Employee with  that id doesn't exist");
                ctx.result("Employee with that id doesn't exist");
                ctx.status(401);

        }
        catch(JWTDecodeException e)
        {
            logger.error("Not a valid JWT");
            ctx.result("Invalid JWT");
            ctx.status(403);
        }
        catch(MoneyOutOfBoundException e)
        {
            logger.error("Invalid Amount");
            ctx.result("Invalid amount");
            ctx.status(403);
        }
        catch(ReasonNotSpecifiedException e)
        {
            logger.error("Reason Not Provided while creating an expense");
            ctx.result("Reason Not Provided");
            ctx.status(403);
        }


    };

    public Handler getAllExpensesLambda = ctx -> {

        try
        {
            String token = ctx.header("Authorization");
            if(token==null)
            {
                logger.info("Not authorized to get all Expenses");
                throw new NotAuthorizedException();

            }
            DecodedJWT jwt = JwtUtil.verifyJwtToken(token);
            String role = jwt.getClaim("role").asString();

            if(!role.equals("manager"))
            {
                throw new NotAuthorizedException();

            }

            String s = ctx.queryParam("status","no");
            String status=s.toLowerCase();
            //System.out.println(status);
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
        }
        catch (NotAuthorizedException e)
        {
            logger.info("Only Managers are authorized to get all the expenses");
            ctx.result("Only Managers are authorized to get all the Expenses");
            ctx.status(401);
        }
        catch(JWTDecodeException e)
        {
            logger.error("Not a valid JWT");
            ctx.result("Invalid JWT");
            ctx.status(403);
        }

    };


    public Handler updateExpenseLambda = ctx ->
    {
        try {


            String token = ctx.header("Authorization");
            if (token == null)
            {
                logger.info("Not authorized to update expenses");
                throw new NotAuthorizedException();

            }

                DecodedJWT jwt = JwtUtil.verifyJwtToken(token);
                String role = jwt.getClaim("role").asString();
                int jwtId = jwt.getClaim("id").asInt();

                if (!role.equals("manager")) {

                    throw new NotAuthorizedException();
                }

                Gson gson = new Gson();
                int id = Integer.parseInt(ctx.pathParam("id"));
                //expense sent by user to update
                Expense expenseFromUser = gson.fromJson(ctx.body(), Expense.class);
                if(expenseFromUser.getStatus().equals("approved") || expenseFromUser.getStatus().equals("denied") || expenseFromUser.getStatus().equals("pending"))
                {

                    int expenseId = expenseFromUser.getExpenseId();
                    //expense pulled from database
                    Expense expenseFromDb = expenseService.getExpenseById(expenseId);

                    if (expenseFromDb != null) {

                        //changing 3 fields in expenses pulled from database for the expense id sent by user and
                        //made changes to decisionDate, status and managerId
                        //doing so doesn't allow user to change anything else beside 3 required fields.
                        expenseFromDb.setDecisionDate(System.currentTimeMillis());
                        expenseFromDb.setStatus(expenseFromUser.getStatus());
                        expenseFromDb.setManagerId(jwtId);
                        expenseFromDb.setAmount(expenseFromUser.getAmount());

                        //finally putting back the expenseFromDb with 3 changes back to database
                        Expense updatedExpense = expenseService.updateExpense(expenseFromDb);

                        String updatedExpenseJson = gson.toJson(updatedExpense);
                        ctx.result(updatedExpenseJson);
                        ctx.status(200);
                    } else {
                        ctx.result("Expense with expense id " + expenseId + " doesn't exist");
                        ctx.status(200);
                    }
                }
                else
                {
                    System.out.println(expenseFromUser.getStatus());
                    System.out.println(!expenseFromUser.getStatus().equals("denied"));
                    throw new StatusOutOfBoundException();
                }


        }
        catch(NotAuthorizedException e)
        {
            logger.error("Not authorized to update expenses");
            ctx.result("Not authorized to update expenses");
            ctx.status(403);
        }
        catch(JWTDecodeException e)
        {
            logger.error("Not a valid JWT");
            ctx.result("Invalid JWT");
            ctx.status(403);
        }
        catch(StatusOutOfBoundException e)
        {
            logger.error("Status should be approved, denied or pending only");
            ctx.result("Invalid Status");
            ctx.status(203);
        }
        catch(MoneyOutOfBoundException e)
        {
            logger.error("Amount cannot be Negative");
            ctx.result("Invalid Amount");
            ctx.status(203);
        }



        };

        public Handler getAllExpensesByEmployeeIdLambda = ctx -> {
            try
            {
                Gson gson = new Gson();
                String token = ctx.header("Authorization");
                if(token==null)
                {
                    throw new  NotAuthorizedException();
                    //ctx.result("Not authorized to access employee expenses");
                    //ctx.status(401);
                }

                DecodedJWT jwt = JwtUtil.verifyJwtToken(token);
                String role = jwt.getClaim("role").asString();
                String username = jwt.getClaim("username").asString();
                int jwtId = jwt.getClaim("id").asInt();

                int id = Integer.parseInt(ctx.pathParam("id"));
                String status = ctx.queryParam("status","no").toLowerCase();

                Employee employee = employeeService.getEmployeeById(jwtId);

                if(!role.equals("employee") )
                {
                    throw new NotAuthorizedException();
                    //ctx.result("Not authorized to access employee expenses with id "+id);
                    //ctx.status(401);
                }
                if(!username.equals(employee.getUsername()))
                {
                    throw new NotAuthorizedException();
                }



                if(employee!=null)
                {
                    Set<Expense> expenses = expenseService.getAllExpenseByEmployeeId(id);
                    if(expenses.isEmpty())
                    {
                        ctx.result("Employee with id "+id+" doesn't have any submitted expenses");
                    }
                    else
                    {
                        Set<Expense>pendingExpense = new HashSet<>();
                        Set<Expense>approvedExpense = new HashSet<>();
                        Set<Expense>deniedExpense = new HashSet<>();

                        for(Expense exp:expenses)
                        {
                            if(exp.getStatus().equals("pending"))
                            {
                                pendingExpense.add(exp);
                            }
                            else if(exp.getStatus().equals("approved"))
                            {
                                approvedExpense.add(exp);
                            }
                            else
                            {
                                deniedExpense.add(exp);
                            }

                        }
                        if(status.equals("pending"))
                        {
                            ctx.result(gson.toJson(pendingExpense));
                            ctx.status(200);

                        }
                        else if(status.equals("approved"))
                        {
                            ctx.result(gson.toJson(approvedExpense));
                            ctx.status(200);
                        }
                        else if(status.equals("denied"))
                        {
                            ctx.result(gson.toJson(deniedExpense));
                            ctx.status(200);
                        }
                        else  if(status.equals("no"))
                        {
                            String expensesJson = gson.toJson(expenses);
                            ctx.result(expensesJson);
                            ctx.status(200);

                        }
                        else
                        {
                            ctx.result("Status can only be approved, denied or pending.");
                            ctx.status(200);
                        }

                    }

                }
                else
                {
                    ctx.result("Employee with emoloyeee id "+id+" doesn't exist");
                }
                ctx.status(200);

            }
            catch (NotAuthorizedException e)
            {
                logger.error("Not authorized to get the expenses either because u are not a manager or because its a different id");
                ctx.result("Not authorized");
                ctx.status(401);
            }


    };
}
