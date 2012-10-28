package com.atos.custpro.parser.message;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.atos.custpro.configuration.TestConfigurations;
import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.domain.exception.InvalidFileStructureException;

/**
 * Unit test of class {@link RegexMessageParser}.
 * @author atos
 * @since 1.0
 */
public class RegexMessageParserTest {

    private MessageParser underTest;

    @BeforeMethod
    public void setUp() {
        underTest = new RegexMessageParser();
    }

    @Test
    public void testParseWithIphoneConfigShouldReturnKeyValuePair() throws InvalidFileStructureException {
        //GIVEN
        FileStructureConfiguration configuration = TestConfigurations.IPHONE_CONFIGURATION;
        String message = "\"key\"=\"value\"";
        //WHEN
        KeyValuePair result = underTest.parseMessage(message, configuration);
        //THEN
        Assert.assertEquals(result.getKey(), "key");
        Assert.assertEquals(result.getValue(), "value");
    }

    @Test(expectedExceptions = InvalidFileStructureException.class)
    public void testParseWhenMessageHasAdditionalCharactersShouldThrowException() throws InvalidFileStructureException {
        //GIVEN
        FileStructureConfiguration configuration = TestConfigurations.IPHONE_CONFIGURATION;
        String message = "\"key\"=\"value\";";
        //WHEN
        underTest.parseMessage(message, configuration);
        //THEN throws exception
    }

    @Test(expectedExceptions = InvalidFileStructureException.class)
    public void testParseWithIphoneConfigWhenMessageIsInvalidShouldReturnKeyValuePair() throws InvalidFileStructureException {
        //GIVEN
        FileStructureConfiguration configuration = TestConfigurations.IPHONE_CONFIGURATION;
        String message = "\"key\"=\"value";
        //WHEN
        underTest.parseMessage(message, configuration);
        //THEN throws exception
    }

    @Test
    public void testParseWithEmptyConfigWhenMessageIsInvalidShouldReturnKeyValuePair() throws InvalidFileStructureException {
        //GIVEN
        FileStructureConfiguration configuration = TestConfigurations.EMPTY_CONFIGURATION;
        String message = "key=value;";
        //WHEN
        KeyValuePair result = underTest.parseMessage(message, configuration);
        //THEN
        Assert.assertEquals(result.getKey(), "key");
        Assert.assertEquals(result.getValue(), "value;");
    }
}
