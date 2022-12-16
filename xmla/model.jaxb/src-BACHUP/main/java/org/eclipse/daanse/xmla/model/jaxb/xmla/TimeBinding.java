
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TimeBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeBinding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Binding"&gt;
 *       &lt;all&gt;
 *         &lt;element name="CalendarStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="CalendarEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="FirstDayOfWeek" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *               &lt;maxInclusive value="7"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CalendarLanguage" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="FiscalFirstMonth" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *               &lt;maxInclusive value="12"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="FiscalFirstDayOfMonth" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *               &lt;maxInclusive value="31"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="FiscalYearName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="CalendarYearName"/&gt;
 *               &lt;enumeration value="NextCalendarYearName"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ReportingFirstMonth" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *               &lt;maxInclusive value="12"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ReportingFirstWeekOfMonth" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *               &lt;enumeration value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ReportingWeekToMonthPattern" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Weeks445"/&gt;
 *               &lt;enumeration value="Weeks454"/&gt;
 *               &lt;enumeration value="Weeks544"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ManufacturingFirstMonth" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *               &lt;maxInclusive value="12"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ManufacturingFirstWeekOfMonth" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *               &lt;maxInclusive value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ManufacturingExtraMonthQuarter" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *               &lt;maxInclusive value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/all&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeBinding", propOrder = {
    "calendarStartDate",
    "calendarEndDate",
    "firstDayOfWeek",
    "calendarLanguage",
    "fiscalFirstMonth",
    "fiscalFirstDayOfMonth",
    "fiscalYearName",
    "reportingFirstMonth",
    "reportingFirstWeekOfMonth",
    "reportingWeekToMonthPattern",
    "manufacturingFirstMonth",
    "manufacturingFirstWeekOfMonth",
    "manufacturingExtraMonthQuarter"
})
public class TimeBinding
    extends Binding
{

    @XmlElement(name = "CalendarStartDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calendarStartDate;
    @XmlElement(name = "CalendarEndDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calendarEndDate;
    @XmlElement(name = "FirstDayOfWeek")
    protected Integer firstDayOfWeek;
    @XmlElement(name = "CalendarLanguage")
    protected BigInteger calendarLanguage;
    @XmlElement(name = "FiscalFirstMonth")
    protected Integer fiscalFirstMonth;
    @XmlElement(name = "FiscalFirstDayOfMonth")
    protected Integer fiscalFirstDayOfMonth;
    @XmlElement(name = "FiscalYearName")
    protected String fiscalYearName;
    @XmlElement(name = "ReportingFirstMonth")
    protected Integer reportingFirstMonth;
    @XmlElement(name = "ReportingFirstWeekOfMonth")
    protected String reportingFirstWeekOfMonth;
    @XmlElement(name = "ReportingWeekToMonthPattern")
    protected String reportingWeekToMonthPattern;
    @XmlElement(name = "ManufacturingFirstMonth")
    protected Integer manufacturingFirstMonth;
    @XmlElement(name = "ManufacturingFirstWeekOfMonth")
    protected Integer manufacturingFirstWeekOfMonth;
    @XmlElement(name = "ManufacturingExtraMonthQuarter")
    protected Integer manufacturingExtraMonthQuarter;

    /**
     * Gets the value of the calendarStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalendarStartDate() {
        return calendarStartDate;
    }

    /**
     * Sets the value of the calendarStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalendarStartDate(XMLGregorianCalendar value) {
        this.calendarStartDate = value;
    }

    public boolean isSetCalendarStartDate() {
        return (this.calendarStartDate!= null);
    }

    /**
     * Gets the value of the calendarEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalendarEndDate() {
        return calendarEndDate;
    }

    /**
     * Sets the value of the calendarEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalendarEndDate(XMLGregorianCalendar value) {
        this.calendarEndDate = value;
    }

    public boolean isSetCalendarEndDate() {
        return (this.calendarEndDate!= null);
    }

    /**
     * Gets the value of the firstDayOfWeek property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    /**
     * Sets the value of the firstDayOfWeek property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFirstDayOfWeek(Integer value) {
        this.firstDayOfWeek = value;
    }

    public boolean isSetFirstDayOfWeek() {
        return (this.firstDayOfWeek!= null);
    }

    /**
     * Gets the value of the calendarLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCalendarLanguage() {
        return calendarLanguage;
    }

    /**
     * Sets the value of the calendarLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCalendarLanguage(BigInteger value) {
        this.calendarLanguage = value;
    }

    public boolean isSetCalendarLanguage() {
        return (this.calendarLanguage!= null);
    }

    /**
     * Gets the value of the fiscalFirstMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFiscalFirstMonth() {
        return fiscalFirstMonth;
    }

    /**
     * Sets the value of the fiscalFirstMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFiscalFirstMonth(Integer value) {
        this.fiscalFirstMonth = value;
    }

    public boolean isSetFiscalFirstMonth() {
        return (this.fiscalFirstMonth!= null);
    }

    /**
     * Gets the value of the fiscalFirstDayOfMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFiscalFirstDayOfMonth() {
        return fiscalFirstDayOfMonth;
    }

    /**
     * Sets the value of the fiscalFirstDayOfMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFiscalFirstDayOfMonth(Integer value) {
        this.fiscalFirstDayOfMonth = value;
    }

    public boolean isSetFiscalFirstDayOfMonth() {
        return (this.fiscalFirstDayOfMonth!= null);
    }

    /**
     * Gets the value of the fiscalYearName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiscalYearName() {
        return fiscalYearName;
    }

    /**
     * Sets the value of the fiscalYearName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiscalYearName(String value) {
        this.fiscalYearName = value;
    }

    public boolean isSetFiscalYearName() {
        return (this.fiscalYearName!= null);
    }

    /**
     * Gets the value of the reportingFirstMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReportingFirstMonth() {
        return reportingFirstMonth;
    }

    /**
     * Sets the value of the reportingFirstMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReportingFirstMonth(Integer value) {
        this.reportingFirstMonth = value;
    }

    public boolean isSetReportingFirstMonth() {
        return (this.reportingFirstMonth!= null);
    }

    /**
     * Gets the value of the reportingFirstWeekOfMonth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportingFirstWeekOfMonth() {
        return reportingFirstWeekOfMonth;
    }

    /**
     * Sets the value of the reportingFirstWeekOfMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportingFirstWeekOfMonth(String value) {
        this.reportingFirstWeekOfMonth = value;
    }

    public boolean isSetReportingFirstWeekOfMonth() {
        return (this.reportingFirstWeekOfMonth!= null);
    }

    /**
     * Gets the value of the reportingWeekToMonthPattern property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportingWeekToMonthPattern() {
        return reportingWeekToMonthPattern;
    }

    /**
     * Sets the value of the reportingWeekToMonthPattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportingWeekToMonthPattern(String value) {
        this.reportingWeekToMonthPattern = value;
    }

    public boolean isSetReportingWeekToMonthPattern() {
        return (this.reportingWeekToMonthPattern!= null);
    }

    /**
     * Gets the value of the manufacturingFirstMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getManufacturingFirstMonth() {
        return manufacturingFirstMonth;
    }

    /**
     * Sets the value of the manufacturingFirstMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setManufacturingFirstMonth(Integer value) {
        this.manufacturingFirstMonth = value;
    }

    public boolean isSetManufacturingFirstMonth() {
        return (this.manufacturingFirstMonth!= null);
    }

    /**
     * Gets the value of the manufacturingFirstWeekOfMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getManufacturingFirstWeekOfMonth() {
        return manufacturingFirstWeekOfMonth;
    }

    /**
     * Sets the value of the manufacturingFirstWeekOfMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setManufacturingFirstWeekOfMonth(Integer value) {
        this.manufacturingFirstWeekOfMonth = value;
    }

    public boolean isSetManufacturingFirstWeekOfMonth() {
        return (this.manufacturingFirstWeekOfMonth!= null);
    }

    /**
     * Gets the value of the manufacturingExtraMonthQuarter property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getManufacturingExtraMonthQuarter() {
        return manufacturingExtraMonthQuarter;
    }

    /**
     * Sets the value of the manufacturingExtraMonthQuarter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setManufacturingExtraMonthQuarter(Integer value) {
        this.manufacturingExtraMonthQuarter = value;
    }

    public boolean isSetManufacturingExtraMonthQuarter() {
        return (this.manufacturingExtraMonthQuarter!= null);
    }

}
