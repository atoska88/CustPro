package com.atos.custpro.parser;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import com.atos.custpro.annotations.Log;
import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;
import com.atos.custpro.configuration.provider.FileStructureConfigurationProvider;
import com.atos.custpro.exception.CustProException;
import com.atos.custpro.io.ResourceReader;

/**
 * {@link PropertyParser} implementation which uses Spring Framework's
 * {@link Resource} instance for opening input path.
 * @author atos
 *
 */
public class ResourcePropertyParser implements PropertyParser, ResourceLoaderAware {

    @Log
    private Logger logger;
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
    public Properties parse(final String resourcePath, final String configurationName) throws UnsupportedCharsetException, IOException,
        InvalidFileStructureException {
        Assert.hasText(resourcePath, "Parameter 'resourcePath' can not be empty!");
        Assert.hasText(configurationName, "Parameter 'configurationName' can not be empty!");
        Resource resource = resourceLoader.getResource(resourcePath);
        logger.debug("Input resourcePath parsed into {}.", resource);
        FileStructureConfiguration configuration = fileStructureProvider.provide(configurationName);
        return parseFromResource(resource, configuration);
    }

    private Properties parseFromResource(final Resource resource, final FileStructureConfiguration fileStructure) throws UnsupportedCharsetException,
        IOException, InvalidFileStructureException {
        String wholeInput = resourceReader.readToString(resource, fileStructure.getCharSet());
        return stringParser.parse(wholeInput, fileStructure);
    }

    @Override
    public void setResourceLoader(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void loadConfigurations(final String[] configLocations) throws CustProException, IOException {
        fileStructureProvider.loadConfigurations(configLocations);
    }

    @Override
    public void loadConfigurations(final String filePath) throws CustProException, IOException {
        loadConfigurations(new String[]{filePath});
    }

    void setLogger(final Logger logger) {
        this.logger = logger;
    }

}
