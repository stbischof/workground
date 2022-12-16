
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Command complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Command"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="Statement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Create" type="{urn:schemas-microsoft-com:xml-analysis}Create" minOccurs="0"/&gt;
 *         &lt;element name="Alter" type="{urn:schemas-microsoft-com:xml-analysis}Alter" minOccurs="0"/&gt;
 *         &lt;element name="Delete" type="{urn:schemas-microsoft-com:xml-analysis}Delete" minOccurs="0"/&gt;
 *         &lt;element name="Process" type="{urn:schemas-microsoft-com:xml-analysis}Process" minOccurs="0"/&gt;
 *         &lt;element name="MergePartitions" type="{urn:schemas-microsoft-com:xml-analysis}MergePartitions" minOccurs="0"/&gt;
 *         &lt;element name="DesignAggregations" type="{urn:schemas-microsoft-com:xml-analysis}DesignAggregations" minOccurs="0"/&gt;
 *         &lt;element name="ClearCache" type="{urn:schemas-microsoft-com:xml-analysis}ClearCache" minOccurs="0"/&gt;
 *         &lt;element name="Subscribe" type="{urn:schemas-microsoft-com:xml-analysis}Subscribe" minOccurs="0"/&gt;
 *         &lt;element name="Unsubscribe" type="{urn:schemas-microsoft-com:xml-analysis}Unsubscribe" minOccurs="0"/&gt;
 *         &lt;element name="Cancel" type="{urn:schemas-microsoft-com:xml-analysis}Cancel" minOccurs="0"/&gt;
 *         &lt;element name="BeginTransaction" type="{urn:schemas-microsoft-com:xml-analysis}BeginTransaction" minOccurs="0"/&gt;
 *         &lt;element name="CommitTransaction" type="{urn:schemas-microsoft-com:xml-analysis}CommitTransaction" minOccurs="0"/&gt;
 *         &lt;element name="RollbackTransaction" type="{urn:schemas-microsoft-com:xml-analysis}RollbackTransaction" minOccurs="0"/&gt;
 *         &lt;element name="Lock" type="{urn:schemas-microsoft-com:xml-analysis}Lock" minOccurs="0"/&gt;
 *         &lt;element name="Unlock" type="{urn:schemas-microsoft-com:xml-analysis}Unlock" minOccurs="0"/&gt;
 *         &lt;element name="Backup" type="{urn:schemas-microsoft-com:xml-analysis}Backup" minOccurs="0"/&gt;
 *         &lt;element name="Restore" type="{urn:schemas-microsoft-com:xml-analysis}Restore" minOccurs="0"/&gt;
 *         &lt;element name="Synchronize" type="{urn:schemas-microsoft-com:xml-analysis}Synchronize" minOccurs="0"/&gt;
 *         &lt;element name="Attach" type="{urn:schemas-microsoft-com:xml-analysis}Attach" minOccurs="0"/&gt;
 *         &lt;element name="Detach" type="{urn:schemas-microsoft-com:xml-analysis}Detach" minOccurs="0"/&gt;
 *         &lt;element name="Insert" type="{urn:schemas-microsoft-com:xml-analysis}Insert" minOccurs="0"/&gt;
 *         &lt;element name="Update" type="{urn:schemas-microsoft-com:xml-analysis}Update" minOccurs="0"/&gt;
 *         &lt;element name="Drop" type="{urn:schemas-microsoft-com:xml-analysis}Drop" minOccurs="0"/&gt;
 *         &lt;element name="UpdateCells" type="{urn:schemas-microsoft-com:xml-analysis}UpdateCells" minOccurs="0"/&gt;
 *         &lt;element name="NotifyTableChange" type="{urn:schemas-microsoft-com:xml-analysis}NotifyTableChange" minOccurs="0"/&gt;
 *         &lt;element name="Batch" type="{urn:schemas-microsoft-com:xml-analysis}Batch" minOccurs="0"/&gt;
 *         &lt;element name="ImageLoad" type="{urn:schemas-microsoft-com:xml-analysis}ImageLoad" minOccurs="0"/&gt;
 *         &lt;element name="ImageSave" type="{urn:schemas-microsoft-com:xml-analysis}ImageSave" minOccurs="0"/&gt;
 *         &lt;element name="CloneDatabase" type="{urn:schemas-microsoft-com:xml-analysis}CloneDatabase" minOccurs="0"/&gt;
 *         &lt;element name="SetAuthContext" type="{urn:schemas-microsoft-com:xml-analysis}SetAuthContext" minOccurs="0"/&gt;
 *         &lt;element name="DBCC" type="{urn:schemas-microsoft-com:xml-analysis}DBCC" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Command", propOrder = { "statement", "create", "alter", "delete", "process", "mergePartitions",
    "designAggregations", "clearCache", "subscribe", "unsubscribe", "cancel", "beginTransaction", "commitTransaction",
    "rollbackTransaction", "lock", "unlock", "backup", "restore", "synchronize", "attach", "detach", "insert", "update",
    "drop", "updateCells", "notifyTableChange", "batch", "imageLoad", "imageSave", "cloneDatabase", "setAuthContext",
    "dbcc" })
