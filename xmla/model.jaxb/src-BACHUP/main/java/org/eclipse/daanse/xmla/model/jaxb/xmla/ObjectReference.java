
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObjectReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObjectReference"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="ServerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DatabaseID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RoleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TraceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AssemblyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DimensionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DimensionPermissionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DataSourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DataSourcePermissionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DatabasePermissionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DataSourceViewID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CubeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MiningStructureID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MeasureGroupID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PerspectiveID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CubePermissionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MdxScriptID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PartitionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AggregationDesignID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MiningModelID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MiningModelPermissionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MiningStructurePermissionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectReference", propOrder = {

})
public class ObjectReference {

    @XmlElement(name = "ServerID")
    protected String serverID;
    @XmlElement(name = "DatabaseID")
    protected String databaseID;
    @XmlElement(name = "RoleID")
    protected String roleID;
    @XmlElement(name = "TraceID")
    protected String traceID;
    @XmlElement(name = "AssemblyID")
    protected String assemblyID;
    @XmlElement(name = "DimensionID")
    protected String dimensionID;
    @XmlElement(name = "DimensionPermissionID")
    protected String dimensionPermissionID;
    @XmlElement(name = "DataSourceID")
    protected String dataSourceID;
    @XmlElement(name = "DataSourcePermissionID")
    protected String dataSourcePermissionID;
    @XmlElement(name = "DatabasePermissionID")
    protected String databasePermissionID;
    @XmlElement(name = "DataSourceViewID")
    protected String dataSourceViewID;
    @XmlElement(name = "CubeID")
    protected String cubeID;
    @XmlElement(name = "MiningStructureID")
    protected String miningStructureID;
    @XmlElement(name = "MeasureGroupID")
    protected String measureGroupID;
    @XmlElement(name = "PerspectiveID")
    protected String perspectiveID;
    @XmlElement(name = "CubePermissionID")
    protected String cubePermissionID;
    @XmlElement(name = "MdxScriptID")
    protected String mdxScriptID;
    @XmlElement(name = "PartitionID")
    protected String partitionID;
    @XmlElement(name = "AggregationDesignID")
    protected String aggregationDesignID;
    @XmlElement(name = "MiningModelID")
    protected String miningModelID;
    @XmlElement(name = "MiningModelPermissionID")
    protected String miningModelPermissionID;
    @XmlElement(name = "MiningStructurePermissionID")
    protected String miningStructurePermissionID;

