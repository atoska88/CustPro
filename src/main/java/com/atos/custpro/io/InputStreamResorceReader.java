package com.atos.custpro.io;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.UnsupportedCharsetException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import com.atos.custpro.io.support.SpringBeanNameConstants;

/**
 * {@link ResourceReader} implementation that uses the {@link
 * InputStream} of the input {@link Resource}.
 * @author atos
 *
 */
public class InputStreamResorceReader implements ResourceReader, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public byte[] read(final Resource resource) throws IOException {
        Assert.notNull(resource, "Parameter 'resource' can not be null!");
        ByteArrayOutputStream outputStream = createStream(resource);
        try {
            return outputStream.toByteArray();
        } finally {
            closeStream(outputStream);
        }
    }

    @Override
    public String readToString(final Resource resource, final String charSet) throws UnsupportedCharsetException, IOException {
        ByteArrayOutputStream outputStream = createStream(resource);
        try {
            return outputStream.toString(charSet);
        } finally {
            closeStream(outputStream);
        }
    }

    private ByteArrayOutputStream createStream(final Resource resource) throws IOException {
        ByteArrayOutputStream outputStream = applicationContext.getBean(SpringBeanNameConstants.BYTE_ARRAY_OUTPUT_STREAM_BEAN_NAME,
                ByteArrayOutputStream.class);
        InputStream inputStream = resource.getInputStream();
        byte[] buffer = new byte[(int) resource.contentLength()];
        while (hasDataToWrite(inputStream, buffer)) {
            outputStream.write(buffer);
        }
        return outputStream;
    }

    private boolean hasDataToWrite(final InputStream inputStream, final byte[] buffer) throws IOException {
        return inputStream.read(buffer) != -1;
    }

    private void closeStream(final Closeable outputStream) throws IOException {
        if (outputStream != null) {
            outputStream.close();
        }
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