public class Command {

  @XmlElement(name = "Statement")
  protected String statement;
  @XmlElement(name = "Create")
  protected Create create;
  @XmlElement(name = "Alter")
  protected Alter alter;
  @XmlElement(name = "Delete")
  protected Delete delete;
  @XmlElement(name = "Process")
  protected Process process;
  @XmlElement(name = "MergePartitions")
  protected MergePartitions mergePartitions;
  @XmlElement(name = "DesignAggregations")
  protected DesignAggregations designAggregations;
  @XmlElement(name = "ClearCache")
  protected ClearCache clearCache;
  @XmlElement(name = "Subscribe")
  protected Subscribe subscribe;
  @XmlElement(name = "Unsubscribe")
  protected Unsubscribe unsubscribe;
  @XmlElement(name = "Cancel")
  protected Cancel cancel;
  @XmlElement(name = "BeginTransaction")
  protected BeginTransaction beginTransaction;
  @XmlElement(name = "CommitTransaction")
  protected CommitTransaction commitTransaction;
  @XmlElement(name = "RollbackTransaction")
  protected RollbackTransaction rollbackTransaction;
  @XmlElement(name = "Lock")
  protected Lock lock;
  @XmlElement(name = "Unlock")
  protected Unlock unlock;
  @XmlElement(name = "Backup")
  protected Backup backup;
  @XmlElement(name = "Restore")
  protected Restore restore;
  @XmlElement(name = "Synchronize")
  protected Synchronize synchronize;
  @XmlElement(name = "Attach")
  protected Attach attach;
  @XmlElement(name = "Detach")
  protected Detach detach;
  @XmlElement(name = "Insert")
  protected Insert insert;
  @XmlElement(name = "Update")
  protected Update update;
  @XmlElement(name = "Drop")
  protected Drop drop;
  @XmlElement(name = "UpdateCells")
  protected UpdateCells updateCells;
  @XmlElement(name = "NotifyTableChange")
  protected NotifyTableChange notifyTableChange;
  @XmlElement(name = "Batch")
  protected Batch batch;
  @XmlElement(name = "ImageLoad")
  protected ImageLoad imageLoad;
  @XmlElement(name = "ImageSave")
  protected ImageSave imageSave;
  @XmlElement(name = "CloneDatabase")
  protected CloneDatabase cloneDatabase;
  @XmlElement(name = "SetAuthContext")
  protected SetAuthContext setAuthContext;
  @XmlElement(name = "DBCC")
  protected DBCC dbcc;

  /**
   * Gets the value of the statement property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getStatement() {
    return statement;
  }

  /**
   * Sets the value of the statement property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setStatement(String value) {
    this.statement = value;
  }

  public boolean isSetStatement() {
    return (this.statement != null);
  }

  /**
   * Gets the value of the create property.
   * 
   * @return possible object is {@link Create }
   * 
   */
  public Create getCreate() {
    return create;
  }

  /**
   * Sets the value of the create property.
   * 
   * @param value allowed object is {@link Create }
   * 
   */
  public void setCreate(Create value) {
    this.create = value;
  }

  public boolean isSetCreate() {
    return (this.create != null);
  }

  /**
   * Gets the value of the alter property.
   * 
   * @return possible object is {@link Alter }
   * 
   */
  public Alter getAlter() {
    return alter;
  }

  /**
   * Sets the value of the alter property.
   * 
   * @param value allowed object is {@link Alter }
   * 
   */
  public void setAlter(Alter value) {
    this.alter = value;
  }

  public boolean isSetAlter() {
    return (this.alter != null);
  }

  /**
   * Gets the value of the delete property.
   * 
   * @return possible object is {@link Delete }
   * 
   */
  public Delete getDelete() {
    return delete;
  }

  /**
   * Sets the value of the delete property.
   * 
   * @param value allowed object is {@link Delete }
   * 
   */
  public void setDelete(Delete value) {
    this.delete = value;
  }

