package com.atos.custpro.configuration.loader.xml.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Affix, contains a prefix and a suffix. The name attribute must be unique.
 * <p>Java class for affix complex type.
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;complexType name="affix">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="place" use="required" type="{http://www.github.com/atoska88/custpro}affixPlace" />
 *       &lt;attribute name="prefix" type="{http://www.w3.org/2001/XMLSchema}string" default="" />
 *       &lt;attribute name="suffix" type="{http://www.w3.org/2001/XMLSchema}string" default="" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "affix")
@XmlSeeAlso({RegexAffix.class, LiteralAffix.class})
public abstract class Affix {

    @XmlAttribute(required = true)
    private AffixPlace place;
    @XmlAttribute
    private String prefix;
    @XmlAttribute
    private String suffix;

    /**
     * Gets the value of the place property.
     * @return
     *     possible object is
     *     {@link AffixPlace }
     */
    public AffixPlace getPlace() {
        return place;
    }

    /**
     * Sets the value of the place property.
     * @param value
     *     allowed object is
     *     {@link AffixPlace }
     */
    public void setPlace(final AffixPlace value) {
        this.place = value;
    }

    /**
     * Gets the value of the prefix property.
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getPrefix() {
        return (prefix == null) ? "" : prefix;
    }

    /**
     * Sets the value of the prefix property.
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setPrefix(final String value) {
        this.prefix = value;
    }

    /**
     * Gets the value of the suffix property.
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getSuffix() {
        return (suffix == null) ? "" : suffix;
    }

    /**
     * Sets the value of the suffix property.
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setSuffix(final String value) {
        this.suffix = value;
    }

}
