
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DimensionAttributeTypeEnumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="DimensionAttributeTypeEnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Account"/&gt;
 *     &lt;enumeration value="AccountName"/&gt;
 *     &lt;enumeration value="AccountNumber"/&gt;
 *     &lt;enumeration value="AccountType"/&gt;
 *     &lt;enumeration value="Address"/&gt;
 *     &lt;enumeration value="AddressBuilding"/&gt;
 *     &lt;enumeration value="AddressCity"/&gt;
 *     &lt;enumeration value="AddressCountry"/&gt;
 *     &lt;enumeration value="AddressFax"/&gt;
 *     &lt;enumeration value="AddressFloor"/&gt;
 *     &lt;enumeration value="AddressHouse"/&gt;
 *     &lt;enumeration value="AddressPhone"/&gt;
 *     &lt;enumeration value="AddressQuarter"/&gt;
 *     &lt;enumeration value="AddressRoom"/&gt;
 *     &lt;enumeration value="AddressStateOrProvince"/&gt;
 *     &lt;enumeration value="AddressStreet"/&gt;
 *     &lt;enumeration value="AddressZip"/&gt;
 *     &lt;enumeration value="BomResource"/&gt;
 *     &lt;enumeration value="Caption"/&gt;
 *     &lt;enumeration value="CaptionAbbreviation"/&gt;
 *     &lt;enumeration value="CaptionDescription"/&gt;
 *     &lt;enumeration value="Channel"/&gt;
 *     &lt;enumeration value="City"/&gt;
 *     &lt;enumeration value="Company"/&gt;
 *     &lt;enumeration value="Continent"/&gt;
 *     &lt;enumeration value="Country"/&gt;
 *     &lt;enumeration value="County"/&gt;
 *     &lt;enumeration value="CurrencyDestination"/&gt;
 *     &lt;enumeration value="CurrencyIsoCode"/&gt;
 *     &lt;enumeration value="CurrencyName"/&gt;
 *     &lt;enumeration value="CurrencySource"/&gt;
 *     &lt;enumeration value="CustomerGroup"/&gt;
 *     &lt;enumeration value="CustomerHousehold"/&gt;
 *     &lt;enumeration value="Customers"/&gt;
 *     &lt;enumeration value="Date"/&gt;
 *     &lt;enumeration value="DateCanceled"/&gt;
 *     &lt;enumeration value="DateDuration"/&gt;
 *     &lt;enumeration value="DateEnded"/&gt;
 *     &lt;enumeration value="DateModified"/&gt;
 *     &lt;enumeration value="DateStart"/&gt;
 *     &lt;enumeration value="DayOfHalfYear"/&gt;
 *     &lt;enumeration value="DayOfMonth"/&gt;
 *     &lt;enumeration value="DayOfQuarter"/&gt;
 *     &lt;enumeration value="DayOfTenDays"/&gt;
 *     &lt;enumeration value="DayOfTrimester"/&gt;
 *     &lt;enumeration value="DayOfWeek"/&gt;
 *     &lt;enumeration value="DayOfYear"/&gt;
 *     &lt;enumeration value="Days"/&gt;
 *     &lt;enumeration value="DeletedFlag"/&gt;
 *     &lt;enumeration value="ExtendedType"/&gt;
 *     &lt;enumeration value="FiscalDate"/&gt;
 *     &lt;enumeration value="FiscalDayOfHalfYear"/&gt;
 *     &lt;enumeration value="FiscalDayOfMonth"/&gt;
 *     &lt;enumeration value="FiscalDayOfQuarter"/&gt;
 *     &lt;enumeration value="FiscalDayOfTrimester"/&gt;
 *     &lt;enumeration value="FiscalDayOfWeek"/&gt;
 *     &lt;enumeration value="FiscalDayOfYear"/&gt;
 *     &lt;enumeration value="FiscalHalfYears"/&gt;
 *     &lt;enumeration value="FiscalHalfYearOfYear"/&gt;
 *     &lt;enumeration value="FiscalMonths"/&gt;
 *     &lt;enumeration value="FiscalMonthOfHalfYear"/&gt;
 *     &lt;enumeration value="FiscalMonthOfQuarter"/&gt;
 *     &lt;enumeration value="FiscalMonthOfTrimester"/&gt;
 *     &lt;enumeration value="FiscalMonthOfYear"/&gt;
 *     &lt;enumeration value="FiscalQuarters"/&gt;
 *     &lt;enumeration value="FiscalQuarterOfHalfYear"/&gt;
 *     &lt;enumeration value="FiscalQuarterOfYear"/&gt;
 *     &lt;enumeration value="FiscalTrimesters"/&gt;
 *     &lt;enumeration value="FiscalTrimesterOfYear"/&gt;
 *     &lt;enumeration value="FiscalWeeks"/&gt;
 *     &lt;enumeration value="FiscalWeekOfHalfYear"/&gt;
 *     &lt;enumeration value="FiscalWeekOfMonth"/&gt;
 *     &lt;enumeration value="FiscalWeekOfQuarter"/&gt;
 *     &lt;enumeration value="FiscalWeekOfTrimester"/&gt;
 *     &lt;enumeration value="FiscalWeekOfYear"/&gt;
 *     &lt;enumeration value="FiscalYears"/&gt;
 *     &lt;enumeration value="FormattingColor"/&gt;
 *     &lt;enumeration value="FormattingFont"/&gt;
 *     &lt;enumeration value="FormattingFontEffects"/&gt;
 *     &lt;enumeration value="FormattingFontSize"/&gt;
 *     &lt;enumeration value="FormattingOrder"/&gt;
 *     &lt;enumeration value="FormattingSubtotal"/&gt;
 *     &lt;enumeration value="GeoBoundaryBottom"/&gt;
 *     &lt;enumeration value="GeoBoundaryFront"/&gt;
 *     &lt;enumeration value="GeoBoundaryLeft"/&gt;
 *     &lt;enumeration value="GeoBoundaryPolygon"/&gt;
 *     &lt;enumeration value="GeoBoundaryRear"/&gt;
 *     &lt;enumeration value="GeoBoundaryRight"/&gt;
 *     &lt;enumeration value="GeoBoundaryTop"/&gt;
 *     &lt;enumeration value="GeoCentroidX"/&gt;
 *     &lt;enumeration value="GeoCentroidY"/&gt;
 *     &lt;enumeration value="GeoCentroidZ"/&gt;
 *     &lt;enumeration value="HalfYears"/&gt;
 *     &lt;enumeration value="HalfYearOfYear"/&gt;
 *     &lt;enumeration value="Hours"/&gt;
 *     &lt;enumeration value="ID"/&gt;
 *     &lt;enumeration value="Image"/&gt;
 *     &lt;enumeration value="ImageBmp"/&gt;
 *     &lt;enumeration value="ImageGif"/&gt;
 *     &lt;enumeration value="ImageJpg"/&gt;
 *     &lt;enumeration value="ImagePng"/&gt;
 *     &lt;enumeration value="ImageTiff"/&gt;
 *     &lt;enumeration value="ImageUrl"/&gt;
 *     &lt;enumeration value="IsHoliday"/&gt;
 *     &lt;enumeration value="Iso8601Date"/&gt;
 *     &lt;enumeration value="Iso8601DayOfWeek"/&gt;
 *     &lt;enumeration value="Iso8601DayOfYear"/&gt;
 *     &lt;enumeration value="Iso8601Weeks"/&gt;
 *     &lt;enumeration value="Iso8601WeekOfYear"/&gt;
 *     &lt;enumeration value="Iso8601Years"/&gt;
 *     &lt;enumeration value="IsPeakDay"/&gt;
 *     &lt;enumeration value="IsWeekDay"/&gt;
 *     &lt;enumeration value="IsWorkingDay"/&gt;
 *     &lt;enumeration value="ManufacturingDate"/&gt;
 *     &lt;enumeration value="ManufacturingDayOfHalfYear"/&gt;
 *     &lt;enumeration value="ManufacturingDayOfMonth"/&gt;
 *     &lt;enumeration value="ManufacturingDayOfQuarter"/&gt;
 *     &lt;enumeration value="ManufacturingDayOfWeek"/&gt;
 *     &lt;enumeration value="ManufacturingDayOfYear"/&gt;
 *     &lt;enumeration value="ManufacturingHalfYears"/&gt;
 *     &lt;enumeration value="ManufacturingHalfYearOfYear"/&gt;
 *     &lt;enumeration value="ManufacturingMonths"/&gt;
 *     &lt;enumeration value="ManufacturingMonthOfHalfYear"/&gt;
 *     &lt;enumeration value="ManufacturingMonthOfQuarter"/&gt;
 *     &lt;enumeration value="ManufacturingMonthOfYear"/&gt;
 *     &lt;enumeration value="ManufacturingQuarters"/&gt;
 *     &lt;enumeration value="ManufacturingQuarterOfHalfYear"/&gt;
 *     &lt;enumeration value="ManufacturingQuarterOfYear"/&gt;
 *     &lt;enumeration value="ManufacturingWeeks"/&gt;
 *     &lt;enumeration value="ManufacturingWeekOfHalfYear"/&gt;
 *     &lt;enumeration value="ManufacturingWeekOfMonth"/&gt;
 *     &lt;enumeration value="ManufacturingWeekOfQuarter"/&gt;
 *     &lt;enumeration value="ManufacturingWeekOfYear"/&gt;
 *     &lt;enumeration value="ManufacturingYears"/&gt;
 *     &lt;enumeration value="Minutes"/&gt;
 *     &lt;enumeration value="Months"/&gt;
 *     &lt;enumeration value="MonthOfHalfYear"/&gt;
 *     &lt;enumeration value="MonthOfQuarter"/&gt;
 *     &lt;enumeration value="MonthOfTrimester"/&gt;
 *     &lt;enumeration value="MonthOfYear"/&gt;
 *     &lt;enumeration value="OrganizationalUnit"/&gt;
 *     &lt;enumeration value="OrgTitle"/&gt;
 *     &lt;enumeration value="PercentOwnership"/&gt;
 *     &lt;enumeration value="PercentVoteRight"/&gt;
 *     &lt;enumeration value="Person"/&gt;
 *     &lt;enumeration value="PersonContact"/&gt;
 *     &lt;enumeration value="PersonDemographic"/&gt;
 *     &lt;enumeration value="PersonFirstName"/&gt;
 *     &lt;enumeration value="PersonFullName"/&gt;
 *     &lt;enumeration value="PersonLastName"/&gt;
 *     &lt;enumeration value="PersonMiddleName"/&gt;
 *     &lt;enumeration value="PhysicalColor"/&gt;
 *     &lt;enumeration value="PhysicalDensity"/&gt;
 *     &lt;enumeration value="PhysicalDepth"/&gt;
 *     &lt;enumeration value="PhysicalHeight"/&gt;
 *     &lt;enumeration value="PhysicalSize"/&gt;
 *     &lt;enumeration value="PhysicalVolume"/&gt;
 *     &lt;enumeration value="PhysicalWeight"/&gt;
 *     &lt;enumeration value="PhysicalWidth"/&gt;
 *     &lt;enumeration value="Point"/&gt;
 *     &lt;enumeration value="PostalCode"/&gt;
 *     &lt;enumeration value="Product"/&gt;
 *     &lt;enumeration value="ProductBrand"/&gt;
 *     &lt;enumeration value="ProductCategory"/&gt;
 *     &lt;enumeration value="ProductGroup"/&gt;
 *     &lt;enumeration value="ProductSKU"/&gt;
 *     &lt;enumeration value="Project"/&gt;
 *     &lt;enumeration value="ProjectCode"/&gt;
 *     &lt;enumeration value="ProjectCompletion"/&gt;
 *     &lt;enumeration value="ProjectEndDate"/&gt;
 *     &lt;enumeration value="ProjectName"/&gt;
 *     &lt;enumeration value="ProjectStartDate"/&gt;
 *     &lt;enumeration value="Promotion"/&gt;
 *     &lt;enumeration value="QtyRangeHigh"/&gt;
 *     &lt;enumeration value="QtyRangeLow"/&gt;
 *     &lt;enumeration value="Quantitative"/&gt;
 *     &lt;enumeration value="Quarters"/&gt;
 *     &lt;enumeration value="QuarterOfHalfYear"/&gt;
 *     &lt;enumeration value="QuarterOfYear"/&gt;
 *     &lt;enumeration value="Rate"/&gt;
 *     &lt;enumeration value="RateType"/&gt;
 *     &lt;enumeration value="Region"/&gt;
 *     &lt;enumeration value="Regular"/&gt;
 *     &lt;enumeration value="RelationToParent"/&gt;
 *     &lt;enumeration value="ReportingDate"/&gt;
 *     &lt;enumeration value="ReportingDayOfHalfYear"/&gt;
 *     &lt;enumeration value="ReportingDayOfMonth"/&gt;
 *     &lt;enumeration value="ReportingDayOfQuarter"/&gt;
 *     &lt;enumeration value="ReportingDayOfTrimester"/&gt;
 *     &lt;enumeration value="ReportingDayOfWeek"/&gt;
 *     &lt;enumeration value="ReportingDayOfYear"/&gt;
 *     &lt;enumeration value="ReportingHalfYears"/&gt;
 *     &lt;enumeration value="ReportingHalfYearOfYear"/&gt;
 *     &lt;enumeration value="ReportingMonths"/&gt;
 *     &lt;enumeration value="ReportingMonthOfHalfYear"/&gt;
 *     &lt;enumeration value="ReportingMonthOfQuarter"/&gt;
 *     &lt;enumeration value="ReportingMonthOfTrimester"/&gt;
 *     &lt;enumeration value="ReportingMonthOfYear"/&gt;
 *     &lt;enumeration value="ReportingQuarters"/&gt;
 *     &lt;enumeration value="ReportingQuarterOfHalfYear"/&gt;
 *     &lt;enumeration value="ReportingQuarterOfYear"/&gt;
 *     &lt;enumeration value="ReportingTrimesters"/&gt;
 *     &lt;enumeration value="ReportingTrimesterOfYear"/&gt;
 *     &lt;enumeration value="ReportingWeeks"/&gt;
 *     &lt;enumeration value="ReportingWeekOfHalfYear"/&gt;
 *     &lt;enumeration value="ReportingWeekOfMonth"/&gt;
 *     &lt;enumeration value="ReportingWeekOfQuarter"/&gt;
 *     &lt;enumeration value="ReportingWeekOfTrimester"/&gt;
 *     &lt;enumeration value="ReportingWeekOfYear"/&gt;
 *     &lt;enumeration value="ReportingYears"/&gt;
 *     &lt;enumeration value="Representative"/&gt;
 *     &lt;enumeration value="RowNumber"/&gt;
 *     &lt;enumeration value="ScdEndDate"/&gt;
 *     &lt;enumeration value="ScdOriginalID"/&gt;
 *     &lt;enumeration value="ScdStartDate"/&gt;
 *     &lt;enumeration value="ScdStatus"/&gt;
 *     &lt;enumeration value="Scenario"/&gt;
 *     &lt;enumeration value="Seconds"/&gt;
 *     &lt;enumeration value="Sequence"/&gt;
 *     &lt;enumeration value="ShortCaption"/&gt;
 *     &lt;enumeration value="StateOrProvince"/&gt;
 *     &lt;enumeration value="TenDay"/&gt;
 *     &lt;enumeration value="TenDayOfHalfYear"/&gt;
 *     &lt;enumeration value="TenDayOfMonth"/&gt;
 *     &lt;enumeration value="TenDayOfQuarter"/&gt;
 *     &lt;enumeration value="TenDayOfTrimester"/&gt;
 *     &lt;enumeration value="TenDayOfYear"/&gt;
 *     &lt;enumeration value="Trimesters"/&gt;
 *     &lt;enumeration value="TrimesterOfYear"/&gt;
 *     &lt;enumeration value="UndefinedTime"/&gt;
 *     &lt;enumeration value="Utility"/&gt;
 *     &lt;enumeration value="Version"/&gt;
 *     &lt;enumeration value="WebHtml"/&gt;
 *     &lt;enumeration value="WebMailAlias"/&gt;
 *     &lt;enumeration value="WebUrl"/&gt;
 *     &lt;enumeration value="WebXmlOrXsl"/&gt;
 *     &lt;enumeration value="WeekOfYear"/&gt;
 *     &lt;enumeration value="Weeks"/&gt;
 *     &lt;enumeration value="WinterSummerSeason"/&gt;
 *     &lt;enumeration value="Years"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DimensionAttributeTypeEnumType")
