package com.atos.custpro.parser.message;

import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;

/**
 * Interface for parsing a single message into a key-value pair.
 * @author atos
 * @since 1.0
 */
public interface MessageParser {

    /**
     * Parses the given string into a key-value pair respecting the
     * given configuration.
     * @param message the message string without the line terminator
     * string
     * @param configuration the configuration bean that contains the
     * structure of the input
     * @return a key value pair
     * @throws InvalidFileStructureException if the given input is not
     * valid for the configuration
     */
    KeyValuePair parseMessage(String message, FileStructureConfiguration configuration) throws InvalidFileStructureException;
}
