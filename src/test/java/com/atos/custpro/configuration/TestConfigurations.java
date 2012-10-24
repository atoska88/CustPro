package com.atos.custpro.configuration;

import com.atos.custpro.configuration.domain.FileStructureConfiguration;
import com.atos.custpro.configuration.domain.affix.LiteralAffix;

/**
 * Contains test {@link FileStructureConfiguration} objects for testing.
 * @author atos
 * @since 1.0
 */
public abstract class TestConfigurations {

    public static final FileStructureConfiguration EMPTY_CONFIGURATION = new FileStructureConfiguration();

    public static final FileStructureConfiguration ANDROID_CONFIGURATION = getAndroidConfig();

    public static final FileStructureConfiguration IPHONE_CONFIGURATION = getIphoneConfig();

    public static final String ANDROID_INPUT_FILE = "<resources>\n<string name=\"key\">value</string>\n<string name=\"key2\">value</string>\n</resources>";

    public static final String ANDROID_EMPTY_INPUT_FILE = "<resources>\n</resources>";

    public static final String ANDROID_INPUT_FILE_WITH_DUPLICATIONS = "<resources>\n<string name=\"key\">value</string>\n<string name=\"key\">newvalue</string>\n</resources>";

    public static final String IPHONE_INPUT_FILE = "\"key\"=\"value\";\n\"key2\"=\"value\";\n";

    public static final String IPHONE_EMPTY_INPUT_FILE = "";

    public static final String IPHONE_INPUT_FILE_WITH_DUPLICATIONS = "\"key\"=\"value\";\n\"key\"=\"newvalue\";\n";

    private static final String UTF_8_CONSTANT = "UTF-8";

    private static FileStructureConfiguration getAndroidConfig() {
        FileStructureConfiguration androidConfig = new FileStructureConfiguration();
        androidConfig.setCharSet(UTF_8_CONSTANT);
        androidConfig.setFileAffix(new LiteralAffix("<resources>\n", "</resources>"));
        androidConfig.setKeyAffix(new LiteralAffix("name=\"", "\""));
        androidConfig.setKeyValueSeparator(">");
        androidConfig.setLineTerminator("</string>\n");
        androidConfig.setPropertyAffix(new LiteralAffix("<string ", ""));
        androidConfig.setValueAffix(new LiteralAffix());
        return androidConfig;
    }

    private static FileStructureConfiguration getIphoneConfig() {
        FileStructureConfiguration iphoneConfig = new FileStructureConfiguration();
        iphoneConfig.setCharSet(UTF_8_CONSTANT);
        iphoneConfig.setFileAffix(new LiteralAffix());
        iphoneConfig.setKeyAffix(new LiteralAffix("\"", "\""));
        iphoneConfig.setKeyValueSeparator("=");
        iphoneConfig.setLineTerminator(";\n");
        iphoneConfig.setPropertyAffix(new LiteralAffix());
        iphoneConfig.setValueAffix(new LiteralAffix("\"", "\""));
        return iphoneConfig;
    }

}
