package com.atos.custpro.configuration.domain;

/**
 * Contains a prefix and a suffix and some helper methods for handling
 * affixes.
 * @author atos
 *
 */
public final class Affix {

    private static final String EMPTY_STRING = "";
    private final String prefix;
    private final String suffix;

    /**
     * Constructs an empty affix with empty prefix and suffix.
     */
    public Affix() {
        this(EMPTY_STRING, EMPTY_STRING);
    }

    /**
     * Constructs a new affix with the given parameters.
     * @param prefix the prefix, that is before a character sequence
     * @param suffix the suffix, that is after a character sequence
     */
    public Affix(final String prefix, final String suffix) {
        super();
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    /**
     * Bounds the given string with this Affix.
     * @param target the target string
     * @return the target string bounded by this affix. First the
     * prefix, the target and the suffix.
     *
     */
    public String putAffixToTarget(final String target) {
        return prefix + target + suffix;
    }
}
