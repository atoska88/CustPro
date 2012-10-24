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
    private String keyValueSeparator;
    private Affix valueAffix;
    private String lineTerminator;
    private String charSet;

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

    public void setKeyValueSeparator(final String keyValueSeparator) {
        this.keyValueSeparator = keyValueSeparator;
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

    public void setLineTerminator(final String lineTerminator) {
        this.lineTerminator = lineTerminator;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(final String charSet) {
        this.charSet = charSet;
    }

    private Affix nullSafeGet(final Affix affix) {
        return affix == null ? EMPTY_AFFIX : affix;
    }
}
