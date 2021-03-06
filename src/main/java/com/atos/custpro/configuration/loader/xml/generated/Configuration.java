package com.atos.custpro.configuration.loader.xml.generated;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Java class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="4">
 *         &lt;element name="regexAffix" type="{http://www.github.com/atoska88/custpro}regexAffix"/>
 *         &lt;element name="literalAffix" type="{http://www.github.com/atoska88/custpro}literalAffix"/>
 *       &lt;/choice>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="charset" type="{http://www.w3.org/2001/XMLSchema}string" default="UTF-8" />
 *       &lt;attribute name="keyValueSeparator" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="lineTerminator" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"regexAffixOrLiteralAffix"})
@XmlRootElement(name = "configuration")
public class Configuration {

    @XmlElements({@XmlElement(name = "regexAffix", type = RegexAffix.class), @XmlElement(name = "literalAffix", type = LiteralAffix.class)})
    private List<Affix> regexAffixOrLiteralAffix;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    private String name;
    @XmlAttribute
    private String charset;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    private String keyValueSeparator;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    private String lineTerminator;

    /**
     * Gets the value of the regexAffixOrLiteralAffix property.
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the regexAffixOrLiteralAffix property.
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegexAffixOrLiteralAffix().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegexAffix }
     * {@link LiteralAffix }
     * @return a list of affixes
     */
    public List<Affix> getRegexAffixOrLiteralAffix() {
        if (regexAffixOrLiteralAffix == null) {
            regexAffixOrLiteralAffix = new ArrayList<Affix>();
        }
        return this.regexAffixOrLiteralAffix;
    }

    /**
     * Gets the value of the name property.
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setName(final String value) {
        this.name = value;
    }

    /**
     * Gets the value of the charset property.
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getCharset() {
        return (charset == null) ? "UTF-8" : charset;
    }

    /**
     * Sets the value of the charset property.
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setCharset(final String value) {
        this.charset = value;
    }

    /**
     * Gets the value of the keyValueSeparator property.
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getKeyValueSeparator() {
        return keyValueSeparator;
    }

    /**
     * Sets the value of the keyValueSeparator property.
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setKeyValueSeparator(final String value) {
        this.keyValueSeparator = value;
    }

    /**
     * Gets the value of the lineTerminator property.
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getLineTerminator() {
        return lineTerminator;
    }

    /**
     * Sets the value of the lineTerminator property.
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setLineTerminator(final String value) {
        this.lineTerminator = value;
    }

}
