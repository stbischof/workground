
package org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset package.
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

  private final static QName _Root_QNAME = new QName("urn:schemas-microsoft-com:xml-analysis:mddataset", "root");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema
   * derived classes for package:
   * org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset
   * 
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link CellType }
   * 
   */
  public CellType createCellType() {
    return new CellType();
  }

  /**
   * Create an instance of {@link SetListType }
   * 
   */
  public SetListType createSetListType() {
    return new SetListType();
  }

  /**
   * Create an instance of {@link Mddataset }
   * 
   */
  public Mddataset createMddataset() {
    return new Mddataset();
  }

  /**
   * Create an instance of {@link OlapInfo }
   * 
   */
  public OlapInfo createOlapInfo() {
    return new OlapInfo();
  }

  /**
   * Create an instance of {@link CubeInfo }
   * 
   */
  public CubeInfo createCubeInfo() {
    return new CubeInfo();
  }

  /**
   * Create an instance of {@link TupleType }
   * 
   */
  public TupleType createTupleType() {
    return new TupleType();
  }

  /**
   * Create an instance of {@link OlapInfoCube }
   * 
   */
  public OlapInfoCube createOlapInfoCube() {
    return new OlapInfoCube();
  }

  /**
   * Create an instance of {@link AxesInfo }
   * 
   */
  public AxesInfo createAxesInfo() {
    return new AxesInfo();
  }

  /**
   * Create an instance of {@link AxisInfo }
   * 
   */
  public AxisInfo createAxisInfo() {
    return new AxisInfo();
  }

  /**
   * Create an instance of {@link HierarchyInfo }
   * 
   */
  public HierarchyInfo createHierarchyInfo() {
    return new HierarchyInfo();
  }

  /**
   * Create an instance of {@link CellInfo }
   * 
   */
  public CellInfo createCellInfo() {
    return new CellInfo();
  }

  /**
   * Create an instance of {@link Axes }
   * 
   */
  public Axes createAxes() {
    return new Axes();
  }

  /**
   * Create an instance of {@link Axis }
   * 
   */
  public Axis createAxis() {
    return new Axis();
  }

  /**
   * Create an instance of {@link MembersType }
   * 
   */
  public MembersType createMembersType() {
    return new MembersType();
  }

  /**
   * Create an instance of {@link MemberType }
   * 
   */
  public MemberType createMemberType() {
    return new MemberType();
  }

  /**
   * Create an instance of {@link TuplesType }
   * 
   */
  public TuplesType createTuplesType() {
    return new TuplesType();
  }

  /**
   * Create an instance of {@link CellData }
   * 
   */
  public CellData createCellData() {
    return new CellData();
  }

  /**
   * Create an instance of {@link CellTypeError }
   * 
   */
  public CellTypeError createCellTypeError() {
    return new CellTypeError();
  }

  /**
   * Create an instance of {@link CellSetType }
   * 
   */
  public CellSetType createCellSetType() {
    return new CellSetType();
  }

  /**
   * Create an instance of {@link CellType.Value }
   * 
   */
  public CellType.Value createCellTypeValue() {
    return new CellType.Value();
  }

  /**
   * Create an instance of {@link SetListType.Union }
   * 
   */
  public Union createSetListTypeUnion() {
    return new Union();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Mddataset
   * }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link Mddataset
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset", name = "root")
  public JAXBElement<Mddataset> createRoot(Mddataset value) {
    return new JAXBElement<Mddataset>(_Root_QNAME, Mddataset.class, null, value);
  }

}
