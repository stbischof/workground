
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MajorObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MajorObject"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="AggregationDesign" type="{urn:schemas-microsoft-com:xml-analysis}AggregationDesign"/&gt;
 *         &lt;element name="Assembly" type="{urn:schemas-microsoft-com:xml-analysis}Assembly"/&gt;
 *         &lt;element name="Cube" type="{urn:schemas-microsoft-com:xml-analysis}Cube"/&gt;
 *         &lt;element name="Database" type="{urn:schemas-microsoft-com:xml-analysis}Database"/&gt;
 *         &lt;element name="DataSource" type="{urn:schemas-microsoft-com:xml-analysis}DataSource"/&gt;
 *         &lt;element name="DataSourceView" type="{urn:schemas-microsoft-com:xml-analysis}DataSourceView"/&gt;
 *         &lt;element name="Dimension" type="{urn:schemas-microsoft-com:xml-analysis}Dimension"/&gt;
 *         &lt;element name="MdxScript" type="{urn:schemas-microsoft-com:xml-analysis}MdxScript"/&gt;
 *         &lt;element name="MeasureGroup" type="{urn:schemas-microsoft-com:xml-analysis}MeasureGroup"/&gt;
 *         &lt;element name="MiningModel" type="{urn:schemas-microsoft-com:xml-analysis}MiningModel"/&gt;
 *         &lt;element name="MiningStructure" type="{urn:schemas-microsoft-com:xml-analysis}MiningStructure"/&gt;
 *         &lt;element name="Partition" type="{urn:schemas-microsoft-com:xml-analysis}Partition"/&gt;
 *         &lt;element name="Permission" type="{urn:schemas-microsoft-com:xml-analysis}Permission"/&gt;
 *         &lt;element name="Perspective" type="{urn:schemas-microsoft-com:xml-analysis}Perspective"/&gt;
 *         &lt;element name="Role" type="{urn:schemas-microsoft-com:xml-analysis}Role"/&gt;
 *         &lt;element name="Server" type="{urn:schemas-microsoft-com:xml-analysis}Server"/&gt;
 *         &lt;element name="Trace" type="{urn:schemas-microsoft-com:xml-analysis}Trace"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MajorObject", propOrder = {
    "aggregationDesign",
    "assembly",
    "cube",
    "database",
    "dataSource",
    "dataSourceView",
    "dimension",
    "mdxScript",
    "measureGroup",
    "miningModel",
    "miningStructure",
    "partition",
    "permission",
    "perspective",
    "role",
    "server",
    "trace"
})
public class MajorObject {

    @XmlElement(name = "AggregationDesign")
    protected AggregationDesign aggregationDesign;
    @XmlElement(name = "Assembly")
    protected Assembly assembly;
    @XmlElement(name = "Cube")
    protected Cube cube;
    @XmlElement(name = "Database")
    protected Database database;
    @XmlElement(name = "DataSource")
    protected DataSource dataSource;
    @XmlElement(name = "DataSourceView")
    protected DataSourceView dataSourceView;
    @XmlElement(name = "Dimension")
    protected Dimension dimension;
    @XmlElement(name = "MdxScript")
    protected MdxScript mdxScript;
    @XmlElement(name = "MeasureGroup")
    protected MeasureGroup measureGroup;
    @XmlElement(name = "MiningModel")
    protected MiningModel miningModel;
    @XmlElement(name = "MiningStructure")
    protected MiningStructure miningStructure;
    @XmlElement(name = "Partition")
    protected Partition partition;
    @XmlElement(name = "Permission")
    protected Permission permission;
    @XmlElement(name = "Perspective")
    protected Perspective perspective;
    @XmlElement(name = "Role")
    protected Role role;
    @XmlElement(name = "Server")
    protected Server server;
    @XmlElement(name = "Trace")
    protected Trace trace;

    /**
     * Gets the value of the aggregationDesign property.
     * 
     * @return
     *     possible object is
     *     {@link AggregationDesign }
     *     
     */
    public AggregationDesign getAggregationDesign() {
        return aggregationDesign;
    }

    /**
     * Sets the value of the aggregationDesign property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggregationDesign }
     *     
     */
    public void setAggregationDesign(AggregationDesign value) {
        this.aggregationDesign = value;
    }

    public boolean isSetAggregationDesign() {
        return (this.aggregationDesign!= null);
    }

    /**
     * Gets the value of the assembly property.
     * 
     * @return
     *     possible object is
     *     {@link Assembly }
     *     
     */
    public Assembly getAssembly() {
        return assembly;
    }

    /**
     * Sets the value of the assembly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Assembly }
     *     
     */
    public void setAssembly(Assembly value) {
        this.assembly = value;
    }

    public boolean isSetAssembly() {
        return (this.assembly!= null);
    }

    /**
     * Gets the value of the cube property.
     * 
     * @return
     *     possible object is
     *     {@link Cube }
     *     
     */
    public Cube getCube() {
        return cube;
    }

    /**
     * Sets the value of the cube property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cube }
     *     
     */
    public void setCube(Cube value) {
        this.cube = value;
    }

    public boolean isSetCube() {
        return (this.cube!= null);
    }

    /**
     * Gets the value of the database property.
     * 
     * @return
     *     possible object is
     *     {@link Database }
     *     
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * Sets the value of the database property.
     * 
     * @param value
     *     allowed object is
     *     {@link Database }
     *     
     */
    public void setDatabase(Database value) {
        this.database = value;
    }

    public boolean isSetDatabase() {
        return (this.database!= null);
    }

