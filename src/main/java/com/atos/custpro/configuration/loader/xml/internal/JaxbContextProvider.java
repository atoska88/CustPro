package com.atos.custpro.configuration.loader.xml.internal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Hider class for providing {@link JAXBContext} objects.
 * @author atos
 * @since 1.0
 */
public class JaxbContextProvider {

    /**
     * Provides a new instance of JAXBContext with the given packgage
     * name.
     * @param packageName the name of the package, fully qualified
     * @return a new instance of JAXBContext
     * @throws JAXBException if an error occurs
     */
    public JAXBContext provide(final String packageName) throws JAXBException {
        return JAXBContext.newInstance(packageName);
    }

}
