package com.atos.custpro.configuration.provider;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import com.atos.custpro.configuration.domain.FileStructure;
import com.atos.custpro.configuration.loader.FileStructureConfigurationLoader;
import com.atos.custpro.configuration.loader.FileStructureWrapper;

/**
 * Provides the structuring configurations from XML file(s).
 * @author atos
 *
 */
public class XmlFileStructureProvider implements FileStructureProvider, ResourceLoaderAware {

    private FileStructureConfigurationLoader configurationLoader;
    private Map<String, FileStructure> container;
    private ResourceLoader resourceLoader;
    private final String[] xmlLocations;

    /**
     * Constructs a new object and loads the configurations from the
     * given location(s).
     * @param xmlLocations the location(s) of the configuration
     * file(s)
     */
    public XmlFileStructureProvider(final String... xmlLocations) {
        this.xmlLocations = xmlLocations;
    }

    /**
     * Initializes the provider by loading the configuration files.
     * Must be called before usage.
     */
    @PostConstruct
    public void loadConfigurations() {
        for (String location : xmlLocations) {
            Resource resource = resourceLoader.getResource(location);
            FileStructureWrapper[] configuration = configurationLoader.load(resource);
            for (FileStructureWrapper fileStructureWrapper : configuration) {
                container.put(fileStructureWrapper.getName(), fileStructureWrapper.getFileStructure());
            }

        }
    }

    @Override
    public FileStructure provide(final String name) {
        Assert.notEmpty(container, "No file structure configurations are mapped!");
        Assert.isTrue(container.containsKey(name), "There is no file structure configuration mapped with name '" + name + "'!");
        return container.get(name);
    }

    @Override
    public void setResourceLoader(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

}
