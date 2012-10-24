package com.atos.custpro.io;

import static org.easymock.EasyMock.expect;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit test of class {@link InputStreamResorceReader}.
 * @author atos
 * @since 1.0
 */
public class InputStreamResorceReaderTest {

    private IMocksControl mockControl;
    private ResourceReader underTest;
    private ApplicationContext applicationContext;
    private Resource inputResource;
    private ByteArrayOutputStream byteArrayOutputStream;
    private InputStream inputStream;
    private final String charSet = "UTF-8";

    @BeforeTest
    public void setUpTest() {
        mockControl = EasyMock.createStrictControl();
        applicationContext = mockControl.createMock(ApplicationContext.class);
        byteArrayOutputStream = mockControl.createMock(ByteArrayOutputStream.class);
        inputResource = mockControl.createMock(Resource.class);
        inputStream = mockControl.createMock(InputStream.class);
    }

    @BeforeMethod
    public void setUp() {
        mockControl.reset();
        InputStreamResorceReader inputStreamResorceReader = new InputStreamResorceReader();
        inputStreamResorceReader.setApplicationContext(applicationContext);

        underTest = inputStreamResorceReader;
    }

    @AfterMethod
    public void tearDown() {
        mockControl.verify();
    }

    @Test
    public void testReadWhenInputContainsDataShouldReturnByteArray() throws IOException {
        //GIVEN
        expect(applicationContext.getBean(ByteArrayOutputStream.class)).andReturn(byteArrayOutputStream);
        expect(inputResource.getInputStream()).andReturn(inputStream);
        expect(inputResource.contentLength()).andReturn(0L);
        expect(inputStream.read(EasyMock.<byte[]>anyObject())).andReturn(0);
        byteArrayOutputStream.write(EasyMock.<byte[]>anyObject());
        expect(inputStream.read(EasyMock.<byte[]>anyObject())).andReturn(-1);
        byteArrayOutputStream.close();
        byte[] resultArray = new byte[0];
        expect(byteArrayOutputStream.toByteArray()).andReturn(resultArray);
        mockControl.replay();
        //WHEN
        byte[] result = underTest.read(inputResource);
        //THEN
        Assert.assertSame(result, resultArray);
    }

    @Test
    public void testReadWhenInputContainsNoDataShouldReturnEmptyByteArray() throws IOException {
        //GIVEN
        expect(applicationContext.getBean(ByteArrayOutputStream.class)).andReturn(byteArrayOutputStream);
        expect(inputResource.getInputStream()).andReturn(inputStream);
        expect(inputResource.contentLength()).andReturn(0L);
        expect(inputStream.read(EasyMock.<byte[]>anyObject())).andReturn(-1);
        byteArrayOutputStream.close();
        byte[] resultArray = new byte[0];
        expect(byteArrayOutputStream.toByteArray()).andReturn(resultArray);
        mockControl.replay();
        //WHEN
        byte[] result = underTest.read(inputResource);
        //THEN
        Assert.assertSame(result, resultArray);
    }

    @Test
    public void testReadToStringWhenInputContainsNoDataShouldReturnEmptyString() throws IOException {
        //GIVEN
        expect(applicationContext.getBean(ByteArrayOutputStream.class)).andReturn(byteArrayOutputStream);
        expect(inputResource.getInputStream()).andReturn(inputStream);
        expect(inputResource.contentLength()).andReturn(0L);
        expect(inputStream.read(EasyMock.<byte[]>anyObject())).andReturn(-1);
        byteArrayOutputStream.close();
        String resultString = "";
        expect(byteArrayOutputStream.toString(charSet)).andReturn(resultString);
        mockControl.replay();
        //WHEN
        String result = underTest.readToString(inputResource, charSet);
        //THEN
        Assert.assertSame(result, resultString);
    }

    @Test
    public void testReadToStringWhenInputContainsDataShouldReturnInputString() throws IOException {
        //GIVEN
        expect(applicationContext.getBean(ByteArrayOutputStream.class)).andReturn(byteArrayOutputStream);
        expect(inputResource.getInputStream()).andReturn(inputStream);
        expect(inputResource.contentLength()).andReturn(12L);
        expect(inputStream.read(EasyMock.<byte[]>anyObject())).andReturn(12);
        byteArrayOutputStream.write(EasyMock.<byte[]>anyObject());
        expect(inputStream.read(EasyMock.<byte[]>anyObject())).andReturn(-1);
        byteArrayOutputStream.close();
        String resultString = "result";
        expect(byteArrayOutputStream.toString(charSet)).andReturn(resultString);
        mockControl.replay();
        //WHEN
        String result = underTest.readToString(inputResource, charSet);
        //THEN
        Assert.assertSame(result, resultString);
    }

    @Test(expectedExceptions = IOException.class)
    public void testReadToStringWhenByteArrayOutputStreamCtorThrowsExceptionShouldThrowIOException() throws IOException {
        //GIVEN
        BeanInitializationException exception = new BeanInitializationException("");
        expect(applicationContext.getBean(ByteArrayOutputStream.class)).andThrow(exception);
        mockControl.replay();
        //WHEN
        underTest.readToString(inputResource, charSet);
        //THEN throws exception
    }

    @Test
    public void testReadWhenGettingInputStreamThrowsExceptionShouldThrowException() throws IOException {
        //GIVEN
        expect(applicationContext.getBean(ByteArrayOutputStream.class)).andReturn(byteArrayOutputStream);
        IOException ioException = new IOException();
        expect(inputResource.getInputStream()).andThrow(ioException);
        byteArrayOutputStream.close();
        mockControl.replay();
        //WHEN

        try {
            underTest.read(inputResource);
        } catch (IOException e) {
            Assert.assertSame(e, ioException);
        }
        //THEN throws exception
    }

}
