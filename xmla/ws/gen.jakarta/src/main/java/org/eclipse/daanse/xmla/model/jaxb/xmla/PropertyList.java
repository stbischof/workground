
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PropertyList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PropertyList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="DataSourceInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Timeout" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LocaleIdentifier" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Catalog" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="StateSupport" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Sessions"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Content" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="None"/&gt;
 *               &lt;enumeration value="Schema"/&gt;
 *               &lt;enumeration value="Data"/&gt;
 *               &lt;enumeration value="SchemaData"/&gt;
 *               &lt;enumeration value="Metadata"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Format" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Tabular"/&gt;
 *               &lt;enumeration value="Multidimensional"/&gt;
 *               &lt;enumeration value="Native"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AxisFormat" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="ClusterFormat"/&gt;
 *               &lt;enumeration value="CustomFormat"/&gt;
 *               &lt;enumeration value="TupleFormat"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BeginRange" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="EndRange" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MDXSupport" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Core"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ProviderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProviderVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DBMSVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProviderType" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *               &lt;enumeration value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ShowHiddenCubes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="SQLSupport" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="TransactionDDL" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MaximumRows" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Roles" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VisualMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EffectiveRoles" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EffectiveUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ServerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CatalogLocation" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropCatalogTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DbpropCatalogUsage" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropColumnDefinition" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropConcatNullBehavior" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropDataSourceReadOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="DbpropGroupBy" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropHeterogeneousTables" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropIdentifierCase" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMaxIndexSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMaxOpenChapters" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMaxRowSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMaxRowSizeIncludeBlob" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMaxTablesInSelect" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMultiTableUpdate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="DbpropNullCollation" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="4"/&gt;
 *               &lt;enumeration value="8"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropOrderByColumnsInSelect" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="DbpropOutputParameterAvailable" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropPersistentIdType" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="4"/&gt;
 *               &lt;enumeration value="8"/&gt;
 *               &lt;enumeration value="16"/&gt;
 *               &lt;enumeration value="32"/&gt;
 *               &lt;enumeration value="64"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropPrepareAbortBehavior" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropPrepareCommitBehavior" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropProcedureTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DbpropQuotedIdentifierCase" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="4"/&gt;
 *               &lt;enumeration value="8"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropSchemaUsage" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropSqlSupport" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropSubqueries" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropSupportedTxnDdl" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxSubqueries" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropSupportedTxnIsoLevels" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropSupportedTxnIsoRetain" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropTableTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MdpropAggregateCellUpdate" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MdpropAxes" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropFlatteningSupport" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *               &lt;enumeration value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MdpropMdxCaseSupport" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxDescFlags" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxDrillFunctions" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxFormulas" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxJoinCubes" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxMemberFunctions" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxNonMeasureExpressions" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MdpropMdxNumericFunctions" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxObjQualification" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxOuterReference" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxQueryByProperty" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxRangeRowset" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MdpropMdxSetFunctions" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxSlicer" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MdpropMdxStringCompop" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropNamedLevels" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdMDXCompatibility" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropMsmdSQLCompatibility" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdMDXUniqueNameStyle" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdCachePolicy" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdCacheRatio" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdCacheMode" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdCompareCaseSensitiveStringFlags" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="16"/&gt;
 *               &lt;enumeration value="256"/&gt;
 *               &lt;enumeration value="4096"/&gt;
 *               &lt;enumeration value="65536"/&gt;
 *               &lt;enumeration value="1048576"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropMsmdCompareCaseNotSensitiveStringFlags" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdFlattened2" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="DbpropInitMode" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="SspropInitAppName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SspropInitWsid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SspropInitPacketsize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="ReadOnlySession" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="SecuredCellValue" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *               &lt;enumeration value="4"/&gt;
 *               &lt;enumeration value="5"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NonEmptyThreshold" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="SafetyOptions" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropMsmdCacheRatio2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdUseFormulaCache" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdDynamicDebugLimit" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdDebugMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Dialect" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="MDX"/&gt;
 *               &lt;enumeration value="DMX"/&gt;
 *               &lt;enumeration value="SQL"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ImpactAnalysis" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="SQLQueryMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Data"/&gt;
 *               &lt;enumeration value="Calculated"/&gt;
 *               &lt;enumeration value="IncludeEmpty"/&gt;
 *               &lt;enumeration value="DataKeys"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ClientProcessID" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Cube" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ReturnCellProperties" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="CommitTimeout" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="ForceCommitTimeout" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="ExecutionMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RealTimeOlap" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="MdxMissingMemberMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Default"/&gt;
 *               &lt;enumeration value="Ignore"/&gt;
 *               &lt;enumeration value="Error"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MdpropMdxNamedSets" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdSubqueries" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropMsmdAutoExists" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CustomData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DisablePrefetchFacts" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="UpdateIsolationLevel" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropMsmdErrorMessageMode" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="MdpropMdxDdlExtensions" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="ResponseEncoding" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Default"/&gt;
 *               &lt;enumeration value="UTF-8"/&gt;
 *               &lt;enumeration value="UTF-16"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MemoryLockingMode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DbpropMsmdOptimizeResponse" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdActivityID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdRequestID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ReturnAffectedObjects" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DbpropMsmdRequestMemoryLimit" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="ApplicationContext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyList", propOrder = {

})
public class PropertyList {

