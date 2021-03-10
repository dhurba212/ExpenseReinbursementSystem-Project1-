package dev.ghimire.exception;

public class EmployeeDoesntExistException extends RuntimeException{

    public EmployeeDoesntExistException()
    {
        super("Employee doesn't exist");
    }
}
