package com.atos.custpro.configuration.loader.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.atos.custpro.configuration.loader.FileStructureWrapper;
import com.atos.custpro.configuration.loader.xml.exception.XmlParsingException;
import com.atos.custpro.configuration.loader.xml.generated.Configuration;
import com.atos.custpro.configuration.loader.xml.generated.Custpro;
import com.atos.custpro.configuration.loader.xml.internal.JaxbConfigurationTransformer;
import com.atos.custpro.configuration.loader.xml.internal.JaxbContextProvider;

/**
 * {@link XmlConfigurationLoader} implementation that uses JAXB for
 * parsing.
 * @author atos
 * @since 1.0
 */
public class JaxbXmlConfigurationLoader implements XmlConfigurationLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(JaxbXmlConfigurationLoader.class);
    private final JaxbContextProvider contextProvider;
    private final JaxbConfigurationTransformer jaxbTransformer;

    /**
     * Constructs a new instance with the given transformer object.
     * @param jaxbTransformer the transformer that transforms the JAXB
     * generated beans to CustPro specific beans.
     * @param contextProvider provides the {@link JAXBContext}
     * objects
     */
    public JaxbXmlConfigurationLoader(final JaxbConfigurationTransformer jaxbTransformer, final JaxbContextProvider contextProvider) {
        this.jaxbTransformer = jaxbTransformer;
        this.contextProvider = contextProvider;
    }

    @Override
    public FileStructureWrapper[] load(final Resource inputResource) throws IOException, XmlParsingException {
        LOGGER.info("Loading configuration from resource '{}'...", inputResource.getDescription());
        List<FileStructureWrapper> resultList = new ArrayList<FileStructureWrapper>();
        try {
            JAXBContext context = contextProvider.provide("com.atos.custpro.configuration.loader.xml.generated");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Custpro rootObject = (Custpro) unmarshaller.unmarshal(inputResource.getInputStream());
            for (Configuration config : rootObject.getConfiguration()) {
                resultList.add(jaxbTransformer.transform(config));
            }
        } catch (JAXBException e) {
            throw new XmlParsingException("Could not parse XML " + inputResource.getFilename() + "!");
        }
        return resultList.toArray(new FileStructureWrapper[0]);
    }
}
