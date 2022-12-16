
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CubePermission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CubePermission"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Permission"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReadSourceData" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Allowed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DimensionPermissions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DimensionPermission" type="{urn:schemas-microsoft-com:xml-analysis}CubeDimensionPermission" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CellPermissions" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="CellPermission" type="{urn:schemas-microsoft-com:xml-analysis}CellPermission" maxOccurs="3" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Write" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Allowed"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CubePermission", propOrder = {
    "readSourceData",
    "dimensionPermissions",
    "cellPermissions",
    "write"
})
public class CubePermission
    extends Permission
{

    @XmlElement(name = "ReadSourceData")
    protected String readSourceData;
    @XmlElement(name = "DimensionPermissions")
    protected CubePermission.DimensionPermissions dimensionPermissions;
    @XmlElement(name = "CellPermissions")
    protected CubePermission.CellPermissions cellPermissions;
    @XmlElement(name = "Write")
    protected String write;

    /**
     * Gets the value of the readSourceData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReadSourceData() {
        return readSourceData;
    }

    /**
     * Sets the value of the readSourceData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReadSourceData(String value) {
        this.readSourceData = value;
    }

    /**
     * Gets the value of the dimensionPermissions property.
     * 
     * @return
     *     possible object is
     *     {@link CubePermission.DimensionPermissions }
     *     
     */
    public CubePermission.DimensionPermissions getDimensionPermissions() {
        return dimensionPermissions;
    }

    /**
     * Sets the value of the dimensionPermissions property.
     * 
     * @param value
     *     allowed object is
     *     {@link CubePermission.DimensionPermissions }
     *     
     */
    public void setDimensionPermissions(CubePermission.DimensionPermissions value) {
        this.dimensionPermissions = value;
    }

    /**
     * Gets the value of the cellPermissions property.
     * 
     * @return
     *     possible object is
     *     {@link CubePermission.CellPermissions }
     *     
     */
    public CubePermission.CellPermissions getCellPermissions() {
        return cellPermissions;
    }

    /**
     * Sets the value of the cellPermissions property.
     * 
     * @param value
     *     allowed object is
     *     {@link CubePermission.CellPermissions }
     *     
     */
    public void setCellPermissions(CubePermission.CellPermissions value) {
        this.cellPermissions = value;
    }

    /**
     * Gets the value of the write property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWrite() {
        return write;
    }

    /**
     * Sets the value of the write property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWrite(String value) {
        this.write = value;
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
     *         &lt;element name="CellPermission" type="{urn:schemas-microsoft-com:xml-analysis}CellPermission" maxOccurs="3" minOccurs="0"/&gt;
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
        "cellPermission"
    })
    public static class CellPermissions {

        @XmlElement(name = "CellPermission")
        protected List<CellPermission> cellPermission;

        /**
         * Gets the value of the cellPermission property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the cellPermission property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCellPermission().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CellPermission }
         * 
         * 
         */
        public List<CellPermission> getCellPermission() {
            if (cellPermission == null) {
                cellPermission = new ArrayList<CellPermission>();
            }
            return this.cellPermission;
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
     *         &lt;element name="DimensionPermission" type="{urn:schemas-microsoft-com:xml-analysis}CubeDimensionPermission" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "dimensionPermission"
    })
    public static class DimensionPermissions {

        @XmlElement(name = "DimensionPermission")
        protected List<CubeDimensionPermission> dimensionPermission;

        /**
         * Gets the value of the dimensionPermission property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the dimensionPermission property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDimensionPermission().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CubeDimensionPermission }
         * 
         * 
         */
        public List<CubeDimensionPermission> getDimensionPermission() {
            if (dimensionPermission == null) {
                dimensionPermission = new ArrayList<CubeDimensionPermission>();
            }
            return this.dimensionPermission;
        }

    }

}
