package dev.ghimire.exception;

public class NotAnEmployeeException extends RuntimeException{
    public NotAnEmployeeException()
    {
        super("Not an Employee");
    }
}