  public boolean isSetDelete() {
    return (this.delete != null);
  }

  /**
   * Gets the value of the process property.
   * 
   * @return possible object is {@link Process }
   * 
   */
  public Process getProcess() {
    return process;
  }

  /**
   * Sets the value of the process property.
   * 
   * @param value allowed object is {@link Process }
   * 
   */
  public void setProcess(Process value) {
    this.process = value;
  }

  public boolean isSetProcess() {
    return (this.process != null);
  }

  /**
   * Gets the value of the mergePartitions property.
   * 
   * @return possible object is {@link MergePartitions }
   * 
   */
  public MergePartitions getMergePartitions() {
    return mergePartitions;
  }

  /**
   * Sets the value of the mergePartitions property.
   * 
   * @param value allowed object is {@link MergePartitions }
   * 
   */
  public void setMergePartitions(MergePartitions value) {
    this.mergePartitions = value;
  }

  public boolean isSetMergePartitions() {
    return (this.mergePartitions != null);
  }

  /**
   * Gets the value of the designAggregations property.
   * 
   * @return possible object is {@link DesignAggregations }
   * 
   */
  public DesignAggregations getDesignAggregations() {
    return designAggregations;
  }

  /**
   * Sets the value of the designAggregations property.
   * 
   * @param value allowed object is {@link DesignAggregations }
   * 
   */
  public void setDesignAggregations(DesignAggregations value) {
    this.designAggregations = value;
  }

  public boolean isSetDesignAggregations() {
    return (this.designAggregations != null);
  }

  /**
   * Gets the value of the clearCache property.
   * 
   * @return possible object is {@link ClearCache }
   * 
   */
  public ClearCache getClearCache() {
    return clearCache;
  }

  /**
   * Sets the value of the clearCache property.
   * 
   * @param value allowed object is {@link ClearCache }
   * 
   */
  public void setClearCache(ClearCache value) {
    this.clearCache = value;
  }

  public boolean isSetClearCache() {
    return (this.clearCache != null);
  }

  /**
   * Gets the value of the subscribe property.
   * 
   * @return possible object is {@link Subscribe }
   * 
   */
  public Subscribe getSubscribe() {
    return subscribe;
  }

  /**
   * Sets the value of the subscribe property.
   * 
   * @param value allowed object is {@link Subscribe }
   * 
   */
  public void setSubscribe(Subscribe value) {
    this.subscribe = value;
  }

  public boolean isSetSubscribe() {
    return (this.subscribe != null);
  }

  /**
   * Gets the value of the unsubscribe property.
   * 
   * @return possible object is {@link Unsubscribe }
   * 
   */
  public Unsubscribe getUnsubscribe() {
    return unsubscribe;
  }

  /**
   * Sets the value of the unsubscribe property.
   * 
   * @param value allowed object is {@link Unsubscribe }
   * 
   */
  public void setUnsubscribe(Unsubscribe value) {
    this.unsubscribe = value;
  }

  public boolean isSetUnsubscribe() {
    return (this.unsubscribe != null);
  }

  /**
   * Gets the value of the cancel property.
   * 
   * @return possible object is {@link Cancel }
   * 
   */
  public Cancel getCancel() {
    return cancel;
  }

  /**
   * Sets the value of the cancel property.
   * 
   * @param value allowed object is {@link Cancel }
   * 
   */
  public void setCancel(Cancel value) {
    this.cancel = value;
  }

  public boolean isSetCancel() {
    return (this.cancel != null);
  }

  /**
   * Gets the value of the beginTransaction property.
   * 
   * @return possible object is {@link BeginTransaction }
   * 
   */
  public BeginTransaction getBeginTransaction() {
    return beginTransaction;
  }

  /**
   * Sets the value of the beginTransaction property.
   * 
   * @param value allowed object is {@link BeginTransaction }
   * 
   */
  public void setBeginTransaction(BeginTransaction value) {
    this.beginTransaction = value;
  }

  public boolean isSetBeginTransaction() {
    return (this.beginTransaction != null);
  }

  /**
   * Gets the value of the commitTransaction property.
   * 
   * @return possible object is {@link CommitTransaction }
   * 
   */
  public CommitTransaction getCommitTransaction() {
    return commitTransaction;
  }

  /**
   * Sets the value of the commitTransaction property.
   * 
   * @param value allowed object is {@link CommitTransaction }
   * 
   */
  public void setCommitTransaction(CommitTransaction value) {
    this.commitTransaction = value;
  }

