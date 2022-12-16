
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Translation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Translation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/&gt;
 *         &lt;element name="Caption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DisplayFolder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "Translation", propOrder = {

})
public class Translation {

    @XmlElement(name = "Language")
    @XmlSchemaType(name = "unsignedInt")
    protected long language;
    @XmlElement(name = "Caption")
    protected String caption;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "DisplayFolder")
    protected String displayFolder;
    @XmlElement(name = "Annotations")
    protected Translation.Annotations annotations;

    /**
     * Gets the value of the language property.
     * 
     */
    public long getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     */
    public void setLanguage(long value) {
        this.language = value;
    }

    public boolean isSetLanguage() {
        return true;
    }

    /**
     * Gets the value of the caption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Sets the value of the caption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaption(String value) {
        this.caption = value;
    }

    public boolean isSetCaption() {
        return (this.caption!= null);
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
     * Gets the value of the displayFolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayFolder() {
        return displayFolder;
    }

    /**
     * Sets the value of the displayFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayFolder(String value) {
        this.displayFolder = value;
    }

    public boolean isSetDisplayFolder() {
        return (this.displayFolder!= null);
    }

    /**
     * Gets the value of the annotations property.
     * 
     * @return
     *     possible object is
     *     {@link Translation.Annotations }
     *     
     */
    public Translation.Annotations getAnnotations() {
        return annotations;
    }

    /**
     * Sets the value of the annotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Translation.Annotations }
     *     
     */
    public void setAnnotations(Translation.Annotations value) {
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
