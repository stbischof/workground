
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MdxScript complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MdxScript"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CreatedTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="LastSchemaUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Annotations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Annotation" type="{urn:schemas-microsoft-com:xml-analysis}Annotation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Commands" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Command" type="{urn:schemas-microsoft-com:xml-analysis}Command" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DefaultScript" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="CalculationProperties" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="CalculationProperty" type="{urn:schemas-microsoft-com:xml-analysis}CalculationProperty" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdxScript", propOrder = {

})
public class MdxScript {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "CreatedTimestamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTimestamp;
    @XmlElement(name = "LastSchemaUpdate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastSchemaUpdate;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Annotations")
    protected MdxScript.Annotations annotations;
    @XmlElement(name = "Commands")
    protected MdxScript.Commands commands;
    @XmlElement(name = "DefaultScript")
    protected Boolean defaultScript;
    @XmlElement(name = "CalculationProperties")
    protected MdxScript.CalculationProperties calculationProperties;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the createdTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * Sets the value of the createdTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTimestamp(XMLGregorianCalendar value) {
        this.createdTimestamp = value;
    }

    /**
     * Gets the value of the lastSchemaUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastSchemaUpdate() {
        return lastSchemaUpdate;
    }

    /**
     * Sets the value of the lastSchemaUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastSchemaUpdate(XMLGregorianCalendar value) {
        this.lastSchemaUpdate = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link MdxScript.Annotations }
     *     
     */
    public MdxScript.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link MdxScript.Annotations }
     *     
     */
    public void setAnnotations(MdxScript.Annotations value) {
        this.annotations = value;
    }

    /**
     * Gets the value of the commands property.
     * 
     * @return
     *     possible object is
     *     {@link MdxScript.Commands }
     *     
     */
    public MdxScript.Commands getCommands() {
        return commands;
    }

    /**
     * Sets the value of the commands property.
     * 
     * @param value
     *     allowed object is
     *     {@link MdxScript.Commands }
     *     
     */
    public void setCommands(MdxScript.Commands value) {
        this.commands = value;
    }

    /**
     * Gets the value of the defaultScript property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDefaultScript() {
        return defaultScript;
    }

    /**
     * Sets the value of the defaultScript property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefaultScript(Boolean value) {
        this.defaultScript = value;
    }

    /**
     * Gets the value of the calculationProperties property.
     * 
     * @return
     *     possible object is
     *     {@link MdxScript.CalculationProperties }
     *     
     */
    public MdxScript.CalculationProperties getCalculationProperties() {
        return calculationProperties;
    }

    /**
     * Sets the value of the calculationProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link MdxScript.CalculationProperties }
     *     
     */
    public void setCalculationProperties(MdxScript.CalculationProperties value) {
        this.calculationProperties = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Annotation" type="{urn:schemas-microsoft-com:xml-analysis}Annotation" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "annotation"
    })
    public static class Annotations {

        @XmlElement(name = "Annotation")
        protected List<Annotation> annotation;

        /**
         * Gets the value of the annotation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the annotation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAnnotation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Annotation }
         * 
         * 
         */
        public List<Annotation> getAnnotation() {
            if (annotation == null) {
                annotation = new ArrayList<Annotation>();
            }
            return this.annotation;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="CalculationProperty" type="{urn:schemas-microsoft-com:xml-analysis}CalculationProperty" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "calculationProperty"
    })
    public static class CalculationProperties {

        @XmlElement(name = "CalculationProperty")
        protected List<CalculationProperty> calculationProperty;

        /**
         * Gets the value of the calculationProperty property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the calculationProperty property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCalculationProperty().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CalculationProperty }
         * 
         * 
         */
        public List<CalculationProperty> getCalculationProperty() {
            if (calculationProperty == null) {
                calculationProperty = new ArrayList<CalculationProperty>();
            }
            return this.calculationProperty;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Command" type="{urn:schemas-microsoft-com:xml-analysis}Command" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "command"
    })
    public static class Commands {

        @XmlElement(name = "Command")
        protected List<Command> command;

        /**
         * Gets the value of the command property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the command property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCommand().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Command }
         * 
         * 
         */
        public List<Command> getCommand() {
            if (command == null) {
                command = new ArrayList<Command>();
            }
            return this.command;
        }

    }

}