  public boolean isSetCommitTransaction() {
    return (this.commitTransaction != null);
  }

  /**
   * Gets the value of the rollbackTransaction property.
   * 
   * @return possible object is {@link RollbackTransaction }
   * 
   */
  public RollbackTransaction getRollbackTransaction() {
    return rollbackTransaction;
  }

  /**
   * Sets the value of the rollbackTransaction property.
   * 
   * @param value allowed object is {@link RollbackTransaction }
   * 
   */
  public void setRollbackTransaction(RollbackTransaction value) {
    this.rollbackTransaction = value;
  }

  public boolean isSetRollbackTransaction() {
    return (this.rollbackTransaction != null);
  }

  /**
   * Gets the value of the lock property.
   * 
   * @return possible object is {@link Lock }
   * 
   */
  public Lock getLock() {
    return lock;
  }

  /**
   * Sets the value of the lock property.
   * 
   * @param value allowed object is {@link Lock }
   * 
   */
  public void setLock(Lock value) {
    this.lock = value;
  }

  public boolean isSetLock() {
    return (this.lock != null);
  }

  /**
   * Gets the value of the unlock property.
   * 
   * @return possible object is {@link Unlock }
   * 
   */
  public Unlock getUnlock() {
    return unlock;
  }

  /**
   * Sets the value of the unlock property.
   * 
   * @param value allowed object is {@link Unlock }
   * 
   */
  public void setUnlock(Unlock value) {
    this.unlock = value;
  }

  public boolean isSetUnlock() {
    return (this.unlock != null);
  }

  /**
   * Gets the value of the backup property.
   * 
   * @return possible object is {@link Backup }
   * 
   */
  public Backup getBackup() {
    return backup;
  }

  /**
   * Sets the value of the backup property.
   * 
   * @param value allowed object is {@link Backup }
   * 
   */
  public void setBackup(Backup value) {
    this.backup = value;
  }

  public boolean isSetBackup() {
    return (this.backup != null);
  }

  /**
   * Gets the value of the restore property.
   * 
   * @return possible object is {@link Restore }
   * 
   */
  public Restore getRestore() {
    return restore;
  }

  /**
   * Sets the value of the restore property.
   * 
   * @param value allowed object is {@link Restore }
   * 
   */
  public void setRestore(Restore value) {
    this.restore = value;
  }

  public boolean isSetRestore() {
    return (this.restore != null);
  }

  /**
   * Gets the value of the synchronize property.
   * 
   * @return possible object is {@link Synchronize }
   * 
   */
  public Synchronize getSynchronize() {
    return synchronize;
  }

  /**
   * Sets the value of the synchronize property.
   * 
   * @param value allowed object is {@link Synchronize }
   * 
   */
  public void setSynchronize(Synchronize value) {
    this.synchronize = value;
  }

  public boolean isSetSynchronize() {
    return (this.synchronize != null);
  }

  /**
   * Gets the value of the attach property.
   * 
   * @return possible object is {@link Attach }
   * 
   */
  public Attach getAttach() {
    return attach;
  }

  /**
   * Sets the value of the attach property.
   * 
   * @param value allowed object is {@link Attach }
   * 
   */
  public void setAttach(Attach value) {
    this.attach = value;
  }

  public boolean isSetAttach() {
    return (this.attach != null);
  }

  /**
   * Gets the value of the detach property.
   * 
   * @return possible object is {@link Detach }
   * 
   */
  public Detach getDetach() {
    return detach;
  }

  /**
   * Sets the value of the detach property.
   * 
   * @param value allowed object is {@link Detach }
   * 
   */
  public void setDetach(Detach value) {
    this.detach = value;
  }

  public boolean isSetDetach() {
    return (this.detach != null);
  }

  /**
   * Gets the value of the insert property.
   * 
   * @return possible object is {@link Insert }
   * 
   */
  public Insert getInsert() {
    return insert;
  }

  /**
   * Sets the value of the insert property.
   * 
   * @param value allowed object is {@link Insert }
   * 
   */
  public void setInsert(Insert value) {
    this.insert = value;
  }

  public boolean isSetInsert() {
    return (this.insert != null);
  }

  /**
   * Gets the value of the update property.
   * 
   * @return possible object is {@link Update }
   * 
   */
  public Update getUpdate() {
    return update;
  }

  /**
   * Sets the value of the update property.
   * 
   * @param value allowed object is {@link Update }
   * 
   */
  public void setUpdate(Update value) {
    this.update = value;
  }

