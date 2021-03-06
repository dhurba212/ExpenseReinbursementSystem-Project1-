package dev.ghimire.app;
import dev.ghimire.controllers.ExpenseController;
import io.javalin.Javalin;
public class App {

    private static ExpenseController  expenseController= new ExpenseController();

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        //employee can create expenses
        app.post("/expenses",expenseController.createExpenseLambda);

        //manager get all expenses of all employees
        app.get("/expenses",expenseController.getAllExpensesLambda);



        //update reimbursement(change status, automatically add decisionDate)
        app.put("/expenses/:id",expenseController.updateExpenseLambda);

        //get all reimbursements by employee id
        app.get("/employees/:id/expenses", expenseController.getAllExpensesByEmployeeIdLambda);

        //employees can update their expenses if it is still pending
        //app.put("/employees/:id/expenses/:id",null);


        app.start();

    }
}
