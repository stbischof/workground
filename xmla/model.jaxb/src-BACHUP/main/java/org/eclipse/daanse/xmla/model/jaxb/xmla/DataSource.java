
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.daanse.xmla.model.jaxb.engine.ImpersonationInfo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataSource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataSource"&gt;
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
 *         &lt;element name="ManagedProvider" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ConnectionString" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ConnectionStringSecurity" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="PasswordRemoved"/&gt;
 *               &lt;enumeration value="Unchanged"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ImpersonationInfo" type="{http://schemas.microsoft.com/analysisservices/2003/engine}ImpersonationInfo" minOccurs="0"/&gt;
 *         &lt;element name="Isolation" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="ReadCommitted"/&gt;
 *               &lt;enumeration value="Snapshot"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MaxActiveConnections" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Timeout" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/&gt;
 *         &lt;element name="DataSourcePermissions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DataSourcePermission" type="{urn:schemas-microsoft-com:xml-analysis}DataSourcePermission" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300}QueryImpersonationInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300}QueryHints" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSource", propOrder = {

})
@XmlSeeAlso({
    RelationalDataSource.class,
    OlapDataSource.class
})
public abstract class DataSource {

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
    protected DataSource.Annotations annotations;
    @XmlElement(name = "ManagedProvider")
    protected String managedProvider;
    @XmlElement(name = "ConnectionString", required = true)
    protected String connectionString;
    @XmlElement(name = "ConnectionStringSecurity")
    protected String connectionStringSecurity;
    @XmlElement(name = "ImpersonationInfo")
    protected ImpersonationInfo impersonationInfo;
    @XmlElement(name = "Isolation")
    protected String isolation;
    @XmlElement(name = "MaxActiveConnections")
    protected BigInteger maxActiveConnections;
    @XmlElement(name = "Timeout")
    protected Duration timeout;
    @XmlElement(name = "DataSourcePermissions")
    protected DataSource.DataSourcePermissions dataSourcePermissions;
    @XmlElement(name = "QueryImpersonationInfo", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected ImpersonationInfo queryImpersonationInfo;
    @XmlElement(name = "QueryHints", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected String queryHints;

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

    public boolean isSetName() {
        return (this.name!= null);
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

    public boolean isSetID() {
        return (this.id!= null);
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

    public boolean isSetCreatedTimestamp() {
        return (this.createdTimestamp!= null);
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

    public boolean isSetLastSchemaUpdate() {
        return (this.lastSchemaUpdate!= null);
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

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link DataSource.Annotations }
     *     
     */
    public DataSource.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSource.Annotations }
     *     
     */
    public void setAnnotations(DataSource.Annotations value) {
        this.annotations = value;
    }

    public boolean isSetAnnotations() {
        return (this.annotations!= null);
    }

    /**
     * Gets the value of the managedProvider property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManagedProvider() {
        return managedProvider;
    }

    /**
     * Sets the value of the managedProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManagedProvider(String value) {
        this.managedProvider = value;
    }

    public boolean isSetManagedProvider() {
        return (this.managedProvider!= null);
    }

    /**
     * Gets the value of the connectionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * Sets the value of the connectionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectionString(String value) {
        this.connectionString = value;
    }

    public boolean isSetConnectionString() {
        return (this.connectionString!= null);
    }

    /**
     * Gets the value of the connectionStringSecurity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectionStringSecurity() {
        return connectionStringSecurity;
    }

    /**
     * Sets the value of the connectionStringSecurity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectionStringSecurity(String value) {
        this.connectionStringSecurity = value;
    }

    public boolean isSetConnectionStringSecurity() {
        return (this.connectionStringSecurity!= null);
    }

    /**
     * Gets the value of the impersonationInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ImpersonationInfo }
     *     
     */
    public ImpersonationInfo getImpersonationInfo() {
        return impersonationInfo;
    }

    /**
     * Sets the value of the impersonationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImpersonationInfo }
     *     
     */
    public void setImpersonationInfo(ImpersonationInfo value) {
        this.impersonationInfo = value;
    }

    public boolean isSetImpersonationInfo() {
        return (this.impersonationInfo!= null);
    }

    /**
     * Gets the value of the isolation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsolation() {
        return isolation;
    }

    /**
     * Sets the value of the isolation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsolation(String value) {
        this.isolation = value;
    }

    public boolean isSetIsolation() {
        return (this.isolation!= null);
    }

    /**
     * Gets the value of the maxActiveConnections property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxActiveConnections() {
        return maxActiveConnections;
    }

    /**
     * Sets the value of the maxActiveConnections property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxActiveConnections(BigInteger value) {
        this.maxActiveConnections = value;
    }

    public boolean isSetMaxActiveConnections() {
        return (this.maxActiveConnections!= null);
    }

    /**
     * Gets the value of the timeout property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getTimeout() {
        return timeout;
    }

    /**
     * Sets the value of the timeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setTimeout(Duration value) {
        this.timeout = value;
    }

    public boolean isSetTimeout() {
        return (this.timeout!= null);
    }

    /**
     * Gets the value of the dataSourcePermissions property.
     * 
     * @return
     *     possible object is
     *     {@link DataSource.DataSourcePermissions }
     *     
     */
    public DataSource.DataSourcePermissions getDataSourcePermissions() {
        return dataSourcePermissions;
    }

    /**
     * Sets the value of the dataSourcePermissions property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSource.DataSourcePermissions }
     *     
     */
    public void setDataSourcePermissions(DataSource.DataSourcePermissions value) {
        this.dataSourcePermissions = value;
    }

    public boolean isSetDataSourcePermissions() {
        return (this.dataSourcePermissions!= null);
    }

    /**
     * Gets the value of the queryImpersonationInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ImpersonationInfo }
     *     
     */
    public ImpersonationInfo getQueryImpersonationInfo() {
        return queryImpersonationInfo;
    }

    /**
     * Sets the value of the queryImpersonationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImpersonationInfo }
     *     
     */
    public void setQueryImpersonationInfo(ImpersonationInfo value) {
        this.queryImpersonationInfo = value;
    }

    public boolean isSetQueryImpersonationInfo() {
        return (this.queryImpersonationInfo!= null);
    }

    /**
     * Gets the value of the queryHints property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryHints() {
        return queryHints;
    }

    /**
     * Sets the value of the queryHints property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryHints(String value) {
        this.queryHints = value;
    }

    public boolean isSetQueryHints() {
        return (this.queryHints!= null);
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

        public boolean isSetAnnotation() {
            return ((this.annotation!= null)&&(!this.annotation.isEmpty()));
        }

        public void unsetAnnotation() {
            this.annotation = null;
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
     *         &lt;element name="DataSourcePermission" type="{urn:schemas-microsoft-com:xml-analysis}DataSourcePermission" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "dataSourcePermission"
    })
    public static class DataSourcePermissions {

        @XmlElement(name = "DataSourcePermission")
        protected List<DataSourcePermission> dataSourcePermission;

        /**
         * Gets the value of the dataSourcePermission property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the dataSourcePermission property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDataSourcePermission().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DataSourcePermission }
         * 
         * 
         */
        public List<DataSourcePermission> getDataSourcePermission() {
            if (dataSourcePermission == null) {
                dataSourcePermission = new ArrayList<DataSourcePermission>();
            }
            return this.dataSourcePermission;
        }

        public boolean isSetDataSourcePermission() {
            return ((this.dataSourcePermission!= null)&&(!this.dataSourcePermission.isEmpty()));
        }

        public void unsetDataSourcePermission() {
            this.dataSourcePermission = null;
        }

    }

}
