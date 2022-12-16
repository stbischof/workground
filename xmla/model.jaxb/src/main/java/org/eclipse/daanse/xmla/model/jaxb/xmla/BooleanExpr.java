
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for boolean_expr complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="boolean_expr"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="and" type="{urn:schemas-microsoft-com:xml-analysis}boolean_expr"/&gt;
 *           &lt;element name="or" type="{urn:schemas-microsoft-com:xml-analysis}boolean_expr"/&gt;
 *           &lt;element name="not" type="{urn:schemas-microsoft-com:xml-analysis}unary_expr"/&gt;
 *           &lt;element name="leaf" type="{urn:schemas-microsoft-com:xml-analysis}pred_leaf"/&gt;
 *         &lt;/choice&gt;
 *         &lt;choice&gt;
 *           &lt;element name="and" type="{urn:schemas-microsoft-com:xml-analysis}boolean_expr"/&gt;
 *           &lt;element name="or" type="{urn:schemas-microsoft-com:xml-analysis}boolean_expr"/&gt;
 *           &lt;element name="not" type="{urn:schemas-microsoft-com:xml-analysis}unary_expr"/&gt;
 *           &lt;element name="leaf" type="{urn:schemas-microsoft-com:xml-analysis}pred_leaf"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boolean_expr", propOrder = { "content" })
public class BooleanExpr {

  @XmlElementRefs({ @XmlElementRef(name = "and", type = JAXBElement.class, required = false),
      @XmlElementRef(name = "or", type = JAXBElement.class, required = false),
      @XmlElementRef(name = "not", type = JAXBElement.class, required = false),
      @XmlElementRef(name = "leaf", type = JAXBElement.class, required = false) })
  protected List<JAXBElement<?>> content;

  /**
   * Gets the rest of the content model.
   * 
   * <p>
   * You are getting this "catch-all" property because of the following reason:
   * The field name "And" is used by two different parts of a schema. See: line
   * 4194 of
   * file:/home/stbischof/dev/git/org.eclipse.daanse.xmla/org.eclipse.daanse.xmla.definition/xmla.xsd
   * line 4188 of
   * file:/home/stbischof/dev/git/org.eclipse.daanse.xmla/org.eclipse.daanse.xmla.definition/xmla.xsd
   * <p>
   * To get rid of this property, apply a property customization to one of both of
   * the following declarations to change their names: Gets the value of the
   * content property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the Jakarta XML Binding object. This is why there is not a
   * <CODE>set</CODE> method for the content property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getContent().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link JAXBElement
   * }{@code <}{@link BooleanExpr }{@code >} {@link JAXBElement
   * }{@code <}{@link BooleanExpr }{@code >} {@link JAXBElement
   * }{@code <}{@link PredLeaf }{@code >} {@link JAXBElement
   * }{@code <}{@link UnaryExpr }{@code >}
   * 
   * 
   */
  public List<JAXBElement<?>> getContent() {
    if (content == null) {
      content = new ArrayList<JAXBElement<?>>();
    }
    return this.content;
  }

}
