
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataItem"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DataType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="WChar"/&gt;
 *               &lt;enumeration value="Integer"/&gt;
 *               &lt;enumeration value="BigInt"/&gt;
 *               &lt;enumeration value="Single"/&gt;
 *               &lt;enumeration value="Double"/&gt;
 *               &lt;enumeration value="Date"/&gt;
 *               &lt;enumeration value="Currency"/&gt;
 *               &lt;enumeration value="UnsignedTinyInt"/&gt;
 *               &lt;enumeration value="UnsignedSmallInt"/&gt;
 *               &lt;enumeration value="UnsignedInt"/&gt;
 *               &lt;enumeration value="UnsignedBigInt"/&gt;
 *               &lt;enumeration value="Bool"/&gt;
 *               &lt;enumeration value="Smallint"/&gt;
 *               &lt;enumeration value="Tinyint"/&gt;
 *               &lt;enumeration value="Binary"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DataSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MimeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NullProcessing" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Preserve"/&gt;
 *               &lt;enumeration value="Error"/&gt;
 *               &lt;enumeration value="UnknownMember"/&gt;
 *               &lt;enumeration value="ZeroOrBlank"/&gt;
 *               &lt;enumeration value="Automatic"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Trimming" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Left"/&gt;
 *               &lt;enumeration value="Right"/&gt;
 *               &lt;enumeration value="LeftRight"/&gt;
 *               &lt;enumeration value="None"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="InvalidXmlCharacters" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Preserve"/&gt;
 *               &lt;enumeration value="Remove"/&gt;
 *               &lt;enumeration value="Replace"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Collation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Format" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TrimRight"/&gt;
 *               &lt;enumeration value="TrimLeft"/&gt;
 *               &lt;enumeration value="TrimAll"/&gt;
 *               &lt;enumeration value="TrimNone"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Source" type="{urn:schemas-microsoft-com:xml-analysis}Binding" minOccurs="0"/&gt;
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
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataItem", propOrder = {

})
public class DataItem {

    @XmlElement(name = "DataType", required = true)
    protected String dataType;
    @XmlElement(name = "DataSize")
    protected BigInteger dataSize;
    @XmlElement(name = "MimeType")
    protected String mimeType;
    @XmlElement(name = "NullProcessing")
    protected String nullProcessing;
    @XmlElement(name = "Trimming")
    protected String trimming;
    @XmlElement(name = "InvalidXmlCharacters")
    protected String invalidXmlCharacters;
    @XmlElement(name = "Collation")
    protected String collation;
    @XmlElement(name = "Format")
    protected String format;
    @XmlElement(name = "Source")
    protected Binding source;
    @XmlElement(name = "Annotations")
    protected DataItem.Annotations annotations;

    /**
     * Gets the value of the dataType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Sets the value of the dataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataType(String value) {
        this.dataType = value;
    }

    public boolean isSetDataType() {
        return (this.dataType!= null);
    }

    /**
     * Gets the value of the dataSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDataSize() {
        return dataSize;
    }

    /**
     * Sets the value of the dataSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDataSize(BigInteger value) {
        this.dataSize = value;
    }

    public boolean isSetDataSize() {
        return (this.dataSize!= null);
    }

    /**
     * Gets the value of the mimeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Sets the value of the mimeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    public boolean isSetMimeType() {
        return (this.mimeType!= null);
    }

    /**
     * Gets the value of the nullProcessing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNullProcessing() {
        return nullProcessing;
    }

    /**
     * Sets the value of the nullProcessing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNullProcessing(String value) {
        this.nullProcessing = value;
    }

    public boolean isSetNullProcessing() {
        return (this.nullProcessing!= null);
    }

    /**
     * Gets the value of the trimming property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrimming() {
        return trimming;
    }

    /**
     * Sets the value of the trimming property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrimming(String value) {
        this.trimming = value;
    }

    public boolean isSetTrimming() {
        return (this.trimming!= null);
    }

    /**
     * Gets the value of the invalidXmlCharacters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvalidXmlCharacters() {
        return invalidXmlCharacters;
    }

    /**
     * Sets the value of the invalidXmlCharacters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvalidXmlCharacters(String value) {
        this.invalidXmlCharacters = value;
    }

    public boolean isSetInvalidXmlCharacters() {
        return (this.invalidXmlCharacters!= null);
    }

    /**
     * Gets the value of the collation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollation() {
        return collation;
    }

    /**
     * Sets the value of the collation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollation(String value) {
        this.collation = value;
    }

    public boolean isSetCollation() {
        return (this.collation!= null);
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    public boolean isSetFormat() {
        return (this.format!= null);
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Binding }
     *     
     */
    public Binding getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Binding }
     *     
     */
    public void setSource(Binding value) {
        this.source = value;
    }

    public boolean isSetSource() {
        return (this.source!= null);
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link DataItem.Annotations }
     *     
     */
    public DataItem.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataItem.Annotations }
     *     
     */
    public void setAnnotations(DataItem.Annotations value) {
        this.annotations = value;
    }

    public boolean isSetAnnotations() {
        return (this.annotations!= null);
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

}
