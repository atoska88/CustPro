package com.atos.custpro.configuration.provider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import com.atos.custpro.annotations.Log;
import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.loader.FileStructureWrapper;
import com.atos.custpro.configuration.loader.xml.XmlConfigurationLoader;
import com.atos.custpro.configuration.loader.xml.exception.XmlParsingException;

/**
 * Provides the structuring configurations from XML file(s).
 * @author atos
 *
 */
public class XmlFileStructureProvider implements FileStructureConfigurationProvider, ResourceLoaderAware {

    @Log
    private Logger logger;

    private final XmlConfigurationLoader configurationLoader;
    private final Map<String, FileStructureConfiguration> container;
    private ResourceLoader resourceLoader;

    /**
     * Constructs a new object and loads the configurations from the
     * given location(s).
     * @param configurationLoader loads the configurations from XML
     * files
     * file(s)
     */
    public XmlFileStructureProvider(final XmlConfigurationLoader configurationLoader) {
        this.configurationLoader = configurationLoader;
        container = new HashMap<String, FileStructureConfiguration>();
    }

    @Override
    public void loadConfigurations(final String[] xmlLocations) throws XmlParsingException, IOException {
        logger.info("Loading configurations from {}...", xmlLocations);
        for (String location : xmlLocations) {
            Resource resource = resourceLoader.getResource(location);
            FileStructureWrapper[] configuration = configurationLoader.load(resource);
            for (FileStructureWrapper fileStructureWrapper : configuration) {
                container.put(fileStructureWrapper.getName(), fileStructureWrapper.getFileStructure());
                logger.debug("Configuration '{}' was loaded successfully.", fileStructureWrapper.getName());
            }
        }
    }

    @Override
    public FileStructureConfiguration provide(final String name) {
        Assert.notEmpty(container, "No file structure configurations are mapped!");
        Assert.isTrue(container.containsKey(name), "There is no file structure configuration mapped with name '" + name + "'!");
        return container.get(name);
    }

    @Override
    public void setResourceLoader(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    void setLogger(final Logger logger) {
        this.logger = logger;
    }

}
