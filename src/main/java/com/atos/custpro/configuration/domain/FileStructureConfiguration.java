package com.atos.custpro.configuration.domain;

import com.atos.custpro.configuration.domain.affix.LiteralAffix;

/**
 * Contains the structure of the input file. A complicated properties
 * file can have many parts, which can be prefixes and suffixes - so
 * affixes. This class works with the {@link LiteralAffix} class mainly.
 * @author atos
 *
 */
public class FileStructureConfiguration {

    private static final Affix EMPTY_AFFIX = new LiteralAffix();

    private Affix fileAffix;
    private Affix propertyAffix;
    private Affix keyAffix;
    private final String keyValueSeparator;
    private Affix valueAffix;
    private final String lineTerminator;
    private final String charSet;

    /**
     * Constructs a new instance with the given parameters. The affixes
     * are not initalised, can be set via the setter methods.
     * @param keyValueSeparator the key-value separator, stands after
     * the key and before the value
     * @param lineTerminator the line terminator string, must contain
     * the carriage return or line feed character too
     * @param charSet the character encoding that should be used
     * during the parsing
     */
    public FileStructureConfiguration(final String keyValueSeparator, final String lineTerminator, final String charSet) {
        this.keyValueSeparator = keyValueSeparator;
        this.lineTerminator = lineTerminator;
        this.charSet = charSet;
    }

    public Affix getFileAffix() {
        return nullSafeGet(fileAffix);
    }

    public void setFileAffix(final Affix fileAffix) {
        this.fileAffix = fileAffix;
    }

    public Affix getPropertyAffix() {
        return nullSafeGet(propertyAffix);
    }

    public void setPropertyAffix(final Affix propertyAffix) {
        this.propertyAffix = propertyAffix;
    }

    public Affix getKeyAffix() {
        return nullSafeGet(keyAffix);
    }

    public void setKeyAffix(final Affix keyAffix) {
        this.keyAffix = keyAffix;
    }

    public String getKeyValueSeparator() {
        return keyValueSeparator;
    }

    public Affix getValueAffix() {
        return nullSafeGet(valueAffix);
    }

    public void setValueAffix(final Affix valueAffix) {
        this.valueAffix = valueAffix;
    }

    public String getLineTerminator() {
        return lineTerminator;
    }

    public String getCharSet() {
        return charSet;
    }

    private Affix nullSafeGet(final Affix affix) {
        return affix == null ? EMPTY_AFFIX : affix;
    }
}
