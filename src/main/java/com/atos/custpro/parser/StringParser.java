package com.atos.custpro.parser;

import java.util.Properties;

import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;

/**
 * Parses the {@link Properties} object from a String object.
 * @author atos
 *
 */
public interface StringParser {

    /**
     * Parses the given input String to a {@link Properties} object.
     * @param input the input string that contains the key-value pairs
     * @param configuration the configuration bean that contains the
     * structure of the given input
     * @return a parsed {@link Properties} object
     * @throws InvalidFileStructureException if the given input is not
     * valid according to the configuration
     */
    Properties parse(String input, FileStructureConfiguration configuration) throws InvalidFileStructureException;

}
