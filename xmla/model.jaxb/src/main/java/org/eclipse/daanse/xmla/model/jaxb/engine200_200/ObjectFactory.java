
package org.eclipse.daanse.xmla.model.jaxb.engine200_200;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the org.eclipse.daanse.xmla.ws.eng200_200
 * package.
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

  private final static QName _StorageEngineUsed_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", "StorageEngineUsed");
  private final static QName _ImagePath_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", "ImagePath");
  private final static QName _ImageUrl_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", "ImageUrl");
  private final static QName _ImageUniqueID_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", "ImageUniqueID");
  private final static QName _ImageVersion_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", "ImageVersion");
  private final static QName _Token_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", "Token");
  private final static QName _ProcessingRecommendation_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", "ProcessingRecommendation");
  private final static QName _ProcessingState_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", "ProcessingState");
  private final static QName _ShareDimensionStorage_QNAME = new QName(
      "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", "ShareDimensionStorage");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema
   * derived classes for package: org.eclipse.daanse.xmla.ws.eng200_200
   * 
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link RowNumberBinding }
   * 
   */
  public RowNumberBinding createRowNumberBinding() {
    return new RowNumberBinding();
  }

  /**
   * Create an instance of {@link ExpressionBinding }
   * 
   */
  public ExpressionBinding createExpressionBinding() {
    return new ExpressionBinding();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "StorageEngineUsed")
  public JAXBElement<String> createStorageEngineUsed(String value) {
    return new JAXBElement<String>(_StorageEngineUsed_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ImagePath")
  public JAXBElement<String> createImagePath(String value) {
    return new JAXBElement<String>(_ImagePath_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ImageUrl")
  public JAXBElement<String> createImageUrl(String value) {
    return new JAXBElement<String>(_ImageUrl_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ImageUniqueID")
  public JAXBElement<String> createImageUniqueID(String value) {
    return new JAXBElement<String>(_ImageUniqueID_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ImageVersion")
  public JAXBElement<String> createImageVersion(String value) {
    return new JAXBElement<String>(_ImageVersion_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "Token")
  public JAXBElement<String> createToken(String value) {
    return new JAXBElement<String>(_Token_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ProcessingRecommendation")
  public JAXBElement<String> createProcessingRecommendation(String value) {
    return new JAXBElement<String>(_ProcessingRecommendation_QNAME, String.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
   * 
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link String
   *         }{@code >}
   */
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ProcessingState")
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
  @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ShareDimensionStorage")
  public JAXBElement<String> createShareDimensionStorage(String value) {
    return new JAXBElement<String>(_ShareDimensionStorage_QNAME, String.class, null, value);
  }

}