    /**
     * Gets the value of the dataSource property.
     * 
     * @return
     *     possible object is
     *     {@link DataSource }
     *     
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Sets the value of the dataSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSource }
     *     
     */
    public void setDataSource(DataSource value) {
        this.dataSource = value;
    }

    public boolean isSetDataSource() {
        return (this.dataSource!= null);
    }

    /**
     * Gets the value of the dataSourceView property.
     * 
     * @return
     *     possible object is
     *     {@link DataSourceView }
     *     
     */
    public DataSourceView getDataSourceView() {
        return dataSourceView;
    }

    /**
     * Sets the value of the dataSourceView property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSourceView }
     *     
     */
    public void setDataSourceView(DataSourceView value) {
        this.dataSourceView = value;
    }

    public boolean isSetDataSourceView() {
        return (this.dataSourceView!= null);
    }

    /**
     * Gets the value of the dimension property.
     * 
     * @return
     *     possible object is
     *     {@link Dimension }
     *     
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Sets the value of the dimension property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dimension }
     *     
     */
    public void setDimension(Dimension value) {
        this.dimension = value;
    }

    public boolean isSetDimension() {
        return (this.dimension!= null);
    }

    /**
     * Gets the value of the mdxScript property.
     * 
     * @return
     *     possible object is
     *     {@link MdxScript }
     *     
     */
    public MdxScript getMdxScript() {
        return mdxScript;
    }

    /**
     * Sets the value of the mdxScript property.
     * 
     * @param value
     *     allowed object is
     *     {@link MdxScript }
     *     
     */
    public void setMdxScript(MdxScript value) {
        this.mdxScript = value;
    }

    public boolean isSetMdxScript() {
        return (this.mdxScript!= null);
    }

    /**
     * Gets the value of the measureGroup property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureGroup }
     *     
     */
    public MeasureGroup getMeasureGroup() {
        return measureGroup;
    }

    /**
     * Sets the value of the measureGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureGroup }
     *     
     */
    public void setMeasureGroup(MeasureGroup value) {
        this.measureGroup = value;
    }

    public boolean isSetMeasureGroup() {
        return (this.measureGroup!= null);
    }

    /**
     * Gets the value of the miningModel property.
     * 
     * @return
     *     possible object is
     *     {@link MiningModel }
     *     
     */
    public MiningModel getMiningModel() {
        return miningModel;
    }

    /**
     * Sets the value of the miningModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link MiningModel }
     *     
     */
    public void setMiningModel(MiningModel value) {
        this.miningModel = value;
    }

    public boolean isSetMiningModel() {
        return (this.miningModel!= null);
    }

    /**
     * Gets the value of the miningStructure property.
     * 
     * @return
     *     possible object is
     *     {@link MiningStructure }
     *     
     */
    public MiningStructure getMiningStructure() {
        return miningStructure;
    }

    /**
     * Sets the value of the miningStructure property.
     * 
     * @param value
     *     allowed object is
     *     {@link MiningStructure }
     *     
     */
    public void setMiningStructure(MiningStructure value) {
        this.miningStructure = value;
    }

    public boolean isSetMiningStructure() {
        return (this.miningStructure!= null);
    }

    /**
     * Gets the value of the partition property.
     * 
     * @return
     *     possible object is
     *     {@link Partition }
     *     
     */
    public Partition getPartition() {
        return partition;
    }

    /**
     * Sets the value of the partition property.
     * 
     * @param value
     *     allowed object is
     *     {@link Partition }
     *     
     */
    public void setPartition(Partition value) {
        this.partition = value;
    }

    public boolean isSetPartition() {
        return (this.partition!= null);
    }

    /**
     * Gets the value of the permission property.
     * 
     * @return
     *     possible object is
     *     {@link Permission }
     *     
     */
    public Permission getPermission() {
        return permission;
    }

    /**
     * Sets the value of the permission property.
     * 
     * @param value
     *     allowed object is
     *     {@link Permission }
     *     
     */
    public void setPermission(Permission value) {
        this.permission = value;
    }

    public boolean isSetPermission() {
        return (this.permission!= null);
    }

    /**
     * Gets the value of the perspective property.
     * 
     * @return
     *     possible object is
     *     {@link Perspective }
     *     
     */
    public Perspective getPerspective() {
        return perspective;
    }

    /**
     * Sets the value of the perspective property.
     * 
     * @param value
     *     allowed object is
     *     {@link Perspective }
     *     
     */
    public void setPerspective(Perspective value) {
        this.perspective = value;
    }

    public boolean isSetPerspective() {
        return (this.perspective!= null);
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link Role }
     *     
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link Role }
     *     
     */
    public void setRole(Role value) {
        this.role = value;
    }

    public boolean isSetRole() {
        return (this.role!= null);
    }

    /**
     * Gets the value of the server property.
     * 
     * @return
     *     possible object is
     *     {@link Server }
     *     
     */
    public Server getServer() {
        return server;
    }

    /**
     * Sets the value of the server property.
     * 
     * @param value
     *     allowed object is
     *     {@link Server }
     *     
     */
    public void setServer(Server value) {
        this.server = value;
    }

    public boolean isSetServer() {
        return (this.server!= null);
    }

    /**
     * Gets the value of the trace property.
     * 
     * @return
     *     possible object is
     *     {@link Trace }
     *     
     */
    public Trace getTrace() {
        return trace;
    }

    /**
     * Sets the value of the trace property.
     * 
     * @param value
     *     allowed object is
     *     {@link Trace }
     *     
     */
    public void setTrace(Trace value) {
        this.trace = value;
    }

    public boolean isSetTrace() {
        return (this.trace!= null);
    }

}
