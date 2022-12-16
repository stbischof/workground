
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Process complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Process"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Type"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="ProcessFull"/&gt;
 *               &lt;enumeration value="ProcessAdd"/&gt;
 *               &lt;enumeration value="ProcessUpdate"/&gt;
 *               &lt;enumeration value="ProcessIndexes"/&gt;
 *               &lt;enumeration value="ProcessScriptCache"/&gt;
 *               &lt;enumeration value="ProcessData"/&gt;
 *               &lt;enumeration value="ProcessDefault"/&gt;
 *               &lt;enumeration value="ProcessClear"/&gt;
 *               &lt;enumeration value="ProcessStructure"/&gt;
 *               &lt;enumeration value="ProcessClearStructureOnly"/&gt;
 *               &lt;enumeration value="ProcessClearIndexes"/&gt;
 *               &lt;enumeration value="ProcessDefrag"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Object" type="{urn:schemas-microsoft-com:xml-analysis}ObjectReference"/&gt;
 *         &lt;element name="Bindings" type="{urn:schemas-microsoft-com:xml-analysis}Bindings" minOccurs="0"/&gt;
 *         &lt;element name="DataSource" type="{urn:schemas-microsoft-com:xml-analysis}DataSource" minOccurs="0"/&gt;
 *         &lt;element name="DataSourceView" type="{urn:schemas-microsoft-com:xml-analysis}DataSourceView" minOccurs="0"/&gt;
 *         &lt;element name="ErrorConfiguration" type="{urn:schemas-microsoft-com:xml-analysis}ErrorConfiguration" minOccurs="0"/&gt;
 *         &lt;element name="WriteBackTableCreation" type="{urn:schemas-microsoft-com:xml-analysis}WriteBackTableCreation" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Process", propOrder = {

})
public class Process {

    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Object", required = true)
    protected ObjectReference object;
    @XmlElement(name = "Bindings")
    protected Bindings bindings;
    @XmlElement(name = "DataSource")
    protected DataSource dataSource;
    @XmlElement(name = "DataSourceView")
    protected DataSourceView dataSourceView;
    @XmlElement(name = "ErrorConfiguration")
    protected ErrorConfiguration errorConfiguration;
    @XmlElement(name = "WriteBackTableCreation")
    @XmlSchemaType(name = "string")
    protected WriteBackTableCreation writeBackTableCreation;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the object property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectReference }
     *     
     */
    public ObjectReference getObject() {
        return object;
    }

    /**
     * Sets the value of the object property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectReference }
     *     
     */
    public void setObject(ObjectReference value) {
        this.object = value;
    }

    /**
     * Gets the value of the bindings property.
     * 
     * @return
     *     possible object is
     *     {@link Bindings }
     *     
     */
    public Bindings getBindings() {
        return bindings;
    }

    /**
     * Sets the value of the bindings property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bindings }
     *     
     */
    public void setBindings(Bindings value) {
        this.bindings = value;
    }

    /**
     * Gets the value of the dataSource property.
     * 
     * @return
     *     possible object is
     *     {@link DataSource }
     *     
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Sets the value of the dataSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSource }
     *     
     */
    public void setDataSource(DataSource value) {
        this.dataSource = value;
    }

    /**
     * Gets the value of the dataSourceView property.
     * 
     * @return
     *     possible object is
     *     {@link DataSourceView }
     *     
     */
    public DataSourceView getDataSourceView() {
        return dataSourceView;
    }

    /**
     * Sets the value of the dataSourceView property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSourceView }
     *     
     */
    public void setDataSourceView(DataSourceView value) {
        this.dataSourceView = value;
    }

    /**
     * Gets the value of the errorConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorConfiguration }
     *     
     */
    public ErrorConfiguration getErrorConfiguration() {
        return errorConfiguration;
    }

    /**
     * Sets the value of the errorConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorConfiguration }
     *     
     */
    public void setErrorConfiguration(ErrorConfiguration value) {
        this.errorConfiguration = value;
    }

    /**
     * Gets the value of the writeBackTableCreation property.
     * 
     * @return
     *     possible object is
     *     {@link WriteBackTableCreation }
     *     
     */
    public WriteBackTableCreation getWriteBackTableCreation() {
        return writeBackTableCreation;
    }

    /**
     * Sets the value of the writeBackTableCreation property.
     * 
     * @param value
     *     allowed object is
     *     {@link WriteBackTableCreation }
     *     
     */
    public void setWriteBackTableCreation(WriteBackTableCreation value) {
        this.writeBackTableCreation = value;
    }

}
