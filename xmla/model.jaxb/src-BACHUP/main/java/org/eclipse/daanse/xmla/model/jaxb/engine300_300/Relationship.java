
package org.eclipse.daanse.xmla.model.jaxb.engine300_300;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Relationship complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Relationship"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="FromRelationshipEnd" type="{http://schemas.microsoft.com/analysisservices/2011/engine/300/300}RelationshipEnd"/&gt;
 *         &lt;element name="ToRelationshipEnd" type="{http://schemas.microsoft.com/analysisservices/2011/engine/300/300}RelationshipEnd"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Relationship", propOrder = {
    "id",
    "visible",
    "fromRelationshipEnd",
    "toRelationshipEnd"
})
public class Relationship
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "ID", required = true)
    protected String id;
    @XmlElement(name = "Visible")
    protected boolean visible;
    @XmlElement(name = "FromRelationshipEnd", required = true)
    protected RelationshipEnd fromRelationshipEnd;
    @XmlElement(name = "ToRelationshipEnd", required = true)
    protected RelationshipEnd toRelationshipEnd;

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
     * Gets the value of the visible property.
     * 
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the value of the visible property.
     * 
     */
    public void setVisible(boolean value) {
        this.visible = value;
    }

    public boolean isSetVisible() {
        return true;
    }

    /**
     * Gets the value of the fromRelationshipEnd property.
     * 
     * @return
     *     possible object is
     *     {@link RelationshipEnd }
     *     
     */
    public RelationshipEnd getFromRelationshipEnd() {
        return fromRelationshipEnd;
    }

    /**
     * Sets the value of the fromRelationshipEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationshipEnd }
     *     
     */
    public void setFromRelationshipEnd(RelationshipEnd value) {
        this.fromRelationshipEnd = value;
    }

    public boolean isSetFromRelationshipEnd() {
        return (this.fromRelationshipEnd!= null);
    }

    /**
     * Gets the value of the toRelationshipEnd property.
     * 
     * @return
     *     possible object is
     *     {@link RelationshipEnd }
     *     
     */
    public RelationshipEnd getToRelationshipEnd() {
        return toRelationshipEnd;
    }

    /**
     * Sets the value of the toRelationshipEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationshipEnd }
     *     
     */
    public void setToRelationshipEnd(RelationshipEnd value) {
        this.toRelationshipEnd = value;
    }

    public boolean isSetToRelationshipEnd() {
        return (this.toRelationshipEnd!= null);
    }

}