  public boolean isSetUpdate() {
    return (this.update != null);
  }

  /**
   * Gets the value of the drop property.
   * 
   * @return possible object is {@link Drop }
   * 
   */
  public Drop getDrop() {
    return drop;
  }

  /**
   * Sets the value of the drop property.
   * 
   * @param value allowed object is {@link Drop }
   * 
   */
  public void setDrop(Drop value) {
    this.drop = value;
  }

  public boolean isSetDrop() {
    return (this.drop != null);
  }

  /**
   * Gets the value of the updateCells property.
   * 
   * @return possible object is {@link UpdateCells }
   * 
   */
  public UpdateCells getUpdateCells() {
    return updateCells;
  }

  /**
   * Sets the value of the updateCells property.
   * 
   * @param value allowed object is {@link UpdateCells }
   * 
   */
  public void setUpdateCells(UpdateCells value) {
    this.updateCells = value;
  }

  public boolean isSetUpdateCells() {
    return (this.updateCells != null);
  }

  /**
   * Gets the value of the notifyTableChange property.
   * 
   * @return possible object is {@link NotifyTableChange }
   * 
   */
  public NotifyTableChange getNotifyTableChange() {
    return notifyTableChange;
  }

  /**
   * Sets the value of the notifyTableChange property.
   * 
   * @param value allowed object is {@link NotifyTableChange }
   * 
   */
  public void setNotifyTableChange(NotifyTableChange value) {
    this.notifyTableChange = value;
  }

  public boolean isSetNotifyTableChange() {
    return (this.notifyTableChange != null);
  }

  /**
   * Gets the value of the batch property.
   * 
   * @return possible object is {@link Batch }
   * 
   */
  public Batch getBatch() {
    return batch;
  }

  /**
   * Sets the value of the batch property.
   * 
   * @param value allowed object is {@link Batch }
   * 
   */
  public void setBatch(Batch value) {
    this.batch = value;
  }

  public boolean isSetBatch() {
    return (this.batch != null);
  }

  /**
   * Gets the value of the imageLoad property.
   * 
   * @return possible object is {@link ImageLoad }
   * 
   */
  public ImageLoad getImageLoad() {
    return imageLoad;
  }

  /**
   * Sets the value of the imageLoad property.
   * 
   * @param value allowed object is {@link ImageLoad }
   * 
   */
  public void setImageLoad(ImageLoad value) {
    this.imageLoad = value;
  }

  public boolean isSetImageLoad() {
    return (this.imageLoad != null);
  }

  /**
   * Gets the value of the imageSave property.
   * 
   * @return possible object is {@link ImageSave }
   * 
   */
  public ImageSave getImageSave() {
    return imageSave;
  }

  /**
   * Sets the value of the imageSave property.
   * 
   * @param value allowed object is {@link ImageSave }
   * 
   */
  public void setImageSave(ImageSave value) {
    this.imageSave = value;
  }

  public boolean isSetImageSave() {
    return (this.imageSave != null);
  }

  /**
   * Gets the value of the cloneDatabase property.
   * 
   * @return possible object is {@link CloneDatabase }
   * 
   */
  public CloneDatabase getCloneDatabase() {
    return cloneDatabase;
  }

  /**
   * Sets the value of the cloneDatabase property.
   * 
   * @param value allowed object is {@link CloneDatabase }
   * 
   */
  public void setCloneDatabase(CloneDatabase value) {
    this.cloneDatabase = value;
  }

  public boolean isSetCloneDatabase() {
    return (this.cloneDatabase != null);
  }

  /**
   * Gets the value of the setAuthContext property.
   * 
   * @return possible object is {@link SetAuthContext }
   * 
   */
  public SetAuthContext getSetAuthContext() {
    return setAuthContext;
  }

  /**
   * Sets the value of the setAuthContext property.
   * 
   * @param value allowed object is {@link SetAuthContext }
   * 
   */
  public void setSetAuthContext(SetAuthContext value) {
    this.setAuthContext = value;
  }

  public boolean isSetSetAuthContext() {
    return (this.setAuthContext != null);
  }

  /**
   * Gets the value of the dbcc property.
   * 
   * @return possible object is {@link DBCC }
   * 
   */
  public DBCC getDBCC() {
    return dbcc;
  }

  /**
   * Sets the value of the dbcc property.
   * 
   * @param value allowed object is {@link DBCC }
   * 
   */
  public void setDBCC(DBCC value) {
    this.dbcc = value;
  }

  public boolean isSetDBCC() {
    return (this.dbcc != null);
  }

}
