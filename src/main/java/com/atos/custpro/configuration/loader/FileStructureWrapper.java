package com.atos.custpro.configuration.loader;

import com.atos.custpro.configuration.domain.FileStructure;

/**
 * Wraps a {@link FileStructure} configuration with its name.
 * @author atos
 *
 */
public class FileStructureWrapper {

    private final String name;
    private final FileStructure fileStructure;

    /**
     * Constructs a new wrapper with the given objects.
     * @param name the name of the configuration
     * @param fileStructure the configuration that contains the
     * structure of a properties file
     */
    public FileStructureWrapper(final String name, final FileStructure fileStructure) {
        super();
        this.name = name;
        this.fileStructure = fileStructure;
    }

    public String getName() {
        return name;
    }

    public FileStructure getFileStructure() {
        return fileStructure;
    }

}
