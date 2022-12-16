
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
 * <p>Java class for Server complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Server"&gt;
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
 *         &lt;element name="ProductName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Edition" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Standard"/&gt;
 *               &lt;enumeration value="Standard64"/&gt;
 *               &lt;enumeration value="Enterprise"/&gt;
 *               &lt;enumeration value="Enterprise64"/&gt;
 *               &lt;enumeration value="Developer"/&gt;
 *               &lt;enumeration value="Developer64"/&gt;
 *               &lt;enumeration value="Evaluation"/&gt;
 *               &lt;enumeration value="Evaluation64"/&gt;
 *               &lt;enumeration value="Local"/&gt;
 *               &lt;enumeration value="Local64"/&gt;
 *               &lt;enumeration value="BusinessIntelligence"/&gt;
 *               &lt;enumeration value="BusinessIntelligence64"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EditionID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2011/engine/300}ServerMode" minOccurs="0"/&gt;
 *         &lt;element name="ProductLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2012/engine/400}DefaultCompatibilityLevel" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2013/engine/600}SupportedCompatibilityLevels" minOccurs="0"/&gt;
 *         &lt;element name="Databases" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Database" type="{urn:schemas-microsoft-com:xml-analysis}Database" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Assemblies" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Assembly" type="{urn:schemas-microsoft-com:xml-analysis}Assembly" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Traces" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Trace" type="{urn:schemas-microsoft-com:xml-analysis}Trace" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Roles" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Role" type="{urn:schemas-microsoft-com:xml-analysis}Role" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ServerProperties" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ServerProperty" type="{urn:schemas-microsoft-com:xml-analysis}ServerProperty" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "Server", propOrder = {

})
public class Server {

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
    protected Server.Annotations annotations;
    @XmlElement(name = "ProductName")
    protected String productName;
    @XmlElement(name = "Edition")
    protected String edition;
    @XmlElement(name = "EditionID")
    protected Long editionID;
    @XmlElement(name = "Version")
    protected String version;
    @XmlElement(name = "ServerMode", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected String serverMode;
    @XmlElement(name = "ProductLevel")
    protected String productLevel;
    @XmlElement(name = "DefaultCompatibilityLevel", namespace = "http://schemas.microsoft.com/analysisservices/2012/engine/400")
    protected Long defaultCompatibilityLevel;
    @XmlElement(name = "SupportedCompatibilityLevels", namespace = "http://schemas.microsoft.com/analysisservices/2013/engine/600")
    protected String supportedCompatibilityLevels;
    @XmlElement(name = "Databases")
    protected Server.Databases databases;
    @XmlElement(name = "Assemblies")
    protected Server.Assemblies assemblies;
    @XmlElement(name = "Traces")
    protected Server.Traces traces;
    @XmlElement(name = "Roles")
    protected Server.Roles roles;
    @XmlElement(name = "ServerProperties")
    protected Server.ServerProperties serverProperties;

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
     *     {@link Server.Annotations }
     *     
     */
    public Server.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Server.Annotations }
     *     
     */
    public void setAnnotations(Server.Annotations value) {
        this.annotations = value;
    }

    public boolean isSetAnnotations() {
        return (this.annotations!= null);
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    public boolean isSetProductName() {
        return (this.productName!= null);
    }

    /**
     * Gets the value of the edition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEdition() {
        return edition;
    }

    /**
     * Sets the value of the edition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEdition(String value) {
        this.edition = value;
    }

    public boolean isSetEdition() {
        return (this.edition!= null);
    }

    /**
     * Gets the value of the editionID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEditionID() {
        return editionID;
    }

    /**
     * Sets the value of the editionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEditionID(Long value) {
        this.editionID = value;
    }

    public boolean isSetEditionID() {
        return (this.editionID!= null);
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    public boolean isSetVersion() {
        return (this.version!= null);
    }

    /**
     * Gets the value of the serverMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerMode() {
        return serverMode;
    }

    /**
     * Sets the value of the serverMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerMode(String value) {
        this.serverMode = value;
    }

    public boolean isSetServerMode() {
        return (this.serverMode!= null);
    }

    /**
     * Gets the value of the productLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductLevel() {
        return productLevel;
    }

    /**
     * Sets the value of the productLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductLevel(String value) {
        this.productLevel = value;
    }

    public boolean isSetProductLevel() {
        return (this.productLevel!= null);
    }

    /**
     * Gets the value of the defaultCompatibilityLevel property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDefaultCompatibilityLevel() {
        return defaultCompatibilityLevel;
    }

    /**
     * Sets the value of the defaultCompatibilityLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDefaultCompatibilityLevel(Long value) {
        this.defaultCompatibilityLevel = value;
    }

    public boolean isSetDefaultCompatibilityLevel() {
        return (this.defaultCompatibilityLevel!= null);
    }

    /**
     * Gets the value of the supportedCompatibilityLevels property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportedCompatibilityLevels() {
        return supportedCompatibilityLevels;
    }

    /**
     * Sets the value of the supportedCompatibilityLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportedCompatibilityLevels(String value) {
        this.supportedCompatibilityLevels = value;
    }

    public boolean isSetSupportedCompatibilityLevels() {
        return (this.supportedCompatibilityLevels!= null);
    }

    /**
     * Gets the value of the databases property.
     * 
     * @return
     *     possible object is
     *     {@link Server.Databases }
     *     
     */
    public Server.Databases getDatabases() {
        return databases;
    }

    /**
     * Sets the value of the databases property.
     * 
     * @param value
     *     allowed object is
     *     {@link Server.Databases }
     *     
     */
    public void setDatabases(Server.Databases value) {
        this.databases = value;
    }

    public boolean isSetDatabases() {
        return (this.databases!= null);
    }

    /**
     * Gets the value of the assemblies property.
     * 
     * @return
     *     possible object is
     *     {@link Server.Assemblies }
     *     
     */
    public Server.Assemblies getAssemblies() {
        return assemblies;
    }

    /**
     * Sets the value of the assemblies property.
     * 
     * @param value
     *     allowed object is
     *     {@link Server.Assemblies }
     *     
     */
    public void setAssemblies(Server.Assemblies value) {
        this.assemblies = value;
    }

    public boolean isSetAssemblies() {
        return (this.assemblies!= null);
    }

    /**
     * Gets the value of the traces property.
     * 
     * @return
     *     possible object is
     *     {@link Server.Traces }
     *     
     */
    public Server.Traces getTraces() {
        return traces;
    }

    /**
     * Sets the value of the traces property.
     * 
     * @param value
     *     allowed object is
     *     {@link Server.Traces }
     *     
     */
    public void setTraces(Server.Traces value) {
        this.traces = value;
    }

    public boolean isSetTraces() {
        return (this.traces!= null);
    }

    /**
     * Gets the value of the roles property.
     * 
     * @return
     *     possible object is
     *     {@link Server.Roles }
     *     
     */
    public Server.Roles getRoles() {
        return roles;
    }

    /**
     * Sets the value of the roles property.
     * 
     * @param value
     *     allowed object is
     *     {@link Server.Roles }
     *     
     */
    public void setRoles(Server.Roles value) {
        this.roles = value;
    }

    public boolean isSetRoles() {
        return (this.roles!= null);
    }

    /**
     * Gets the value of the serverProperties property.
     * 
     * @return
     *     possible object is
     *     {@link Server.ServerProperties }
     *     
     */
    public Server.ServerProperties getServerProperties() {
        return serverProperties;
    }

    /**
     * Sets the value of the serverProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Server.ServerProperties }
     *     
     */
    public void setServerProperties(Server.ServerProperties value) {
        this.serverProperties = value;
    }

    public boolean isSetServerProperties() {
        return (this.serverProperties!= null);
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
     *         &lt;element name="Assembly" type="{urn:schemas-microsoft-com:xml-analysis}Assembly" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "assembly"
    })
    public static class Assemblies {

