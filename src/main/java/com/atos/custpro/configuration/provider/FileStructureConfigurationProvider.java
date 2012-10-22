package com.atos.custpro.configuration.provider;

import java.io.IOException;

import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.exception.CustProException;

/**
 * Provides {@link FileStructureConfiguration} objects for parsing a properties
 * file.
 * @author atos
 *
 */
public interface FileStructureConfigurationProvider {

    /**
     * Provides a {@link FileStructureConfiguration} object associated with the
     * given name.
     * @param name the name of the file structure configuration
     * @return a {@link FileStructureConfiguration} object associated with the name
     */
    FileStructureConfiguration provide(String name);

    /**
     * Loads the configuration files located under the files in the
     * parameter. This method does not remove previously loaded
     * configurations but may overwrite previously loaded ones which
     * had the same name.
     * Must be called before usage.
     * @param configLocations the locations of the configuration
     * files; wildcards are not supported
     * @throws IOException when an IO error occurs during the
     * configuration loading
     * @throws CustProException when a configuration file contains
     * some error
     */
    void loadConfigurations(String[] configLocations) throws CustProException, IOException;
}
