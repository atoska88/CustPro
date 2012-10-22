package com.atos.custpro.parser.factory;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.custpro.exception.CustProException;
import com.atos.custpro.parser.PropertyParser;

/**
 * Factory for getting a {@link PropertyParser} object.
 * @author atos
 * @since 1.0
 */
public abstract class PropertyParserFactory {

    private static final String CONFIGURATION_LOCATION = "classpath:conf/spring/custpro-*.xml";

    /**
     * Returns a {@link PropertyParser} instance with loaded with the
     * configurations.
     * @param configLocations the locations of the configurations;
     * wildcards are not supported
     * @return a ready-to-use {@link PropertyParser} instance
     * @throws IOException if an IO error occurs during loading the
     * configurations
     * @throws CustProException if an internal error occurs
     */
    public static PropertyParser getInstance(final String[] configLocations) throws CustProException, IOException {
        PropertyParser propertyParser = ApplicationContextHolder.APPCONTEXT.getBean(PropertyParser.class);
        propertyParser.loadConfigurations(configLocations);
        return propertyParser;
    }

    /**
     * Returns a {@link PropertyParser} instance with loaded with the
     * configurations.
     * @param configLocation the location of the configurations;
     * wildcards are not supported
     * @return a ready-to-use {@link PropertyParser} instance
     * @throws IOException if an IO error occurs during loading the
     * configurations
     * @throws CustProException if an internal error occurs
     */
    public static PropertyParser getInstance(final String configLocation) throws CustProException, IOException {
        return getInstance(new String[]{configLocation});
    }

    private static final class ApplicationContextHolder {
        private static final ApplicationContext APPCONTEXT = new ClassPathXmlApplicationContext(CONFIGURATION_LOCATION);
    }
}
