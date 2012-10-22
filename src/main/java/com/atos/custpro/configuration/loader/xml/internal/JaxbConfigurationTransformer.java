package com.atos.custpro.configuration.loader.xml.internal;

import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.loader.FileStructureWrapper;
import com.atos.custpro.configuration.loader.xml.exception.XmlParsingException;
import com.atos.custpro.configuration.loader.xml.generated.Configuration;

/**
 * Transforms the generated JAXB {@link Configuration} beans to the
 * CustPro {@link FileStructureConfiguration} beans.
 * @author atos
 * @since 1.0
 */
public interface JaxbConfigurationTransformer {

    /**
     * Transforms the given JAXB bean to application specific {@link
     * FileStructureConfiguration} bean.
     * @param jaxbConfiguration the original, JAXB-parsed
     * configuration
     * @return the CustPro specific {@link FileStructureWrapper}
     * @throws XmlParsingException when an error occurs during the
     * parsing process because of an XML error
     */
    FileStructureWrapper transform(Configuration jaxbConfiguration) throws XmlParsingException;
}
