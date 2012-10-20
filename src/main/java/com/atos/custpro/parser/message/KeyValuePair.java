package com.atos.custpro.parser.message;

/**
 * Represents a key value pair.
 * @author atos
 * @since 1.0
 */
public final class KeyValuePair {

    private final String key;
    private final String value;

    /**
     * Constructs a new pair with the given key and value.
     * @param key the key
     * @param value the value
     */
    public KeyValuePair(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
