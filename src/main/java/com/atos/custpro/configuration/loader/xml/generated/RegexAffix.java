package com.atos.custpro.configuration.loader.xml.generated;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for regexAffix complex type.
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;complexType name="regexAffix">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.github.com/atoska88/custpro}affix">
 *       &lt;attribute name="numOfCapturingGroupsInPrefix" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "regexAffix")
public class RegexAffix extends Affix {

    @XmlAttribute(required = true)
    private BigInteger numOfCapturingGroupsInPrefix;

    /**
     * Gets the value of the numOfCapturingGroupsInPrefix property.
     * @return
     *     possible object is
     *     {@link BigInteger }
     */
    public BigInteger getNumOfCapturingGroupsInPrefix() {
        return numOfCapturingGroupsInPrefix;
    }

    /**
     * Sets the value of the numOfCapturingGroupsInPrefix property.
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     */
    public void setNumOfCapturingGroupsInPrefix(final BigInteger value) {
        this.numOfCapturingGroupsInPrefix = value;
    }

}
