
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Batch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Batch"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Parallel" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Process" type="{urn:schemas-microsoft-com:xml-analysis}Process" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="MaxParallel" type="{http://www.w3.org/2001/XMLSchema}int" default="0" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Bindings" type="{urn:schemas-microsoft-com:xml-analysis}OutOfLineBinding" minOccurs="0"/&gt;
 *         &lt;element name="DataSource" type="{urn:schemas-microsoft-com:xml-analysis}DataSource" minOccurs="0"/&gt;
 *         &lt;element name="DataSourceView" type="{urn:schemas-microsoft-com:xml-analysis}DataSourceView" minOccurs="0"/&gt;
 *         &lt;element name="ErrorConfiguration" type="{urn:schemas-microsoft-com:xml-analysis}ErrorConfiguration" minOccurs="0"/&gt;
 *         &lt;element name="Create" type="{urn:schemas-microsoft-com:xml-analysis}Create" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Alter" type="{urn:schemas-microsoft-com:xml-analysis}Alter" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Delete" type="{urn:schemas-microsoft-com:xml-analysis}Delete" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Process" type="{urn:schemas-microsoft-com:xml-analysis}Process" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="MergePartitions" type="{urn:schemas-microsoft-com:xml-analysis}MergePartitions" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DesignAggregations" type="{urn:schemas-microsoft-com:xml-analysis}DesignAggregations" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="NotifyTableChange" type="{urn:schemas-microsoft-com:xml-analysis}NotifyTableChange" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Insert" type="{urn:schemas-microsoft-com:xml-analysis}Insert" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Update" type="{urn:schemas-microsoft-com:xml-analysis}Update" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Drop" type="{urn:schemas-microsoft-com:xml-analysis}Drop" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="UpdateCells" type="{urn:schemas-microsoft-com:xml-analysis}UpdateCells" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Backup" type="{urn:schemas-microsoft-com:xml-analysis}Backup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Restore" type="{urn:schemas-microsoft-com:xml-analysis}Restore" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Synchronize" type="{urn:schemas-microsoft-com:xml-analysis}Synchronize" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Cancel" type="{urn:schemas-microsoft-com:xml-analysis}Cancel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="BeginTransaction" type="{urn:schemas-microsoft-com:xml-analysis}BeginTransaction" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CommitTransaction" type="{urn:schemas-microsoft-com:xml-analysis}CommitTransaction" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="RollbackTransaction" type="{urn:schemas-microsoft-com:xml-analysis}RollbackTransaction" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ClearCache" type="{urn:schemas-microsoft-com:xml-analysis}ClearCache" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Subscribe" type="{urn:schemas-microsoft-com:xml-analysis}Subscribe" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Unsubscribe" type="{urn:schemas-microsoft-com:xml-analysis}Unsubscribe" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Detach" type="{urn:schemas-microsoft-com:xml-analysis}Detach" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Attach" type="{urn:schemas-microsoft-com:xml-analysis}Attach" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Lock" type="{urn:schemas-microsoft-com:xml-analysis}Lock" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Unlock" type="{urn:schemas-microsoft-com:xml-analysis}Unlock" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ImageLoad" type="{urn:schemas-microsoft-com:xml-analysis}ImageLoad" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ImageSave" type="{urn:schemas-microsoft-com:xml-analysis}ImageSave" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CloneDatabase" type="{urn:schemas-microsoft-com:xml-analysis}CloneDatabase" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SetAuthContext" type="{urn:schemas-microsoft-com:xml-analysis}SetAuthContext" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DBCC" type="{urn:schemas-microsoft-com:xml-analysis}DBCC" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:schemas-microsoft-com:xml-analysis}Discover" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Transaction" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="ProcessAffectedObjects" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Batch", propOrder = {
    "parallel",
    "bindings",
    "dataSource",
    "dataSourceView",
    "errorConfiguration",
    "create",
    "alter",
    "delete",
    "process",
    "mergePartitions",
    "designAggregations",
    "notifyTableChange",
    "insert",
    "update",
    "drop",
    "updateCells",
    "backup",
    "restore",
    "synchronize",
    "cancel",
    "beginTransaction",
    "commitTransaction",
    "rollbackTransaction",
    "clearCache",
    "subscribe",
    "unsubscribe",
    "detach",
    "attach",
    "lock",
    "unlock",
    "imageLoad",
    "imageSave",
    "cloneDatabase",
    "setAuthContext",
    "dbcc",
    "discover"
})
public class Batch {

