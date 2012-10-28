package com.atos.custpro.configuration.loader.xml.generated;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.github.com/atoska88/custpro}configuration" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"configuration"})
@XmlRootElement(name = "custpro")
public class Custpro {

    @XmlElement(required = true)
    private List<Configuration> configuration;

    /**
     * Gets the value of the configuration property.
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the configuration property.
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfiguration().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Configuration }
     * @return a list of {@link Configuration}
     */
    public List<Configuration> getConfiguration() {
        if (configuration == null) {
            configuration = new ArrayList<Configuration>();
        }
        return this.configuration;
    }

}
