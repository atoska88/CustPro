package com.atos.custpro.configuration.domain.exception;

import com.atos.custpro.exception.CustProException;

/**
 * Signals that the being validated file has structure errors.
 * @author atos
 *
 */
public class InvalidFileStructureException extends CustProException {

    /**
     * Constructs a new instance with the given message.
     * @param message the detailed message
     */
    public InvalidFileStructureException(final String message) {
        super(message);
    }

}
