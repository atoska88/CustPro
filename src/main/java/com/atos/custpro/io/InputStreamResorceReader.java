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

/**
 * {@link ResourceReader} implementation that uses the {@link
 * InputStream} of the input {@link Resource}.
 * @author atos
 *
 */
public class InputStreamResorceReader implements ResourceReader, ApplicationContextAware {

    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private ApplicationContext applicationContext;

    @Override
    public byte[] read(final Resource resource) throws IOException {
        Assert.notNull(resource, "Parameter 'resource' can not be null!");
        ByteArrayOutputStream outputStream = null;
        outputStream = createStream(resource);
        return outputStream.toByteArray();
    }

    @Override
    public String readToString(final Resource resource, final String charSet) throws UnsupportedCharsetException, IOException {
        ByteArrayOutputStream outputStream = createStream(resource);
        return outputStream.toString(charSet);
    }

    private ByteArrayOutputStream createStream(final Resource resource) throws IOException {
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = applicationContext.getBean(ByteArrayOutputStream.class);
            InputStream inputStream = resource.getInputStream();
            byte[] buffer = new byte[getSizeOfResourceContent(resource)];
            while (hasDataToWrite(inputStream, buffer)) {
                outputStream.write(buffer);
            }
            return outputStream;
        } catch (BeansException exception) {
            throw new IOException(exception.getMostSpecificCause());
        } finally {
            closeStream(outputStream);
        }
    }

    private int getSizeOfResourceContent(final Resource resource) throws IOException {
        int contentLength = (int) resource.contentLength();
        return contentLength > 0 ? contentLength : DEFAULT_BUFFER_SIZE;
    }

    private boolean hasDataToWrite(final InputStream inputStream, final byte[] buffer) throws IOException {
        return inputStream.read(buffer) != -1;
    }

    private void closeStream(final Closeable closable) throws IOException {
        if (closable != null) {
            closable.close();
        }
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
