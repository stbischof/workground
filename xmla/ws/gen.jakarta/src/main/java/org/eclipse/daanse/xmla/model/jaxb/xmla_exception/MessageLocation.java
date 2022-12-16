
package org.eclipse.daanse.xmla.model.jaxb.xmla_exception;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.xmla.model.jaxb.engine200.WarningLocationObject;


/**
 * <p>Java class for MessageLocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageLocation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Start"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;all&gt;
 *                   &lt;element name="Line" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="Column" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="End"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;all&gt;
 *                   &lt;element name="Line" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="Column" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="LineOffset" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="TextLength" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="SourceObject" type="{http://schemas.microsoft.com/analysisservices/2010/engine/200}WarningLocationObject" minOccurs="0"/&gt;
 *         &lt;element name="DependsOnObject" type="{http://schemas.microsoft.com/analysisservices/2010/engine/200}WarningLocationObject" minOccurs="0"/&gt;
 *         &lt;element name="RowNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageLocation", propOrder = {

})
public class MessageLocation {

    @XmlElement(name = "Start", required = true)
    protected MessageLocation.Start start;
    @XmlElement(name = "End", required = true)
    protected MessageLocation.End end;
    @XmlElement(name = "LineOffset")
    protected Integer lineOffset;
    @XmlElement(name = "TextLength")
    protected Integer textLength;
    @XmlElement(name = "SourceObject")
    protected WarningLocationObject sourceObject;
    @XmlElement(name = "DependsOnObject")
    protected WarningLocationObject dependsOnObject;
    @XmlElement(name = "RowNumber")
    protected Integer rowNumber;

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link MessageLocation.Start }
     *     
     */
    public MessageLocation.Start getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageLocation.Start }
     *     
     */
    public void setStart(MessageLocation.Start value) {
        this.start = value;
    }

    /**
     * Gets the value of the end property.
     * 
     * @return
     *     possible object is
     *     {@link MessageLocation.End }
     *     
     */
    public MessageLocation.End getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageLocation.End }
     *     
     */
    public void setEnd(MessageLocation.End value) {
        this.end = value;
    }

    /**
     * Gets the value of the lineOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLineOffset() {
        return lineOffset;
    }

    /**
     * Sets the value of the lineOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLineOffset(Integer value) {
        this.lineOffset = value;
    }

    /**
     * Gets the value of the textLength property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTextLength() {
        return textLength;
    }

    /**
     * Sets the value of the textLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTextLength(Integer value) {
        this.textLength = value;
    }

    /**
     * Gets the value of the sourceObject property.
     * 
     * @return
     *     possible object is
     *     {@link WarningLocationObject }
     *     
     */
    public WarningLocationObject getSourceObject() {
        return sourceObject;
    }

    /**
     * Sets the value of the sourceObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link WarningLocationObject }
     *     
     */
    public void setSourceObject(WarningLocationObject value) {
        this.sourceObject = value;
    }

    /**
     * Gets the value of the dependsOnObject property.
     * 
     * @return
     *     possible object is
     *     {@link WarningLocationObject }
     *     
     */
    public WarningLocationObject getDependsOnObject() {
        return dependsOnObject;
    }

    /**
     * Sets the value of the dependsOnObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link WarningLocationObject }
     *     
     */
    public void setDependsOnObject(WarningLocationObject value) {
        this.dependsOnObject = value;
    }

    /**
     * Gets the value of the rowNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRowNumber() {
        return rowNumber;
    }

    /**
     * Sets the value of the rowNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRowNumber(Integer value) {
        this.rowNumber = value;
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
     *       &lt;all&gt;
     *         &lt;element name="Line" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="Column" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class End {

        @XmlElement(name = "Line")
        protected int line;
        @XmlElement(name = "Column")
        protected int column;

        /**
         * Gets the value of the line property.
         * 
         */
        public int getLine() {
            return line;
        }

        /**
         * Sets the value of the line property.
         * 
         */
        public void setLine(int value) {
            this.line = value;
        }

        /**
         * Gets the value of the column property.
         * 
         */
        public int getColumn() {
            return column;
        }

        /**
         * Sets the value of the column property.
         * 
         */
        public void setColumn(int value) {
            this.column = value;
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
     *       &lt;all&gt;
     *         &lt;element name="Line" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="Column" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Start {

        @XmlElement(name = "Line")
        protected int line;
        @XmlElement(name = "Column")
        protected int column;

        /**
         * Gets the value of the line property.
         * 
         */
        public int getLine() {
            return line;
        }

        /**
         * Sets the value of the line property.
         * 
         */
        public void setLine(int value) {
            this.line = value;
        }

        /**
         * Gets the value of the column property.
         * 
         */
        public int getColumn() {
            return column;
        }

        /**
         * Sets the value of the column property.
         * 
         */
        public void setColumn(int value) {
            this.column = value;
        }

    }

}
