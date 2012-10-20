package com.atos.custpro.parser;

import java.io.IOException;
import java.util.Properties;

import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;

/**
 * Resolves the properties from a source.
 * @author atos
 *
 */
public interface PropertyParser {

    /**
     * Resolves a {@link Properties} object from the given path. The
     * path should be a file.
     * @param inputPath the path of the input file. The resolving of
     * the inputPath depends on the concrete implementation
     * @param configurationName the name of the file structure
     * configuration in the configuration XML that should be used
     * during the parsing process
     * @return a {@link Properties} object with parsed from the given
     * path
     * @throws IOException it an I/O error occurs during parsing
     * @throws InvalidFileStructureException if the structure of the
     * input is not valid according to the configuration
     */
    Properties parse(String inputPath, String configurationName) throws IOException, InvalidFileStructureException;

    /**
     * Loads the file structure configurations from the given
     * locations.
     * @param xmlLocations the filenames, wildcards are not supported
     */
    void loadConfigurations(String[] xmlLocations);
}
