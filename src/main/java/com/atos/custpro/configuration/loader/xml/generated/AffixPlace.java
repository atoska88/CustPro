package com.atos.custpro.configuration.loader.xml.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for affixPlace.
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="affixPlace">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="file"/>
 *     &lt;enumeration value="property"/>
 *     &lt;enumeration value="key"/>
 *     &lt;enumeration value="value"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "affixPlace")
@XmlEnum
public enum AffixPlace {

    @XmlEnumValue("file")
    FILE("file"),
    @XmlEnumValue("property")
    PROPERTY("property"),
    @XmlEnumValue("key")
    KEY("key"),
    @XmlEnumValue("value")
    VALUE("value");
    private final String value;

    AffixPlace(final String v) {
        value = v;
    }

    /**
     * Returns the value.
     * @return value
     */
    public String value() {
        return value;
    }

    /**
     * Returns the enum constant of the specified enum type with the
     * specified name.
     * @param v the name
     * @return the enum
     */
    public static AffixPlace fromValue(final String v) {
        for (AffixPlace c : AffixPlace.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
