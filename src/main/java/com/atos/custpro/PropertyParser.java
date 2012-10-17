package com.atos.custpro;

import java.util.Properties;

import com.atos.custpro.configuration.domain.FileStructure;

/**
 * Resolves the properties from a source.
 * @author atos
 *
 */
public interface PropertyParser {

    /**
     * Resolves a {@link Properties} object from the given path. The
     * path should be a file.
     * @param resourcePath the path of the input file. Will be handled
     * as a Spring Framework's Resource interface.
     * @param fileStructure the file structure configuration that
     * contains all needed information for the parsing
     * @return a {@link Properties} object with parsed from the given
     * path
     */
    Properties parse(String resourcePath, FileStructure fileStructure);
}