    @XmlElement(name = "DataSourceInfo")
    protected String dataSourceInfo;
    @XmlElement(name = "Timeout")
    protected BigInteger timeout;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "LocaleIdentifier")
    protected BigInteger localeIdentifier;
    @XmlElement(name = "Catalog")
    protected String catalog;
    @XmlElement(name = "StateSupport")
    protected String stateSupport;
    @XmlElement(name = "Content")
    protected String content;
    @XmlElement(name = "Format")
    protected String format;
    @XmlElement(name = "AxisFormat")
    protected String axisFormat;
    @XmlElement(name = "BeginRange")
    protected BigInteger beginRange;
    @XmlElement(name = "EndRange")
    protected BigInteger endRange;
    @XmlElement(name = "MDXSupport")
    protected String mdxSupport;
    @XmlElement(name = "ProviderName")
    protected String providerName;
    @XmlElement(name = "ProviderVersion")
    protected String providerVersion;
    @XmlElement(name = "DBMSVersion")
    protected String dbmsVersion;
    @XmlElement(name = "ProviderType")
    protected BigInteger providerType;
    @XmlElement(name = "ShowHiddenCubes")
    protected Boolean showHiddenCubes;
    @XmlElement(name = "SQLSupport")
    protected BigInteger sqlSupport;
    @XmlElement(name = "TransactionDDL")
    protected BigInteger transactionDDL;
    @XmlElement(name = "MaximumRows")
    protected BigInteger maximumRows;
    @XmlElement(name = "Roles")
    protected String roles;
    @XmlElement(name = "VisualMode")
    protected BigInteger visualMode;
    @XmlElement(name = "EffectiveRoles")
    protected String effectiveRoles;
    @XmlElement(name = "EffectiveUserName")
    protected String effectiveUserName;
    @XmlElement(name = "ServerName")
    protected String serverName;
    @XmlElement(name = "CatalogLocation")
    protected BigInteger catalogLocation;
    @XmlElement(name = "DbpropCatalogTerm")
    protected String dbpropCatalogTerm;
    @XmlElement(name = "DbpropCatalogUsage")
    protected BigInteger dbpropCatalogUsage;
    @XmlElement(name = "DbpropColumnDefinition")
    protected BigInteger dbpropColumnDefinition;
    @XmlElement(name = "DbpropConcatNullBehavior")
    protected BigInteger dbpropConcatNullBehavior;
    @XmlElement(name = "DbpropDataSourceReadOnly")
    protected Boolean dbpropDataSourceReadOnly;
    @XmlElement(name = "DbpropGroupBy")
    protected BigInteger dbpropGroupBy;
    @XmlElement(name = "DbpropHeterogeneousTables")
    protected BigInteger dbpropHeterogeneousTables;
    @XmlElement(name = "DbpropIdentifierCase")
    protected BigInteger dbpropIdentifierCase;
    @XmlElement(name = "DbpropMaxIndexSize")
    protected BigInteger dbpropMaxIndexSize;
    @XmlElement(name = "DbpropMaxOpenChapters")
    protected BigInteger dbpropMaxOpenChapters;
    @XmlElement(name = "DbpropMaxRowSize")
    protected BigInteger dbpropMaxRowSize;
    @XmlElement(name = "DbpropMaxRowSizeIncludeBlob")
    protected Boolean dbpropMaxRowSizeIncludeBlob;
    @XmlElement(name = "DbpropMaxTablesInSelect")
    protected BigInteger dbpropMaxTablesInSelect;
    @XmlElement(name = "DbpropMultiTableUpdate")
    protected Boolean dbpropMultiTableUpdate;
    @XmlElement(name = "DbpropNullCollation")
    protected BigInteger dbpropNullCollation;
    @XmlElement(name = "DbpropOrderByColumnsInSelect")
    protected Boolean dbpropOrderByColumnsInSelect;
    @XmlElement(name = "DbpropOutputParameterAvailable")
    protected BigInteger dbpropOutputParameterAvailable;
    @XmlElement(name = "DbpropPersistentIdType")
    protected BigInteger dbpropPersistentIdType;
    @XmlElement(name = "DbpropPrepareAbortBehavior")
    protected BigInteger dbpropPrepareAbortBehavior;
    @XmlElement(name = "DbpropPrepareCommitBehavior")
    protected BigInteger dbpropPrepareCommitBehavior;
    @XmlElement(name = "DbpropProcedureTerm")
    protected String dbpropProcedureTerm;
    @XmlElement(name = "DbpropQuotedIdentifierCase")
    protected BigInteger dbpropQuotedIdentifierCase;
    @XmlElement(name = "DbpropSchemaUsage")
    protected BigInteger dbpropSchemaUsage;
    @XmlElement(name = "DbpropSqlSupport")
    protected BigInteger dbpropSqlSupport;
    @XmlElement(name = "DbpropSubqueries")
    protected BigInteger dbpropSubqueries;
    @XmlElement(name = "DbpropSupportedTxnDdl")
    protected BigInteger dbpropSupportedTxnDdl;
    @XmlElement(name = "MdpropMdxSubqueries")
    protected BigInteger mdpropMdxSubqueries;
    @XmlElement(name = "DbpropSupportedTxnIsoLevels")
    protected BigInteger dbpropSupportedTxnIsoLevels;
    @XmlElement(name = "DbpropSupportedTxnIsoRetain")
    protected BigInteger dbpropSupportedTxnIsoRetain;
    @XmlElement(name = "DbpropTableTerm")
    protected String dbpropTableTerm;
    @XmlElement(name = "MdpropAggregateCellUpdate")
    protected BigInteger mdpropAggregateCellUpdate;
    @XmlElement(name = "MdpropAxes")
    protected BigInteger mdpropAxes;
    @XmlElement(name = "MdpropFlatteningSupport")
    protected BigInteger mdpropFlatteningSupport;
    @XmlElement(name = "MdpropMdxCaseSupport")
    protected BigInteger mdpropMdxCaseSupport;
    @XmlElement(name = "MdpropMdxDescFlags")
    protected BigInteger mdpropMdxDescFlags;
    @XmlElement(name = "MdpropMdxDrillFunctions")
    protected BigInteger mdpropMdxDrillFunctions;
    @XmlElement(name = "MdpropMdxFormulas")
    protected BigInteger mdpropMdxFormulas;
    @XmlElement(name = "MdpropMdxJoinCubes")
    protected BigInteger mdpropMdxJoinCubes;
    @XmlElement(name = "MdpropMdxMemberFunctions")
    protected BigInteger mdpropMdxMemberFunctions;
    @XmlElement(name = "MdpropMdxNonMeasureExpressions")
    protected BigInteger mdpropMdxNonMeasureExpressions;
    @XmlElement(name = "MdpropMdxNumericFunctions")
    protected BigInteger mdpropMdxNumericFunctions;
    @XmlElement(name = "MdpropMdxObjQualification")
    protected BigInteger mdpropMdxObjQualification;
    @XmlElement(name = "MdpropMdxOuterReference")
    protected BigInteger mdpropMdxOuterReference;
    @XmlElement(name = "MdpropMdxQueryByProperty")
    protected Boolean mdpropMdxQueryByProperty;
    @XmlElement(name = "MdpropMdxRangeRowset")
    protected BigInteger mdpropMdxRangeRowset;
    @XmlElement(name = "MdpropMdxSetFunctions")
    protected BigInteger mdpropMdxSetFunctions;
    @XmlElement(name = "MdpropMdxSlicer")
    protected BigInteger mdpropMdxSlicer;
    @XmlElement(name = "MdpropMdxStringCompop")
    protected BigInteger mdpropMdxStringCompop;
    @XmlElement(name = "MdpropNamedLevels")
    protected BigInteger mdpropNamedLevels;
    @XmlElement(name = "DbpropMsmdMDXCompatibility")
    protected BigInteger dbpropMsmdMDXCompatibility;
    @XmlElement(name = "DbpropMsmdSQLCompatibility")
    protected BigInteger dbpropMsmdSQLCompatibility;
    @XmlElement(name = "DbpropMsmdMDXUniqueNameStyle")
    protected BigInteger dbpropMsmdMDXUniqueNameStyle;
    @XmlElement(name = "DbpropMsmdCachePolicy")
    protected BigInteger dbpropMsmdCachePolicy;
    @XmlElement(name = "DbpropMsmdCacheRatio")
    protected BigInteger dbpropMsmdCacheRatio;
    @XmlElement(name = "DbpropMsmdCacheMode")
    protected BigInteger dbpropMsmdCacheMode;
    @XmlElement(name = "DbpropMsmdCompareCaseSensitiveStringFlags")
    protected BigInteger dbpropMsmdCompareCaseSensitiveStringFlags;
    @XmlElement(name = "DbpropMsmdCompareCaseNotSensitiveStringFlags")
    protected BigInteger dbpropMsmdCompareCaseNotSensitiveStringFlags;
    @XmlElement(name = "DbpropMsmdFlattened2")
    protected Boolean dbpropMsmdFlattened2;
    @XmlElement(name = "DbpropInitMode")
    protected BigInteger dbpropInitMode;
    @XmlElement(name = "SspropInitAppName")
    protected String sspropInitAppName;
    @XmlElement(name = "SspropInitWsid")
    protected String sspropInitWsid;
    @XmlElement(name = "SspropInitPacketsize")
    protected BigInteger sspropInitPacketsize;
    @XmlElement(name = "ReadOnlySession")
    protected BigInteger readOnlySession;
    @XmlElement(name = "SecuredCellValue")
    protected BigInteger securedCellValue;
    @XmlElement(name = "NonEmptyThreshold")
    protected BigInteger nonEmptyThreshold;
    @XmlElement(name = "SafetyOptions")
    protected BigInteger safetyOptions;
    @XmlElement(name = "DbpropMsmdCacheRatio2")
    protected Double dbpropMsmdCacheRatio2;
    @XmlElement(name = "DbpropMsmdUseFormulaCache")
    protected String dbpropMsmdUseFormulaCache;
    @XmlElement(name = "DbpropMsmdDynamicDebugLimit")
    protected BigInteger dbpropMsmdDynamicDebugLimit;
    @XmlElement(name = "DbpropMsmdDebugMode")
    protected String dbpropMsmdDebugMode;
    @XmlElement(name = "Dialect")
    protected String dialect;
    @XmlElement(name = "ImpactAnalysis")
    protected Boolean impactAnalysis;
    @XmlElement(name = "SQLQueryMode")
    protected String sqlQueryMode;
    @XmlElement(name = "ClientProcessID")
    protected BigInteger clientProcessID;
    @XmlElement(name = "Cube")
    protected String cube;
    @XmlElement(name = "ReturnCellProperties")
    protected Boolean returnCellProperties;
    @XmlElement(name = "CommitTimeout")
    protected BigInteger commitTimeout;
    @XmlElement(name = "ForceCommitTimeout")
    protected BigInteger forceCommitTimeout;
    @XmlElement(name = "ExecutionMode")
    protected String executionMode;
    @XmlElement(name = "RealTimeOlap")
    protected Boolean realTimeOlap;
    @XmlElement(name = "MdxMissingMemberMode")
    protected String mdxMissingMemberMode;
    @XmlElement(name = "MdpropMdxNamedSets")
    protected BigInteger mdpropMdxNamedSets;
    @XmlElement(name = "DbpropMsmdSubqueries")
    protected BigInteger dbpropMsmdSubqueries;
    @XmlElement(name = "DbpropMsmdAutoExists")
    protected BigInteger dbpropMsmdAutoExists;
    @XmlElement(name = "CustomData")
    protected String customData;
    @XmlElement(name = "DisablePrefetchFacts")
    protected Boolean disablePrefetchFacts;
    @XmlElement(name = "UpdateIsolationLevel")
    protected BigInteger updateIsolationLevel;
    @XmlElement(name = "DbpropMsmdErrorMessageMode")
    protected BigInteger dbpropMsmdErrorMessageMode;
    @XmlElement(name = "MdpropMdxDdlExtensions")
    protected BigInteger mdpropMdxDdlExtensions;
    @XmlElement(name = "ResponseEncoding")
    protected String responseEncoding;
    @XmlElement(name = "MemoryLockingMode")
    protected BigInteger memoryLockingMode;
    @XmlElement(name = "DbpropMsmdOptimizeResponse")
    protected BigInteger dbpropMsmdOptimizeResponse;
    @XmlElement(name = "DbpropMsmdActivityID")
    protected String dbpropMsmdActivityID;
    @XmlElement(name = "DbpropMsmdRequestID")
    protected String dbpropMsmdRequestID;
    @XmlElement(name = "ReturnAffectedObjects")
    protected BigInteger returnAffectedObjects;
    @XmlElement(name = "DbpropMsmdRequestMemoryLimit")
    protected BigInteger dbpropMsmdRequestMemoryLimit;
    @XmlElement(name = "ApplicationContext")
    protected String applicationContext;

    /**
     * Gets the value of the dataSourceInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSourceInfo() {
        return dataSourceInfo;
    }

    /**
     * Sets the value of the dataSourceInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSourceInfo(String value) {
        this.dataSourceInfo = value;
    }

    /**
     * Gets the value of the timeout property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimeout() {
        return timeout;
    }

    /**
     * Sets the value of the timeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimeout(BigInteger value) {
        this.timeout = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the localeIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLocaleIdentifier() {
        return localeIdentifier;
    }

    /**
     * Sets the value of the localeIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLocaleIdentifier(BigInteger value) {
        this.localeIdentifier = value;
    }

    /**
     * Gets the value of the catalog property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatalog() {
        return catalog;
    }

    /**
     * Sets the value of the catalog property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatalog(String value) {
        this.catalog = value;
    }

    /**
     * Gets the value of the stateSupport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateSupport() {
        return stateSupport;
    }

    /**
     * Sets the value of the stateSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateSupport(String value) {
        this.stateSupport = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Gets the value of the axisFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAxisFormat() {
        return axisFormat;
    }

    /**
     * Sets the value of the axisFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAxisFormat(String value) {
        this.axisFormat = value;
    }

    /**
     * Gets the value of the beginRange property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBeginRange() {
        return beginRange;
    }

    /**
     * Sets the value of the beginRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBeginRange(BigInteger value) {
        this.beginRange = value;
    }

    /**
     * Gets the value of the endRange property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEndRange() {
        return endRange;
    }

    /**
     * Sets the value of the endRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEndRange(BigInteger value) {
        this.endRange = value;
    }

    /**
     * Gets the value of the mdxSupport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMDXSupport() {
        return mdxSupport;
    }

    /**
     * Sets the value of the mdxSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMDXSupport(String value) {
        this.mdxSupport = value;
    }

    /**
     * Gets the value of the providerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * Sets the value of the providerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderName(String value) {
        this.providerName = value;
    }

    /**
     * Gets the value of the providerVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderVersion() {
        return providerVersion;
    }

    /**
     * Sets the value of the providerVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderVersion(String value) {
        this.providerVersion = value;
    }

    /**
     * Gets the value of the dbmsVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDBMSVersion() {
        return dbmsVersion;
    }

    /**
     * Sets the value of the dbmsVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDBMSVersion(String value) {
        this.dbmsVersion = value;
    }

    /**
     * Gets the value of the providerType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getProviderType() {
        return providerType;
    }

    /**
     * Sets the value of the providerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setProviderType(BigInteger value) {
        this.providerType = value;
    }

    /**
     * Gets the value of the showHiddenCubes property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowHiddenCubes() {
        return showHiddenCubes;
    }

    /**
     * Sets the value of the showHiddenCubes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowHiddenCubes(Boolean value) {
        this.showHiddenCubes = value;
    }

    /**
     * Gets the value of the sqlSupport property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSQLSupport() {
        return sqlSupport;
    }

    /**
     * Sets the value of the sqlSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSQLSupport(BigInteger value) {
        this.sqlSupport = value;
    }

    /**
     * Gets the value of the transactionDDL property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTransactionDDL() {
        return transactionDDL;
    }

    /**
     * Sets the value of the transactionDDL property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTransactionDDL(BigInteger value) {
        this.transactionDDL = value;
    }

    /**
     * Gets the value of the maximumRows property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumRows() {
        return maximumRows;
    }

    /**
     * Sets the value of the maximumRows property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaximumRows(BigInteger value) {
        this.maximumRows = value;
    }

    /**
     * Gets the value of the roles property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoles() {
        return roles;
    }

    /**
     * Sets the value of the roles property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoles(String value) {
        this.roles = value;
    }

    /**
     * Gets the value of the visualMode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVisualMode() {
        return visualMode;
    }

    /**
     * Sets the value of the visualMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVisualMode(BigInteger value) {
        this.visualMode = value;
    }

    /**
     * Gets the value of the effectiveRoles property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectiveRoles() {
        return effectiveRoles;
    }

    /**
     * Sets the value of the effectiveRoles property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveRoles(String value) {
        this.effectiveRoles = value;
    }

    /**
     * Gets the value of the effectiveUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectiveUserName() {
        return effectiveUserName;
    }

    /**
     * Sets the value of the effectiveUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveUserName(String value) {
        this.effectiveUserName = value;
    }

    /**
     * Gets the value of the serverName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets the value of the serverName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerName(String value) {
        this.serverName = value;
    }

    /**
     * Gets the value of the catalogLocation property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCatalogLocation() {
        return catalogLocation;
    }

    /**
     * Sets the value of the catalogLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCatalogLocation(BigInteger value) {
        this.catalogLocation = value;
    }

    /**
     * Gets the value of the dbpropCatalogTerm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbpropCatalogTerm() {
        return dbpropCatalogTerm;
    }

    /**
     * Sets the value of the dbpropCatalogTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbpropCatalogTerm(String value) {
        this.dbpropCatalogTerm = value;
    }

    /**
     * Gets the value of the dbpropCatalogUsage property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropCatalogUsage() {
        return dbpropCatalogUsage;
    }

    /**
     * Sets the value of the dbpropCatalogUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropCatalogUsage(BigInteger value) {
        this.dbpropCatalogUsage = value;
    }

    /**
     * Gets the value of the dbpropColumnDefinition property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropColumnDefinition() {
        return dbpropColumnDefinition;
    }

    /**
     * Sets the value of the dbpropColumnDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropColumnDefinition(BigInteger value) {
        this.dbpropColumnDefinition = value;
    }

    /**
     * Gets the value of the dbpropConcatNullBehavior property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropConcatNullBehavior() {
        return dbpropConcatNullBehavior;
    }

    /**
     * Sets the value of the dbpropConcatNullBehavior property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropConcatNullBehavior(BigInteger value) {
        this.dbpropConcatNullBehavior = value;
    }

    /**
     * Gets the value of the dbpropDataSourceReadOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDbpropDataSourceReadOnly() {
        return dbpropDataSourceReadOnly;
    }

    /**
     * Sets the value of the dbpropDataSourceReadOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDbpropDataSourceReadOnly(Boolean value) {
        this.dbpropDataSourceReadOnly = value;
    }

    /**
     * Gets the value of the dbpropGroupBy property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropGroupBy() {
        return dbpropGroupBy;
    }

    /**
     * Sets the value of the dbpropGroupBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropGroupBy(BigInteger value) {
        this.dbpropGroupBy = value;
    }

    /**
     * Gets the value of the dbpropHeterogeneousTables property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropHeterogeneousTables() {
        return dbpropHeterogeneousTables;
    }

    /**
     * Sets the value of the dbpropHeterogeneousTables property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropHeterogeneousTables(BigInteger value) {
        this.dbpropHeterogeneousTables = value;
    }

    /**
     * Gets the value of the dbpropIdentifierCase property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropIdentifierCase() {
        return dbpropIdentifierCase;
    }

    /**
     * Sets the value of the dbpropIdentifierCase property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropIdentifierCase(BigInteger value) {
        this.dbpropIdentifierCase = value;
    }

    /**
     * Gets the value of the dbpropMaxIndexSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMaxIndexSize() {
        return dbpropMaxIndexSize;
    }

    /**
     * Sets the value of the dbpropMaxIndexSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMaxIndexSize(BigInteger value) {
        this.dbpropMaxIndexSize = value;
    }

    /**
     * Gets the value of the dbpropMaxOpenChapters property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMaxOpenChapters() {
        return dbpropMaxOpenChapters;
    }

    /**
     * Sets the value of the dbpropMaxOpenChapters property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMaxOpenChapters(BigInteger value) {
        this.dbpropMaxOpenChapters = value;
    }

    /**
     * Gets the value of the dbpropMaxRowSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMaxRowSize() {
        return dbpropMaxRowSize;
    }

    /**
     * Sets the value of the dbpropMaxRowSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMaxRowSize(BigInteger value) {
        this.dbpropMaxRowSize = value;
    }

    /**
     * Gets the value of the dbpropMaxRowSizeIncludeBlob property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDbpropMaxRowSizeIncludeBlob() {
        return dbpropMaxRowSizeIncludeBlob;
    }

    /**
     * Sets the value of the dbpropMaxRowSizeIncludeBlob property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDbpropMaxRowSizeIncludeBlob(Boolean value) {
        this.dbpropMaxRowSizeIncludeBlob = value;
    }

    /**
     * Gets the value of the dbpropMaxTablesInSelect property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMaxTablesInSelect() {
        return dbpropMaxTablesInSelect;
    }

    /**
     * Sets the value of the dbpropMaxTablesInSelect property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMaxTablesInSelect(BigInteger value) {
        this.dbpropMaxTablesInSelect = value;
    }

    /**
     * Gets the value of the dbpropMultiTableUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDbpropMultiTableUpdate() {
        return dbpropMultiTableUpdate;
    }

    /**
     * Sets the value of the dbpropMultiTableUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDbpropMultiTableUpdate(Boolean value) {
        this.dbpropMultiTableUpdate = value;
    }

    /**
     * Gets the value of the dbpropNullCollation property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropNullCollation() {
        return dbpropNullCollation;
    }

    /**
     * Sets the value of the dbpropNullCollation property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropNullCollation(BigInteger value) {
        this.dbpropNullCollation = value;
    }

    /**
     * Gets the value of the dbpropOrderByColumnsInSelect property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDbpropOrderByColumnsInSelect() {
        return dbpropOrderByColumnsInSelect;
    }

    /**
     * Sets the value of the dbpropOrderByColumnsInSelect property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDbpropOrderByColumnsInSelect(Boolean value) {
        this.dbpropOrderByColumnsInSelect = value;
    }

    /**
     * Gets the value of the dbpropOutputParameterAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropOutputParameterAvailable() {
        return dbpropOutputParameterAvailable;
    }

    /**
     * Sets the value of the dbpropOutputParameterAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropOutputParameterAvailable(BigInteger value) {
        this.dbpropOutputParameterAvailable = value;
    }

    /**
     * Gets the value of the dbpropPersistentIdType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropPersistentIdType() {
        return dbpropPersistentIdType;
    }

    /**
     * Sets the value of the dbpropPersistentIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropPersistentIdType(BigInteger value) {
        this.dbpropPersistentIdType = value;
    }

    /**
     * Gets the value of the dbpropPrepareAbortBehavior property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropPrepareAbortBehavior() {
        return dbpropPrepareAbortBehavior;
    }

    /**
     * Sets the value of the dbpropPrepareAbortBehavior property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropPrepareAbortBehavior(BigInteger value) {
        this.dbpropPrepareAbortBehavior = value;
    }

    /**
     * Gets the value of the dbpropPrepareCommitBehavior property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropPrepareCommitBehavior() {
        return dbpropPrepareCommitBehavior;
    }

    /**
     * Sets the value of the dbpropPrepareCommitBehavior property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropPrepareCommitBehavior(BigInteger value) {
        this.dbpropPrepareCommitBehavior = value;
    }

    /**
     * Gets the value of the dbpropProcedureTerm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbpropProcedureTerm() {
        return dbpropProcedureTerm;
    }

    /**
     * Sets the value of the dbpropProcedureTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbpropProcedureTerm(String value) {
        this.dbpropProcedureTerm = value;
    }

    /**
     * Gets the value of the dbpropQuotedIdentifierCase property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropQuotedIdentifierCase() {
        return dbpropQuotedIdentifierCase;
    }

    /**
     * Sets the value of the dbpropQuotedIdentifierCase property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropQuotedIdentifierCase(BigInteger value) {
        this.dbpropQuotedIdentifierCase = value;
    }

    /**
     * Gets the value of the dbpropSchemaUsage property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropSchemaUsage() {
        return dbpropSchemaUsage;
    }

    /**
     * Sets the value of the dbpropSchemaUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropSchemaUsage(BigInteger value) {
        this.dbpropSchemaUsage = value;
    }

    /**
     * Gets the value of the dbpropSqlSupport property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropSqlSupport() {
        return dbpropSqlSupport;
    }

    /**
     * Sets the value of the dbpropSqlSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropSqlSupport(BigInteger value) {
        this.dbpropSqlSupport = value;
    }

    /**
     * Gets the value of the dbpropSubqueries property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropSubqueries() {
        return dbpropSubqueries;
    }

    /**
     * Sets the value of the dbpropSubqueries property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropSubqueries(BigInteger value) {
        this.dbpropSubqueries = value;
    }

    /**
     * Gets the value of the dbpropSupportedTxnDdl property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropSupportedTxnDdl() {
        return dbpropSupportedTxnDdl;
    }

    /**
     * Sets the value of the dbpropSupportedTxnDdl property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropSupportedTxnDdl(BigInteger value) {
        this.dbpropSupportedTxnDdl = value;
    }

    /**
     * Gets the value of the mdpropMdxSubqueries property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxSubqueries() {
        return mdpropMdxSubqueries;
    }

    /**
     * Sets the value of the mdpropMdxSubqueries property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxSubqueries(BigInteger value) {
        this.mdpropMdxSubqueries = value;
    }

    /**
     * Gets the value of the dbpropSupportedTxnIsoLevels property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropSupportedTxnIsoLevels() {
        return dbpropSupportedTxnIsoLevels;
    }

    /**
     * Sets the value of the dbpropSupportedTxnIsoLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropSupportedTxnIsoLevels(BigInteger value) {
        this.dbpropSupportedTxnIsoLevels = value;
    }

    /**
     * Gets the value of the dbpropSupportedTxnIsoRetain property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropSupportedTxnIsoRetain() {
        return dbpropSupportedTxnIsoRetain;
    }

    /**
     * Sets the value of the dbpropSupportedTxnIsoRetain property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropSupportedTxnIsoRetain(BigInteger value) {
        this.dbpropSupportedTxnIsoRetain = value;
    }

    /**
     * Gets the value of the dbpropTableTerm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbpropTableTerm() {
        return dbpropTableTerm;
    }

    /**
     * Sets the value of the dbpropTableTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbpropTableTerm(String value) {
        this.dbpropTableTerm = value;
    }

    /**
     * Gets the value of the mdpropAggregateCellUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropAggregateCellUpdate() {
        return mdpropAggregateCellUpdate;
    }

    /**
     * Sets the value of the mdpropAggregateCellUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropAggregateCellUpdate(BigInteger value) {
        this.mdpropAggregateCellUpdate = value;
    }

    /**
     * Gets the value of the mdpropAxes property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropAxes() {
        return mdpropAxes;
    }

    /**
     * Sets the value of the mdpropAxes property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropAxes(BigInteger value) {
        this.mdpropAxes = value;
    }

    /**
     * Gets the value of the mdpropFlatteningSupport property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropFlatteningSupport() {
        return mdpropFlatteningSupport;
    }

    /**
     * Sets the value of the mdpropFlatteningSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropFlatteningSupport(BigInteger value) {
        this.mdpropFlatteningSupport = value;
    }

    /**
     * Gets the value of the mdpropMdxCaseSupport property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxCaseSupport() {
        return mdpropMdxCaseSupport;
    }

    /**
     * Sets the value of the mdpropMdxCaseSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxCaseSupport(BigInteger value) {
        this.mdpropMdxCaseSupport = value;
    }

    /**
     * Gets the value of the mdpropMdxDescFlags property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxDescFlags() {
        return mdpropMdxDescFlags;
    }

    /**
     * Sets the value of the mdpropMdxDescFlags property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxDescFlags(BigInteger value) {
        this.mdpropMdxDescFlags = value;
    }

    /**
     * Gets the value of the mdpropMdxDrillFunctions property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxDrillFunctions() {
        return mdpropMdxDrillFunctions;
    }

    /**
     * Sets the value of the mdpropMdxDrillFunctions property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxDrillFunctions(BigInteger value) {
        this.mdpropMdxDrillFunctions = value;
    }

    /**
     * Gets the value of the mdpropMdxFormulas property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxFormulas() {
        return mdpropMdxFormulas;
    }

    /**
     * Sets the value of the mdpropMdxFormulas property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxFormulas(BigInteger value) {
        this.mdpropMdxFormulas = value;
    }

    /**
     * Gets the value of the mdpropMdxJoinCubes property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxJoinCubes() {
        return mdpropMdxJoinCubes;
    }

    /**
     * Sets the value of the mdpropMdxJoinCubes property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxJoinCubes(BigInteger value) {
        this.mdpropMdxJoinCubes = value;
    }

    /**
     * Gets the value of the mdpropMdxMemberFunctions property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxMemberFunctions() {
        return mdpropMdxMemberFunctions;
    }

    /**
     * Sets the value of the mdpropMdxMemberFunctions property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxMemberFunctions(BigInteger value) {
        this.mdpropMdxMemberFunctions = value;
    }

    /**
     * Gets the value of the mdpropMdxNonMeasureExpressions property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxNonMeasureExpressions() {
        return mdpropMdxNonMeasureExpressions;
    }

    /**
     * Sets the value of the mdpropMdxNonMeasureExpressions property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxNonMeasureExpressions(BigInteger value) {
        this.mdpropMdxNonMeasureExpressions = value;
    }

    /**
     * Gets the value of the mdpropMdxNumericFunctions property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxNumericFunctions() {
        return mdpropMdxNumericFunctions;
    }

    /**
     * Sets the value of the mdpropMdxNumericFunctions property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxNumericFunctions(BigInteger value) {
        this.mdpropMdxNumericFunctions = value;
    }

    /**
     * Gets the value of the mdpropMdxObjQualification property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxObjQualification() {
        return mdpropMdxObjQualification;
    }

    /**
     * Sets the value of the mdpropMdxObjQualification property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxObjQualification(BigInteger value) {
        this.mdpropMdxObjQualification = value;
    }

    /**
     * Gets the value of the mdpropMdxOuterReference property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxOuterReference() {
        return mdpropMdxOuterReference;
    }

    /**
     * Sets the value of the mdpropMdxOuterReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxOuterReference(BigInteger value) {
        this.mdpropMdxOuterReference = value;
    }

    /**
     * Gets the value of the mdpropMdxQueryByProperty property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMdpropMdxQueryByProperty() {
        return mdpropMdxQueryByProperty;
    }

    /**
     * Sets the value of the mdpropMdxQueryByProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMdpropMdxQueryByProperty(Boolean value) {
        this.mdpropMdxQueryByProperty = value;
    }

    /**
     * Gets the value of the mdpropMdxRangeRowset property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxRangeRowset() {
        return mdpropMdxRangeRowset;
    }

    /**
     * Sets the value of the mdpropMdxRangeRowset property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxRangeRowset(BigInteger value) {
        this.mdpropMdxRangeRowset = value;
    }

    /**
     * Gets the value of the mdpropMdxSetFunctions property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxSetFunctions() {
        return mdpropMdxSetFunctions;
    }

    /**
     * Sets the value of the mdpropMdxSetFunctions property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxSetFunctions(BigInteger value) {
        this.mdpropMdxSetFunctions = value;
    }

    /**
     * Gets the value of the mdpropMdxSlicer property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxSlicer() {
        return mdpropMdxSlicer;
    }

    /**
     * Sets the value of the mdpropMdxSlicer property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxSlicer(BigInteger value) {
        this.mdpropMdxSlicer = value;
    }

    /**
     * Gets the value of the mdpropMdxStringCompop property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxStringCompop() {
        return mdpropMdxStringCompop;
    }

    /**
     * Sets the value of the mdpropMdxStringCompop property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxStringCompop(BigInteger value) {
        this.mdpropMdxStringCompop = value;
    }

    /**
     * Gets the value of the mdpropNamedLevels property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropNamedLevels() {
        return mdpropNamedLevels;
    }

    /**
     * Sets the value of the mdpropNamedLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropNamedLevels(BigInteger value) {
        this.mdpropNamedLevels = value;
    }

    /**
     * Gets the value of the dbpropMsmdMDXCompatibility property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdMDXCompatibility() {
        return dbpropMsmdMDXCompatibility;
    }

    /**
     * Sets the value of the dbpropMsmdMDXCompatibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdMDXCompatibility(BigInteger value) {
        this.dbpropMsmdMDXCompatibility = value;
    }

    /**
     * Gets the value of the dbpropMsmdSQLCompatibility property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdSQLCompatibility() {
        return dbpropMsmdSQLCompatibility;
    }

    /**
     * Sets the value of the dbpropMsmdSQLCompatibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdSQLCompatibility(BigInteger value) {
        this.dbpropMsmdSQLCompatibility = value;
    }

    /**
     * Gets the value of the dbpropMsmdMDXUniqueNameStyle property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdMDXUniqueNameStyle() {
        return dbpropMsmdMDXUniqueNameStyle;
    }

    /**
     * Sets the value of the dbpropMsmdMDXUniqueNameStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdMDXUniqueNameStyle(BigInteger value) {
        this.dbpropMsmdMDXUniqueNameStyle = value;
    }

    /**
     * Gets the value of the dbpropMsmdCachePolicy property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdCachePolicy() {
        return dbpropMsmdCachePolicy;
    }

    /**
     * Sets the value of the dbpropMsmdCachePolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdCachePolicy(BigInteger value) {
        this.dbpropMsmdCachePolicy = value;
    }

    /**
     * Gets the value of the dbpropMsmdCacheRatio property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdCacheRatio() {
        return dbpropMsmdCacheRatio;
    }

    /**
     * Sets the value of the dbpropMsmdCacheRatio property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdCacheRatio(BigInteger value) {
        this.dbpropMsmdCacheRatio = value;
    }

    /**
     * Gets the value of the dbpropMsmdCacheMode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdCacheMode() {
        return dbpropMsmdCacheMode;
    }

    /**
     * Sets the value of the dbpropMsmdCacheMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdCacheMode(BigInteger value) {
        this.dbpropMsmdCacheMode = value;
    }

    /**
     * Gets the value of the dbpropMsmdCompareCaseSensitiveStringFlags property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdCompareCaseSensitiveStringFlags() {
        return dbpropMsmdCompareCaseSensitiveStringFlags;
    }

    /**
     * Sets the value of the dbpropMsmdCompareCaseSensitiveStringFlags property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdCompareCaseSensitiveStringFlags(BigInteger value) {
        this.dbpropMsmdCompareCaseSensitiveStringFlags = value;
    }

    /**
     * Gets the value of the dbpropMsmdCompareCaseNotSensitiveStringFlags property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdCompareCaseNotSensitiveStringFlags() {
        return dbpropMsmdCompareCaseNotSensitiveStringFlags;
    }

    /**
     * Sets the value of the dbpropMsmdCompareCaseNotSensitiveStringFlags property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdCompareCaseNotSensitiveStringFlags(BigInteger value) {
        this.dbpropMsmdCompareCaseNotSensitiveStringFlags = value;
    }

    /**
     * Gets the value of the dbpropMsmdFlattened2 property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDbpropMsmdFlattened2() {
        return dbpropMsmdFlattened2;
    }

    /**
     * Sets the value of the dbpropMsmdFlattened2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDbpropMsmdFlattened2(Boolean value) {
        this.dbpropMsmdFlattened2 = value;
    }

    /**
     * Gets the value of the dbpropInitMode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropInitMode() {
        return dbpropInitMode;
    }

    /**
     * Sets the value of the dbpropInitMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropInitMode(BigInteger value) {
        this.dbpropInitMode = value;
    }

    /**
     * Gets the value of the sspropInitAppName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSspropInitAppName() {
        return sspropInitAppName;
    }

    /**
     * Sets the value of the sspropInitAppName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSspropInitAppName(String value) {
        this.sspropInitAppName = value;
    }

    /**
     * Gets the value of the sspropInitWsid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSspropInitWsid() {
        return sspropInitWsid;
    }

    /**
     * Sets the value of the sspropInitWsid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSspropInitWsid(String value) {
        this.sspropInitWsid = value;
    }

    /**
     * Gets the value of the sspropInitPacketsize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSspropInitPacketsize() {
        return sspropInitPacketsize;
    }

    /**
     * Sets the value of the sspropInitPacketsize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSspropInitPacketsize(BigInteger value) {
        this.sspropInitPacketsize = value;
    }

    /**
     * Gets the value of the readOnlySession property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getReadOnlySession() {
        return readOnlySession;
    }

    /**
     * Sets the value of the readOnlySession property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setReadOnlySession(BigInteger value) {
        this.readOnlySession = value;
    }

    /**
     * Gets the value of the securedCellValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSecuredCellValue() {
        return securedCellValue;
    }

    /**
     * Sets the value of the securedCellValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSecuredCellValue(BigInteger value) {
        this.securedCellValue = value;
    }

    /**
     * Gets the value of the nonEmptyThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNonEmptyThreshold() {
        return nonEmptyThreshold;
    }

    /**
     * Sets the value of the nonEmptyThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNonEmptyThreshold(BigInteger value) {
        this.nonEmptyThreshold = value;
    }

    /**
     * Gets the value of the safetyOptions property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSafetyOptions() {
        return safetyOptions;
    }

    /**
     * Sets the value of the safetyOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSafetyOptions(BigInteger value) {
        this.safetyOptions = value;
    }

    /**
     * Gets the value of the dbpropMsmdCacheRatio2 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDbpropMsmdCacheRatio2() {
        return dbpropMsmdCacheRatio2;
    }

    /**
     * Sets the value of the dbpropMsmdCacheRatio2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDbpropMsmdCacheRatio2(Double value) {
        this.dbpropMsmdCacheRatio2 = value;
    }

    /**
     * Gets the value of the dbpropMsmdUseFormulaCache property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbpropMsmdUseFormulaCache() {
        return dbpropMsmdUseFormulaCache;
    }

    /**
     * Sets the value of the dbpropMsmdUseFormulaCache property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbpropMsmdUseFormulaCache(String value) {
        this.dbpropMsmdUseFormulaCache = value;
    }

    /**
     * Gets the value of the dbpropMsmdDynamicDebugLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdDynamicDebugLimit() {
        return dbpropMsmdDynamicDebugLimit;
    }

    /**
     * Sets the value of the dbpropMsmdDynamicDebugLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdDynamicDebugLimit(BigInteger value) {
        this.dbpropMsmdDynamicDebugLimit = value;
    }

    /**
     * Gets the value of the dbpropMsmdDebugMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbpropMsmdDebugMode() {
        return dbpropMsmdDebugMode;
    }

    /**
     * Sets the value of the dbpropMsmdDebugMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbpropMsmdDebugMode(String value) {
        this.dbpropMsmdDebugMode = value;
    }

    /**
     * Gets the value of the dialect property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDialect() {
        return dialect;
    }

    /**
     * Sets the value of the dialect property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDialect(String value) {
        this.dialect = value;
    }

    /**
     * Gets the value of the impactAnalysis property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isImpactAnalysis() {
        return impactAnalysis;
    }

    /**
     * Sets the value of the impactAnalysis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setImpactAnalysis(Boolean value) {
        this.impactAnalysis = value;
    }

    /**
     * Gets the value of the sqlQueryMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSQLQueryMode() {
        return sqlQueryMode;
    }

    /**
     * Sets the value of the sqlQueryMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSQLQueryMode(String value) {
        this.sqlQueryMode = value;
    }

    /**
     * Gets the value of the clientProcessID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getClientProcessID() {
        return clientProcessID;
    }

    /**
     * Sets the value of the clientProcessID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setClientProcessID(BigInteger value) {
        this.clientProcessID = value;
    }

    /**
     * Gets the value of the cube property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCube() {
        return cube;
    }

    /**
     * Sets the value of the cube property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCube(String value) {
        this.cube = value;
    }

    /**
     * Gets the value of the returnCellProperties property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnCellProperties() {
        return returnCellProperties;
    }

    /**
     * Sets the value of the returnCellProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnCellProperties(Boolean value) {
        this.returnCellProperties = value;
    }

    /**
     * Gets the value of the commitTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCommitTimeout() {
        return commitTimeout;
    }

    /**
     * Sets the value of the commitTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCommitTimeout(BigInteger value) {
        this.commitTimeout = value;
    }

    /**
     * Gets the value of the forceCommitTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getForceCommitTimeout() {
        return forceCommitTimeout;
    }

    /**
     * Sets the value of the forceCommitTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setForceCommitTimeout(BigInteger value) {
        this.forceCommitTimeout = value;
    }

    /**
     * Gets the value of the executionMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutionMode() {
        return executionMode;
    }

    /**
     * Sets the value of the executionMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutionMode(String value) {
        this.executionMode = value;
    }

    /**
     * Gets the value of the realTimeOlap property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRealTimeOlap() {
        return realTimeOlap;
    }

    /**
     * Sets the value of the realTimeOlap property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRealTimeOlap(Boolean value) {
        this.realTimeOlap = value;
    }

    /**
     * Gets the value of the mdxMissingMemberMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdxMissingMemberMode() {
        return mdxMissingMemberMode;
    }

    /**
     * Sets the value of the mdxMissingMemberMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdxMissingMemberMode(String value) {
        this.mdxMissingMemberMode = value;
    }

    /**
     * Gets the value of the mdpropMdxNamedSets property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxNamedSets() {
        return mdpropMdxNamedSets;
    }

    /**
     * Sets the value of the mdpropMdxNamedSets property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxNamedSets(BigInteger value) {
        this.mdpropMdxNamedSets = value;
    }

    /**
     * Gets the value of the dbpropMsmdSubqueries property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdSubqueries() {
        return dbpropMsmdSubqueries;
    }

    /**
     * Sets the value of the dbpropMsmdSubqueries property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdSubqueries(BigInteger value) {
        this.dbpropMsmdSubqueries = value;
    }

    /**
     * Gets the value of the dbpropMsmdAutoExists property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdAutoExists() {
        return dbpropMsmdAutoExists;
    }

    /**
     * Sets the value of the dbpropMsmdAutoExists property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdAutoExists(BigInteger value) {
        this.dbpropMsmdAutoExists = value;
    }

    /**
     * Gets the value of the customData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomData() {
        return customData;
    }

    /**
     * Sets the value of the customData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomData(String value) {
        this.customData = value;
    }

    /**
     * Gets the value of the disablePrefetchFacts property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisablePrefetchFacts() {
        return disablePrefetchFacts;
    }

    /**
     * Sets the value of the disablePrefetchFacts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisablePrefetchFacts(Boolean value) {
        this.disablePrefetchFacts = value;
    }

    /**
     * Gets the value of the updateIsolationLevel property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUpdateIsolationLevel() {
        return updateIsolationLevel;
    }

    /**
     * Sets the value of the updateIsolationLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUpdateIsolationLevel(BigInteger value) {
        this.updateIsolationLevel = value;
    }

    /**
     * Gets the value of the dbpropMsmdErrorMessageMode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdErrorMessageMode() {
        return dbpropMsmdErrorMessageMode;
    }

    /**
     * Sets the value of the dbpropMsmdErrorMessageMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdErrorMessageMode(BigInteger value) {
        this.dbpropMsmdErrorMessageMode = value;
    }

    /**
     * Gets the value of the mdpropMdxDdlExtensions property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMdpropMdxDdlExtensions() {
        return mdpropMdxDdlExtensions;
    }

    /**
     * Sets the value of the mdpropMdxDdlExtensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMdpropMdxDdlExtensions(BigInteger value) {
        this.mdpropMdxDdlExtensions = value;
    }

    /**
     * Gets the value of the responseEncoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseEncoding() {
        return responseEncoding;
    }

    /**
     * Sets the value of the responseEncoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseEncoding(String value) {
        this.responseEncoding = value;
    }

    /**
     * Gets the value of the memoryLockingMode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMemoryLockingMode() {
        return memoryLockingMode;
    }

    /**
     * Sets the value of the memoryLockingMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMemoryLockingMode(BigInteger value) {
        this.memoryLockingMode = value;
    }

    /**
     * Gets the value of the dbpropMsmdOptimizeResponse property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdOptimizeResponse() {
        return dbpropMsmdOptimizeResponse;
    }

    /**
     * Sets the value of the dbpropMsmdOptimizeResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdOptimizeResponse(BigInteger value) {
        this.dbpropMsmdOptimizeResponse = value;
    }

    /**
     * Gets the value of the dbpropMsmdActivityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbpropMsmdActivityID() {
        return dbpropMsmdActivityID;
    }

    /**
     * Sets the value of the dbpropMsmdActivityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbpropMsmdActivityID(String value) {
        this.dbpropMsmdActivityID = value;
    }

    /**
     * Gets the value of the dbpropMsmdRequestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbpropMsmdRequestID() {
        return dbpropMsmdRequestID;
    }

    /**
     * Sets the value of the dbpropMsmdRequestID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbpropMsmdRequestID(String value) {
        this.dbpropMsmdRequestID = value;
    }

    /**
     * Gets the value of the returnAffectedObjects property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getReturnAffectedObjects() {
        return returnAffectedObjects;
    }

    /**
     * Sets the value of the returnAffectedObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setReturnAffectedObjects(BigInteger value) {
        this.returnAffectedObjects = value;
    }

    /**
     * Gets the value of the dbpropMsmdRequestMemoryLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDbpropMsmdRequestMemoryLimit() {
        return dbpropMsmdRequestMemoryLimit;
    }

    /**
     * Sets the value of the dbpropMsmdRequestMemoryLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDbpropMsmdRequestMemoryLimit(BigInteger value) {
        this.dbpropMsmdRequestMemoryLimit = value;
    }

    /**
     * Gets the value of the applicationContext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationContext() {
        return applicationContext;
    }

    /**
     * Sets the value of the applicationContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationContext(String value) {
        this.applicationContext = value;
    }

}
