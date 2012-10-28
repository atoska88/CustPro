package com.atos.custpro.configuration.provider;

import static org.easymock.EasyMock.expect;

import java.io.IOException;

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
import com.atos.custpro.configuration.loader.FileStructureWrapper;
import com.atos.custpro.configuration.loader.xml.XmlConfigurationLoader;
import com.atos.custpro.exception.CustProException;

/**
 * Unit test of class {@link XmlFileStructureProvider}.
 * @author atos
 * @since 1.0
 */
public class XmlFileStructureProviderTest {

    private IMocksControl mockControl;
    private FileStructureConfigurationProvider underTest;
    private ResourceLoader resourceLoader;
    private XmlConfigurationLoader configurationLoader;
    private final String[] configLocations = new String[]{"configurations.xml"};
    private final FileStructureConfiguration configuration = TestConfigurations.EMPTY_CONFIGURATION;
    private final FileStructureWrapper[] wrapperArray = new FileStructureWrapper[]{new FileStructureWrapper("name", configuration)};
    private Logger logger;

    @BeforeTest
    public void setUpTest() {
        mockControl = EasyMock.createStrictControl();
        resourceLoader = mockControl.createMock(ResourceLoader.class);
        configurationLoader = mockControl.createMock(XmlConfigurationLoader.class);
        logger = mockControl.createMock(Logger.class);
    }

    @BeforeMethod
    public void setUp() {
        mockControl.reset();

        XmlFileStructureProvider xmlFileStructureProvider = new XmlFileStructureProvider(configurationLoader);
        xmlFileStructureProvider.setResourceLoader(resourceLoader);
        xmlFileStructureProvider.setLogger(logger);
        underTest = xmlFileStructureProvider;
    }

    @AfterMethod
    public void tearDown() {
        mockControl.verify();
    }

    @Test
    public void testLoadConfigurationsWhenOneConfigFileContainsOneConfigurationShouldRunWithoutException() throws CustProException, IOException {
        //GIVEN
        logger.info("Loading configurations from {}...", configLocations);
        Resource resultResource = mockControl.createMock(Resource.class);
        for (String configLocation : configLocations) {
            expect(resourceLoader.getResource(configLocation)).andReturn(resultResource);
            expect(configurationLoader.load(resultResource)).andReturn(wrapperArray);
            for (FileStructureWrapper wrapper : wrapperArray) {
                logger.debug("Configuration '{}' was loaded successfully.", wrapper.getName());
            }
        }
        mockControl.replay();
        //WHEN
        underTest.loadConfigurations(configLocations);
        //THEN
    }

    @Test
    public void testProvideWhenOneConfigIsLoadedBeforeShouldRunWithoutException() throws CustProException, IOException {
        //GIVEN
        logger.info("Loading configurations from {}...", configLocations);
        Resource resultResource = mockControl.createMock(Resource.class);
        for (String configLocation : configLocations) {
            expect(resourceLoader.getResource(configLocation)).andReturn(resultResource);
            expect(configurationLoader.load(resultResource)).andReturn(wrapperArray);
            for (FileStructureWrapper wrapper : wrapperArray) {
                logger.debug("Configuration '{}' was loaded successfully.", wrapper.getName());
            }
        }
        mockControl.replay();
        //WHEN
        underTest.loadConfigurations(configLocations);
        FileStructureConfiguration result = underTest.provide("name");
        //THEN
        Assert.assertSame(result, configuration);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testProvideWhenConfigDoesNotExistShouldThrowException() throws CustProException, IOException {
        //GIVEN
        logger.info("Loading configurations from {}...", configLocations);
        Resource resultResource = mockControl.createMock(Resource.class);
        for (String configLocation : configLocations) {
            expect(resourceLoader.getResource(configLocation)).andReturn(resultResource);
            expect(configurationLoader.load(resultResource)).andReturn(wrapperArray);
            for (FileStructureWrapper wrapper : wrapperArray) {
                logger.debug("Configuration '{}' was loaded successfully.", wrapper.getName());
            }
        }
        mockControl.replay();
        //WHEN
        underTest.loadConfigurations(configLocations);
        underTest.provide("unknown");
        //THEN throws exception
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testProvideWhenNoConfigurationsAreLoadedExistShouldThrowException() {
        //GIVEN
        mockControl.replay();
        //WHEN
        underTest.provide("unknown");
        //THEN throws exception
    }
}