    @XmlElement(name = "Parallel")
    protected List<Batch.Parallel> parallel;
    @XmlElement(name = "Bindings")
    protected OutOfLineBinding bindings;
    @XmlElement(name = "DataSource")
    protected DataSource dataSource;
    @XmlElement(name = "DataSourceView")
    protected DataSourceView dataSourceView;
    @XmlElement(name = "ErrorConfiguration")
    protected ErrorConfiguration errorConfiguration;
    @XmlElement(name = "Create")
    protected List<Create> create;
    @XmlElement(name = "Alter")
    protected List<Alter> alter;
    @XmlElement(name = "Delete")
    protected List<Delete> delete;
    @XmlElement(name = "Process")
    protected List<Process> process;
    @XmlElement(name = "MergePartitions")
    protected List<MergePartitions> mergePartitions;
    @XmlElement(name = "DesignAggregations")
    protected List<DesignAggregations> designAggregations;
    @XmlElement(name = "NotifyTableChange")
    protected List<NotifyTableChange> notifyTableChange;
    @XmlElement(name = "Insert")
    protected List<Insert> insert;
    @XmlElement(name = "Update")
    protected List<Update> update;
    @XmlElement(name = "Drop")
    protected List<Drop> drop;
    @XmlElement(name = "UpdateCells")
    protected List<UpdateCells> updateCells;
    @XmlElement(name = "Backup")
    protected List<Backup> backup;
    @XmlElement(name = "Restore")
    protected List<Restore> restore;
    @XmlElement(name = "Synchronize")
    protected List<Synchronize> synchronize;
    @XmlElement(name = "Cancel")
    protected List<Cancel> cancel;
    @XmlElement(name = "BeginTransaction")
    protected List<BeginTransaction> beginTransaction;
    @XmlElement(name = "CommitTransaction")
    protected List<CommitTransaction> commitTransaction;
    @XmlElement(name = "RollbackTransaction")
    protected List<RollbackTransaction> rollbackTransaction;
    @XmlElement(name = "ClearCache")
    protected List<ClearCache> clearCache;
    @XmlElement(name = "Subscribe")
    protected List<Subscribe> subscribe;
    @XmlElement(name = "Unsubscribe")
    protected List<Unsubscribe> unsubscribe;
    @XmlElement(name = "Detach")
    protected List<Detach> detach;
    @XmlElement(name = "Attach")
    protected List<Attach> attach;
    @XmlElement(name = "Lock")
    protected List<Lock> lock;
    @XmlElement(name = "Unlock")
    protected List<Unlock> unlock;
    @XmlElement(name = "ImageLoad")
    protected List<ImageLoad> imageLoad;
    @XmlElement(name = "ImageSave")
    protected List<ImageSave> imageSave;
    @XmlElement(name = "CloneDatabase")
    protected List<CloneDatabase> cloneDatabase;
    @XmlElement(name = "SetAuthContext")
    protected List<SetAuthContext> setAuthContext;
    @XmlElement(name = "DBCC")
    protected DBCC dbcc;
    @XmlElement(name = "Discover", namespace = "urn:schemas-microsoft-com:xml-analysis")
    protected List<Discover> discover;
    @XmlAttribute(name = "Transaction")
    protected Boolean transaction;
    @XmlAttribute(name = "ProcessAffectedObjects")
    protected Boolean processAffectedObjects;

    /**
     * Gets the value of the parallel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the parallel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParallel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Batch.Parallel }
     * 
     * 
     */
    public List<Batch.Parallel> getParallel() {
        if (parallel == null) {
            parallel = new ArrayList<Batch.Parallel>();
        }
        return this.parallel;
    }

    /**
     * Gets the value of the bindings property.
     * 
     * @return
     *     possible object is
     *     {@link OutOfLineBinding }
     *     
     */
    public OutOfLineBinding getBindings() {
        return bindings;
    }

