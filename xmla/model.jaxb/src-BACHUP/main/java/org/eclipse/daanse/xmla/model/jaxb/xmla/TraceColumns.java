
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trace_Columns complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trace_Columns"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Data"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Column"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;all&gt;
 *                             &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                             &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="Filterable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                             &lt;element name="Repeatable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                             &lt;element name="RepeatedBase" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                           &lt;/all&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
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
@XmlType(name = "Trace_Columns", propOrder = {
    "data"
})
public class TraceColumns {

    @XmlElement(name = "Data", required = true)
    protected TraceColumns.Data data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link TraceColumns.Data }
     *     
     */
    public TraceColumns.Data getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link TraceColumns.Data }
     *     
     */
    public void setData(TraceColumns.Data value) {
        this.data = value;
    }

    public boolean isSetData() {
        return (this.data!= null);
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
     *         &lt;element name="Column"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;all&gt;
     *                   &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *                   &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="Filterable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *                   &lt;element name="Repeatable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *                   &lt;element name="RepeatedBase" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *                 &lt;/all&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
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
    @XmlType(name = "", propOrder = {
        "column"
    })
    public static class Data {

        @XmlElement(name = "Column", required = true)
        protected TraceColumns.Data.Column column;

        /**
         * Gets the value of the column property.
         * 
         * @return
         *     possible object is
         *     {@link TraceColumns.Data.Column }
         *     
         */
        public TraceColumns.Data.Column getColumn() {
            return column;
        }

        /**
         * Sets the value of the column property.
         * 
         * @param value
         *     allowed object is
         *     {@link TraceColumns.Data.Column }
         *     
         */
        public void setColumn(TraceColumns.Data.Column value) {
            this.column = value;
        }

        public boolean isSetColumn() {
            return (this.column!= null);
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
         *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
         *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
         *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="Filterable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
         *         &lt;element name="Repeatable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
         *         &lt;element name="RepeatedBase" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
        public static class Column {

            @XmlElement(name = "ID", required = true)
            protected BigInteger id;
            @XmlElement(name = "Type", required = true)
            protected BigInteger type;
            @XmlElement(name = "Name", required = true)
            protected String name;
            @XmlElement(name = "Description")
            protected String description;
            @XmlElement(name = "Filterable")
            protected boolean filterable;
            @XmlElement(name = "Repeatable")
            protected boolean repeatable;
            @XmlElement(name = "RepeatedBase")
            protected boolean repeatedBase;

            /**
             * Gets the value of the id property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getID() {
                return id;
            }

            /**
             * Sets the value of the id property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setID(BigInteger value) {
                this.id = value;
            }

            public boolean isSetID() {
                return (this.id!= null);
            }

            /**
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setType(BigInteger value) {
                this.type = value;
            }

            public boolean isSetType() {
                return (this.type!= null);
            }

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
             * Gets the value of the filterable property.
             * 
             */
            public boolean isFilterable() {
                return filterable;
            }

            /**
             * Sets the value of the filterable property.
             * 
             */
            public void setFilterable(boolean value) {
                this.filterable = value;
            }

            public boolean isSetFilterable() {
                return true;
            }

            /**
             * Gets the value of the repeatable property.
             * 
             */
            public boolean isRepeatable() {
                return repeatable;
            }

            /**
             * Sets the value of the repeatable property.
             * 
             */
            public void setRepeatable(boolean value) {
                this.repeatable = value;
            }

            public boolean isSetRepeatable() {
                return true;
            }

            /**
             * Gets the value of the repeatedBase property.
             * 
             */
            public boolean isRepeatedBase() {
                return repeatedBase;
            }

            /**
             * Sets the value of the repeatedBase property.
             * 
             */
            public void setRepeatedBase(boolean value) {
                this.repeatedBase = value;
            }

            public boolean isSetRepeatedBase() {
                return true;
            }

        }

    }

}
