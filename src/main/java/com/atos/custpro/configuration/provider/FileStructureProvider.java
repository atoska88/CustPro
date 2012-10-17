package com.atos.custpro.configuration.provider;

import com.atos.custpro.configuration.domain.FileStructure;

/**
 * Provides {@link FileStructure} objects for parsing a properties
 * file.
 * @author atos
 *
 */
public interface FileStructureProvider {

    /**
     * Provides a {@link FileStructure} object associated with the
     * given name.
     * @param name the name of the file structure configuration
     * @return a {@link FileStructure} object associated with the name
     */
    FileStructure provide(String name);
}