        @XmlElement(name = "Assembly")
        protected List<Assembly> assembly;

        /**
         * Gets the value of the assembly property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the assembly property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssembly().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Assembly }
         * 
         * 
         */
        public List<Assembly> getAssembly() {
            if (assembly == null) {
                assembly = new ArrayList<Assembly>();
            }
            return this.assembly;
        }

        public boolean isSetAssembly() {
            return ((this.assembly!= null)&&(!this.assembly.isEmpty()));
        }

        public void unsetAssembly() {
            this.assembly = null;
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
     *         &lt;element name="Database" type="{urn:schemas-microsoft-com:xml-analysis}Database" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "database"
    })
    public static class Databases {

        @XmlElement(name = "Database")
        protected List<Database> database;

        /**
         * Gets the value of the database property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the database property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDatabase().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Database }
         * 
         * 
         */
        public List<Database> getDatabase() {
            if (database == null) {
                database = new ArrayList<Database>();
            }
            return this.database;
        }

        public boolean isSetDatabase() {
            return ((this.database!= null)&&(!this.database.isEmpty()));
        }

        public void unsetDatabase() {
            this.database = null;
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
     *         &lt;element name="Role" type="{urn:schemas-microsoft-com:xml-analysis}Role" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "role"
    })
    public static class Roles {

        @XmlElement(name = "Role")
        protected List<Role> role;

        /**
         * Gets the value of the role property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the role property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRole().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Role }
         * 
         * 
         */
        public List<Role> getRole() {
            if (role == null) {
                role = new ArrayList<Role>();
            }
            return this.role;
        }

        public boolean isSetRole() {
            return ((this.role!= null)&&(!this.role.isEmpty()));
        }

        public void unsetRole() {
            this.role = null;
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
     *         &lt;element name="ServerProperty" type="{urn:schemas-microsoft-com:xml-analysis}ServerProperty" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "serverProperty"
    })
    public static class ServerProperties {

        @XmlElement(name = "ServerProperty")
        protected List<ServerProperty> serverProperty;

        /**
         * Gets the value of the serverProperty property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the serverProperty property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServerProperty().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ServerProperty }
         * 
         * 
         */
        public List<ServerProperty> getServerProperty() {
            if (serverProperty == null) {
                serverProperty = new ArrayList<ServerProperty>();
            }
            return this.serverProperty;
        }

        public boolean isSetServerProperty() {
            return ((this.serverProperty!= null)&&(!this.serverProperty.isEmpty()));
        }

        public void unsetServerProperty() {
            this.serverProperty = null;
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
     *         &lt;element name="Trace" type="{urn:schemas-microsoft-com:xml-analysis}Trace" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "trace"
    })
    public static class Traces {

        @XmlElement(name = "Trace")
        protected List<Trace> trace;

        /**
         * Gets the value of the trace property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the trace property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTrace().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Trace }
         * 
         * 
         */
        public List<Trace> getTrace() {
            if (trace == null) {
                trace = new ArrayList<Trace>();
            }
            return this.trace;
        }

        public boolean isSetTrace() {
            return ((this.trace!= null)&&(!this.trace.isEmpty()));
        }

        public void unsetTrace() {
            this.trace = null;
        }

    }

}
