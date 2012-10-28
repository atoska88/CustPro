package com.atos.custpro.configuration.loader.xml.internal;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.atos.custpro.configuration.TestJaxbConfigurations;
import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.loader.FileStructureWrapper;
import com.atos.custpro.configuration.loader.xml.exception.XmlParsingException;
import com.atos.custpro.configuration.loader.xml.generated.Affix;
import com.atos.custpro.configuration.loader.xml.generated.Configuration;

/**
 * Unit test of class {@link SimpleJaxbConfigurationTransformer}.
 * @author atos
 * @since 1.0
 */
public class SimpleJaxbConfigurationTransformerTest {

    private JaxbConfigurationTransformer underTest;

    @BeforeMethod
    public void setUp() {
        underTest = new SimpleJaxbConfigurationTransformer();
    }

    @Test
    public void testTransformWithAndroidConfigurationShouldTransformConfig() throws XmlParsingException {
        //GIVEN
        //WHEN
        Configuration original = TestJaxbConfigurations.ANDROID_CONFIGURATION;
        FileStructureWrapper result = underTest.transform(original);
        //THEN
        validate(result.getFileStructure(), original);
        assertEquals(result.getName(), original.getName());
    }

    @Test
    public void testTransformWithAndroidRegexConfigurationShouldTransformConfig() throws XmlParsingException {
        //GIVEN
        //WHEN
        Configuration original = TestJaxbConfigurations.ANDROID_REGEX_CONFIGURATION;
        FileStructureWrapper result = underTest.transform(original);
        //THEN
        validate(result.getFileStructure(), original);
        assertEquals(result.getName(), original.getName());
    }

    @Test
    public void testTransformWithIphoneConfigurationShouldTransformConfig() throws XmlParsingException {
        //GIVEN
        //WHEN
        Configuration original = TestJaxbConfigurations.IPHONE_CONFIGURATION;
        FileStructureWrapper result = underTest.transform(original);
        //THEN
        validate(result.getFileStructure(), original);
        assertEquals(result.getName(), original.getName());
    }

    @Test
    public void testTransformWithEmptyConfigurationShouldTransformConfig() throws XmlParsingException {
        //GIVEN
        //WHEN
        Configuration original = TestJaxbConfigurations.EMPTY_CONFIGURATION;
        FileStructureWrapper result = underTest.transform(original);
        //THEN
        validate(result.getFileStructure(), original);
        assertEquals(result.getName(), original.getName());
    }

    private void validate(final FileStructureConfiguration result, final Configuration original) {
        assertEquals(result.getCharSet(), original.getCharset());
        assertEquals(result.getKeyValueSeparator(), original.getKeyValueSeparator());
        assertEquals(result.getLineTerminator(), original.getLineTerminator());
        List<Affix> affixes = original.getRegexAffixOrLiteralAffix();
        for (Affix affix : affixes) {
            switch (affix.getPlace()) {
            case FILE:
                assertEquals(result.getFileAffix().getPrefix(), affix.getPrefix());
                assertEquals(result.getFileAffix().getSuffix(), affix.getSuffix());
                break;
            case KEY:
                assertEquals(result.getKeyAffix().getPrefix(), affix.getPrefix());
                assertEquals(result.getKeyAffix().getSuffix(), affix.getSuffix());
                break;
            case PROPERTY:
                assertEquals(result.getPropertyAffix().getPrefix(), affix.getPrefix());
                assertEquals(result.getPropertyAffix().getSuffix(), affix.getSuffix());
                break;
            case VALUE:
                assertEquals(result.getValueAffix().getPrefix(), affix.getPrefix());
                assertEquals(result.getValueAffix().getSuffix(), affix.getSuffix());
                break;
            default:
                break;

            }
        }

    }
}
