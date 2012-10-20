package com.atos.custpro.io;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import org.springframework.core.io.Resource;

/**
 * Reads {@link Resource} objects to byte arrays according to a given
 * character encoding.
 * @author atos
 *
 */
public interface ResourceReader {

    /**
     * Reads the given resource into a byte array.
     * @param resource the resource object that holds the input
     * @return a byte array containing the input
     * @throws UnsupportedCharsetException if the given character
     * encoding is not supported
     * @throws IOException if an I/O error occurs
     */
    byte[] read(final Resource resource) throws IOException;

    /**
     * Reads the given resource into a String respecting the given
     * character encoding.
     * @param resource the resource object that holds the input
     * @return a byte array containing the input
     * @param charSet the name of the character encoding
     * @throws UnsupportedCharsetException if the given character
     * encoding is not supported
     * @throws IOException if an I/O error occurs
     */
    String readToString(final Resource resource, final String charSet) throws UnsupportedCharsetException, IOException;
}
