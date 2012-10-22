package com.atos.custpro.configuration.loader.xml;

import java.io.IOException;

import org.springframework.core.io.Resource;

import com.atos.custpro.configuration.loader.FileStructureWrapper;
import com.atos.custpro.configuration.loader.xml.exception.XmlParsingException;

/**
 * Loads CustPro configuration file from a {@link Resource}.
 * @author atos
 *
 */
public interface XmlConfigurationLoader {

    /**
     * Loads the file structure configuration from the given resource.
     * @param inputResource the input resource that contains the
     * configuration
     * @return an array of fully loaded {@link FileStructureWrapper}
     * objects
     * @throws IOException when an IO error occurs during the process
     * @throws XmlParsingException when an error occurs during the XML
     * parsing
     */
    FileStructureWrapper[] load(Resource inputResource) throws IOException, XmlParsingException;
}