@XmlEnum
public enum DimensionAttributeTypeEnumType {

    @XmlEnumValue("Account")
    ACCOUNT("Account"),
    @XmlEnumValue("AccountName")
    ACCOUNT_NAME("AccountName"),
    @XmlEnumValue("AccountNumber")
    ACCOUNT_NUMBER("AccountNumber"),
    @XmlEnumValue("AccountType")
    ACCOUNT_TYPE("AccountType"),
    @XmlEnumValue("Address")
    ADDRESS("Address"),
    @XmlEnumValue("AddressBuilding")
    ADDRESS_BUILDING("AddressBuilding"),
    @XmlEnumValue("AddressCity")
    ADDRESS_CITY("AddressCity"),
    @XmlEnumValue("AddressCountry")
    ADDRESS_COUNTRY("AddressCountry"),
    @XmlEnumValue("AddressFax")
    ADDRESS_FAX("AddressFax"),
    @XmlEnumValue("AddressFloor")
    ADDRESS_FLOOR("AddressFloor"),
    @XmlEnumValue("AddressHouse")
    ADDRESS_HOUSE("AddressHouse"),
    @XmlEnumValue("AddressPhone")
    ADDRESS_PHONE("AddressPhone"),
    @XmlEnumValue("AddressQuarter")
    ADDRESS_QUARTER("AddressQuarter"),
    @XmlEnumValue("AddressRoom")
    ADDRESS_ROOM("AddressRoom"),
    @XmlEnumValue("AddressStateOrProvince")
    ADDRESS_STATE_OR_PROVINCE("AddressStateOrProvince"),
    @XmlEnumValue("AddressStreet")
    ADDRESS_STREET("AddressStreet"),
    @XmlEnumValue("AddressZip")
    ADDRESS_ZIP("AddressZip"),
    @XmlEnumValue("BomResource")
    BOM_RESOURCE("BomResource"),
    @XmlEnumValue("Caption")
    CAPTION("Caption"),
    @XmlEnumValue("CaptionAbbreviation")
    CAPTION_ABBREVIATION("CaptionAbbreviation"),
    @XmlEnumValue("CaptionDescription")
    CAPTION_DESCRIPTION("CaptionDescription"),
    @XmlEnumValue("Channel")
    CHANNEL("Channel"),
    @XmlEnumValue("City")
    CITY("City"),
    @XmlEnumValue("Company")
    COMPANY("Company"),
    @XmlEnumValue("Continent")
    CONTINENT("Continent"),
    @XmlEnumValue("Country")
    COUNTRY("Country"),
    @XmlEnumValue("County")
    COUNTY("County"),
    @XmlEnumValue("CurrencyDestination")
    CURRENCY_DESTINATION("CurrencyDestination"),
    @XmlEnumValue("CurrencyIsoCode")
    CURRENCY_ISO_CODE("CurrencyIsoCode"),
    @XmlEnumValue("CurrencyName")
    CURRENCY_NAME("CurrencyName"),
    @XmlEnumValue("CurrencySource")
    CURRENCY_SOURCE("CurrencySource"),
    @XmlEnumValue("CustomerGroup")
    CUSTOMER_GROUP("CustomerGroup"),
    @XmlEnumValue("CustomerHousehold")
    CUSTOMER_HOUSEHOLD("CustomerHousehold"),
    @XmlEnumValue("Customers")
    CUSTOMERS("Customers"),
    @XmlEnumValue("Date")
    DATE("Date"),
    @XmlEnumValue("DateCanceled")
    DATE_CANCELED("DateCanceled"),
    @XmlEnumValue("DateDuration")
    DATE_DURATION("DateDuration"),
    @XmlEnumValue("DateEnded")
    DATE_ENDED("DateEnded"),
    @XmlEnumValue("DateModified")
    DATE_MODIFIED("DateModified"),
    @XmlEnumValue("DateStart")
    DATE_START("DateStart"),
    @XmlEnumValue("DayOfHalfYear")
    DAY_OF_HALF_YEAR("DayOfHalfYear"),
    @XmlEnumValue("DayOfMonth")
    DAY_OF_MONTH("DayOfMonth"),
    @XmlEnumValue("DayOfQuarter")
    DAY_OF_QUARTER("DayOfQuarter"),
    @XmlEnumValue("DayOfTenDays")
    DAY_OF_TEN_DAYS("DayOfTenDays"),
    @XmlEnumValue("DayOfTrimester")
    DAY_OF_TRIMESTER("DayOfTrimester"),
    @XmlEnumValue("DayOfWeek")
    DAY_OF_WEEK("DayOfWeek"),
    @XmlEnumValue("DayOfYear")
    DAY_OF_YEAR("DayOfYear"),
    @XmlEnumValue("Days")
    DAYS("Days"),
    @XmlEnumValue("DeletedFlag")
    DELETED_FLAG("DeletedFlag"),
    @XmlEnumValue("ExtendedType")
    EXTENDED_TYPE("ExtendedType"),
    @XmlEnumValue("FiscalDate")
    FISCAL_DATE("FiscalDate"),
    @XmlEnumValue("FiscalDayOfHalfYear")
    FISCAL_DAY_OF_HALF_YEAR("FiscalDayOfHalfYear"),
    @XmlEnumValue("FiscalDayOfMonth")
    FISCAL_DAY_OF_MONTH("FiscalDayOfMonth"),
    @XmlEnumValue("FiscalDayOfQuarter")
    FISCAL_DAY_OF_QUARTER("FiscalDayOfQuarter"),
    @XmlEnumValue("FiscalDayOfTrimester")
    FISCAL_DAY_OF_TRIMESTER("FiscalDayOfTrimester"),
    @XmlEnumValue("FiscalDayOfWeek")
    FISCAL_DAY_OF_WEEK("FiscalDayOfWeek"),
    @XmlEnumValue("FiscalDayOfYear")
    FISCAL_DAY_OF_YEAR("FiscalDayOfYear"),
    @XmlEnumValue("FiscalHalfYears")
    FISCAL_HALF_YEARS("FiscalHalfYears"),
    @XmlEnumValue("FiscalHalfYearOfYear")
    FISCAL_HALF_YEAR_OF_YEAR("FiscalHalfYearOfYear"),
    @XmlEnumValue("FiscalMonths")
    FISCAL_MONTHS("FiscalMonths"),
    @XmlEnumValue("FiscalMonthOfHalfYear")
    FISCAL_MONTH_OF_HALF_YEAR("FiscalMonthOfHalfYear"),
    @XmlEnumValue("FiscalMonthOfQuarter")
    FISCAL_MONTH_OF_QUARTER("FiscalMonthOfQuarter"),
    @XmlEnumValue("FiscalMonthOfTrimester")
    FISCAL_MONTH_OF_TRIMESTER("FiscalMonthOfTrimester"),
    @XmlEnumValue("FiscalMonthOfYear")
    FISCAL_MONTH_OF_YEAR("FiscalMonthOfYear"),
    @XmlEnumValue("FiscalQuarters")
    FISCAL_QUARTERS("FiscalQuarters"),
    @XmlEnumValue("FiscalQuarterOfHalfYear")
    FISCAL_QUARTER_OF_HALF_YEAR("FiscalQuarterOfHalfYear"),
    @XmlEnumValue("FiscalQuarterOfYear")
    FISCAL_QUARTER_OF_YEAR("FiscalQuarterOfYear"),
    @XmlEnumValue("FiscalTrimesters")
    FISCAL_TRIMESTERS("FiscalTrimesters"),
    @XmlEnumValue("FiscalTrimesterOfYear")
    FISCAL_TRIMESTER_OF_YEAR("FiscalTrimesterOfYear"),
    @XmlEnumValue("FiscalWeeks")
    FISCAL_WEEKS("FiscalWeeks"),
    @XmlEnumValue("FiscalWeekOfHalfYear")
    FISCAL_WEEK_OF_HALF_YEAR("FiscalWeekOfHalfYear"),
    @XmlEnumValue("FiscalWeekOfMonth")
    FISCAL_WEEK_OF_MONTH("FiscalWeekOfMonth"),
    @XmlEnumValue("FiscalWeekOfQuarter")
    FISCAL_WEEK_OF_QUARTER("FiscalWeekOfQuarter"),
    @XmlEnumValue("FiscalWeekOfTrimester")
    FISCAL_WEEK_OF_TRIMESTER("FiscalWeekOfTrimester"),
    @XmlEnumValue("FiscalWeekOfYear")
    FISCAL_WEEK_OF_YEAR("FiscalWeekOfYear"),
    @XmlEnumValue("FiscalYears")
    FISCAL_YEARS("FiscalYears"),
    @XmlEnumValue("FormattingColor")
    FORMATTING_COLOR("FormattingColor"),
    @XmlEnumValue("FormattingFont")
    FORMATTING_FONT("FormattingFont"),
    @XmlEnumValue("FormattingFontEffects")
    FORMATTING_FONT_EFFECTS("FormattingFontEffects"),
    @XmlEnumValue("FormattingFontSize")
    FORMATTING_FONT_SIZE("FormattingFontSize"),
    @XmlEnumValue("FormattingOrder")
    FORMATTING_ORDER("FormattingOrder"),
    @XmlEnumValue("FormattingSubtotal")
    FORMATTING_SUBTOTAL("FormattingSubtotal"),
    @XmlEnumValue("GeoBoundaryBottom")
    GEO_BOUNDARY_BOTTOM("GeoBoundaryBottom"),
    @XmlEnumValue("GeoBoundaryFront")
    GEO_BOUNDARY_FRONT("GeoBoundaryFront"),
    @XmlEnumValue("GeoBoundaryLeft")
    GEO_BOUNDARY_LEFT("GeoBoundaryLeft"),
    @XmlEnumValue("GeoBoundaryPolygon")
    GEO_BOUNDARY_POLYGON("GeoBoundaryPolygon"),
    @XmlEnumValue("GeoBoundaryRear")
    GEO_BOUNDARY_REAR("GeoBoundaryRear"),
    @XmlEnumValue("GeoBoundaryRight")
    GEO_BOUNDARY_RIGHT("GeoBoundaryRight"),
    @XmlEnumValue("GeoBoundaryTop")
    GEO_BOUNDARY_TOP("GeoBoundaryTop"),
    @XmlEnumValue("GeoCentroidX")
    GEO_CENTROID_X("GeoCentroidX"),
    @XmlEnumValue("GeoCentroidY")
    GEO_CENTROID_Y("GeoCentroidY"),
    @XmlEnumValue("GeoCentroidZ")
    GEO_CENTROID_Z("GeoCentroidZ"),
    @XmlEnumValue("HalfYears")
    HALF_YEARS("HalfYears"),
    @XmlEnumValue("HalfYearOfYear")
    HALF_YEAR_OF_YEAR("HalfYearOfYear"),
    @XmlEnumValue("Hours")
    HOURS("Hours"),
    ID("ID"),
    @XmlEnumValue("Image")
    IMAGE("Image"),
    @XmlEnumValue("ImageBmp")
    IMAGE_BMP("ImageBmp"),
    @XmlEnumValue("ImageGif")
    IMAGE_GIF("ImageGif"),
    @XmlEnumValue("ImageJpg")
    IMAGE_JPG("ImageJpg"),
    @XmlEnumValue("ImagePng")
    IMAGE_PNG("ImagePng"),
    @XmlEnumValue("ImageTiff")
    IMAGE_TIFF("ImageTiff"),
    @XmlEnumValue("ImageUrl")
    IMAGE_URL("ImageUrl"),
    @XmlEnumValue("IsHoliday")
    IS_HOLIDAY("IsHoliday"),
    @XmlEnumValue("Iso8601Date")
    ISO_8601_DATE("Iso8601Date"),
    @XmlEnumValue("Iso8601DayOfWeek")
    ISO_8601_DAY_OF_WEEK("Iso8601DayOfWeek"),
    @XmlEnumValue("Iso8601DayOfYear")
    ISO_8601_DAY_OF_YEAR("Iso8601DayOfYear"),
    @XmlEnumValue("Iso8601Weeks")
    ISO_8601_WEEKS("Iso8601Weeks"),
    @XmlEnumValue("Iso8601WeekOfYear")
    ISO_8601_WEEK_OF_YEAR("Iso8601WeekOfYear"),
    @XmlEnumValue("Iso8601Years")
    ISO_8601_YEARS("Iso8601Years"),
    @XmlEnumValue("IsPeakDay")
    IS_PEAK_DAY("IsPeakDay"),
    @XmlEnumValue("IsWeekDay")
    IS_WEEK_DAY("IsWeekDay"),
    @XmlEnumValue("IsWorkingDay")
    IS_WORKING_DAY("IsWorkingDay"),
    @XmlEnumValue("ManufacturingDate")
    MANUFACTURING_DATE("ManufacturingDate"),
    @XmlEnumValue("ManufacturingDayOfHalfYear")
    MANUFACTURING_DAY_OF_HALF_YEAR("ManufacturingDayOfHalfYear"),
    @XmlEnumValue("ManufacturingDayOfMonth")
    MANUFACTURING_DAY_OF_MONTH("ManufacturingDayOfMonth"),
    @XmlEnumValue("ManufacturingDayOfQuarter")
    MANUFACTURING_DAY_OF_QUARTER("ManufacturingDayOfQuarter"),
    @XmlEnumValue("ManufacturingDayOfWeek")
    MANUFACTURING_DAY_OF_WEEK("ManufacturingDayOfWeek"),
    @XmlEnumValue("ManufacturingDayOfYear")
    MANUFACTURING_DAY_OF_YEAR("ManufacturingDayOfYear"),
    @XmlEnumValue("ManufacturingHalfYears")
    MANUFACTURING_HALF_YEARS("ManufacturingHalfYears"),
    @XmlEnumValue("ManufacturingHalfYearOfYear")
    MANUFACTURING_HALF_YEAR_OF_YEAR("ManufacturingHalfYearOfYear"),
    @XmlEnumValue("ManufacturingMonths")
    MANUFACTURING_MONTHS("ManufacturingMonths"),
    @XmlEnumValue("ManufacturingMonthOfHalfYear")
    MANUFACTURING_MONTH_OF_HALF_YEAR("ManufacturingMonthOfHalfYear"),
    @XmlEnumValue("ManufacturingMonthOfQuarter")
    MANUFACTURING_MONTH_OF_QUARTER("ManufacturingMonthOfQuarter"),
    @XmlEnumValue("ManufacturingMonthOfYear")
    MANUFACTURING_MONTH_OF_YEAR("ManufacturingMonthOfYear"),
    @XmlEnumValue("ManufacturingQuarters")
    MANUFACTURING_QUARTERS("ManufacturingQuarters"),
    @XmlEnumValue("ManufacturingQuarterOfHalfYear")
    MANUFACTURING_QUARTER_OF_HALF_YEAR("ManufacturingQuarterOfHalfYear"),
    @XmlEnumValue("ManufacturingQuarterOfYear")
    MANUFACTURING_QUARTER_OF_YEAR("ManufacturingQuarterOfYear"),
    @XmlEnumValue("ManufacturingWeeks")
    MANUFACTURING_WEEKS("ManufacturingWeeks"),
    @XmlEnumValue("ManufacturingWeekOfHalfYear")
    MANUFACTURING_WEEK_OF_HALF_YEAR("ManufacturingWeekOfHalfYear"),
    @XmlEnumValue("ManufacturingWeekOfMonth")
    MANUFACTURING_WEEK_OF_MONTH("ManufacturingWeekOfMonth"),
    @XmlEnumValue("ManufacturingWeekOfQuarter")
    MANUFACTURING_WEEK_OF_QUARTER("ManufacturingWeekOfQuarter"),
    @XmlEnumValue("ManufacturingWeekOfYear")
    MANUFACTURING_WEEK_OF_YEAR("ManufacturingWeekOfYear"),
    @XmlEnumValue("ManufacturingYears")
    MANUFACTURING_YEARS("ManufacturingYears"),
    @XmlEnumValue("Minutes")
    MINUTES("Minutes"),
    @XmlEnumValue("Months")
    MONTHS("Months"),
    @XmlEnumValue("MonthOfHalfYear")
    MONTH_OF_HALF_YEAR("MonthOfHalfYear"),
    @XmlEnumValue("MonthOfQuarter")
    MONTH_OF_QUARTER("MonthOfQuarter"),
    @XmlEnumValue("MonthOfTrimester")
    MONTH_OF_TRIMESTER("MonthOfTrimester"),
    @XmlEnumValue("MonthOfYear")
    MONTH_OF_YEAR("MonthOfYear"),
    @XmlEnumValue("OrganizationalUnit")
    ORGANIZATIONAL_UNIT("OrganizationalUnit"),
    @XmlEnumValue("OrgTitle")
    ORG_TITLE("OrgTitle"),
    @XmlEnumValue("PercentOwnership")
    PERCENT_OWNERSHIP("PercentOwnership"),
    @XmlEnumValue("PercentVoteRight")
    PERCENT_VOTE_RIGHT("PercentVoteRight"),
    @XmlEnumValue("Person")
    PERSON("Person"),
    @XmlEnumValue("PersonContact")
    PERSON_CONTACT("PersonContact"),
    @XmlEnumValue("PersonDemographic")
    PERSON_DEMOGRAPHIC("PersonDemographic"),
    @XmlEnumValue("PersonFirstName")
    PERSON_FIRST_NAME("PersonFirstName"),
    @XmlEnumValue("PersonFullName")
    PERSON_FULL_NAME("PersonFullName"),
    @XmlEnumValue("PersonLastName")
    PERSON_LAST_NAME("PersonLastName"),
    @XmlEnumValue("PersonMiddleName")
    PERSON_MIDDLE_NAME("PersonMiddleName"),
    @XmlEnumValue("PhysicalColor")
    PHYSICAL_COLOR("PhysicalColor"),
    @XmlEnumValue("PhysicalDensity")
    PHYSICAL_DENSITY("PhysicalDensity"),
    @XmlEnumValue("PhysicalDepth")
    PHYSICAL_DEPTH("PhysicalDepth"),
    @XmlEnumValue("PhysicalHeight")
    PHYSICAL_HEIGHT("PhysicalHeight"),
    @XmlEnumValue("PhysicalSize")
    PHYSICAL_SIZE("PhysicalSize"),
    @XmlEnumValue("PhysicalVolume")
    PHYSICAL_VOLUME("PhysicalVolume"),
    @XmlEnumValue("PhysicalWeight")
    PHYSICAL_WEIGHT("PhysicalWeight"),
    @XmlEnumValue("PhysicalWidth")
    PHYSICAL_WIDTH("PhysicalWidth"),
    @XmlEnumValue("Point")
    POINT("Point"),
    @XmlEnumValue("PostalCode")
    POSTAL_CODE("PostalCode"),
    @XmlEnumValue("Product")
    PRODUCT("Product"),
    @XmlEnumValue("ProductBrand")
    PRODUCT_BRAND("ProductBrand"),
    @XmlEnumValue("ProductCategory")
    PRODUCT_CATEGORY("ProductCategory"),
    @XmlEnumValue("ProductGroup")
    PRODUCT_GROUP("ProductGroup"),
    @XmlEnumValue("ProductSKU")
    PRODUCT_SKU("ProductSKU"),
    @XmlEnumValue("Project")
    PROJECT("Project"),
    @XmlEnumValue("ProjectCode")
    PROJECT_CODE("ProjectCode"),
    @XmlEnumValue("ProjectCompletion")
    PROJECT_COMPLETION("ProjectCompletion"),
    @XmlEnumValue("ProjectEndDate")
    PROJECT_END_DATE("ProjectEndDate"),
    @XmlEnumValue("ProjectName")
    PROJECT_NAME("ProjectName"),
    @XmlEnumValue("ProjectStartDate")
    PROJECT_START_DATE("ProjectStartDate"),
    @XmlEnumValue("Promotion")
    PROMOTION("Promotion"),
    @XmlEnumValue("QtyRangeHigh")
    QTY_RANGE_HIGH("QtyRangeHigh"),
    @XmlEnumValue("QtyRangeLow")
    QTY_RANGE_LOW("QtyRangeLow"),
    @XmlEnumValue("Quantitative")
    QUANTITATIVE("Quantitative"),
    @XmlEnumValue("Quarters")
    QUARTERS("Quarters"),
    @XmlEnumValue("QuarterOfHalfYear")
    QUARTER_OF_HALF_YEAR("QuarterOfHalfYear"),
    @XmlEnumValue("QuarterOfYear")
    QUARTER_OF_YEAR("QuarterOfYear"),
    @XmlEnumValue("Rate")
    RATE("Rate"),
    @XmlEnumValue("RateType")
    RATE_TYPE("RateType"),
    @XmlEnumValue("Region")
    REGION("Region"),
    @XmlEnumValue("Regular")
    REGULAR("Regular"),
    @XmlEnumValue("RelationToParent")
    RELATION_TO_PARENT("RelationToParent"),
    @XmlEnumValue("ReportingDate")
    REPORTING_DATE("ReportingDate"),
    @XmlEnumValue("ReportingDayOfHalfYear")
    REPORTING_DAY_OF_HALF_YEAR("ReportingDayOfHalfYear"),
    @XmlEnumValue("ReportingDayOfMonth")
    REPORTING_DAY_OF_MONTH("ReportingDayOfMonth"),
    @XmlEnumValue("ReportingDayOfQuarter")
    REPORTING_DAY_OF_QUARTER("ReportingDayOfQuarter"),
    @XmlEnumValue("ReportingDayOfTrimester")
    REPORTING_DAY_OF_TRIMESTER("ReportingDayOfTrimester"),
    @XmlEnumValue("ReportingDayOfWeek")
    REPORTING_DAY_OF_WEEK("ReportingDayOfWeek"),
    @XmlEnumValue("ReportingDayOfYear")
    REPORTING_DAY_OF_YEAR("ReportingDayOfYear"),
    @XmlEnumValue("ReportingHalfYears")
    REPORTING_HALF_YEARS("ReportingHalfYears"),
    @XmlEnumValue("ReportingHalfYearOfYear")
    REPORTING_HALF_YEAR_OF_YEAR("ReportingHalfYearOfYear"),
    @XmlEnumValue("ReportingMonths")
    REPORTING_MONTHS("ReportingMonths"),
    @XmlEnumValue("ReportingMonthOfHalfYear")
    REPORTING_MONTH_OF_HALF_YEAR("ReportingMonthOfHalfYear"),
    @XmlEnumValue("ReportingMonthOfQuarter")
    REPORTING_MONTH_OF_QUARTER("ReportingMonthOfQuarter"),
    @XmlEnumValue("ReportingMonthOfTrimester")
    REPORTING_MONTH_OF_TRIMESTER("ReportingMonthOfTrimester"),
    @XmlEnumValue("ReportingMonthOfYear")
    REPORTING_MONTH_OF_YEAR("ReportingMonthOfYear"),
    @XmlEnumValue("ReportingQuarters")
    REPORTING_QUARTERS("ReportingQuarters"),
    @XmlEnumValue("ReportingQuarterOfHalfYear")
    REPORTING_QUARTER_OF_HALF_YEAR("ReportingQuarterOfHalfYear"),
    @XmlEnumValue("ReportingQuarterOfYear")
    REPORTING_QUARTER_OF_YEAR("ReportingQuarterOfYear"),
    @XmlEnumValue("ReportingTrimesters")
    REPORTING_TRIMESTERS("ReportingTrimesters"),
    @XmlEnumValue("ReportingTrimesterOfYear")
    REPORTING_TRIMESTER_OF_YEAR("ReportingTrimesterOfYear"),
    @XmlEnumValue("ReportingWeeks")
    REPORTING_WEEKS("ReportingWeeks"),
    @XmlEnumValue("ReportingWeekOfHalfYear")
    REPORTING_WEEK_OF_HALF_YEAR("ReportingWeekOfHalfYear"),
    @XmlEnumValue("ReportingWeekOfMonth")
    REPORTING_WEEK_OF_MONTH("ReportingWeekOfMonth"),
    @XmlEnumValue("ReportingWeekOfQuarter")
    REPORTING_WEEK_OF_QUARTER("ReportingWeekOfQuarter"),
    @XmlEnumValue("ReportingWeekOfTrimester")
    REPORTING_WEEK_OF_TRIMESTER("ReportingWeekOfTrimester"),
    @XmlEnumValue("ReportingWeekOfYear")
    REPORTING_WEEK_OF_YEAR("ReportingWeekOfYear"),
    @XmlEnumValue("ReportingYears")
    REPORTING_YEARS("ReportingYears"),
    @XmlEnumValue("Representative")
    REPRESENTATIVE("Representative"),
    @XmlEnumValue("RowNumber")
    ROW_NUMBER("RowNumber"),
    @XmlEnumValue("ScdEndDate")
    SCD_END_DATE("ScdEndDate"),
    @XmlEnumValue("ScdOriginalID")
    SCD_ORIGINAL_ID("ScdOriginalID"),
    @XmlEnumValue("ScdStartDate")
    SCD_START_DATE("ScdStartDate"),
    @XmlEnumValue("ScdStatus")
    SCD_STATUS("ScdStatus"),
    @XmlEnumValue("Scenario")
    SCENARIO("Scenario"),
    @XmlEnumValue("Seconds")
    SECONDS("Seconds"),
    @XmlEnumValue("Sequence")
    SEQUENCE("Sequence"),
    @XmlEnumValue("ShortCaption")
    SHORT_CAPTION("ShortCaption"),
    @XmlEnumValue("StateOrProvince")
    STATE_OR_PROVINCE("StateOrProvince"),
    @XmlEnumValue("TenDay")
    TEN_DAY("TenDay"),
    @XmlEnumValue("TenDayOfHalfYear")
    TEN_DAY_OF_HALF_YEAR("TenDayOfHalfYear"),
    @XmlEnumValue("TenDayOfMonth")
    TEN_DAY_OF_MONTH("TenDayOfMonth"),
    @XmlEnumValue("TenDayOfQuarter")
    TEN_DAY_OF_QUARTER("TenDayOfQuarter"),
    @XmlEnumValue("TenDayOfTrimester")
    TEN_DAY_OF_TRIMESTER("TenDayOfTrimester"),
    @XmlEnumValue("TenDayOfYear")
    TEN_DAY_OF_YEAR("TenDayOfYear"),
    @XmlEnumValue("Trimesters")
    TRIMESTERS("Trimesters"),
    @XmlEnumValue("TrimesterOfYear")
    TRIMESTER_OF_YEAR("TrimesterOfYear"),
    @XmlEnumValue("UndefinedTime")
    UNDEFINED_TIME("UndefinedTime"),
    @XmlEnumValue("Utility")
    UTILITY("Utility"),
    @XmlEnumValue("Version")
    VERSION("Version"),
    @XmlEnumValue("WebHtml")
    WEB_HTML("WebHtml"),
    @XmlEnumValue("WebMailAlias")
    WEB_MAIL_ALIAS("WebMailAlias"),
    @XmlEnumValue("WebUrl")
    WEB_URL("WebUrl"),
    @XmlEnumValue("WebXmlOrXsl")
    WEB_XML_OR_XSL("WebXmlOrXsl"),
    @XmlEnumValue("WeekOfYear")
    WEEK_OF_YEAR("WeekOfYear"),
    @XmlEnumValue("Weeks")
    WEEKS("Weeks"),
    @XmlEnumValue("WinterSummerSeason")
    WINTER_SUMMER_SEASON("WinterSummerSeason"),
    @XmlEnumValue("Years")
    YEARS("Years");
    private final String value;

    DimensionAttributeTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DimensionAttributeTypeEnumType fromValue(String v) {
        for (DimensionAttributeTypeEnumType c: DimensionAttributeTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
