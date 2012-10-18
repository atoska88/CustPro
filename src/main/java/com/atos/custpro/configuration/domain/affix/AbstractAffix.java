package com.atos.custpro.configuration.domain.affix;

import org.springframework.util.Assert;

import com.atos.custpro.configuration.domain.Affix;

/**
 * A base {@link Affix} that contains the prefix and suffix strings.
 * @author atos
 *
 */
public abstract class AbstractAffix implements Affix {

    private static final String EMPTY_STRING = "";
    private final String prefix;
    private final String suffix;

    /**
     * Constructs a new empty affix with empty prefix and suffix.
     */
    public AbstractAffix() {
        this(EMPTY_STRING, EMPTY_STRING);
    }

    /**
     * Constructs a new affix with the given prefix and suffix.
     * @param prefix the prefix that stands before the string
     * @param suffix the suffix that stands after the string
     */
    public AbstractAffix(final String prefix, final String suffix) {
        Assert.notNull(prefix, "Parameter 'prefix' can not be null!");
        Assert.notNull(suffix, "Parameter 'suffix' can not be null!");
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

}