    /**
     * Gets the value of the serverID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerID() {
        return serverID;
    }

    /**
     * Sets the value of the serverID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerID(String value) {
        this.serverID = value;
    }

    public boolean isSetServerID() {
        return (this.serverID!= null);
    }

    /**
     * Gets the value of the databaseID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabaseID() {
        return databaseID;
    }

    /**
     * Sets the value of the databaseID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabaseID(String value) {
        this.databaseID = value;
    }

    public boolean isSetDatabaseID() {
        return (this.databaseID!= null);
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

    public boolean isSetRoleID() {
        return (this.roleID!= null);
    }

    /**
     * Gets the value of the traceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTraceID() {
        return traceID;
    }

    /**
     * Sets the value of the traceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTraceID(String value) {
        this.traceID = value;
    }

    public boolean isSetTraceID() {
        return (this.traceID!= null);
    }

    /**
     * Gets the value of the assemblyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssemblyID() {
        return assemblyID;
    }

    /**
     * Sets the value of the assemblyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssemblyID(String value) {
        this.assemblyID = value;
    }

    public boolean isSetAssemblyID() {
        return (this.assemblyID!= null);
    }

    /**
     * Gets the value of the dimensionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDimensionID() {
        return dimensionID;
    }

    /**
     * Sets the value of the dimensionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDimensionID(String value) {
        this.dimensionID = value;
    }

    public boolean isSetDimensionID() {
        return (this.dimensionID!= null);
    }

    /**
     * Gets the value of the dimensionPermissionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDimensionPermissionID() {
        return dimensionPermissionID;
    }

    /**
     * Sets the value of the dimensionPermissionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDimensionPermissionID(String value) {
        this.dimensionPermissionID = value;
    }

    public boolean isSetDimensionPermissionID() {
        return (this.dimensionPermissionID!= null);
    }

    /**
     * Gets the value of the dataSourceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSourceID() {
        return dataSourceID;
    }

    /**
     * Sets the value of the dataSourceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSourceID(String value) {
        this.dataSourceID = value;
    }

    public boolean isSetDataSourceID() {
        return (this.dataSourceID!= null);
    }

    /**
     * Gets the value of the dataSourcePermissionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSourcePermissionID() {
        return dataSourcePermissionID;
    }

    /**
     * Sets the value of the dataSourcePermissionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSourcePermissionID(String value) {
        this.dataSourcePermissionID = value;
    }

    public boolean isSetDataSourcePermissionID() {
        return (this.dataSourcePermissionID!= null);
    }

    /**
     * Gets the value of the databasePermissionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabasePermissionID() {
        return databasePermissionID;
    }

    /**
     * Sets the value of the databasePermissionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabasePermissionID(String value) {
        this.databasePermissionID = value;
    }

    public boolean isSetDatabasePermissionID() {
        return (this.databasePermissionID!= null);
    }

    /**
     * Gets the value of the dataSourceViewID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSourceViewID() {
        return dataSourceViewID;
    }

    /**
     * Sets the value of the dataSourceViewID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSourceViewID(String value) {
        this.dataSourceViewID = value;
    }

    public boolean isSetDataSourceViewID() {
        return (this.dataSourceViewID!= null);
    }

    /**
     * Gets the value of the cubeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCubeID() {
        return cubeID;
    }

    /**
     * Sets the value of the cubeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCubeID(String value) {
        this.cubeID = value;
    }

    public boolean isSetCubeID() {
        return (this.cubeID!= null);
    }

    /**
     * Gets the value of the miningStructureID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiningStructureID() {
        return miningStructureID;
    }

    /**
     * Sets the value of the miningStructureID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiningStructureID(String value) {
        this.miningStructureID = value;
    }

    public boolean isSetMiningStructureID() {
        return (this.miningStructureID!= null);
    }

    /**
     * Gets the value of the measureGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasureGroupID() {
        return measureGroupID;
    }

    /**
     * Sets the value of the measureGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasureGroupID(String value) {
        this.measureGroupID = value;
    }

    public boolean isSetMeasureGroupID() {
        return (this.measureGroupID!= null);
    }

    /**
     * Gets the value of the perspectiveID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerspectiveID() {
        return perspectiveID;
    }

    /**
     * Sets the value of the perspectiveID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerspectiveID(String value) {
        this.perspectiveID = value;
    }

    public boolean isSetPerspectiveID() {
        return (this.perspectiveID!= null);
    }

    /**
     * Gets the value of the cubePermissionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCubePermissionID() {
        return cubePermissionID;
    }

    /**
     * Sets the value of the cubePermissionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCubePermissionID(String value) {
        this.cubePermissionID = value;
    }

    public boolean isSetCubePermissionID() {
        return (this.cubePermissionID!= null);
    }

    /**
     * Gets the value of the mdxScriptID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdxScriptID() {
        return mdxScriptID;
    }

    /**
     * Sets the value of the mdxScriptID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdxScriptID(String value) {
        this.mdxScriptID = value;
    }

    public boolean isSetMdxScriptID() {
        return (this.mdxScriptID!= null);
    }

    /**
     * Gets the value of the partitionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartitionID() {
        return partitionID;
    }

    /**
     * Sets the value of the partitionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartitionID(String value) {
        this.partitionID = value;
    }

    public boolean isSetPartitionID() {
        return (this.partitionID!= null);
    }

    /**
     * Gets the value of the aggregationDesignID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAggregationDesignID() {
        return aggregationDesignID;
    }

    /**
     * Sets the value of the aggregationDesignID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAggregationDesignID(String value) {
        this.aggregationDesignID = value;
    }

    public boolean isSetAggregationDesignID() {
        return (this.aggregationDesignID!= null);
    }

    /**
     * Gets the value of the miningModelID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiningModelID() {
        return miningModelID;
    }

    /**
     * Sets the value of the miningModelID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiningModelID(String value) {
        this.miningModelID = value;
    }

    public boolean isSetMiningModelID() {
        return (this.miningModelID!= null);
    }

    /**
     * Gets the value of the miningModelPermissionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiningModelPermissionID() {
        return miningModelPermissionID;
    }

    /**
     * Sets the value of the miningModelPermissionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiningModelPermissionID(String value) {
        this.miningModelPermissionID = value;
    }

    public boolean isSetMiningModelPermissionID() {
        return (this.miningModelPermissionID!= null);
    }

    /**
     * Gets the value of the miningStructurePermissionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiningStructurePermissionID() {
        return miningStructurePermissionID;
    }

    /**
     * Sets the value of the miningStructurePermissionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiningStructurePermissionID(String value) {
        this.miningStructurePermissionID = value;
    }

    public boolean isSetMiningStructurePermissionID() {
        return (this.miningStructurePermissionID!= null);
    }

}
