package com.atos.custpro.parser.message.exception;

import com.atos.custpro.configuration.domain.Affix;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;

/**
 * Signals that a being parsed message is invalid and could not been
 * parsed.
 * @author atos
 * @since 1.0
 */
public class InvalidMessageException extends InvalidFileStructureException {

    private static final String MESSAGE_PATTERN = "The following message could not been parsed: '%s'";

    /**
     * Constructs a new exception with the given message.
     * @param message the message
     * @param affix the affix that did not matched the message
     */
    public InvalidMessageException(final Affix affix, final String message) {
        super(affix, message);
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_PATTERN, getTargetString());
    }
}
