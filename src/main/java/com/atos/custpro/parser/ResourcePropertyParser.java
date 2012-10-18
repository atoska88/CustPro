package com.atos.custpro.parser;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.atos.custpro.PropertyParser;
import com.atos.custpro.annotations.Log;
import com.atos.custpro.configuration.domain.FileStructure;

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

    @Override
    public Properties parse(final String resourcePath, final FileStructure fileStructure) {
        Resource resource = resourceLoader.getResource(resourcePath);
        logger.debug("Input resourcePath parsed into {}.", resource);
        return parseFromResource(resource, fileStructure);
    }

    private Properties parseFromResource(final Resource resource, final FileStructure fileStructure) {

        return null;
    }

    @Override
    public void setResourceLoader(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;

    }

}
