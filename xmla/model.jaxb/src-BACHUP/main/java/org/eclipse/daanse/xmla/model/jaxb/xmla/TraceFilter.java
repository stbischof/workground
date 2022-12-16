
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TraceFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TraceFilter"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="Not" type="{urn:schemas-microsoft-com:xml-analysis}not_type" minOccurs="0"/&gt;
 *         &lt;element name="Or" type="{urn:schemas-microsoft-com:xml-analysis}and_or_type" minOccurs="0"/&gt;
 *         &lt;element name="And" type="{urn:schemas-microsoft-com:xml-analysis}and_or_type" minOccurs="0"/&gt;
 *         &lt;element name="Equal" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="NotEqual" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="Less" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="LessOrEqual" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="Greater" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="GreaterOrEqual" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="Like" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *         &lt;element name="NotLike" type="{urn:schemas-microsoft-com:xml-analysis}bool_binop" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TraceFilter", propOrder = {
    "not",
    "or",
    "and",
    "equal",
    "notEqual",
    "less",
    "lessOrEqual",
    "greater",
    "greaterOrEqual",
    "like",
    "notLike"
})
public class TraceFilter {

    @XmlElement(name = "Not")
    protected NotType not;
    @XmlElement(name = "Or")
    protected AndOrType or;
    @XmlElement(name = "And")
    protected AndOrType and;
    @XmlElement(name = "Equal")
    protected BoolBinop equal;
    @XmlElement(name = "NotEqual")
    protected BoolBinop notEqual;
    @XmlElement(name = "Less")
    protected BoolBinop less;
    @XmlElement(name = "LessOrEqual")
    protected BoolBinop lessOrEqual;
    @XmlElement(name = "Greater")
    protected BoolBinop greater;
    @XmlElement(name = "GreaterOrEqual")
    protected BoolBinop greaterOrEqual;
    @XmlElement(name = "Like")
    protected BoolBinop like;
    @XmlElement(name = "NotLike")
    protected BoolBinop notLike;

    /**
     * Gets the value of the not property.
     * 
     * @return
     *     possible object is
     *     {@link NotType }
     *     
     */
    public NotType getNot() {
        return not;
    }

    /**
     * Sets the value of the not property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotType }
     *     
     */
    public void setNot(NotType value) {
        this.not = value;
    }

    public boolean isSetNot() {
        return (this.not!= null);
    }

    /**
     * Gets the value of the or property.
     * 
     * @return
     *     possible object is
     *     {@link AndOrType }
     *     
     */
    public AndOrType getOr() {
        return or;
    }

    /**
     * Sets the value of the or property.
     * 
     * @param value
     *     allowed object is
     *     {@link AndOrType }
     *     
     */
    public void setOr(AndOrType value) {
        this.or = value;
    }

    public boolean isSetOr() {
        return (this.or!= null);
    }

    /**
     * Gets the value of the and property.
     * 
     * @return
     *     possible object is
     *     {@link AndOrType }
     *     
     */
    public AndOrType getAnd() {
        return and;
    }

    /**
     * Sets the value of the and property.
     * 
     * @param value
     *     allowed object is
     *     {@link AndOrType }
     *     
     */
    public void setAnd(AndOrType value) {
        this.and = value;
    }

    public boolean isSetAnd() {
        return (this.and!= null);
    }

    /**
     * Gets the value of the equal property.
     * 
     * @return
     *     possible object is
     *     {@link BoolBinop }
     *     
     */
    public BoolBinop getEqual() {
        return equal;
    }

    /**
     * Sets the value of the equal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoolBinop }
     *     
     */
    public void setEqual(BoolBinop value) {
        this.equal = value;
    }

    public boolean isSetEqual() {
        return (this.equal!= null);
    }

    /**
     * Gets the value of the notEqual property.
     * 
     * @return
     *     possible object is
     *     {@link BoolBinop }
     *     
     */
    public BoolBinop getNotEqual() {
        return notEqual;
    }

    /**
     * Sets the value of the notEqual property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoolBinop }
     *     
     */
    public void setNotEqual(BoolBinop value) {
        this.notEqual = value;
    }

    public boolean isSetNotEqual() {
        return (this.notEqual!= null);
    }

    /**
     * Gets the value of the less property.
     * 
     * @return
     *     possible object is
     *     {@link BoolBinop }
     *     
     */
    public BoolBinop getLess() {
        return less;
    }

    /**
     * Sets the value of the less property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoolBinop }
     *     
     */
    public void setLess(BoolBinop value) {
        this.less = value;
    }

    public boolean isSetLess() {
        return (this.less!= null);
    }

    /**
     * Gets the value of the lessOrEqual property.
     * 
     * @return
     *     possible object is
     *     {@link BoolBinop }
     *     
     */
    public BoolBinop getLessOrEqual() {
        return lessOrEqual;
    }

    /**
     * Sets the value of the lessOrEqual property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoolBinop }
     *     
     */
    public void setLessOrEqual(BoolBinop value) {
        this.lessOrEqual = value;
    }

    public boolean isSetLessOrEqual() {
        return (this.lessOrEqual!= null);
    }

    /**
     * Gets the value of the greater property.
     * 
     * @return
     *     possible object is
     *     {@link BoolBinop }
     *     
     */
    public BoolBinop getGreater() {
        return greater;
    }

    /**
     * Sets the value of the greater property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoolBinop }
     *     
     */
    public void setGreater(BoolBinop value) {
        this.greater = value;
    }

    public boolean isSetGreater() {
        return (this.greater!= null);
    }

    /**
     * Gets the value of the greaterOrEqual property.
     * 
     * @return
     *     possible object is
     *     {@link BoolBinop }
     *     
     */
    public BoolBinop getGreaterOrEqual() {
        return greaterOrEqual;
    }

    /**
     * Sets the value of the greaterOrEqual property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoolBinop }
     *     
     */
    public void setGreaterOrEqual(BoolBinop value) {
        this.greaterOrEqual = value;
    }

    public boolean isSetGreaterOrEqual() {
        return (this.greaterOrEqual!= null);
    }

    /**
     * Gets the value of the like property.
     * 
     * @return
     *     possible object is
     *     {@link BoolBinop }
     *     
     */
    public BoolBinop getLike() {
        return like;
    }

    /**
     * Sets the value of the like property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoolBinop }
     *     
     */
    public void setLike(BoolBinop value) {
        this.like = value;
    }

    public boolean isSetLike() {
        return (this.like!= null);
    }

    /**
     * Gets the value of the notLike property.
     * 
     * @return
     *     possible object is
     *     {@link BoolBinop }
     *     
     */
    public BoolBinop getNotLike() {
        return notLike;
    }

    /**
     * Sets the value of the notLike property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoolBinop }
     *     
     */
    public void setNotLike(BoolBinop value) {
        this.notLike = value;
    }

    public boolean isSetNotLike() {
        return (this.notLike!= null);
    }

}
