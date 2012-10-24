package com.atos.custpro.parser;

import static org.easymock.EasyMock.expect;

import java.util.Properties;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atos.custpro.configuration.TestConfigurations;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;
import com.atos.custpro.parser.message.KeyValuePair;
import com.atos.custpro.parser.message.MessageParser;

/**
 * Unit test of class {@link LineStringParser}.
 * @author atos
 * @since 1.0
 */
public class LineStringParserTest {

    private IMocksControl mockControl;
    private StringParser underTest;
    private MessageParser messageParser;

    @BeforeTest
    public void setUpTest() {
        mockControl = EasyMock.createStrictControl();
        messageParser = mockControl.createMock(MessageParser.class);
    }

    @BeforeMethod
    public void setUp() {
        mockControl.reset();
        underTest = new LineStringParser(messageParser);
    }

    @AfterMethod
    public void tearDown() {
        mockControl.verify();
    }

    @Test
    public void testParseWithAndroidInputShouldReturnProperties() throws InvalidFileStructureException {
        //GIVEN
        String input = TestConfigurations.ANDROID_INPUT_FILE;
        KeyValuePair resultPair = new KeyValuePair("key", "value");
        expect(messageParser.parseMessage("<string name=\"key\">value", TestConfigurations.ANDROID_CONFIGURATION)).andReturn(resultPair);
        KeyValuePair resultPair2 = new KeyValuePair("key2", "value");
        expect(messageParser.parseMessage("<string name=\"key2\">value", TestConfigurations.ANDROID_CONFIGURATION)).andReturn(resultPair2);
        mockControl.replay();
        //WHEN
        Properties resultProperties = underTest.parse(input, TestConfigurations.ANDROID_CONFIGURATION);
        //THEN
        Object result = resultProperties.get("key");
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "value");
        result = resultProperties.get("key2");
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "value");
    }

    @Test
    public void testParseWithAndroidWhenInputContainsDuplicationsShouldReturnProperties() throws InvalidFileStructureException {
        //GIVEN
        String input = TestConfigurations.ANDROID_INPUT_FILE_WITH_DUPLICATIONS;
        KeyValuePair resultPair = new KeyValuePair("key", "value");
        expect(messageParser.parseMessage("<string name=\"key\">value", TestConfigurations.ANDROID_CONFIGURATION)).andReturn(resultPair);
        KeyValuePair resultPair2 = new KeyValuePair("key", "newvalue");
        expect(messageParser.parseMessage("<string name=\"key\">newvalue", TestConfigurations.ANDROID_CONFIGURATION)).andReturn(resultPair2);
        mockControl.replay();
        //WHEN
        Properties resultProperties = underTest.parse(input, TestConfigurations.ANDROID_CONFIGURATION);
        //THEN
        Assert.assertEquals(resultProperties.size(), 1);
        Object result = resultProperties.get("key");
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "newvalue");
        result = resultProperties.get("key2");
        Assert.assertNull(result);
    }

    @Test
    public void testParseWithEmptyAndroidInputShouldReturnProperties() throws InvalidFileStructureException {
        //GIVEN
        String input = TestConfigurations.ANDROID_EMPTY_INPUT_FILE;
        mockControl.replay();
        //WHEN
        Properties resultProperties = underTest.parse(input, TestConfigurations.ANDROID_CONFIGURATION);
        //THEN
        Assert.assertEquals(resultProperties.size(), 0);
    }

    @Test
    public void testParseWithIphoneInputShouldReturnProperties() throws InvalidFileStructureException {
        //GIVEN
        String input = TestConfigurations.IPHONE_INPUT_FILE;
        KeyValuePair resultPair = new KeyValuePair("key", "value");
        expect(messageParser.parseMessage("\"key\"=\"value\"", TestConfigurations.IPHONE_CONFIGURATION)).andReturn(resultPair);
        KeyValuePair resultPair2 = new KeyValuePair("key2", "value");
        expect(messageParser.parseMessage("\"key2\"=\"value\"", TestConfigurations.IPHONE_CONFIGURATION)).andReturn(resultPair2);
        mockControl.replay();
        //WHEN
        Properties resultProperties = underTest.parse(input, TestConfigurations.IPHONE_CONFIGURATION);
        //THEN
        Object result = resultProperties.get("key");
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "value");
        result = resultProperties.get("key2");
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "value");
    }

    @Test
    public void testParseWithIphoneWhenInputContainsDuplicationsShouldReturnProperties() throws InvalidFileStructureException {
        //GIVEN
        String input = TestConfigurations.IPHONE_INPUT_FILE_WITH_DUPLICATIONS;
        KeyValuePair resultPair = new KeyValuePair("key", "value");
        expect(messageParser.parseMessage("\"key\"=\"value\"", TestConfigurations.IPHONE_CONFIGURATION)).andReturn(resultPair);
        KeyValuePair resultPair2 = new KeyValuePair("key", "newvalue");
        expect(messageParser.parseMessage("\"key\"=\"newvalue\"", TestConfigurations.IPHONE_CONFIGURATION)).andReturn(resultPair2);
        mockControl.replay();
        //WHEN
        Properties resultProperties = underTest.parse(input, TestConfigurations.IPHONE_CONFIGURATION);
        //THEN
        Assert.assertEquals(resultProperties.size(), 1);
        Object result = resultProperties.get("key");
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "newvalue");
        result = resultProperties.get("key2");
        Assert.assertNull(result);
    }

    @Test
    public void testParseWithEmptyIphoneInputShouldReturnProperties() throws InvalidFileStructureException {
        //GIVEN
        String input = TestConfigurations.IPHONE_EMPTY_INPUT_FILE;
        mockControl.replay();
        //WHEN
        Properties resultProperties = underTest.parse(input, TestConfigurations.IPHONE_CONFIGURATION);
        //THEN
        Assert.assertEquals(resultProperties.size(), 0);
    }

}