    /**
     * Sets the value of the bindings property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutOfLineBinding }
     *     
     */
    public void setBindings(OutOfLineBinding value) {
        this.bindings = value;
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

    /**
     * Gets the value of the errorConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorConfiguration }
     *     
     */
    public ErrorConfiguration getErrorConfiguration() {
        return errorConfiguration;
    }

    /**
     * Sets the value of the errorConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorConfiguration }
     *     
     */
    public void setErrorConfiguration(ErrorConfiguration value) {
        this.errorConfiguration = value;
    }

    /**
     * Gets the value of the create property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the create property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Create }
     * 
     * 
     */
    public List<Create> getCreate() {
        if (create == null) {
            create = new ArrayList<Create>();
        }
        return this.create;
    }

    /**
     * Gets the value of the alter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the alter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Alter }
     * 
     * 
     */
    public List<Alter> getAlter() {
        if (alter == null) {
            alter = new ArrayList<Alter>();
        }
        return this.alter;
    }

    /**
     * Gets the value of the delete property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the delete property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDelete().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Delete }
     * 
     * 
     */
    public List<Delete> getDelete() {
        if (delete == null) {
            delete = new ArrayList<Delete>();
        }
        return this.delete;
    }

    /**
     * Gets the value of the process property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the process property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Process }
     * 
     * 
     */
    public List<Process> getProcess() {
        if (process == null) {
            process = new ArrayList<Process>();
        }
        return this.process;
    }

    /**
     * Gets the value of the mergePartitions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the mergePartitions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMergePartitions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MergePartitions }
     * 
     * 
     */
    public List<MergePartitions> getMergePartitions() {
        if (mergePartitions == null) {
            mergePartitions = new ArrayList<MergePartitions>();
        }
        return this.mergePartitions;
    }

    /**
     * Gets the value of the designAggregations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the designAggregations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDesignAggregations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DesignAggregations }
     * 
     * 
     */
    public List<DesignAggregations> getDesignAggregations() {
        if (designAggregations == null) {
            designAggregations = new ArrayList<DesignAggregations>();
        }
        return this.designAggregations;
    }

    /**
     * Gets the value of the notifyTableChange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the notifyTableChange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotifyTableChange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NotifyTableChange }
     * 
     * 
     */
    public List<NotifyTableChange> getNotifyTableChange() {
        if (notifyTableChange == null) {
            notifyTableChange = new ArrayList<NotifyTableChange>();
        }
        return this.notifyTableChange;
    }

    /**
     * Gets the value of the insert property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the insert property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInsert().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Insert }
     * 
     * 
     */
    public List<Insert> getInsert() {
        if (insert == null) {
            insert = new ArrayList<Insert>();
        }
        return this.insert;
    }

    /**
     * Gets the value of the update property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the update property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUpdate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Update }
     * 
     * 
     */
    public List<Update> getUpdate() {
        if (update == null) {
            update = new ArrayList<Update>();
        }
        return this.update;
    }

    /**
     * Gets the value of the drop property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the drop property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrop().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Drop }
     * 
     * 
     */
    public List<Drop> getDrop() {
        if (drop == null) {
            drop = new ArrayList<Drop>();
        }
        return this.drop;
    }

    /**
     * Gets the value of the updateCells property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the updateCells property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUpdateCells().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UpdateCells }
     * 
     * 
     */
    public List<UpdateCells> getUpdateCells() {
        if (updateCells == null) {
            updateCells = new ArrayList<UpdateCells>();
        }
        return this.updateCells;
    }

    /**
     * Gets the value of the backup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the backup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBackup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Backup }
     * 
     * 
     */
    public List<Backup> getBackup() {
        if (backup == null) {
            backup = new ArrayList<Backup>();
        }
        return this.backup;
    }

    /**
     * Gets the value of the restore property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the restore property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRestore().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Restore }
     * 
     * 
     */
    public List<Restore> getRestore() {
        if (restore == null) {
            restore = new ArrayList<Restore>();
        }
        return this.restore;
    }

    /**
     * Gets the value of the synchronize property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the synchronize property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSynchronize().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Synchronize }
     * 
     * 
     */
    public List<Synchronize> getSynchronize() {
        if (synchronize == null) {
            synchronize = new ArrayList<Synchronize>();
        }
        return this.synchronize;
    }

    /**
     * Gets the value of the cancel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the cancel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCancel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cancel }
     * 
     * 
     */
    public List<Cancel> getCancel() {
        if (cancel == null) {
            cancel = new ArrayList<Cancel>();
        }
        return this.cancel;
    }

    /**
     * Gets the value of the beginTransaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the beginTransaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBeginTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BeginTransaction }
     * 
     * 
     */
    public List<BeginTransaction> getBeginTransaction() {
        if (beginTransaction == null) {
            beginTransaction = new ArrayList<BeginTransaction>();
        }
        return this.beginTransaction;
    }

    /**
     * Gets the value of the commitTransaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the commitTransaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommitTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommitTransaction }
     * 
     * 
     */
    public List<CommitTransaction> getCommitTransaction() {
        if (commitTransaction == null) {
            commitTransaction = new ArrayList<CommitTransaction>();
        }
        return this.commitTransaction;
    }

    /**
     * Gets the value of the rollbackTransaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the rollbackTransaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRollbackTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RollbackTransaction }
     * 
     * 
     */
    public List<RollbackTransaction> getRollbackTransaction() {
        if (rollbackTransaction == null) {
            rollbackTransaction = new ArrayList<RollbackTransaction>();
        }
        return this.rollbackTransaction;
    }

    /**
     * Gets the value of the clearCache property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the clearCache property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClearCache().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClearCache }
     * 
     * 
     */
    public List<ClearCache> getClearCache() {
        if (clearCache == null) {
            clearCache = new ArrayList<ClearCache>();
        }
        return this.clearCache;
    }

    /**
     * Gets the value of the subscribe property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the subscribe property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscribe().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Subscribe }
     * 
     * 
     */
    public List<Subscribe> getSubscribe() {
        if (subscribe == null) {
            subscribe = new ArrayList<Subscribe>();
        }
        return this.subscribe;
    }

    /**
     * Gets the value of the unsubscribe property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the unsubscribe property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnsubscribe().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Unsubscribe }
     * 
     * 
     */
    public List<Unsubscribe> getUnsubscribe() {
        if (unsubscribe == null) {
            unsubscribe = new ArrayList<Unsubscribe>();
        }
        return this.unsubscribe;
    }

    /**
     * Gets the value of the detach property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the detach property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetach().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Detach }
     * 
     * 
     */
    public List<Detach> getDetach() {
        if (detach == null) {
            detach = new ArrayList<Detach>();
        }
        return this.detach;
    }

    /**
     * Gets the value of the attach property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the attach property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttach().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Attach }
     * 
     * 
     */
    public List<Attach> getAttach() {
        if (attach == null) {
            attach = new ArrayList<Attach>();
        }
        return this.attach;
    }

    /**
     * Gets the value of the lock property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the lock property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLock().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Lock }
     * 
     * 
     */
    public List<Lock> getLock() {
        if (lock == null) {
            lock = new ArrayList<Lock>();
        }
        return this.lock;
    }

    /**
     * Gets the value of the unlock property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the unlock property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnlock().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Unlock }
     * 
     * 
     */
    public List<Unlock> getUnlock() {
        if (unlock == null) {
            unlock = new ArrayList<Unlock>();
        }
        return this.unlock;
    }

    /**
     * Gets the value of the imageLoad property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the imageLoad property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImageLoad().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImageLoad }
     * 
     * 
     */
    public List<ImageLoad> getImageLoad() {
        if (imageLoad == null) {
            imageLoad = new ArrayList<ImageLoad>();
        }
        return this.imageLoad;
    }

    /**
     * Gets the value of the imageSave property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the imageSave property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImageSave().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImageSave }
     * 
     * 
     */
    public List<ImageSave> getImageSave() {
        if (imageSave == null) {
            imageSave = new ArrayList<ImageSave>();
        }
        return this.imageSave;
    }

    /**
     * Gets the value of the cloneDatabase property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the cloneDatabase property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCloneDatabase().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CloneDatabase }
     * 
     * 
     */
    public List<CloneDatabase> getCloneDatabase() {
        if (cloneDatabase == null) {
            cloneDatabase = new ArrayList<CloneDatabase>();
        }
        return this.cloneDatabase;
    }

    /**
     * Gets the value of the setAuthContext property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the setAuthContext property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetAuthContext().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SetAuthContext }
     * 
     * 
     */
    public List<SetAuthContext> getSetAuthContext() {
        if (setAuthContext == null) {
            setAuthContext = new ArrayList<SetAuthContext>();
        }
        return this.setAuthContext;
    }

    /**
     * Gets the value of the dbcc property.
     * 
     * @return
     *     possible object is
     *     {@link DBCC }
     *     
     */
    public DBCC getDBCC() {
        return dbcc;
    }

    /**
     * Sets the value of the dbcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link DBCC }
     *     
     */
    public void setDBCC(DBCC value) {
        this.dbcc = value;
    }

    /**
     * Gets the value of the discover property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the discover property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiscover().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Discover }
     * 
     * 
     */
    public List<Discover> getDiscover() {
        if (discover == null) {
            discover = new ArrayList<Discover>();
        }
        return this.discover;
    }

    /**
     * Gets the value of the transaction property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTransaction() {
        return transaction;
    }

    /**
     * Sets the value of the transaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTransaction(Boolean value) {
        this.transaction = value;
    }

    /**
     * Gets the value of the processAffectedObjects property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProcessAffectedObjects() {
        return processAffectedObjects;
    }

    /**
     * Sets the value of the processAffectedObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProcessAffectedObjects(Boolean value) {
        this.processAffectedObjects = value;
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
     *         &lt;element name="Process" type="{urn:schemas-microsoft-com:xml-analysis}Process" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="MaxParallel" type="{http://www.w3.org/2001/XMLSchema}int" default="0" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "process"
    })
    public static class Parallel {

        @XmlElement(name = "Process")
        protected List<Process> process;
        @XmlAttribute(name = "MaxParallel")
        protected Integer maxParallel;

        /**
         * Gets the value of the process property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the process property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProcess().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Process }
         * 
         * 
         */
        public List<Process> getProcess() {
            if (process == null) {
                process = new ArrayList<Process>();
            }
            return this.process;
        }

        /**
         * Gets the value of the maxParallel property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public int getMaxParallel() {
            if (maxParallel == null) {
                return  0;
            } else {
                return maxParallel;
            }
        }

        /**
         * Sets the value of the maxParallel property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setMaxParallel(Integer value) {
            this.maxParallel = value;
        }

    }

}
