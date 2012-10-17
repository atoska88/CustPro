package com.atos.custpro.configuration.loader;

import org.springframework.core.io.Resource;

/**
 * Loads CustPro configuration file from a {@link Resource}.
 * @author atos
 *
 */
public interface FileStructureConfigurationLoader {

    /**
     * Loads the file structure configuration from the given resource.
     * @param inputResource the input resource that contains the
     * configuration
     * @return an array of fully loaded {@link FileStructureWrapper}
     * objects
     */
    FileStructureWrapper[] load(Resource inputResource);
}
