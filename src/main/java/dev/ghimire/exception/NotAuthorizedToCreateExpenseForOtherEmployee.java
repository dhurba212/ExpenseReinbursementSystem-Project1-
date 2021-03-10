package dev.ghimire.exception;

public class NotAuthorizedToCreateExpenseForOtherEmployee extends RuntimeException{

    public NotAuthorizedToCreateExpenseForOtherEmployee()
    {
        super("Not allowed to create expenses for other employee");
    }
}
