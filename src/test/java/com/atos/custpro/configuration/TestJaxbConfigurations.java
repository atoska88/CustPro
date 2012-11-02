package com.atos.custpro.configuration;

import java.math.BigInteger;
import java.util.List;

import com.atos.custpro.configuration.loader.xml.generated.Affix;
import com.atos.custpro.configuration.loader.xml.generated.AffixPlace;
import com.atos.custpro.configuration.loader.xml.generated.Configuration;
import com.atos.custpro.configuration.loader.xml.generated.Custpro;
import com.atos.custpro.configuration.loader.xml.generated.ObjectFactory;
import com.atos.custpro.configuration.loader.xml.generated.RegexAffix;

/**
 * Contains test {@link Configuration} objects for testing.
 * @author atos
 * @since 1.0
 */
public abstract class TestJaxbConfigurations {

    public static final Configuration EMPTY_CONFIGURATION = new Configuration();

    public static final Configuration ANDROID_CONFIGURATION = getLiteralAndroidConfig();

    public static final Configuration ANDROID_REGEX_CONFIGURATION = getRegexAndroidConfig();

    public static final Configuration IPHONE_CONFIGURATION = getIphoneConfig();

    public static final String ANDROID_INPUT_FILE = "<resources>\n<string name=\"key\">value</string>\n<string name=\"key2\">value</string>\n</resources>";

    public static final String ANDROID_EMPTY_INPUT_FILE = "<resources>\n</resources>";

    public static final String ANDROID_INPUT_FILE_WITH_DUPLICATIONS = "<resources>\n<string name=\"key\">value</string>\n<string name=\"key\">newvalue</string>\n</resources>";

    public static final String IPHONE_INPUT_FILE = "\"key\"=\"value\";\n\"key2\"=\"value\";\n";

    public static final String IPHONE_EMPTY_INPUT_FILE = "";

    public static final String IPHONE_INPUT_FILE_WITH_DUPLICATIONS = "\"key\"=\"value\";\n\"key\"=\"newvalue\";\n";

    private static ObjectFactory objectFactory;

    private static final String UTF_8_CONSTANT = "UTF-8";

    private static ObjectFactory getObjectFactory() {
        if (objectFactory == null) {
            objectFactory = new ObjectFactory();
        }
        return objectFactory;
    }

    private static Configuration getLiteralAndroidConfig() {
        Custpro custPro = getObjectFactory().createCustpro();
        Configuration androidConfig = getObjectFactory().createConfiguration();
        custPro.getConfiguration().add(androidConfig);
        androidConfig.setCharset(UTF_8_CONSTANT);
        androidConfig.setName("android");
        Affix fileAffix = getObjectFactory().createLiteralAffix();
        fileAffix.setPlace(AffixPlace.FILE);
        fileAffix.setPrefix("<resources>\n");
        fileAffix.setSuffix("</resources>");
        androidConfig.getRegexAffixOrLiteralAffix().add(fileAffix);
        Affix keyAffix = getObjectFactory().createLiteralAffix();
        keyAffix.setPlace(AffixPlace.KEY);
        keyAffix.setPrefix("name=\"");
        keyAffix.setSuffix("\"");
        androidConfig.getRegexAffixOrLiteralAffix().add(keyAffix);
        androidConfig.setKeyValueSeparator(">");
        androidConfig.setLineTerminator("</string>\n");
        Affix propertyAffix = getObjectFactory().createLiteralAffix();
        propertyAffix.setPlace(AffixPlace.PROPERTY);
        propertyAffix.setPrefix("<string ");
        androidConfig.getRegexAffixOrLiteralAffix().add(propertyAffix);
        Affix valueAffix = getObjectFactory().createLiteralAffix();
        valueAffix.setPlace(AffixPlace.VALUE);
        androidConfig.getRegexAffixOrLiteralAffix().add(valueAffix);
        return androidConfig;
    }

    private static Configuration getRegexAndroidConfig() {
        Configuration androidConfig = getObjectFactory().createConfiguration();
        androidConfig.setCharset(UTF_8_CONSTANT);
        androidConfig.setName("android");
        RegexAffix fileAffix = getObjectFactory().createRegexAffix();
        fileAffix.setNumOfCapturingGroupsInPrefix(new BigInteger("0"));
        fileAffix.setPlace(AffixPlace.FILE);
        fileAffix.setPrefix("<resources>\\s*");
        fileAffix.setSuffix("</resources>");
        androidConfig.getRegexAffixOrLiteralAffix().add(fileAffix);
        Affix keyAffix = getObjectFactory().createLiteralAffix();
        keyAffix.setPlace(AffixPlace.KEY);
        keyAffix.setPrefix("name=\"");
        keyAffix.setSuffix("\"");
        androidConfig.getRegexAffixOrLiteralAffix().add(keyAffix);
        androidConfig.setKeyValueSeparator(">");
        androidConfig.setLineTerminator("</string>\n");
        Affix propertyAffix = getObjectFactory().createLiteralAffix();
        propertyAffix.setPlace(AffixPlace.PROPERTY);
        propertyAffix.setPrefix("<string ");
        androidConfig.getRegexAffixOrLiteralAffix().add(propertyAffix);
        Affix valueAffix = getObjectFactory().createLiteralAffix();
        valueAffix.setPlace(AffixPlace.VALUE);
        androidConfig.getRegexAffixOrLiteralAffix().add(valueAffix);
        return androidConfig;
    }

    private static Configuration getIphoneConfig() {
        Configuration iphoneConfig = getObjectFactory().createConfiguration();
        iphoneConfig.setCharset(UTF_8_CONSTANT);
        List<Affix> affixes = iphoneConfig.getRegexAffixOrLiteralAffix();
        Affix fileAffix = getObjectFactory().createLiteralAffix();
        fileAffix.setPlace(AffixPlace.FILE);
        affixes.add(fileAffix);
        iphoneConfig.setName("iphone");
        Affix keyAffix = getObjectFactory().createLiteralAffix();
        keyAffix.setPlace(AffixPlace.KEY);
        keyAffix.setPrefix("\"");
        keyAffix.setSuffix("\"");
        affixes.add(keyAffix);
        iphoneConfig.setKeyValueSeparator("=");
        iphoneConfig.setLineTerminator(";\n");
        Affix propertyAffix = getObjectFactory().createLiteralAffix();
        propertyAffix.setPlace(AffixPlace.PROPERTY);
        affixes.add(propertyAffix);
        Affix valueAffix = getObjectFactory().createLiteralAffix();
        valueAffix.setPlace(AffixPlace.VALUE);
        valueAffix.setPrefix("\"");
        valueAffix.setSuffix("\"");
        affixes.add(valueAffix);
        return iphoneConfig;
    }
}
