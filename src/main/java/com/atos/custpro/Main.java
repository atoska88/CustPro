package com.atos.custpro;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import com.atos.custpro.exception.CustProException;
import com.atos.custpro.parser.PropertyParser;
import com.atos.custpro.parser.factory.PropertyParserFactory;

/**
 * Main class.
 * @author atos
 * @since 1.0
 */
public final class Main {

    private Main() {
    }

    /**
     * Main test method.
     * @param args arguments
     * @throws IOException in case of IO exception
     * @throws CustProException when an internal exception occurs
     */
    public static void main(final String[] args) throws CustProException, IOException {
        PropertyParser parser = PropertyParserFactory.getInstance("classpath:configurations.xml");
        Properties properties = parser.parse("classpath:strings.xml", "android");
        for (Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

}
