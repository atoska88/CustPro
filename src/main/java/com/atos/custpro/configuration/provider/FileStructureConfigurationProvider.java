package com.atos.custpro.configuration.provider;

import com.atos.custpro.configuration.domain.FileStructureConfiguration;

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
     */
    void loadConfigurations(String[] configLocations);
}
