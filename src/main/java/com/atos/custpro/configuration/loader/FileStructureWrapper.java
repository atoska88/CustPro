package com.atos.custpro.configuration.loader;

import com.atos.custpro.configuration.domain.FileStructureConfiguration;

/**
 * Wraps a {@link FileStructureConfiguration} configuration with its name.
 * @author atos
 *
 */
public class FileStructureWrapper {

    private final String name;
    private final FileStructureConfiguration fileStructure;

    /**
     * Constructs a new wrapper with the given objects.
     * @param name the name of the configuration
     * @param fileStructure the configuration that contains the
     * structure of a properties file
     */
    public FileStructureWrapper(final String name, final FileStructureConfiguration fileStructure) {
        super();
        this.name = name;
        this.fileStructure = fileStructure;
    }

    public String getName() {
        return name;
    }

    public FileStructureConfiguration getFileStructure() {
        return fileStructure;
    }

}
