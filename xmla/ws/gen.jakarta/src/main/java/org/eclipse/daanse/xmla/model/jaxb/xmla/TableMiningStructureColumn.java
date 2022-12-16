
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TableMiningStructureColumn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TableMiningStructureColumn"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="ForeignKeyColumns" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ForeignKeyColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SourceMeasureGroup" type="{urn:schemas-microsoft-com:xml-analysis}MeasureGroupBinding" minOccurs="0"/&gt;
 *         &lt;element name="Columns" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Column" type="{urn:schemas-microsoft-com:xml-analysis}MiningStructureColumn" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Translations" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "TableMiningStructureColumn", propOrder = {

})
public class TableMiningStructureColumn {

    @XmlElement(name = "ForeignKeyColumns")
    protected TableMiningStructureColumn.ForeignKeyColumns foreignKeyColumns;
    @XmlElement(name = "SourceMeasureGroup")
    protected MeasureGroupBinding sourceMeasureGroup;
    @XmlElement(name = "Columns")
    protected TableMiningStructureColumn.Columns columns;
    @XmlElement(name = "Translations")
    protected TableMiningStructureColumn.Translations translations;

    /**
     * Gets the value of the foreignKeyColumns property.
     * 
     * @return
     *     possible object is
     *     {@link TableMiningStructureColumn.ForeignKeyColumns }
     *     
     */
    public TableMiningStructureColumn.ForeignKeyColumns getForeignKeyColumns() {
        return foreignKeyColumns;
    }

    /**
     * Sets the value of the foreignKeyColumns property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableMiningStructureColumn.ForeignKeyColumns }
     *     
     */
    public void setForeignKeyColumns(TableMiningStructureColumn.ForeignKeyColumns value) {
        this.foreignKeyColumns = value;
    }

    /**
     * Gets the value of the sourceMeasureGroup property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureGroupBinding }
     *     
     */
    public MeasureGroupBinding getSourceMeasureGroup() {
        return sourceMeasureGroup;
    }

    /**
     * Sets the value of the sourceMeasureGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureGroupBinding }
     *     
     */
    public void setSourceMeasureGroup(MeasureGroupBinding value) {
        this.sourceMeasureGroup = value;
    }

    /**
     * Gets the value of the columns property.
     * 
     * @return
     *     possible object is
     *     {@link TableMiningStructureColumn.Columns }
     *     
     */
    public TableMiningStructureColumn.Columns getColumns() {
        return columns;
    }

    /**
     * Sets the value of the columns property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableMiningStructureColumn.Columns }
     *     
     */
    public void setColumns(TableMiningStructureColumn.Columns value) {
        this.columns = value;
    }

    /**
     * Gets the value of the translations property.
     * 
     * @return
     *     possible object is
     *     {@link TableMiningStructureColumn.Translations }
     *     
     */
    public TableMiningStructureColumn.Translations getTranslations() {
        return translations;
    }

    /**
     * Sets the value of the translations property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableMiningStructureColumn.Translations }
     *     
     */
    public void setTranslations(TableMiningStructureColumn.Translations value) {
        this.translations = value;
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
     *         &lt;element name="Column" type="{urn:schemas-microsoft-com:xml-analysis}MiningStructureColumn" maxOccurs="unbounded"/&gt;
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
    public static class Columns {

        @XmlElement(name = "Column", required = true)
        protected List<MiningStructureColumn> column;

        /**
         * Gets the value of the column property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the column property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getColumn().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MiningStructureColumn }
         * 
         * 
         */
        public List<MiningStructureColumn> getColumn() {
            if (column == null) {
                column = new ArrayList<MiningStructureColumn>();
            }
            return this.column;
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
     *         &lt;element name="ForeignKeyColumn" type="{urn:schemas-microsoft-com:xml-analysis}DataItem" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "foreignKeyColumn"
    })
    public static class ForeignKeyColumns {

        @XmlElement(name = "ForeignKeyColumn")
        protected List<DataItem> foreignKeyColumn;

        /**
         * Gets the value of the foreignKeyColumn property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the foreignKeyColumn property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getForeignKeyColumn().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DataItem }
         * 
         * 
         */
        public List<DataItem> getForeignKeyColumn() {
            if (foreignKeyColumn == null) {
                foreignKeyColumn = new ArrayList<DataItem>();
            }
            return this.foreignKeyColumn;
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
     *         &lt;element name="Translation" type="{urn:schemas-microsoft-com:xml-analysis}Translation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "translation"
    })
    public static class Translations {

        @XmlElement(name = "Translation")
        protected List<Translation> translation;

        /**
         * Gets the value of the translation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the translation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTranslation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Translation }
         * 
         * 
         */
        public List<Translation> getTranslation() {
            if (translation == null) {
                translation = new ArrayList<Translation>();
            }
            return this.translation;
        }

    }

}
