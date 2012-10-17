package com.atos.custpro.configuration.domain;

/**
 * Contains the structure of the input file. A complicated properties
 * file can have many parts, which can be prefixes and suffixes - so
 * affixes. This class works with the {@link Affix} class mainly.
 * @author atos
 *
 */
public class FileStructure {

    private Affix fileAffix;
    private Affix propertyAffix;
    private Affix keyAffix;
    private String keyValueSeparator;
    private Affix valueAffix;

    public Affix getFileAffix() {
        return fileAffix;
    }

    public void setFileAffix(final Affix fileAffix) {
        this.fileAffix = fileAffix;
    }

    public Affix getPropertyAffix() {
        return propertyAffix;
    }

    public void setPropertyAffix(final Affix propertyAffix) {
        this.propertyAffix = propertyAffix;
    }

    public Affix getKeyAffix() {
        return keyAffix;
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
        return valueAffix;
    }

    public void setValueAffix(final Affix valueAffix) {
        this.valueAffix = valueAffix;
    }

}
