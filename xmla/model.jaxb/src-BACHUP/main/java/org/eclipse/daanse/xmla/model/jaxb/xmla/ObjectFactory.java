
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.eclipse.daanse.xmla.model.jaxb.xmla package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AllowedRowsExpression_QNAME = new QName("urn:schemas-microsoft-com:xml-analysis", "AllowedRowsExpression");
    private final static QName _ShareDimensionStorage_QNAME = new QName("urn:schemas-microsoft-com:xml-analysis", "ShareDimensionStorage");
    private final static QName _BooleanExprAnd_QNAME = new QName("", "and");
    private final static QName _BooleanExprOr_QNAME = new QName("", "or");
    private final static QName _BooleanExprNot_QNAME = new QName("", "not");
    private final static QName _BooleanExprLeaf_QNAME = new QName("", "leaf");
    private final static QName _AndOrTypeNot_QNAME = new QName("", "Not");
    private final static QName _AndOrTypeOr_QNAME = new QName("", "Or");
    private final static QName _AndOrTypeAnd_QNAME = new QName("", "And");
    private final static QName _AndOrTypeEqual_QNAME = new QName("", "Equal");
    private final static QName _AndOrTypeNotEqual_QNAME = new QName("", "NotEqual");
    private final static QName _AndOrTypeLess_QNAME = new QName("", "Less");
    private final static QName _AndOrTypeLessOrEqual_QNAME = new QName("", "LessOrEqual");
    private final static QName _AndOrTypeGreater_QNAME = new QName("", "Greater");
    private final static QName _AndOrTypeGreaterOrEqual_QNAME = new QName("", "GreaterOrEqual");
    private final static QName _AndOrTypeLike_QNAME = new QName("", "Like");
    private final static QName _AndOrTypeNotLike_QNAME = new QName("", "NotLike");
    private final static QName _DiscoverRestrictionsRestrictionList_QNAME = new QName("", "RestrictionList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.eclipse.daanse.xmla.model.jaxb.xmla
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EventType }
     * 
     */
    public EventType createEventType() {
        return new EventType();
    }

    /**
     * Create an instance of {@link Execute }
     * 
     */
    public Execute createExecute() {
        return new Execute();
    }

    /**
     * Create an instance of {@link Discover }
     * 
     */
    public Discover createDiscover() {
        return new Discover();
    }

    /**
     * Create an instance of {@link DiscoverResponse }
     * 
     */
    public DiscoverResponse createDiscoverResponse() {
        return new DiscoverResponse();
    }

    /**
     * Create an instance of {@link Account }
     * 
     */
    public Account createAccount() {
        return new Account();
    }

    /**
     * Create an instance of {@link AggregationInstanceAttribute }
     * 
     */
    public AggregationInstanceAttribute createAggregationInstanceAttribute() {
        return new AggregationInstanceAttribute();
    }

    /**
     * Create an instance of {@link AggregationInstanceDimension }
     * 
     */
    public AggregationInstanceDimension createAggregationInstanceDimension() {
        return new AggregationInstanceDimension();
    }

    /**
     * Create an instance of {@link AggregationInstance }
     * 
     */
    public AggregationInstance createAggregationInstance() {
        return new AggregationInstance();
    }

    /**
     * Create an instance of {@link Partition }
     * 
     */
    public Partition createPartition() {
        return new Partition();
    }

    /**
     * Create an instance of {@link AggregationAttribute }
     * 
     */
    public AggregationAttribute createAggregationAttribute() {
        return new AggregationAttribute();
    }

    /**
     * Create an instance of {@link AggregationDimension }
     * 
     */
    public AggregationDimension createAggregationDimension() {
        return new AggregationDimension();
    }

    /**
     * Create an instance of {@link Aggregation }
     * 
     */
    public Aggregation createAggregation() {
        return new Aggregation();
    }

    /**
     * Create an instance of {@link AggregationDesignDimension }
     * 
     */
    public AggregationDesignDimension createAggregationDesignDimension() {
        return new AggregationDesignDimension();
    }

    /**
     * Create an instance of {@link AggregationDesign }
     * 
     */
    public AggregationDesign createAggregationDesign() {
        return new AggregationDesign();
    }

    /**
     * Create an instance of {@link Measure }
     * 
     */
    public Measure createMeasure() {
        return new Measure();
    }

    /**
     * Create an instance of {@link MeasureGroupAttribute }
     * 
     */
    public MeasureGroupAttribute createMeasureGroupAttribute() {
        return new MeasureGroupAttribute();
    }

    /**
     * Create an instance of {@link DataMiningMeasureGroupDimension }
     * 
     */
    public DataMiningMeasureGroupDimension createDataMiningMeasureGroupDimension() {
        return new DataMiningMeasureGroupDimension();
    }

    /**
     * Create an instance of {@link DegenerateMeasureGroupDimension }
     * 
     */
    public DegenerateMeasureGroupDimension createDegenerateMeasureGroupDimension() {
        return new DegenerateMeasureGroupDimension();
    }

    /**
     * Create an instance of {@link ReferenceMeasureGroupDimension }
     * 
     */
    public ReferenceMeasureGroupDimension createReferenceMeasureGroupDimension() {
        return new ReferenceMeasureGroupDimension();
    }

    /**
     * Create an instance of {@link RegularMeasureGroupDimension }
     * 
     */
    public RegularMeasureGroupDimension createRegularMeasureGroupDimension() {
        return new RegularMeasureGroupDimension();
    }

    /**
     * Create an instance of {@link ManyToManyMeasureGroupDimension }
     * 
     */
    public ManyToManyMeasureGroupDimension createManyToManyMeasureGroupDimension() {
        return new ManyToManyMeasureGroupDimension();
    }

    /**
     * Create an instance of {@link MeasureGroup }
     * 
     */
    public MeasureGroup createMeasureGroup() {
        return new MeasureGroup();
    }

    /**
     * Create an instance of {@link PerspectiveAction }
     * 
     */
    public PerspectiveAction createPerspectiveAction() {
        return new PerspectiveAction();
    }

    /**
     * Create an instance of {@link PerspectiveKpi }
     * 
     */
    public PerspectiveKpi createPerspectiveKpi() {
        return new PerspectiveKpi();
    }

    /**
     * Create an instance of {@link PerspectiveCalculation }
     * 
     */
    public PerspectiveCalculation createPerspectiveCalculation() {
        return new PerspectiveCalculation();
    }

    /**
     * Create an instance of {@link PerspectiveMeasure }
     * 
     */
    public PerspectiveMeasure createPerspectiveMeasure() {
        return new PerspectiveMeasure();
    }

    /**
     * Create an instance of {@link PerspectiveMeasureGroup }
     * 
     */
    public PerspectiveMeasureGroup createPerspectiveMeasureGroup() {
        return new PerspectiveMeasureGroup();
    }

    /**
     * Create an instance of {@link PerspectiveHierarchy }
     * 
     */
    public PerspectiveHierarchy createPerspectiveHierarchy() {
        return new PerspectiveHierarchy();
    }

    /**
     * Create an instance of {@link PerspectiveAttribute }
     * 
     */
    public PerspectiveAttribute createPerspectiveAttribute() {
        return new PerspectiveAttribute();
    }

    /**
     * Create an instance of {@link PerspectiveDimension }
     * 
     */
    public PerspectiveDimension createPerspectiveDimension() {
        return new PerspectiveDimension();
    }

    /**
     * Create an instance of {@link Perspective }
     * 
     */
    public Perspective createPerspective() {
        return new Perspective();
    }

    /**
     * Create an instance of {@link CalculationProperty }
     * 
     */
    public CalculationProperty createCalculationProperty() {
        return new CalculationProperty();
    }

    /**
     * Create an instance of {@link MdxScript }
     * 
     */
    public MdxScript createMdxScript() {
        return new MdxScript();
    }

    /**
     * Create an instance of {@link DrillThroughAction }
     * 
     */
    public DrillThroughAction createDrillThroughAction() {
        return new DrillThroughAction();
    }

    /**
     * Create an instance of {@link ReportAction }
     * 
     */
    public ReportAction createReportAction() {
        return new ReportAction();
    }

    /**
     * Create an instance of {@link StandardAction }
     * 
     */
    public StandardAction createStandardAction() {
        return new StandardAction();
    }

    /**
     * Create an instance of {@link Kpi }
     * 
     */
    public Kpi createKpi() {
        return new Kpi();
    }

    /**
     * Create an instance of {@link CubeHierarchy }
     * 
     */
    public CubeHierarchy createCubeHierarchy() {
        return new CubeHierarchy();
    }

    /**
     * Create an instance of {@link CubeAttribute }
     * 
     */
    public CubeAttribute createCubeAttribute() {
        return new CubeAttribute();
    }

    /**
     * Create an instance of {@link CubeDimension }
     * 
     */
    public CubeDimension createCubeDimension() {
        return new CubeDimension();
    }

    /**
     * Create an instance of {@link Cube }
     * 
     */
    public Cube createCube() {
        return new Cube();
    }

    /**
     * Create an instance of {@link MiningModelColumn }
     * 
     */
    public MiningModelColumn createMiningModelColumn() {
        return new MiningModelColumn();
    }

    /**
     * Create an instance of {@link MiningModel }
     * 
     */
    public MiningModel createMiningModel() {
        return new MiningModel();
    }

    /**
     * Create an instance of {@link TableMiningStructureColumn }
     * 
     */
    public TableMiningStructureColumn createTableMiningStructureColumn() {
        return new TableMiningStructureColumn();
    }

    /**
     * Create an instance of {@link ScalarMiningStructureColumn }
     * 
     */
    public ScalarMiningStructureColumn createScalarMiningStructureColumn() {
        return new ScalarMiningStructureColumn();
    }

    /**
     * Create an instance of {@link MiningStructure }
     * 
     */
    public MiningStructure createMiningStructure() {
        return new MiningStructure();
    }

    /**
     * Create an instance of {@link Database }
     * 
     */
    public Database createDatabase() {
        return new Database();
    }

    /**
     * Create an instance of {@link PredLeaf }
     * 
     */
    public PredLeaf createPredLeaf() {
        return new PredLeaf();
    }

    /**
     * Create an instance of {@link Trace }
     * 
     */
    public Trace createTrace() {
        return new Trace();
    }

    /**
     * Create an instance of {@link ClrAssembly }
     * 
     */
    public ClrAssembly createClrAssembly() {
        return new ClrAssembly();
    }

    /**
     * Create an instance of {@link Server }
     * 
     */
    public Server createServer() {
        return new Server();
    }

    /**
     * Create an instance of {@link Level }
     * 
     */
    public Level createLevel() {
        return new Level();
    }

    /**
     * Create an instance of {@link Hierarchy }
     * 
     */
    public Hierarchy createHierarchy() {
        return new Hierarchy();
    }

    /**
     * Create an instance of {@link AttributeRelationship }
     * 
     */
    public AttributeRelationship createAttributeRelationship() {
        return new AttributeRelationship();
    }

    /**
     * Create an instance of {@link AttributeTranslation }
     * 
     */
    public AttributeTranslation createAttributeTranslation() {
        return new AttributeTranslation();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jaxb.xmla.Translation }
     * 
     */
    public org.eclipse.daanse.xmla.model.jaxb.xmla.Translation createTranslation() {
        return new org.eclipse.daanse.xmla.model.jaxb.xmla.Translation();
    }

    /**
     * Create an instance of {@link DataItem }
     * 
     */
    public DataItem createDataItem() {
        return new DataItem();
    }

    /**
     * Create an instance of {@link DimensionAttribute }
     * 
     */
    public DimensionAttribute createDimensionAttribute() {
        return new DimensionAttribute();
    }

    /**
     * Create an instance of {@link Dimension }
     * 
     */
    public Dimension createDimension() {
        return new Dimension();
    }

    /**
     * Create an instance of {@link PushedDataSource }
     * 
     */
    public PushedDataSource createPushedDataSource() {
        return new PushedDataSource();
    }

    /**
     * Create an instance of {@link TraceColumns }
     * 
     */
    public TraceColumns createTraceColumns() {
        return new TraceColumns();
    }

    /**
     * Create an instance of {@link TraceColumns.Data }
     * 
     */
    public TraceColumns.Data createTraceColumnsData() {
        return new TraceColumns.Data();
    }

    /**
     * Create an instance of {@link EventColumn }
     * 
     */
    public EventColumn createEventColumn() {
        return new EventColumn();
    }

    /**
     * Create an instance of {@link EventColumn.EventColumnSubclassList }
     * 
     */
    public EventColumn.EventColumnSubclassList createEventColumnEventColumnSubclassList() {
        return new EventColumn.EventColumnSubclassList();
    }

    /**
     * Create an instance of {@link TraceEvent }
     * 
     */
    public TraceEvent createTraceEvent() {
        return new TraceEvent();
    }

    /**
     * Create an instance of {@link TraceEventCategories }
     * 
     */
    public TraceEventCategories createTraceEventCategories() {
        return new TraceEventCategories();
    }

    /**
     * Create an instance of {@link TraceEventCategories.Data }
     * 
     */
    public TraceEventCategories.Data createTraceEventCategoriesData() {
        return new TraceEventCategories.Data();
    }

    /**
     * Create an instance of {@link TraceEventCategories.Data.EventCategory }
     * 
     */
    public TraceEventCategories.Data.EventCategory createTraceEventCategoriesDataEventCategory() {
        return new TraceEventCategories.Data.EventCategory();
    }

    /**
     * Create an instance of {@link TraceDefinitionProviderInfo }
     * 
     */
    public TraceDefinitionProviderInfo createTraceDefinitionProviderInfo() {
        return new TraceDefinitionProviderInfo();
    }

    /**
     * Create an instance of {@link TraceDefinitionProviderInfo.Data }
     * 
     */
    public TraceDefinitionProviderInfo.Data createTraceDefinitionProviderInfoData() {
        return new TraceDefinitionProviderInfo.Data();
    }

    /**
     * Create an instance of {@link Role }
     * 
     */
    public Role createRole() {
        return new Role();
    }

    /**
     * Create an instance of {@link Permission }
     * 
     */
    public Permission createPermission() {
        return new Permission();
    }

    /**
     * Create an instance of {@link CubePermission }
     * 
     */
    public CubePermission createCubePermission() {
        return new CubePermission();
    }

    /**
     * Create an instance of {@link DimensionPermission }
     * 
     */
    public DimensionPermission createDimensionPermission() {
        return new DimensionPermission();
    }

    /**
     * Create an instance of {@link CellPermission }
     * 
     */
    public CellPermission createCellPermission() {
        return new CellPermission();
    }

    /**
     * Create an instance of {@link AttributePermission }
     * 
     */
    public AttributePermission createAttributePermission() {
        return new AttributePermission();
    }

    /**
     * Create an instance of {@link CubeDimensionPermission }
     * 
     */
    public CubeDimensionPermission createCubeDimensionPermission() {
        return new CubeDimensionPermission();
    }

    /**
     * Create an instance of {@link ProactiveCachingIncrementalProcessingBinding }
     * 
     */
    public ProactiveCachingIncrementalProcessingBinding createProactiveCachingIncrementalProcessingBinding() {
        return new ProactiveCachingIncrementalProcessingBinding();
    }

    /**
     * Create an instance of {@link ProactiveCachingQueryBinding }
     * 
     */
    public ProactiveCachingQueryBinding createProactiveCachingQueryBinding() {
        return new ProactiveCachingQueryBinding();
    }

    /**
     * Create an instance of {@link ProactiveCachingTablesBinding }
     * 
     */
    public ProactiveCachingTablesBinding createProactiveCachingTablesBinding() {
        return new ProactiveCachingTablesBinding();
    }

    /**
     * Create an instance of {@link CubeAttributeBinding }
     * 
     */
    public CubeAttributeBinding createCubeAttributeBinding() {
        return new CubeAttributeBinding();
    }

    /**
     * Create an instance of {@link Group }
     * 
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link UserDefinedGroupBinding }
     * 
     */
    public UserDefinedGroupBinding createUserDefinedGroupBinding() {
        return new UserDefinedGroupBinding();
    }

    /**
     * Create an instance of {@link OutOfLineBinding }
     * 
     */
    public OutOfLineBinding createOutOfLineBinding() {
        return new OutOfLineBinding();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.Translations }
     * 
     */
    public OutOfLineBinding.Translations createOutOfLineBindingTranslations() {
        return new OutOfLineBinding.Translations();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.ForeignKeyColumns }
     * 
     */
    public OutOfLineBinding.ForeignKeyColumns createOutOfLineBindingForeignKeyColumns() {
        return new OutOfLineBinding.ForeignKeyColumns();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.KeyColumns }
     * 
     */
    public OutOfLineBinding.KeyColumns createOutOfLineBindingKeyColumns() {
        return new OutOfLineBinding.KeyColumns();
    }

    /**
     * Create an instance of {@link CloneDatabase }
     * 
     */
    public CloneDatabase createCloneDatabase() {
        return new CloneDatabase();
    }

    /**
     * Create an instance of {@link ImageLoad }
     * 
     */
    public ImageLoad createImageLoad() {
        return new ImageLoad();
    }

    /**
     * Create an instance of {@link Batch }
     * 
     */
    public Batch createBatch() {
        return new Batch();
    }

    /**
     * Create an instance of {@link NotifyTableChange }
     * 
     */
    public NotifyTableChange createNotifyTableChange() {
        return new NotifyTableChange();
    }

    /**
     * Create an instance of {@link WhereAttribute }
     * 
     */
    public WhereAttribute createWhereAttribute() {
        return new WhereAttribute();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link AttributeInsertUpdate }
     * 
     */
    public AttributeInsertUpdate createAttributeInsertUpdate() {
        return new AttributeInsertUpdate();
    }

    /**
     * Create an instance of {@link Insert }
     * 
     */
    public Insert createInsert() {
        return new Insert();
    }

    /**
     * Create an instance of {@link Synchronize }
     * 
     */
    public Synchronize createSynchronize() {
        return new Synchronize();
    }

    /**
     * Create an instance of {@link Location }
     * 
     */
    public Location createLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link Restore }
     * 
     */
    public Restore createRestore() {
        return new Restore();
    }

    /**
     * Create an instance of {@link Backup }
     * 
     */
    public Backup createBackup() {
        return new Backup();
    }

    /**
     * Create an instance of {@link DesignAggregations }
     * 
     */
    public DesignAggregations createDesignAggregations() {
        return new DesignAggregations();
    }

    /**
     * Create an instance of {@link MergePartitions }
     * 
     */
    public MergePartitions createMergePartitions() {
        return new MergePartitions();
    }

    /**
     * Create an instance of {@link DataSourceView }
     * 
     */
    public DataSourceView createDataSourceView() {
        return new DataSourceView();
    }

    /**
     * Create an instance of {@link Discover.Restrictions }
     * 
     */
    public Discover.Restrictions createDiscoverRestrictions() {
        return new Discover.Restrictions();
    }

    /**
     * Create an instance of {@link KeepResult }
     * 
     */
    public KeepResult createKeepResult() {
        return new KeepResult();
    }

    /**
     * Create an instance of {@link ClearResult }
     * 
     */
    public ClearResult createClearResult() {
        return new ClearResult();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link ExecuteResponse }
     * 
     */
    public ExecuteResponse createExecuteResponse() {
        return new ExecuteResponse();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jaxb.xmla.Return }
     * 
     */
    public org.eclipse.daanse.xmla.model.jaxb.xmla.Return createReturn() {
        return new org.eclipse.daanse.xmla.model.jaxb.xmla.Return();
    }

    /**
     * Create an instance of {@link EventType.Events }
     * 
     */
    public EventType.Events createEventTypeEvents() {
        return new EventType.Events();
    }

    /**
     * Create an instance of {@link EventSession }
     * 
     */
    public EventSession createEventSession() {
        return new EventSession();
    }

    /**
     * Create an instance of {@link Event2 }
     * 
     */
    public Event2 createEvent2() {
        return new Event2();
    }

    /**
     * Create an instance of {@link Action2 }
     * 
     */
    public Action2 createAction2() {
        return new Action2();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jaxb.xmla.Target }
     * 
     */
    public org.eclipse.daanse.xmla.model.jaxb.xmla.Target createTarget() {
        return new org.eclipse.daanse.xmla.model.jaxb.xmla.Target();
    }

    /**
     * Create an instance of {@link Parameter }
     * 
     */
    public Parameter createParameter() {
        return new Parameter();
    }

    /**
     * Create an instance of {@link Command }
     * 
     */
    public Command createCommand() {
        return new Command();
    }

    /**
     * Create an instance of {@link Execute.Properties }
     * 
     */
    public Execute.Properties createExecuteProperties() {
        return new Execute.Properties();
    }

    /**
     * Create an instance of {@link Execute.Parameters }
     * 
     */
    public Execute.Parameters createExecuteParameters() {
        return new Execute.Parameters();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jaxb.xmla.Properties }
     * 
     */
    public org.eclipse.daanse.xmla.model.jaxb.xmla.Properties createProperties() {
        return new org.eclipse.daanse.xmla.model.jaxb.xmla.Properties();
    }

    /**
     * Create an instance of {@link BeginSession }
     * 
     */
    public BeginSession createBeginSession() {
        return new BeginSession();
    }

    /**
     * Create an instance of {@link EndSession }
     * 
     */
    public EndSession createEndSession() {
        return new EndSession();
    }

    /**
     * Create an instance of {@link Session }
     * 
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link DiscoverResponse.Return }
     * 
     */
    public DiscoverResponse.Return createDiscoverResponseReturn() {
        return new DiscoverResponse.Return();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jaxb.xmla.Parameters }
     * 
     */
    public org.eclipse.daanse.xmla.model.jaxb.xmla.Parameters createParameters() {
        return new org.eclipse.daanse.xmla.model.jaxb.xmla.Parameters();
    }

    /**
     * Create an instance of {@link PropertyList }
     * 
     */
    public PropertyList createPropertyList() {
        return new PropertyList();
    }

    /**
     * Create an instance of {@link ObjectReference }
     * 
     */
    public ObjectReference createObjectReference() {
        return new ObjectReference();
    }

    /**
     * Create an instance of {@link Statement }
     * 
     */
    public Statement createStatement() {
        return new Statement();
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link Alter }
     * 
     */
    public Alter createAlter() {
        return new Alter();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link Process }
     * 
     */
    public Process createProcess() {
        return new Process();
    }

    /**
     * Create an instance of {@link Bindings }
     * 
     */
    public Bindings createBindings() {
        return new Bindings();
    }

    /**
     * Create an instance of {@link ClearCache }
     * 
     */
    public ClearCache createClearCache() {
        return new ClearCache();
    }

    /**
     * Create an instance of {@link Subscribe }
     * 
     */
    public Subscribe createSubscribe() {
        return new Subscribe();
    }

    /**
     * Create an instance of {@link Unsubscribe }
     * 
     */
    public Unsubscribe createUnsubscribe() {
        return new Unsubscribe();
    }

    /**
     * Create an instance of {@link Cancel }
     * 
     */
    public Cancel createCancel() {
        return new Cancel();
    }

    /**
     * Create an instance of {@link BeginTransaction }
     * 
     */
    public BeginTransaction createBeginTransaction() {
        return new BeginTransaction();
    }

    /**
     * Create an instance of {@link CommitTransaction }
     * 
     */
    public CommitTransaction createCommitTransaction() {
        return new CommitTransaction();
    }

    /**
     * Create an instance of {@link RollbackTransaction }
     * 
     */
    public RollbackTransaction createRollbackTransaction() {
        return new RollbackTransaction();
    }

    /**
     * Create an instance of {@link Lock }
     * 
     */
    public Lock createLock() {
        return new Lock();
    }

    /**
     * Create an instance of {@link Unlock }
     * 
     */
    public Unlock createUnlock() {
        return new Unlock();
    }

    /**
     * Create an instance of {@link LocationBackup }
     * 
     */
    public LocationBackup createLocationBackup() {
        return new LocationBackup();
    }

    /**
     * Create an instance of {@link MajorObject }
     * 
     */
    public MajorObject createMajorObject() {
        return new MajorObject();
    }

    /**
     * Create an instance of {@link Folder }
     * 
     */
    public Folder createFolder() {
        return new Folder();
    }

    /**
     * Create an instance of {@link Source }
     * 
     */
    public Source createSource() {
        return new Source();
    }

    /**
     * Create an instance of {@link Attach }
     * 
     */
    public Attach createAttach() {
        return new Attach();
    }

    /**
     * Create an instance of {@link Detach }
     * 
     */
    public Detach createDetach() {
        return new Detach();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jaxb.xmla.Object }
     * 
     */
    public org.eclipse.daanse.xmla.model.jaxb.xmla.Object createObject() {
        return new org.eclipse.daanse.xmla.model.jaxb.xmla.Object();
    }

    /**
     * Create an instance of {@link TranslationInsertUpdate }
     * 
     */
    public TranslationInsertUpdate createTranslationInsertUpdate() {
        return new TranslationInsertUpdate();
    }

    /**
     * Create an instance of {@link Where }
     * 
     */
    public Where createWhere() {
        return new Where();
    }

    /**
     * Create an instance of {@link Drop }
     * 
     */
    public Drop createDrop() {
        return new Drop();
    }

    /**
     * Create an instance of {@link UpdateCells }
     * 
     */
    public UpdateCells createUpdateCells() {
        return new UpdateCells();
    }

    /**
     * Create an instance of {@link Cell }
     * 
     */
    public Cell createCell() {
        return new Cell();
    }

    /**
     * Create an instance of {@link ImageSave }
     * 
     */
    public ImageSave createImageSave() {
        return new ImageSave();
    }

    /**
     * Create an instance of {@link SetAuthContext }
     * 
     */
    public SetAuthContext createSetAuthContext() {
        return new SetAuthContext();
    }

    /**
     * Create an instance of {@link DBCC }
     * 
     */
    public DBCC createDBCC() {
        return new DBCC();
    }

    /**
     * Create an instance of {@link ExecuteParameter }
     * 
     */
    public ExecuteParameter createExecuteParameter() {
        return new ExecuteParameter();
    }

    /**
     * Create an instance of {@link ColumnBinding }
     * 
     */
    public ColumnBinding createColumnBinding() {
        return new ColumnBinding();
    }

    /**
     * Create an instance of {@link RowBinding }
     * 
     */
    public RowBinding createRowBinding() {
        return new RowBinding();
    }

    /**
     * Create an instance of {@link DataSourceViewBinding }
     * 
     */
    public DataSourceViewBinding createDataSourceViewBinding() {
        return new DataSourceViewBinding();
    }

    /**
     * Create an instance of {@link AttributeBinding }
     * 
     */
    public AttributeBinding createAttributeBinding() {
        return new AttributeBinding();
    }

    /**
     * Create an instance of {@link MeasureBinding }
     * 
     */
    public MeasureBinding createMeasureBinding() {
        return new MeasureBinding();
    }

    /**
     * Create an instance of {@link DimensionBinding }
     * 
     */
    public DimensionBinding createDimensionBinding() {
        return new DimensionBinding();
    }

    /**
     * Create an instance of {@link CubeDimensionBinding }
     * 
     */
    public CubeDimensionBinding createCubeDimensionBinding() {
        return new CubeDimensionBinding();
    }

    /**
     * Create an instance of {@link MeasureGroupBinding }
     * 
     */
    public MeasureGroupBinding createMeasureGroupBinding() {
        return new MeasureGroupBinding();
    }

    /**
     * Create an instance of {@link MeasureGroupDimensionBinding }
     * 
     */
    public MeasureGroupDimensionBinding createMeasureGroupDimensionBinding() {
        return new MeasureGroupDimensionBinding();
    }

    /**
     * Create an instance of {@link TimeBinding }
     * 
     */
    public TimeBinding createTimeBinding() {
        return new TimeBinding();
    }

    /**
     * Create an instance of {@link TimeAttributeBinding }
     * 
     */
    public TimeAttributeBinding createTimeAttributeBinding() {
        return new TimeAttributeBinding();
    }

    /**
     * Create an instance of {@link InheritedBinding }
     * 
     */
    public InheritedBinding createInheritedBinding() {
        return new InheritedBinding();
    }

    /**
     * Create an instance of {@link TableBinding }
     * 
     */
    public TableBinding createTableBinding() {
        return new TableBinding();
    }

    /**
     * Create an instance of {@link QueryBinding }
     * 
     */
    public QueryBinding createQueryBinding() {
        return new QueryBinding();
    }

    /**
     * Create an instance of {@link DSVTableBinding }
     * 
     */
    public DSVTableBinding createDSVTableBinding() {
        return new DSVTableBinding();
    }

    /**
     * Create an instance of {@link ProactiveCachingInheritedBinding }
     * 
     */
    public ProactiveCachingInheritedBinding createProactiveCachingInheritedBinding() {
        return new ProactiveCachingInheritedBinding();
    }

    /**
     * Create an instance of {@link QueryNotification }
     * 
     */
    public QueryNotification createQueryNotification() {
        return new QueryNotification();
    }

    /**
     * Create an instance of {@link IncrementalProcessingNotification }
     * 
     */
    public IncrementalProcessingNotification createIncrementalProcessingNotification() {
        return new IncrementalProcessingNotification();
    }

    /**
     * Create an instance of {@link TableNotification }
     * 
     */
    public TableNotification createTableNotification() {
        return new TableNotification();
    }

    /**
     * Create an instance of {@link CalculatedMeasureBinding }
     * 
     */
    public CalculatedMeasureBinding createCalculatedMeasureBinding() {
        return new CalculatedMeasureBinding();
    }

    /**
     * Create an instance of {@link DatabasePermission }
     * 
     */
    public DatabasePermission createDatabasePermission() {
        return new DatabasePermission();
    }

    /**
     * Create an instance of {@link DataSourcePermission }
     * 
     */
    public DataSourcePermission createDataSourcePermission() {
        return new DataSourcePermission();
    }

    /**
     * Create an instance of {@link MiningStructurePermission }
     * 
     */
    public MiningStructurePermission createMiningStructurePermission() {
        return new MiningStructurePermission();
    }

    /**
     * Create an instance of {@link MiningModelPermission }
     * 
     */
    public MiningModelPermission createMiningModelPermission() {
        return new MiningModelPermission();
    }

    /**
     * Create an instance of {@link Member }
     * 
     */
    public Member createMember() {
        return new Member();
    }

    /**
     * Create an instance of {@link ProactiveCaching }
     * 
     */
    public ProactiveCaching createProactiveCaching() {
        return new ProactiveCaching();
    }

    /**
     * Create an instance of {@link ErrorConfiguration }
     * 
     */
    public ErrorConfiguration createErrorConfiguration() {
        return new ErrorConfiguration();
    }

    /**
     * Create an instance of {@link Annotation }
     * 
     */
    public Annotation createAnnotation() {
        return new Annotation();
    }

    /**
     * Create an instance of {@link RelationalDataSource }
     * 
     */
    public RelationalDataSource createRelationalDataSource() {
        return new RelationalDataSource();
    }

    /**
     * Create an instance of {@link OlapDataSource }
     * 
     */
    public OlapDataSource createOlapDataSource() {
        return new OlapDataSource();
    }

    /**
     * Create an instance of {@link ServerProperty }
     * 
     */
    public ServerProperty createServerProperty() {
        return new ServerProperty();
    }

    /**
     * Create an instance of {@link ComAssembly }
     * 
     */
    public ComAssembly createComAssembly() {
        return new ComAssembly();
    }

    /**
     * Create an instance of {@link ClrAssemblyFile }
     * 
     */
    public ClrAssemblyFile createClrAssemblyFile() {
        return new ClrAssemblyFile();
    }

    /**
     * Create an instance of {@link DataBlock }
     * 
     */
    public DataBlock createDataBlock() {
        return new DataBlock();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jaxb.xmla.Event }
     * 
     */
    public org.eclipse.daanse.xmla.model.jaxb.xmla.Event createEvent() {
        return new org.eclipse.daanse.xmla.model.jaxb.xmla.Event();
    }

    /**
     * Create an instance of {@link EventColumnID }
     * 
     */
    public EventColumnID createEventColumnID() {
        return new EventColumnID();
    }

    /**
     * Create an instance of {@link TraceFilter }
     * 
     */
    public TraceFilter createTraceFilter() {
        return new TraceFilter();
    }

    /**
     * Create an instance of {@link NotType }
     * 
     */
    public NotType createNotType() {
        return new NotType();
    }

    /**
     * Create an instance of {@link AndOrType }
     * 
     */
    public AndOrType createAndOrType() {
        return new AndOrType();
    }

    /**
     * Create an instance of {@link BoolBinop }
     * 
     */
    public BoolBinop createBoolBinop() {
        return new BoolBinop();
    }

    /**
     * Create an instance of {@link UnaryExpr }
     * 
     */
    public UnaryExpr createUnaryExpr() {
        return new UnaryExpr();
    }

    /**
     * Create an instance of {@link BooleanExpr }
     * 
     */
    public BooleanExpr createBooleanExpr() {
        return new BooleanExpr();
    }

    /**
     * Create an instance of {@link MiningModelingFlag }
     * 
     */
    public MiningModelingFlag createMiningModelingFlag() {
        return new MiningModelingFlag();
    }

    /**
     * Create an instance of {@link AlgorithmParameter }
     * 
     */
    public AlgorithmParameter createAlgorithmParameter() {
        return new AlgorithmParameter();
    }

    /**
     * Create an instance of {@link FoldingParameters }
     * 
     */
    public FoldingParameters createFoldingParameters() {
        return new FoldingParameters();
    }

    /**
     * Create an instance of {@link ReportParameter }
     * 
     */
    public ReportParameter createReportParameter() {
        return new ReportParameter();
    }

    /**
     * Create an instance of {@link ReportFormatParameter }
     * 
     */
    public ReportFormatParameter createReportFormatParameter() {
        return new ReportFormatParameter();
    }

    /**
     * Create an instance of {@link AggregationDesignAttribute }
     * 
     */
    public AggregationDesignAttribute createAggregationDesignAttribute() {
        return new AggregationDesignAttribute();
    }

    /**
     * Create an instance of {@link AggregationInstanceMeasure }
     * 
     */
    public AggregationInstanceMeasure createAggregationInstanceMeasure() {
        return new AggregationInstanceMeasure();
    }

    /**
     * Create an instance of {@link Account.Aliases }
     * 
     */
    public Account.Aliases createAccountAliases() {
        return new Account.Aliases();
    }

    /**
     * Create an instance of {@link Account.Annotations }
     * 
     */
    public Account.Annotations createAccountAnnotations() {
        return new Account.Annotations();
    }

    /**
     * Create an instance of {@link AggregationInstanceAttribute.KeyColumns }
     * 
     */
    public AggregationInstanceAttribute.KeyColumns createAggregationInstanceAttributeKeyColumns() {
        return new AggregationInstanceAttribute.KeyColumns();
    }

    /**
     * Create an instance of {@link AggregationInstanceDimension.Attributes }
     * 
     */
    public AggregationInstanceDimension.Attributes createAggregationInstanceDimensionAttributes() {
        return new AggregationInstanceDimension.Attributes();
    }

    /**
     * Create an instance of {@link AggregationInstance.Dimensions }
     * 
     */
    public AggregationInstance.Dimensions createAggregationInstanceDimensions() {
        return new AggregationInstance.Dimensions();
    }

    /**
     * Create an instance of {@link AggregationInstance.Measures }
     * 
     */
    public AggregationInstance.Measures createAggregationInstanceMeasures() {
        return new AggregationInstance.Measures();
    }

    /**
     * Create an instance of {@link AggregationInstance.Annotations }
     * 
     */
    public AggregationInstance.Annotations createAggregationInstanceAnnotations() {
        return new AggregationInstance.Annotations();
    }

    /**
     * Create an instance of {@link Partition.Annotations }
     * 
     */
    public Partition.Annotations createPartitionAnnotations() {
        return new Partition.Annotations();
    }

    /**
     * Create an instance of {@link Partition.StorageMode }
     * 
     */
    public Partition.StorageMode createPartitionStorageMode() {
        return new Partition.StorageMode();
    }

    /**
     * Create an instance of {@link Partition.CurrentStorageMode }
     * 
     */
    public Partition.CurrentStorageMode createPartitionCurrentStorageMode() {
        return new Partition.CurrentStorageMode();
    }

    /**
     * Create an instance of {@link Partition.AggregationInstances }
     * 
     */
    public Partition.AggregationInstances createPartitionAggregationInstances() {
        return new Partition.AggregationInstances();
    }

    /**
     * Create an instance of {@link AggregationAttribute.Annotations }
     * 
     */
    public AggregationAttribute.Annotations createAggregationAttributeAnnotations() {
        return new AggregationAttribute.Annotations();
    }

    /**
     * Create an instance of {@link AggregationDimension.Attributes }
     * 
     */
    public AggregationDimension.Attributes createAggregationDimensionAttributes() {
        return new AggregationDimension.Attributes();
    }

    /**
     * Create an instance of {@link AggregationDimension.Annotations }
     * 
     */
    public AggregationDimension.Annotations createAggregationDimensionAnnotations() {
        return new AggregationDimension.Annotations();
    }

    /**
     * Create an instance of {@link Aggregation.Dimensions }
     * 
     */
    public Aggregation.Dimensions createAggregationDimensions() {
        return new Aggregation.Dimensions();
    }

    /**
     * Create an instance of {@link Aggregation.Annotations }
     * 
     */
    public Aggregation.Annotations createAggregationAnnotations() {
        return new Aggregation.Annotations();
    }

    /**
     * Create an instance of {@link AggregationDesignDimension.Attributes }
     * 
     */
    public AggregationDesignDimension.Attributes createAggregationDesignDimensionAttributes() {
        return new AggregationDesignDimension.Attributes();
    }

    /**
     * Create an instance of {@link AggregationDesignDimension.Annotations }
     * 
     */
    public AggregationDesignDimension.Annotations createAggregationDesignDimensionAnnotations() {
        return new AggregationDesignDimension.Annotations();
    }

    /**
     * Create an instance of {@link AggregationDesign.Annotations }
     * 
     */
    public AggregationDesign.Annotations createAggregationDesignAnnotations() {
        return new AggregationDesign.Annotations();
    }

    /**
     * Create an instance of {@link AggregationDesign.Dimensions }
     * 
     */
    public AggregationDesign.Dimensions createAggregationDesignDimensions() {
        return new AggregationDesign.Dimensions();
    }

    /**
     * Create an instance of {@link AggregationDesign.Aggregations }
     * 
     */
    public AggregationDesign.Aggregations createAggregationDesignAggregations() {
        return new AggregationDesign.Aggregations();
    }

    /**
     * Create an instance of {@link Measure.Translations }
     * 
     */
    public Measure.Translations createMeasureTranslations() {
        return new Measure.Translations();
    }

    /**
     * Create an instance of {@link Measure.Annotations }
     * 
     */
    public Measure.Annotations createMeasureAnnotations() {
        return new Measure.Annotations();
    }

    /**
     * Create an instance of {@link MeasureGroupAttribute.KeyColumns }
     * 
     */
    public MeasureGroupAttribute.KeyColumns createMeasureGroupAttributeKeyColumns() {
        return new MeasureGroupAttribute.KeyColumns();
    }

    /**
     * Create an instance of {@link MeasureGroupAttribute.Annotations }
     * 
     */
    public MeasureGroupAttribute.Annotations createMeasureGroupAttributeAnnotations() {
        return new MeasureGroupAttribute.Annotations();
    }

    /**
     * Create an instance of {@link DataMiningMeasureGroupDimension.Annotations }
     * 
     */
    public DataMiningMeasureGroupDimension.Annotations createDataMiningMeasureGroupDimensionAnnotations() {
        return new DataMiningMeasureGroupDimension.Annotations();
    }

    /**
     * Create an instance of {@link DegenerateMeasureGroupDimension.Annotations }
     * 
     */
    public DegenerateMeasureGroupDimension.Annotations createDegenerateMeasureGroupDimensionAnnotations() {
        return new DegenerateMeasureGroupDimension.Annotations();
    }

    /**
     * Create an instance of {@link ReferenceMeasureGroupDimension.Annotations }
     * 
     */
    public ReferenceMeasureGroupDimension.Annotations createReferenceMeasureGroupDimensionAnnotations() {
        return new ReferenceMeasureGroupDimension.Annotations();
    }

    /**
     * Create an instance of {@link RegularMeasureGroupDimension.Annotations }
     * 
     */
    public RegularMeasureGroupDimension.Annotations createRegularMeasureGroupDimensionAnnotations() {
        return new RegularMeasureGroupDimension.Annotations();
    }

    /**
     * Create an instance of {@link RegularMeasureGroupDimension.Attributes }
     * 
     */
    public RegularMeasureGroupDimension.Attributes createRegularMeasureGroupDimensionAttributes() {
        return new RegularMeasureGroupDimension.Attributes();
    }

    /**
     * Create an instance of {@link ManyToManyMeasureGroupDimension.Annotations }
     * 
     */
    public ManyToManyMeasureGroupDimension.Annotations createManyToManyMeasureGroupDimensionAnnotations() {
        return new ManyToManyMeasureGroupDimension.Annotations();
    }

    /**
     * Create an instance of {@link MeasureGroup.Annotations }
     * 
     */
    public MeasureGroup.Annotations createMeasureGroupAnnotations() {
        return new MeasureGroup.Annotations();
    }

    /**
     * Create an instance of {@link MeasureGroup.Translations }
     * 
     */
    public MeasureGroup.Translations createMeasureGroupTranslations() {
        return new MeasureGroup.Translations();
    }

    /**
     * Create an instance of {@link MeasureGroup.Measures }
     * 
     */
    public MeasureGroup.Measures createMeasureGroupMeasures() {
        return new MeasureGroup.Measures();
    }

    /**
     * Create an instance of {@link MeasureGroup.StorageMode }
     * 
     */
    public MeasureGroup.StorageMode createMeasureGroupStorageMode() {
        return new MeasureGroup.StorageMode();
    }

    /**
     * Create an instance of {@link MeasureGroup.Dimensions }
     * 
     */
    public MeasureGroup.Dimensions createMeasureGroupDimensions() {
        return new MeasureGroup.Dimensions();
    }

    /**
     * Create an instance of {@link MeasureGroup.Partitions }
     * 
     */
    public MeasureGroup.Partitions createMeasureGroupPartitions() {
        return new MeasureGroup.Partitions();
    }

    /**
     * Create an instance of {@link MeasureGroup.AggregationDesigns }
     * 
     */
    public MeasureGroup.AggregationDesigns createMeasureGroupAggregationDesigns() {
        return new MeasureGroup.AggregationDesigns();
    }

    /**
     * Create an instance of {@link PerspectiveAction.Annotations }
     * 
     */
    public PerspectiveAction.Annotations createPerspectiveActionAnnotations() {
        return new PerspectiveAction.Annotations();
    }

    /**
     * Create an instance of {@link PerspectiveKpi.Annotations }
     * 
     */
    public PerspectiveKpi.Annotations createPerspectiveKpiAnnotations() {
        return new PerspectiveKpi.Annotations();
    }

    /**
     * Create an instance of {@link PerspectiveCalculation.Annotations }
     * 
     */
    public PerspectiveCalculation.Annotations createPerspectiveCalculationAnnotations() {
        return new PerspectiveCalculation.Annotations();
    }

    /**
     * Create an instance of {@link PerspectiveMeasure.Annotations }
     * 
     */
    public PerspectiveMeasure.Annotations createPerspectiveMeasureAnnotations() {
        return new PerspectiveMeasure.Annotations();
    }

    /**
     * Create an instance of {@link PerspectiveMeasureGroup.Measures }
     * 
     */
    public PerspectiveMeasureGroup.Measures createPerspectiveMeasureGroupMeasures() {
        return new PerspectiveMeasureGroup.Measures();
    }

    /**
     * Create an instance of {@link PerspectiveMeasureGroup.Annotations }
     * 
     */
    public PerspectiveMeasureGroup.Annotations createPerspectiveMeasureGroupAnnotations() {
        return new PerspectiveMeasureGroup.Annotations();
    }

    /**
     * Create an instance of {@link PerspectiveHierarchy.Annotations }
     * 
     */
    public PerspectiveHierarchy.Annotations createPerspectiveHierarchyAnnotations() {
        return new PerspectiveHierarchy.Annotations();
    }

    /**
     * Create an instance of {@link PerspectiveAttribute.Annotations }
     * 
     */
    public PerspectiveAttribute.Annotations createPerspectiveAttributeAnnotations() {
        return new PerspectiveAttribute.Annotations();
    }

    /**
     * Create an instance of {@link PerspectiveDimension.Attributes }
     * 
     */
    public PerspectiveDimension.Attributes createPerspectiveDimensionAttributes() {
        return new PerspectiveDimension.Attributes();
    }

    /**
     * Create an instance of {@link PerspectiveDimension.Hierarchies }
     * 
     */
    public PerspectiveDimension.Hierarchies createPerspectiveDimensionHierarchies() {
        return new PerspectiveDimension.Hierarchies();
    }

    /**
     * Create an instance of {@link PerspectiveDimension.Annotations }
     * 
     */
    public PerspectiveDimension.Annotations createPerspectiveDimensionAnnotations() {
        return new PerspectiveDimension.Annotations();
    }

    /**
     * Create an instance of {@link Perspective.Annotations }
     * 
     */
    public Perspective.Annotations createPerspectiveAnnotations() {
        return new Perspective.Annotations();
    }

    /**
     * Create an instance of {@link Perspective.Translations }
     * 
     */
    public Perspective.Translations createPerspectiveTranslations() {
        return new Perspective.Translations();
    }

    /**
     * Create an instance of {@link Perspective.Dimensions }
     * 
     */
    public Perspective.Dimensions createPerspectiveDimensions() {
        return new Perspective.Dimensions();
    }

    /**
     * Create an instance of {@link Perspective.MeasureGroups }
     * 
     */
    public Perspective.MeasureGroups createPerspectiveMeasureGroups() {
        return new Perspective.MeasureGroups();
    }

    /**
     * Create an instance of {@link Perspective.Calculations }
     * 
     */
    public Perspective.Calculations createPerspectiveCalculations() {
        return new Perspective.Calculations();
    }

    /**
     * Create an instance of {@link Perspective.Kpis }
     * 
     */
    public Perspective.Kpis createPerspectiveKpis() {
        return new Perspective.Kpis();
    }

    /**
     * Create an instance of {@link Perspective.Actions }
     * 
     */
    public Perspective.Actions createPerspectiveActions() {
        return new Perspective.Actions();
    }

    /**
     * Create an instance of {@link CalculationProperty.Translations }
     * 
     */
    public CalculationProperty.Translations createCalculationPropertyTranslations() {
        return new CalculationProperty.Translations();
    }

    /**
     * Create an instance of {@link MdxScript.Annotations }
     * 
     */
    public MdxScript.Annotations createMdxScriptAnnotations() {
        return new MdxScript.Annotations();
    }

    /**
     * Create an instance of {@link MdxScript.Commands }
     * 
     */
    public MdxScript.Commands createMdxScriptCommands() {
        return new MdxScript.Commands();
    }

    /**
     * Create an instance of {@link MdxScript.CalculationProperties }
     * 
     */
    public MdxScript.CalculationProperties createMdxScriptCalculationProperties() {
        return new MdxScript.CalculationProperties();
    }

    /**
     * Create an instance of {@link DrillThroughAction.Translations }
     * 
     */
    public DrillThroughAction.Translations createDrillThroughActionTranslations() {
        return new DrillThroughAction.Translations();
    }

    /**
     * Create an instance of {@link DrillThroughAction.Annotations }
     * 
     */
    public DrillThroughAction.Annotations createDrillThroughActionAnnotations() {
        return new DrillThroughAction.Annotations();
    }

    /**
     * Create an instance of {@link DrillThroughAction.Columns }
     * 
     */
    public DrillThroughAction.Columns createDrillThroughActionColumns() {
        return new DrillThroughAction.Columns();
    }

    /**
     * Create an instance of {@link ReportAction.Translations }
     * 
     */
    public ReportAction.Translations createReportActionTranslations() {
        return new ReportAction.Translations();
    }

    /**
     * Create an instance of {@link ReportAction.Annotations }
     * 
     */
    public ReportAction.Annotations createReportActionAnnotations() {
        return new ReportAction.Annotations();
    }

    /**
     * Create an instance of {@link ReportAction.ReportParameters }
     * 
     */
    public ReportAction.ReportParameters createReportActionReportParameters() {
        return new ReportAction.ReportParameters();
    }

    /**
     * Create an instance of {@link ReportAction.ReportFormatParameters }
     * 
     */
    public ReportAction.ReportFormatParameters createReportActionReportFormatParameters() {
        return new ReportAction.ReportFormatParameters();
    }

    /**
     * Create an instance of {@link StandardAction.Translations }
     * 
     */
    public StandardAction.Translations createStandardActionTranslations() {
        return new StandardAction.Translations();
    }

    /**
     * Create an instance of {@link StandardAction.Annotations }
     * 
     */
    public StandardAction.Annotations createStandardActionAnnotations() {
        return new StandardAction.Annotations();
    }

    /**
     * Create an instance of {@link Kpi.Translations }
     * 
     */
    public Kpi.Translations createKpiTranslations() {
        return new Kpi.Translations();
    }

    /**
     * Create an instance of {@link Kpi.Annotations }
     * 
     */
    public Kpi.Annotations createKpiAnnotations() {
        return new Kpi.Annotations();
    }

    /**
     * Create an instance of {@link CubeHierarchy.Annotations }
     * 
     */
    public CubeHierarchy.Annotations createCubeHierarchyAnnotations() {
        return new CubeHierarchy.Annotations();
    }

    /**
     * Create an instance of {@link CubeAttribute.Annotations }
     * 
     */
    public CubeAttribute.Annotations createCubeAttributeAnnotations() {
        return new CubeAttribute.Annotations();
    }

    /**
     * Create an instance of {@link CubeDimension.Translations }
     * 
     */
    public CubeDimension.Translations createCubeDimensionTranslations() {
        return new CubeDimension.Translations();
    }

    /**
     * Create an instance of {@link CubeDimension.Attributes }
     * 
     */
    public CubeDimension.Attributes createCubeDimensionAttributes() {
        return new CubeDimension.Attributes();
    }

    /**
     * Create an instance of {@link CubeDimension.Hierarchies }
     * 
     */
    public CubeDimension.Hierarchies createCubeDimensionHierarchies() {
        return new CubeDimension.Hierarchies();
    }

    /**
     * Create an instance of {@link CubeDimension.Annotations }
     * 
     */
    public CubeDimension.Annotations createCubeDimensionAnnotations() {
        return new CubeDimension.Annotations();
    }

    /**
     * Create an instance of {@link Cube.Annotations }
     * 
     */
    public Cube.Annotations createCubeAnnotations() {
        return new Cube.Annotations();
    }

    /**
     * Create an instance of {@link Cube.Translations }
     * 
     */
    public Cube.Translations createCubeTranslations() {
        return new Cube.Translations();
    }

    /**
     * Create an instance of {@link Cube.Dimensions }
     * 
     */
    public Cube.Dimensions createCubeDimensions() {
        return new Cube.Dimensions();
    }

    /**
     * Create an instance of {@link Cube.CubePermissions }
     * 
     */
    public Cube.CubePermissions createCubeCubePermissions() {
        return new Cube.CubePermissions();
    }

    /**
     * Create an instance of {@link Cube.MdxScripts }
     * 
     */
    public Cube.MdxScripts createCubeMdxScripts() {
        return new Cube.MdxScripts();
    }

    /**
     * Create an instance of {@link Cube.Perspectives }
     * 
     */
    public Cube.Perspectives createCubePerspectives() {
        return new Cube.Perspectives();
    }

    /**
     * Create an instance of {@link Cube.MeasureGroups }
     * 
     */
    public Cube.MeasureGroups createCubeMeasureGroups() {
        return new Cube.MeasureGroups();
    }

    /**
     * Create an instance of {@link Cube.StorageMode }
     * 
     */
    public Cube.StorageMode createCubeStorageMode() {
        return new Cube.StorageMode();
    }

    /**
     * Create an instance of {@link Cube.Kpis }
     * 
     */
    public Cube.Kpis createCubeKpis() {
        return new Cube.Kpis();
    }

    /**
     * Create an instance of {@link Cube.Actions }
     * 
     */
    public Cube.Actions createCubeActions() {
        return new Cube.Actions();
    }

    /**
     * Create an instance of {@link MiningModelColumn.Translations }
     * 
     */
    public MiningModelColumn.Translations createMiningModelColumnTranslations() {
        return new MiningModelColumn.Translations();
    }

    /**
     * Create an instance of {@link MiningModelColumn.Columns }
     * 
     */
    public MiningModelColumn.Columns createMiningModelColumnColumns() {
        return new MiningModelColumn.Columns();
    }

    /**
     * Create an instance of {@link MiningModelColumn.ModelingFlags }
     * 
     */
    public MiningModelColumn.ModelingFlags createMiningModelColumnModelingFlags() {
        return new MiningModelColumn.ModelingFlags();
    }

    /**
     * Create an instance of {@link MiningModelColumn.Annotations }
     * 
     */
    public MiningModelColumn.Annotations createMiningModelColumnAnnotations() {
        return new MiningModelColumn.Annotations();
    }

    /**
     * Create an instance of {@link MiningModel.Annotations }
     * 
     */
    public MiningModel.Annotations createMiningModelAnnotations() {
        return new MiningModel.Annotations();
    }

    /**
     * Create an instance of {@link MiningModel.AlgorithmParameters }
     * 
     */
    public MiningModel.AlgorithmParameters createMiningModelAlgorithmParameters() {
        return new MiningModel.AlgorithmParameters();
    }

    /**
     * Create an instance of {@link MiningModel.Translations }
     * 
     */
    public MiningModel.Translations createMiningModelTranslations() {
        return new MiningModel.Translations();
    }

    /**
     * Create an instance of {@link MiningModel.Columns }
     * 
     */
    public MiningModel.Columns createMiningModelColumns() {
        return new MiningModel.Columns();
    }

    /**
     * Create an instance of {@link MiningModel.MiningModelPermissions }
     * 
     */
    public MiningModel.MiningModelPermissions createMiningModelMiningModelPermissions() {
        return new MiningModel.MiningModelPermissions();
    }

    /**
     * Create an instance of {@link TableMiningStructureColumn.ForeignKeyColumns }
     * 
     */
    public TableMiningStructureColumn.ForeignKeyColumns createTableMiningStructureColumnForeignKeyColumns() {
        return new TableMiningStructureColumn.ForeignKeyColumns();
    }

    /**
     * Create an instance of {@link TableMiningStructureColumn.Columns }
     * 
     */
    public TableMiningStructureColumn.Columns createTableMiningStructureColumnColumns() {
        return new TableMiningStructureColumn.Columns();
    }

    /**
     * Create an instance of {@link TableMiningStructureColumn.Translations }
     * 
     */
    public TableMiningStructureColumn.Translations createTableMiningStructureColumnTranslations() {
        return new TableMiningStructureColumn.Translations();
    }

    /**
     * Create an instance of {@link ScalarMiningStructureColumn.Annotations }
     * 
     */
    public ScalarMiningStructureColumn.Annotations createScalarMiningStructureColumnAnnotations() {
        return new ScalarMiningStructureColumn.Annotations();
    }

    /**
     * Create an instance of {@link ScalarMiningStructureColumn.ModelingFlags }
     * 
     */
    public ScalarMiningStructureColumn.ModelingFlags createScalarMiningStructureColumnModelingFlags() {
        return new ScalarMiningStructureColumn.ModelingFlags();
    }

    /**
     * Create an instance of {@link ScalarMiningStructureColumn.ClassifiedColumns }
     * 
     */
    public ScalarMiningStructureColumn.ClassifiedColumns createScalarMiningStructureColumnClassifiedColumns() {
        return new ScalarMiningStructureColumn.ClassifiedColumns();
    }

    /**
     * Create an instance of {@link ScalarMiningStructureColumn.KeyColumns }
     * 
     */
    public ScalarMiningStructureColumn.KeyColumns createScalarMiningStructureColumnKeyColumns() {
        return new ScalarMiningStructureColumn.KeyColumns();
    }

    /**
     * Create an instance of {@link ScalarMiningStructureColumn.Translations }
     * 
     */
    public ScalarMiningStructureColumn.Translations createScalarMiningStructureColumnTranslations() {
        return new ScalarMiningStructureColumn.Translations();
    }

    /**
     * Create an instance of {@link MiningStructure.Annotations }
     * 
     */
    public MiningStructure.Annotations createMiningStructureAnnotations() {
        return new MiningStructure.Annotations();
    }

    /**
     * Create an instance of {@link MiningStructure.Translations }
     * 
     */
    public MiningStructure.Translations createMiningStructureTranslations() {
        return new MiningStructure.Translations();
    }

    /**
     * Create an instance of {@link MiningStructure.Columns }
     * 
     */
    public MiningStructure.Columns createMiningStructureColumns() {
        return new MiningStructure.Columns();
    }

    /**
     * Create an instance of {@link MiningStructure.MiningStructurePermissions }
     * 
     */
    public MiningStructure.MiningStructurePermissions createMiningStructureMiningStructurePermissions() {
        return new MiningStructure.MiningStructurePermissions();
    }

    /**
     * Create an instance of {@link MiningStructure.MiningModels }
     * 
     */
    public MiningStructure.MiningModels createMiningStructureMiningModels() {
        return new MiningStructure.MiningModels();
    }

    /**
     * Create an instance of {@link Database.Annotations }
     * 
     */
    public Database.Annotations createDatabaseAnnotations() {
        return new Database.Annotations();
    }

    /**
     * Create an instance of {@link Database.Accounts }
     * 
     */
    public Database.Accounts createDatabaseAccounts() {
        return new Database.Accounts();
    }

    /**
     * Create an instance of {@link Database.DataSources }
     * 
     */
    public Database.DataSources createDatabaseDataSources() {
        return new Database.DataSources();
    }

    /**
     * Create an instance of {@link Database.DataSourceViews }
     * 
     */
    public Database.DataSourceViews createDatabaseDataSourceViews() {
        return new Database.DataSourceViews();
    }

    /**
     * Create an instance of {@link Database.Dimensions }
     * 
     */
    public Database.Dimensions createDatabaseDimensions() {
        return new Database.Dimensions();
    }

    /**
     * Create an instance of {@link Database.Cubes }
     * 
     */
    public Database.Cubes createDatabaseCubes() {
        return new Database.Cubes();
    }

    /**
     * Create an instance of {@link Database.MiningStructures }
     * 
     */
    public Database.MiningStructures createDatabaseMiningStructures() {
        return new Database.MiningStructures();
    }

    /**
     * Create an instance of {@link Database.Roles }
     * 
     */
    public Database.Roles createDatabaseRoles() {
        return new Database.Roles();
    }

    /**
     * Create an instance of {@link Database.Assemblies }
     * 
     */
    public Database.Assemblies createDatabaseAssemblies() {
        return new Database.Assemblies();
    }

    /**
     * Create an instance of {@link Database.DatabasePermissions }
     * 
     */
    public Database.DatabasePermissions createDatabaseDatabasePermissions() {
        return new Database.DatabasePermissions();
    }

    /**
     * Create an instance of {@link Database.Translations }
     * 
     */
    public Database.Translations createDatabaseTranslations() {
        return new Database.Translations();
    }

    /**
     * Create an instance of {@link PredLeaf.Comparator }
     * 
     */
    public PredLeaf.Comparator createPredLeafComparator() {
        return new PredLeaf.Comparator();
    }

    /**
     * Create an instance of {@link PredLeaf.Event }
     * 
     */
    public PredLeaf.Event createPredLeafEvent() {
        return new PredLeaf.Event();
    }

    /**
     * Create an instance of {@link PredLeaf.Global }
     * 
     */
    public PredLeaf.Global createPredLeafGlobal() {
        return new PredLeaf.Global();
    }

    /**
     * Create an instance of {@link Trace.Annotations }
     * 
     */
    public Trace.Annotations createTraceAnnotations() {
        return new Trace.Annotations();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jaxb.xmla.Assembly.Annotations }
     * 
     */
    public org.eclipse.daanse.xmla.model.jaxb.xmla.Assembly.Annotations createAssemblyAnnotations() {
        return new org.eclipse.daanse.xmla.model.jaxb.xmla.Assembly.Annotations();
    }

    /**
     * Create an instance of {@link ClrAssembly.Files }
     * 
     */
    public ClrAssembly.Files createClrAssemblyFiles() {
        return new ClrAssembly.Files();
    }

    /**
     * Create an instance of {@link Server.Annotations }
     * 
     */
    public Server.Annotations createServerAnnotations() {
        return new Server.Annotations();
    }

    /**
     * Create an instance of {@link Server.Databases }
     * 
     */
    public Server.Databases createServerDatabases() {
        return new Server.Databases();
    }

    /**
     * Create an instance of {@link Server.Assemblies }
     * 
     */
    public Server.Assemblies createServerAssemblies() {
        return new Server.Assemblies();
    }

    /**
     * Create an instance of {@link Server.Traces }
     * 
     */
    public Server.Traces createServerTraces() {
        return new Server.Traces();
    }

    /**
     * Create an instance of {@link Server.Roles }
     * 
     */
    public Server.Roles createServerRoles() {
        return new Server.Roles();
    }

    /**
     * Create an instance of {@link Server.ServerProperties }
     * 
     */
    public Server.ServerProperties createServerServerProperties() {
        return new Server.ServerProperties();
    }

    /**
     * Create an instance of {@link Level.Translations }
     * 
     */
    public Level.Translations createLevelTranslations() {
        return new Level.Translations();
    }

    /**
     * Create an instance of {@link Level.Annotations }
     * 
     */
    public Level.Annotations createLevelAnnotations() {
        return new Level.Annotations();
    }

    /**
     * Create an instance of {@link Hierarchy.Translations }
     * 
     */
    public Hierarchy.Translations createHierarchyTranslations() {
        return new Hierarchy.Translations();
    }

    /**
     * Create an instance of {@link Hierarchy.AllMemberTranslations }
     * 
     */
    public Hierarchy.AllMemberTranslations createHierarchyAllMemberTranslations() {
        return new Hierarchy.AllMemberTranslations();
    }

    /**
     * Create an instance of {@link Hierarchy.Levels }
     * 
     */
    public Hierarchy.Levels createHierarchyLevels() {
        return new Hierarchy.Levels();
    }

    /**
     * Create an instance of {@link Hierarchy.Annotations }
     * 
     */
    public Hierarchy.Annotations createHierarchyAnnotations() {
        return new Hierarchy.Annotations();
    }

    /**
     * Create an instance of {@link AttributeRelationship.Annotations }
     * 
     */
    public AttributeRelationship.Annotations createAttributeRelationshipAnnotations() {
        return new AttributeRelationship.Annotations();
    }

    /**
     * Create an instance of {@link AttributeRelationship.Translations }
     * 
     */
    public AttributeRelationship.Translations createAttributeRelationshipTranslations() {
        return new AttributeRelationship.Translations();
    }

    /**
     * Create an instance of {@link AttributeTranslation.Annotations }
     * 
     */
    public AttributeTranslation.Annotations createAttributeTranslationAnnotations() {
        return new AttributeTranslation.Annotations();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jaxb.xmla.Translation.Annotations }
     * 
     */
    public org.eclipse.daanse.xmla.model.jaxb.xmla.Translation.Annotations createTranslationAnnotations() {
        return new org.eclipse.daanse.xmla.model.jaxb.xmla.Translation.Annotations();
    }

    /**
     * Create an instance of {@link DataItem.Annotations }
     * 
     */
    public DataItem.Annotations createDataItemAnnotations() {
        return new DataItem.Annotations();
    }

    /**
     * Create an instance of {@link DimensionAttribute.Type }
     * 
     */
    public DimensionAttribute.Type createDimensionAttributeType() {
        return new DimensionAttribute.Type();
    }

    /**
     * Create an instance of {@link DimensionAttribute.KeyColumns }
     * 
     */
    public DimensionAttribute.KeyColumns createDimensionAttributeKeyColumns() {
        return new DimensionAttribute.KeyColumns();
    }

    /**
     * Create an instance of {@link DimensionAttribute.Translations }
     * 
     */
    public DimensionAttribute.Translations createDimensionAttributeTranslations() {
        return new DimensionAttribute.Translations();
    }

    /**
     * Create an instance of {@link DimensionAttribute.AttributeRelationships }
     * 
     */
    public DimensionAttribute.AttributeRelationships createDimensionAttributeAttributeRelationships() {
        return new DimensionAttribute.AttributeRelationships();
    }

    /**
     * Create an instance of {@link DimensionAttribute.NamingTemplateTranslations }
     * 
     */
    public DimensionAttribute.NamingTemplateTranslations createDimensionAttributeNamingTemplateTranslations() {
        return new DimensionAttribute.NamingTemplateTranslations();
    }

    /**
     * Create an instance of {@link DimensionAttribute.Annotations }
     * 
     */
    public DimensionAttribute.Annotations createDimensionAttributeAnnotations() {
        return new DimensionAttribute.Annotations();
    }

    /**
     * Create an instance of {@link Dimension.Annotations }
     * 
     */
    public Dimension.Annotations createDimensionAnnotations() {
        return new Dimension.Annotations();
    }

    /**
     * Create an instance of {@link Dimension.UnknownMember }
     * 
     */
    public Dimension.UnknownMember createDimensionUnknownMember() {
        return new Dimension.UnknownMember();
    }

    /**
     * Create an instance of {@link Dimension.DimensionPermissions }
     * 
     */
    public Dimension.DimensionPermissions createDimensionDimensionPermissions() {
        return new Dimension.DimensionPermissions();
    }

    /**
     * Create an instance of {@link Dimension.UnknownMemberTranslations }
     * 
     */
    public Dimension.UnknownMemberTranslations createDimensionUnknownMemberTranslations() {
        return new Dimension.UnknownMemberTranslations();
    }

    /**
     * Create an instance of {@link Dimension.CurrentStorageMode }
     * 
     */
    public Dimension.CurrentStorageMode createDimensionCurrentStorageMode() {
        return new Dimension.CurrentStorageMode();
    }

    /**
     * Create an instance of {@link Dimension.Translations }
     * 
     */
    public Dimension.Translations createDimensionTranslations() {
        return new Dimension.Translations();
    }

    /**
     * Create an instance of {@link Dimension.Attributes }
     * 
     */
    public Dimension.Attributes createDimensionAttributes() {
        return new Dimension.Attributes();
    }

    /**
     * Create an instance of {@link Dimension.AttributeAllMemberTranslations }
     * 
     */
    public Dimension.AttributeAllMemberTranslations createDimensionAttributeAllMemberTranslations() {
        return new Dimension.AttributeAllMemberTranslations();
    }

    /**
     * Create an instance of {@link Dimension.Hierarchies }
     * 
     */
    public Dimension.Hierarchies createDimensionHierarchies() {
        return new Dimension.Hierarchies();
    }

    /**
     * Create an instance of {@link PushedDataSource.Root }
     * 
     */
    public PushedDataSource.Root createPushedDataSourceRoot() {
        return new PushedDataSource.Root();
    }

    /**
     * Create an instance of {@link PushedDataSource.EndOfData }
     * 
     */
    public PushedDataSource.EndOfData createPushedDataSourceEndOfData() {
        return new PushedDataSource.EndOfData();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jaxb.xmla.DataSource.Annotations }
     * 
     */
    public org.eclipse.daanse.xmla.model.jaxb.xmla.DataSource.Annotations createDataSourceAnnotations() {
        return new org.eclipse.daanse.xmla.model.jaxb.xmla.DataSource.Annotations();
    }

    /**
     * Create an instance of {@link org.eclipse.daanse.xmla.model.jaxb.xmla.DataSource.DataSourcePermissions }
     * 
     */
    public org.eclipse.daanse.xmla.model.jaxb.xmla.DataSource.DataSourcePermissions createDataSourceDataSourcePermissions() {
        return new org.eclipse.daanse.xmla.model.jaxb.xmla.DataSource.DataSourcePermissions();
    }

    /**
     * Create an instance of {@link TraceColumns.Data.Column }
     * 
     */
    public TraceColumns.Data.Column createTraceColumnsDataColumn() {
        return new TraceColumns.Data.Column();
    }

    /**
     * Create an instance of {@link EventColumn.EventColumnSubclassList.EventColumnSubclass }
     * 
     */
    public EventColumn.EventColumnSubclassList.EventColumnSubclass createEventColumnEventColumnSubclassListEventColumnSubclass() {
        return new EventColumn.EventColumnSubclassList.EventColumnSubclass();
    }

    /**
     * Create an instance of {@link TraceEvent.EventColumnList }
     * 
     */
    public TraceEvent.EventColumnList createTraceEventEventColumnList() {
        return new TraceEvent.EventColumnList();
    }

    /**
     * Create an instance of {@link TraceEventCategories.Data.EventCategory.EventList }
     * 
     */
    public TraceEventCategories.Data.EventCategory.EventList createTraceEventCategoriesDataEventCategoryEventList() {
        return new TraceEventCategories.Data.EventCategory.EventList();
    }

    /**
     * Create an instance of {@link TraceDefinitionProviderInfo.Data.Version }
     * 
     */
    public TraceDefinitionProviderInfo.Data.Version createTraceDefinitionProviderInfoDataVersion() {
        return new TraceDefinitionProviderInfo.Data.Version();
    }

    /**
     * Create an instance of {@link Role.Annotations }
     * 
     */
    public Role.Annotations createRoleAnnotations() {
        return new Role.Annotations();
    }

    /**
     * Create an instance of {@link Role.Members }
     * 
     */
    public Role.Members createRoleMembers() {
        return new Role.Members();
    }

    /**
     * Create an instance of {@link Permission.Annotations }
     * 
     */
    public Permission.Annotations createPermissionAnnotations() {
        return new Permission.Annotations();
    }

    /**
     * Create an instance of {@link CubePermission.DimensionPermissions }
     * 
     */
    public CubePermission.DimensionPermissions createCubePermissionDimensionPermissions() {
        return new CubePermission.DimensionPermissions();
    }

    /**
     * Create an instance of {@link CubePermission.CellPermissions }
     * 
     */
    public CubePermission.CellPermissions createCubePermissionCellPermissions() {
        return new CubePermission.CellPermissions();
    }

    /**
     * Create an instance of {@link DimensionPermission.AttributePermissions }
     * 
     */
    public DimensionPermission.AttributePermissions createDimensionPermissionAttributePermissions() {
        return new DimensionPermission.AttributePermissions();
    }

    /**
     * Create an instance of {@link CellPermission.Annotations }
     * 
     */
    public CellPermission.Annotations createCellPermissionAnnotations() {
        return new CellPermission.Annotations();
    }

    /**
     * Create an instance of {@link AttributePermission.Annotations }
     * 
     */
    public AttributePermission.Annotations createAttributePermissionAnnotations() {
        return new AttributePermission.Annotations();
    }

    /**
     * Create an instance of {@link CubeDimensionPermission.AttributePermissions }
     * 
     */
    public CubeDimensionPermission.AttributePermissions createCubeDimensionPermissionAttributePermissions() {
        return new CubeDimensionPermission.AttributePermissions();
    }

    /**
     * Create an instance of {@link CubeDimensionPermission.Annotations }
     * 
     */
    public CubeDimensionPermission.Annotations createCubeDimensionPermissionAnnotations() {
        return new CubeDimensionPermission.Annotations();
    }

    /**
     * Create an instance of {@link ProactiveCachingIncrementalProcessingBinding.IncrementalProcessingNotifications }
     * 
     */
    public ProactiveCachingIncrementalProcessingBinding.IncrementalProcessingNotifications createProactiveCachingIncrementalProcessingBindingIncrementalProcessingNotifications() {
        return new ProactiveCachingIncrementalProcessingBinding.IncrementalProcessingNotifications();
    }

    /**
     * Create an instance of {@link ProactiveCachingQueryBinding.QueryNotifications }
     * 
     */
    public ProactiveCachingQueryBinding.QueryNotifications createProactiveCachingQueryBindingQueryNotifications() {
        return new ProactiveCachingQueryBinding.QueryNotifications();
    }

    /**
     * Create an instance of {@link ProactiveCachingTablesBinding.TableNotifications }
     * 
     */
    public ProactiveCachingTablesBinding.TableNotifications createProactiveCachingTablesBindingTableNotifications() {
        return new ProactiveCachingTablesBinding.TableNotifications();
    }

    /**
     * Create an instance of {@link CubeAttributeBinding.Ordinal }
     * 
     */
    public CubeAttributeBinding.Ordinal createCubeAttributeBindingOrdinal() {
        return new CubeAttributeBinding.Ordinal();
    }

    /**
     * Create an instance of {@link Group.Members }
     * 
     */
    public Group.Members createGroupMembers() {
        return new Group.Members();
    }

    /**
     * Create an instance of {@link UserDefinedGroupBinding.Groups }
     * 
     */
    public UserDefinedGroupBinding.Groups createUserDefinedGroupBindingGroups() {
        return new UserDefinedGroupBinding.Groups();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.NameColumn }
     * 
     */
    public OutOfLineBinding.NameColumn createOutOfLineBindingNameColumn() {
        return new OutOfLineBinding.NameColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.SkippedLevelsColumn }
     * 
     */
    public OutOfLineBinding.SkippedLevelsColumn createOutOfLineBindingSkippedLevelsColumn() {
        return new OutOfLineBinding.SkippedLevelsColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.CustomRollupColumn }
     * 
     */
    public OutOfLineBinding.CustomRollupColumn createOutOfLineBindingCustomRollupColumn() {
        return new OutOfLineBinding.CustomRollupColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.CustomRollupPropertiesColumn }
     * 
     */
    public OutOfLineBinding.CustomRollupPropertiesColumn createOutOfLineBindingCustomRollupPropertiesColumn() {
        return new OutOfLineBinding.CustomRollupPropertiesColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.ValueColumn }
     * 
     */
    public OutOfLineBinding.ValueColumn createOutOfLineBindingValueColumn() {
        return new OutOfLineBinding.ValueColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.UnaryOperatorColumn }
     * 
     */
    public OutOfLineBinding.UnaryOperatorColumn createOutOfLineBindingUnaryOperatorColumn() {
        return new OutOfLineBinding.UnaryOperatorColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.Translations.Translation }
     * 
     */
    public OutOfLineBinding.Translations.Translation createOutOfLineBindingTranslationsTranslation() {
        return new OutOfLineBinding.Translations.Translation();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn }
     * 
     */
    public OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn createOutOfLineBindingForeignKeyColumnsForeignKeyColumn() {
        return new OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn();
    }

    /**
     * Create an instance of {@link OutOfLineBinding.KeyColumns.KeyColumn }
     * 
     */
    public OutOfLineBinding.KeyColumns.KeyColumn createOutOfLineBindingKeyColumnsKeyColumn() {
        return new OutOfLineBinding.KeyColumns.KeyColumn();
    }

    /**
     * Create an instance of {@link CloneDatabase.Object }
     * 
     */
    public CloneDatabase.Object createCloneDatabaseObject() {
        return new CloneDatabase.Object();
    }

    /**
     * Create an instance of {@link CloneDatabase.Target }
     * 
     */
    public CloneDatabase.Target createCloneDatabaseTarget() {
        return new CloneDatabase.Target();
    }

    /**
     * Create an instance of {@link ImageLoad.Data }
     * 
     */
    public ImageLoad.Data createImageLoadData() {
        return new ImageLoad.Data();
    }

    /**
     * Create an instance of {@link Batch.Parallel }
     * 
     */
    public Batch.Parallel createBatchParallel() {
        return new Batch.Parallel();
    }

    /**
     * Create an instance of {@link NotifyTableChange.TableNotifications }
     * 
     */
    public NotifyTableChange.TableNotifications createNotifyTableChangeTableNotifications() {
        return new NotifyTableChange.TableNotifications();
    }

    /**
     * Create an instance of {@link WhereAttribute.Keys }
     * 
     */
    public WhereAttribute.Keys createWhereAttributeKeys() {
        return new WhereAttribute.Keys();
    }

    /**
     * Create an instance of {@link Update.Attributes }
     * 
     */
    public Update.Attributes createUpdateAttributes() {
        return new Update.Attributes();
    }

    /**
     * Create an instance of {@link AttributeInsertUpdate.Keys }
     * 
     */
    public AttributeInsertUpdate.Keys createAttributeInsertUpdateKeys() {
        return new AttributeInsertUpdate.Keys();
    }

    /**
     * Create an instance of {@link AttributeInsertUpdate.Translations }
     * 
     */
    public AttributeInsertUpdate.Translations createAttributeInsertUpdateTranslations() {
        return new AttributeInsertUpdate.Translations();
    }

    /**
     * Create an instance of {@link Insert.Attributes }
     * 
     */
    public Insert.Attributes createInsertAttributes() {
        return new Insert.Attributes();
    }

    /**
     * Create an instance of {@link Synchronize.Locations }
     * 
     */
    public Synchronize.Locations createSynchronizeLocations() {
        return new Synchronize.Locations();
    }

    /**
     * Create an instance of {@link Location.Folders }
     * 
     */
    public Location.Folders createLocationFolders() {
        return new Location.Folders();
    }

    /**
     * Create an instance of {@link Restore.Locations }
     * 
     */
    public Restore.Locations createRestoreLocations() {
        return new Restore.Locations();
    }

    /**
     * Create an instance of {@link Backup.Locations }
     * 
     */
    public Backup.Locations createBackupLocations() {
        return new Backup.Locations();
    }

    /**
     * Create an instance of {@link DesignAggregations.Queries }
     * 
     */
    public DesignAggregations.Queries createDesignAggregationsQueries() {
        return new DesignAggregations.Queries();
    }

    /**
     * Create an instance of {@link MergePartitions.Sources }
     * 
     */
    public MergePartitions.Sources createMergePartitionsSources() {
        return new MergePartitions.Sources();
    }

    /**
     * Create an instance of {@link DataSourceView.Annotations }
     * 
     */
    public DataSourceView.Annotations createDataSourceViewAnnotations() {
        return new DataSourceView.Annotations();
    }

    /**
     * Create an instance of {@link Discover.Restrictions.RestrictionList }
     * 
     */
    public Discover.Restrictions.RestrictionList createDiscoverRestrictionsRestrictionList() {
        return new Discover.Restrictions.RestrictionList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "urn:schemas-microsoft-com:xml-analysis", name = "AllowedRowsExpression")
    public JAXBElement<String> createAllowedRowsExpression(String value) {
        return new JAXBElement<String>(_AllowedRowsExpression_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "urn:schemas-microsoft-com:xml-analysis", name = "ShareDimensionStorage")
    public JAXBElement<String> createShareDimensionStorage(String value) {
        return new JAXBElement<String>(_ShareDimensionStorage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BooleanExpr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BooleanExpr }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "and", scope = BooleanExpr.class)
    public JAXBElement<BooleanExpr> createBooleanExprAnd(BooleanExpr value) {
        return new JAXBElement<BooleanExpr>(_BooleanExprAnd_QNAME, BooleanExpr.class, BooleanExpr.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BooleanExpr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BooleanExpr }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "or", scope = BooleanExpr.class)
    public JAXBElement<BooleanExpr> createBooleanExprOr(BooleanExpr value) {
        return new JAXBElement<BooleanExpr>(_BooleanExprOr_QNAME, BooleanExpr.class, BooleanExpr.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnaryExpr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UnaryExpr }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "not", scope = BooleanExpr.class)
    public JAXBElement<UnaryExpr> createBooleanExprNot(UnaryExpr value) {
        return new JAXBElement<UnaryExpr>(_BooleanExprNot_QNAME, UnaryExpr.class, BooleanExpr.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PredLeaf }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PredLeaf }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "leaf", scope = BooleanExpr.class)
    public JAXBElement<PredLeaf> createBooleanExprLeaf(PredLeaf value) {
        return new JAXBElement<PredLeaf>(_BooleanExprLeaf_QNAME, PredLeaf.class, BooleanExpr.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NotType }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Not", scope = AndOrType.class)
    public JAXBElement<NotType> createAndOrTypeNot(NotType value) {
        return new JAXBElement<NotType>(_AndOrTypeNot_QNAME, NotType.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AndOrType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AndOrType }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Or", scope = AndOrType.class)
    public JAXBElement<AndOrType> createAndOrTypeOr(AndOrType value) {
        return new JAXBElement<AndOrType>(_AndOrTypeOr_QNAME, AndOrType.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AndOrType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AndOrType }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "And", scope = AndOrType.class)
    public JAXBElement<AndOrType> createAndOrTypeAnd(AndOrType value) {
        return new JAXBElement<AndOrType>(_AndOrTypeAnd_QNAME, AndOrType.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Equal", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeEqual(BoolBinop value) {
        return new JAXBElement<BoolBinop>(_AndOrTypeEqual_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "NotEqual", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeNotEqual(BoolBinop value) {
        return new JAXBElement<BoolBinop>(_AndOrTypeNotEqual_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Less", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeLess(BoolBinop value) {
        return new JAXBElement<BoolBinop>(_AndOrTypeLess_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "LessOrEqual", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeLessOrEqual(BoolBinop value) {
        return new JAXBElement<BoolBinop>(_AndOrTypeLessOrEqual_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Greater", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeGreater(BoolBinop value) {
        return new JAXBElement<BoolBinop>(_AndOrTypeGreater_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "GreaterOrEqual", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeGreaterOrEqual(BoolBinop value) {
        return new JAXBElement<BoolBinop>(_AndOrTypeGreaterOrEqual_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Like", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeLike(BoolBinop value) {
        return new JAXBElement<BoolBinop>(_AndOrTypeLike_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BoolBinop }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "NotLike", scope = AndOrType.class)
    public JAXBElement<BoolBinop> createAndOrTypeNotLike(BoolBinop value) {
        return new JAXBElement<BoolBinop>(_AndOrTypeNotLike_QNAME, BoolBinop.class, AndOrType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Discover.Restrictions.RestrictionList }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Discover.Restrictions.RestrictionList }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "RestrictionList", scope = Discover.Restrictions.class)
    public JAXBElement<Discover.Restrictions.RestrictionList> createDiscoverRestrictionsRestrictionList(Discover.Restrictions.RestrictionList value) {
        return new JAXBElement<Discover.Restrictions.RestrictionList>(_DiscoverRestrictionsRestrictionList_QNAME, Discover.Restrictions.RestrictionList.class, Discover.Restrictions.class, value);
    }

}
