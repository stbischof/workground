
package org.eclipse.daanse.xmla.model.jaxb.engine300;

import javax.xml.namespace.QName;

import org.eclipse.daanse.xmla.model.jaxb.engine.ImpersonationInfo;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the org.eclipse.daanse.xmla.ws.eng300 package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _DataEmbeddingStyle_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2011/engine/300", "DataEmbeddingStyle");
  private final static QName _QueryImpersonationInfo_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2011/engine/300", "QueryImpersonationInfo");
  private final static QName _QueryHints_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2011/engine/300", "QueryHints");
  private final static QName _StringStoresCompatibilityLevel_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2011/engine/300", "StringStoresCompatibilityLevel");
  private final static QName _CurrentStringStoresCompatibilityLevel_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2011/engine/300", "CurrentStringStoresCompatibilityLevel");
  private final static QName _ProcessingState_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2011/engine/300", "ProcessingState");
  private final static QName _StructureType_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2011/engine/300", "StructureType");
  private final static QName _ServerMode_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2011/engine/300", "ServerMode");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema
   * derived classes for package: org.eclipse.daanse.xmla.ws.eng300
   * 
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link RelationshipEndVisualizationProperties }
   * 
   */
  public RelationshipEndVisualizationProperties createRelationshipEndVisualizationProperties() {
    return new RelationshipEndVisualizationProperties();
  }

  /**
   * Create an instance of {@link DimensionAttributeVisualizationProperties }
   * 
   */
  public DimensionAttributeVisualizationProperties createDimensionAttributeVisualizationProperties() {
    return new DimensionAttributeVisualizationProperties();
  }

  /**
   * Create an instance of {@link HierarchyVisualizationProperties }
   * 
   */
  public HierarchyVisualizationProperties createHierarchyVisualizationProperties() {
    return new HierarchyVisualizationProperties();
  }

  /**
   * Create an instance of {@link CalculationPropertiesVisualizationProperties }
   * 
   */
  public CalculationPropertiesVisualizationProperties createCalculationPropertiesVisualizationProperties() {
    return new CalculationPropertiesVisualizationProperties();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "DataEmbeddingStyle")
  public JAXBElement<String> createDataEmbeddingStyle(String value) {
    return new JAXBElement<String>(_DataEmbeddingStyle_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ImpersonationInfo
   * }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement
   *         }{@code <}{@link ImpersonationInfo }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "QueryImpersonationInfo")
  public JAXBElement<ImpersonationInfo> createQueryImpersonationInfo(ImpersonationInfo value) {
    return new JAXBElement<ImpersonationInfo>(_QueryImpersonationInfo_QNAME, ImpersonationInfo.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "QueryHints")
  public JAXBElement<String> createQueryHints(String value) {
    return new JAXBElement<String>(_QueryHints_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link Integer
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "StringStoresCompatibilityLevel")
  public JAXBElement<Integer> createStringStoresCompatibilityLevel(Integer value) {
    return new JAXBElement<Integer>(_StringStoresCompatibilityLevel_QNAME, Integer.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link Integer
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "CurrentStringStoresCompatibilityLevel")
  public JAXBElement<Integer> createCurrentStringStoresCompatibilityLevel(Integer value) {
    return new JAXBElement<Integer>(_CurrentStringStoresCompatibilityLevel_QNAME, Integer.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "ProcessingState")
  public JAXBElement<String> createProcessingState(String value) {
    return new JAXBElement<String>(_ProcessingState_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "StructureType")
  public JAXBElement<String> createStructureType(String value) {
    return new JAXBElement<String>(_StructureType_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "ServerMode")
  public JAXBElement<String> createServerMode(String value) {
    return new JAXBElement<String>(_ServerMode_QNAME, String.class, null, value);
  }

}
