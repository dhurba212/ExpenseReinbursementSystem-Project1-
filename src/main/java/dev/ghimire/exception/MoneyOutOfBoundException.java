package dev.ghimire.exception;

public class MoneyOutOfBoundException extends RuntimeException{
    public MoneyOutOfBoundException()
    {
        super("Amount cannot be Negative");
    }
}
