package dev.ghimire.exception;

public class StatusOutOfBoundException extends RuntimeException{

    public StatusOutOfBoundException(){
        super("Status can only be Approved, Denied or Pending");
    }
}
