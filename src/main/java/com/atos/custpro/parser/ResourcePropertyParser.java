package com.atos.custpro.parser;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.atos.custpro.annotations.Log;
import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;
import com.atos.custpro.configuration.provider.FileStructureConfigurationProvider;
import com.atos.custpro.io.ResourceReader;

/**
 * {@link PropertyParser} implementation which uses Spring Framework's
 * {@link Resource} instance for opening input path.
 * @author atos
 *
 */
public class ResourcePropertyParser implements PropertyParser, ResourceLoaderAware {

    @Log
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ResourceLoader resourceLoader;
    private final ResourceReader resourceReader;
    private final FileStructureConfigurationProvider fileStructureProvider;
    private final StringParser stringParser;

    /**
     * Constructs a new instance with the given parameters.
     * @param resourceReader reads the {@link Resource} into a string
     * object
     * @param fileStructureProvider provides the configurations
     * @param stringParser parses the previously read Resource
     */
    public ResourcePropertyParser(final ResourceReader resourceReader, final FileStructureConfigurationProvider fileStructureProvider,
            final StringParser stringParser) {
        this.resourceReader = resourceReader;
        this.fileStructureProvider = fileStructureProvider;
        this.stringParser = stringParser;
    }

    @Override
    public Properties parse(final String resourcePath, final String configurationName) throws IOException, InvalidFileStructureException {
        Resource resource = resourceLoader.getResource(resourcePath);
        logger.debug("Input resourcePath parsed into {}.", resource);
        FileStructureConfiguration configuration = fileStructureProvider.provide(configurationName);
        return parseFromResource(resource, configuration);
    }

    private Properties parseFromResource(final Resource resource, final FileStructureConfiguration fileStructure) throws IOException,
        InvalidFileStructureException {
        String wholeInput = resourceReader.readToString(resource, fileStructure.getCharSet());
        return stringParser.parse(wholeInput, fileStructure);
    }

    @Override
    public void setResourceLoader(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void loadConfigurations(final String[] configLocations) {
        fileStructureProvider.loadConfigurations(configLocations);
    }

}
