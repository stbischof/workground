
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for unary_expr complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="unary_expr"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="not" type="{urn:schemas-microsoft-com:xml-analysis}unary_expr"/&gt;
 *         &lt;element name="and" type="{urn:schemas-microsoft-com:xml-analysis}boolean_expr"/&gt;
 *         &lt;element name="or" type="{urn:schemas-microsoft-com:xml-analysis}boolean_expr"/&gt;
 *         &lt;element name="leaf" type="{urn:schemas-microsoft-com:xml-analysis}pred_leaf"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "unary_expr", propOrder = {
    "not",
    "and",
    "or",
    "leaf"
})
public class UnaryExpr {

    protected UnaryExpr not;
    protected BooleanExpr and;
    protected BooleanExpr or;
    protected PredLeaf leaf;

    /**
     * Gets the value of the not property.
     * 
     * @return
     *     possible object is
     *     {@link UnaryExpr }
     *     
     */
    public UnaryExpr getNot() {
        return not;
    }

    /**
     * Sets the value of the not property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnaryExpr }
     *     
     */
    public void setNot(UnaryExpr value) {
        this.not = value;
    }

    /**
     * Gets the value of the and property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanExpr }
     *     
     */
    public BooleanExpr getAnd() {
        return and;
    }

    /**
     * Sets the value of the and property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanExpr }
     *     
     */
    public void setAnd(BooleanExpr value) {
        this.and = value;
    }

    /**
     * Gets the value of the or property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanExpr }
     *     
     */
    public BooleanExpr getOr() {
        return or;
    }

    /**
     * Sets the value of the or property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanExpr }
     *     
     */
    public void setOr(BooleanExpr value) {
        this.or = value;
    }

    /**
     * Gets the value of the leaf property.
     * 
     * @return
     *     possible object is
     *     {@link PredLeaf }
     *     
     */
    public PredLeaf getLeaf() {
        return leaf;
    }

    /**
     * Sets the value of the leaf property.
     * 
     * @param value
     *     allowed object is
     *     {@link PredLeaf }
     *     
     */
    public void setLeaf(PredLeaf value) {
        this.leaf = value;
    }

}
