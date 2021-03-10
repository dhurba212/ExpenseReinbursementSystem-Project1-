package dev.ghimire.exception;

public class NotAuthorizedException extends RuntimeException{

    public NotAuthorizedException()
    {
        super("Not authorized to perform this operation.");
    }
}
