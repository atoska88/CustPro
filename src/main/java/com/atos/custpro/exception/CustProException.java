package com.atos.custpro.exception;

/**
 * Main checked exception of the library. Each checked exception is
 * the subclass of this class.
 * @author atos
 *
 */
public class CustProException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message. The detail message is saved
     * for later retrieval by the Throwable.getMessage() method.
     */
    public CustProException(final String message) {
        super(message);
    }

}
