
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Permission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Permission"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
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
 *         &lt;element name="RoleID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Process" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ReadDefinition" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Basic"/&gt;
 *               &lt;enumeration value="Allowed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Read" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Allowed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Permission", propOrder = {
    "name",
    "id",
    "createdTimestamp",
    "lastSchemaUpdate",
    "description",
    "annotations",
    "roleID",
    "process",
    "readDefinition",
    "read"
})
@XmlSeeAlso({
    DatabasePermission.class,
    DataSourcePermission.class,
    DimensionPermission.class,
    MiningStructurePermission.class,
    MiningModelPermission.class,
    CubePermission.class
})
public class Permission {

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
    protected Permission.Annotations annotations;
    @XmlElement(name = "RoleID", required = true)
    protected String roleID;
    @XmlElement(name = "Process")
    protected Boolean process;
    @XmlElement(name = "ReadDefinition")
    protected String readDefinition;
    @XmlElement(name = "Read")
    protected String read;

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
     *     {@link Permission.Annotations }
     *     
     */
    public Permission.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Permission.Annotations }
     *     
     */
    public void setAnnotations(Permission.Annotations value) {
        this.annotations = value;
    }

    /**
     * Gets the value of the roleID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleID() {
        return roleID;
    }

    /**
     * Sets the value of the roleID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleID(String value) {
        this.roleID = value;
    }

    /**
     * Gets the value of the process property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProcess() {
        return process;
    }

    /**
     * Sets the value of the process property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProcess(Boolean value) {
        this.process = value;
    }

    /**
     * Gets the value of the readDefinition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReadDefinition() {
        return readDefinition;
    }

    /**
     * Sets the value of the readDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReadDefinition(String value) {
        this.readDefinition = value;
    }

    /**
     * Gets the value of the read property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRead() {
        return read;
    }

    /**
     * Sets the value of the read property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRead(String value) {
        this.read = value;
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

}
