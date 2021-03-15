package dev.ghimire.exception;

public class ReasonNotSpecifiedException extends RuntimeException{
    public ReasonNotSpecifiedException()
    {
        super("Reason Not Provided");
    }
}
