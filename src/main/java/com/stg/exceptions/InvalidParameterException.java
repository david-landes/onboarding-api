package com.stg.exceptions;

/**
 * Exception to be thrown when a user sends an invalid parameter
 *
 * @author David Landes
 */
public class InvalidParameterException extends Exception {

    /**
     * Auto-generated serial version
     */
    private static final long serialVersionUID = -7206683525558555848L;

    public InvalidParameterException() {
	super();
    }

    public InvalidParameterException(String message) {
	super(message);
    }
}
