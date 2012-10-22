package com.atos.custpro.configuration.loader.xml.exception;

import com.atos.custpro.exception.CustProException;

/**
 * Signals that there was a problem during an XML parsing process.
 * @author atos
 * @since 1.0
 */
public class XmlParsingException extends CustProException {

    /**
     * Constructs a new exception with the given message.
     * @param message the error message
     */
    public XmlParsingException(final String message) {
        super(message);
    }
}
