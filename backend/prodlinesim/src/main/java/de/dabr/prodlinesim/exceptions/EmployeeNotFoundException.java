package de.dabr.prodlinesim.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
