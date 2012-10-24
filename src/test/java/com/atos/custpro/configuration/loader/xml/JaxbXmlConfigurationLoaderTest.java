package com.atos.custpro.configuration.loader.xml;

import static org.easymock.EasyMock.expect;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.loader.FileStructureWrapper;
import com.atos.custpro.configuration.loader.xml.exception.XmlParsingException;
import com.atos.custpro.configuration.loader.xml.generated.Configuration;
import com.atos.custpro.configuration.loader.xml.generated.Custpro;
import com.atos.custpro.configuration.loader.xml.internal.JaxbConfigurationTransformer;
import com.atos.custpro.configuration.loader.xml.internal.JaxbContextProvider;

/**
 * Unit test of class {@link JaxbXmlConfigurationLoader}.
 * @author atos
 * @since 1.0
 */
public class JaxbXmlConfigurationLoaderTest {

    private IMocksControl mockControl;
    private XmlConfigurationLoader underTest;
    private JaxbContextProvider contextProvider;
    private JaxbConfigurationTransformer jaxbTransformer;
    private Resource inputResource;
    private JAXBContext jaxbContext;
    private Unmarshaller unmarshaller;

    private final Custpro rootObject = new Custpro();
    private final Configuration jaxbAndroidConfiguration = new Configuration();
    private InputStream inputStream;
    private final FileStructureConfiguration custProAndroidConfiguration = new FileStructureConfiguration();
    private Logger logger;

    @BeforeTest
    public void setUpTest() {
        mockControl = EasyMock.createStrictControl();
        contextProvider = mockControl.createMock(JaxbContextProvider.class);
        jaxbTransformer = mockControl.createMock(JaxbConfigurationTransformer.class);
        inputResource = mockControl.createMock(Resource.class);
        jaxbContext = mockControl.createMock(JAXBContext.class);
        unmarshaller = mockControl.createMock(Unmarshaller.class);
        logger = mockControl.createMock(Logger.class);
    }

    @BeforeMethod
    public void setUp() {
        mockControl.reset();
        JaxbXmlConfigurationLoader jaxbXmlConfigurationLoader = new JaxbXmlConfigurationLoader(jaxbTransformer, contextProvider);
        jaxbXmlConfigurationLoader.setLogger(logger);
        underTest = jaxbXmlConfigurationLoader;
    }

    @AfterMethod
    public void tearDown() {
        mockControl.verify();
    }

    @Test
    public void testLoadWhenInputContainsOneConfigurationShouldReturnOneSizedArray() throws XmlParsingException, IOException, JAXBException {
        //GIVEN
        rootObject.getConfiguration().add(jaxbAndroidConfiguration);
        expect(inputResource.getDescription()).andReturn("configurations.xml");
        logger.debug("Loading configuration from resource '{}'...", "configurations.xml");
        expect(contextProvider.provide("com.atos.custpro.configuration.loader.xml.generated")).andReturn(jaxbContext);
        expect(jaxbContext.createUnmarshaller()).andReturn(unmarshaller);
        expect(inputResource.getInputStream()).andReturn(inputStream);
        expect(unmarshaller.unmarshal(inputStream)).andReturn(rootObject);
        expect(inputResource.getDescription()).andReturn("configurations.xml");
        expect(jaxbTransformer.transform(jaxbAndroidConfiguration)).andReturn(new FileStructureWrapper("android", custProAndroidConfiguration));
        mockControl.replay();
        //WHEN
        FileStructureWrapper[] result = underTest.load(inputResource);
        //THEN
        Assert.assertTrue(result.length == 1);
        Assert.assertSame(result[0].getFileStructure(), custProAndroidConfiguration);
        Assert.assertEquals(result[0].getName(), "android");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testLoadWhenInputContainsNoConfigurationShouldThrowException() throws XmlParsingException, IOException, JAXBException {
        //GIVEN
        expect(inputResource.getDescription()).andReturn("configurations.xml");
        logger.debug("Loading configuration from resource '{}'...", "configurations.xml");
        expect(contextProvider.provide("com.atos.custpro.configuration.loader.xml.generated")).andReturn(jaxbContext);
        expect(jaxbContext.createUnmarshaller()).andReturn(unmarshaller);
        expect(inputResource.getInputStream()).andReturn(inputStream);
        expect(unmarshaller.unmarshal(inputStream)).andReturn(rootObject);
        expect(inputResource.getDescription()).andReturn("configurations.xml");
        mockControl.replay();
        //WHEN
        underTest.load(inputResource);
        //THEN throws exception
    }

    @Test(expectedExceptions = XmlParsingException.class)
    public void testLoadWhenUnmarshallingThrowsExeptionShouldThrowException() throws XmlParsingException, IOException, JAXBException {
        //GIVEN
        expect(inputResource.getDescription()).andReturn("configurations.xml");
        logger.debug("Loading configuration from resource '{}'...", "configurations.xml");
        expect(contextProvider.provide("com.atos.custpro.configuration.loader.xml.generated")).andReturn(jaxbContext);
        expect(jaxbContext.createUnmarshaller()).andReturn(unmarshaller);
        expect(inputResource.getInputStream()).andReturn(inputStream);
        expect(unmarshaller.unmarshal(inputStream)).andThrow(new JAXBException(""));
        expect(inputResource.getFilename()).andReturn("configurations.xml");
        mockControl.replay();
        //WHEN
        underTest.load(inputResource);
        //THEN throws exception
    }

}
