package com.atos.custpro.io.support;

import java.io.InputStream;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.atos.custpro.io.exception.CustProIOException;

/**
 * Helper class for file related operations, such as opening, creating
 * etc.
 * @author atos
 *
 */
public class FileIOHelper implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * Opens the file on the given path and returns a fresh {@link
     * InputStream} associated with the file.
     * @param path the path of the file
     * @return a fresh, opened InputStream
     * @throws CustProIOException if an error occurs during opening
     * the file
     */
    public InputStream openFile(final String path) throws CustProIOException {
        try {
            return getBeanFromSpring(SpringBeanNameConstants.FILE_INPUT_STREAM_BEAN_NAME, path);
        } catch (BeansException e) {
            throw new CustProIOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private <TYPE> TYPE getBeanFromSpring(final String beanName, final Object... args) {
        return (TYPE) applicationContext.getBean(beanName, args);
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
