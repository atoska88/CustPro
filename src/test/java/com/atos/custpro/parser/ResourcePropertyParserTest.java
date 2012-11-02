package com.atos.custpro.parser;

import static org.easymock.EasyMock.expect;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Properties;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atos.custpro.configuration.TestConfigurations;
import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;
import com.atos.custpro.configuration.provider.FileStructureConfigurationProvider;
import com.atos.custpro.exception.CustProException;
import com.atos.custpro.io.ResourceReader;

/**
 * Unit test of class {@link ResourcePropertyParser}.
 * @author atos
 * @since 1.0
 */
public class ResourcePropertyParserTest {

    private IMocksControl mockControl;
    private PropertyParser underTest;
    private ResourceLoader resourceLoader;
    private ResourceReader resourceReader;
    private FileStructureConfigurationProvider fileStructureProvider;
    private StringParser stringParser;
    private Logger logger;
    private String[] fileLocations;
    private final String configurationName = "empty";
    private final String inputPath = "inputfile";
    private Resource resource;
    private final Properties resultProperties = new Properties();

    @BeforeTest
    public void setUpTest() {
        mockControl = EasyMock.createStrictControl();
        resourceLoader = mockControl.createMock(ResourceLoader.class);
        resourceReader = mockControl.createMock(ResourceReader.class);
        fileStructureProvider = mockControl.createMock(FileStructureConfigurationProvider.class);
        stringParser = mockControl.createMock(StringParser.class);
        logger = mockControl.createMock(Logger.class);
        resource = mockControl.createMock(Resource.class);
    }

    @BeforeMethod
    public void setUp() {
        mockControl.reset();
        ResourcePropertyParser resourcePropertyParser = new ResourcePropertyParser(resourceReader, fileStructureProvider, stringParser);
        resourcePropertyParser.setResourceLoader(resourceLoader);
        resourcePropertyParser.setLogger(logger);

        underTest = resourcePropertyParser;
    }

    @AfterMethod
    public void tearDown() {
        mockControl.verify();
    }

    @Test
    public void testLoadConfigurationsShouldCallDependency() throws CustProException, IOException {
        //GIVEN
        fileStructureProvider.loadConfigurations(fileLocations);
        mockControl.replay();
        //WHEN
        underTest.loadConfigurations(fileLocations);
        //THEN
    }

    @Test
    public void testLoadConfigurationShouldCallDependency() throws CustProException, IOException {
        //GIVEN
        String fileLocation = "";
        fileStructureProvider.loadConfigurations(EasyMock.<String[]>anyObject());
        mockControl.replay();
        //WHEN
        underTest.loadConfigurations(fileLocation);
        //THEN
    }

    @Test
    public void testParseShouldCallDependencies() throws CustProException, IOException {
        //GIVEN
        expect(resourceLoader.getResource(inputPath)).andReturn(resource);
        logger.debug("Input resourcePath parsed into {}.", resource);
        FileStructureConfiguration emptyConfiguration = TestConfigurations.EMPTY_CONFIGURATION;
        expect(fileStructureProvider.provide(configurationName)).andReturn(emptyConfiguration);
        String wholeInput = null;
        expect(resourceReader.readToString(resource, emptyConfiguration.getCharSet())).andReturn(wholeInput);
        expect(stringParser.parse(wholeInput, emptyConfiguration)).andReturn(resultProperties);
        mockControl.replay();
        //WHEN
        Properties result = underTest.parse(inputPath, configurationName);
        //THEN
        Assert.assertSame(result, resultProperties);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testParseWhenResourcePathIsEmptyShouldThrowException() throws CustProException, IOException {
        //GIVEN
        mockControl.replay();
        //WHEN
        underTest.parse("", configurationName);
        //THEN throws exception
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testParseWhenConfigurationNameIsEmptyShouldThrowException() throws CustProException, IOException {
        //GIVEN
        mockControl.replay();
        //WHEN
        underTest.parse(inputPath, " ");
        //THEN throws exception
    }

    @Test
    public void testParseWhenStringParserThrowsExceptionShouldThrowException() throws CustProException, IOException {
        //GIVEN
        expect(resourceLoader.getResource(inputPath)).andReturn(resource);
        logger.debug("Input resourcePath parsed into {}.", resource);
        FileStructureConfiguration emptyConfiguration = TestConfigurations.EMPTY_CONFIGURATION;
        expect(fileStructureProvider.provide(configurationName)).andReturn(emptyConfiguration);
        String wholeInput = null;
        expect(resourceReader.readToString(resource, emptyConfiguration.getCharSet())).andReturn(wholeInput);
        InvalidFileStructureException fileStructureException = new InvalidFileStructureException(
                TestConfigurations.EMPTY_CONFIGURATION.getFileAffix(), wholeInput);
        expect(stringParser.parse(wholeInput, emptyConfiguration)).andThrow(fileStructureException);
        mockControl.replay();
        //WHEN
        try {
            underTest.parse(inputPath, configurationName);
        } catch (Throwable t) {
            //THEN
            Assert.assertSame(t, fileStructureException);
        }
    }

    @Test
    public void testParseWhenReaderThrowsExceptionShouldThrowException() throws IOException {
        //GIVEN
        expect(resourceLoader.getResource(inputPath)).andReturn(resource);
        logger.debug("Input resourcePath parsed into {}.", resource);
        FileStructureConfiguration emptyConfiguration = TestConfigurations.EMPTY_CONFIGURATION;
        expect(fileStructureProvider.provide(configurationName)).andReturn(emptyConfiguration);
        UnsupportedCharsetException exception = new UnsupportedCharsetException(emptyConfiguration.getCharSet());
        expect(resourceReader.readToString(resource, emptyConfiguration.getCharSet())).andThrow(exception);
        mockControl.replay();
        //WHEN
        try {
            underTest.parse(inputPath, configurationName);
        } catch (Throwable t) {
            //THEN
            Assert.assertSame(t, exception);
        }
    }

}
