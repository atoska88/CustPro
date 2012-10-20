package com.atos.custpro.io.exception;

import com.atos.custpro.exception.CustProException;

/**
 * Signals that there was an error during an I/O call.
 * @author atos
 *
 */
public class CustProIOException extends CustProException {

    /**
     * Constructs a new exception with the given message.
     * @param message the message
     */
    public CustProIOException(final String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the given cause.
     * @param exception the original cause
     */
    public CustProIOException(final Throwable exception) {
        super(exception);
    }

}
