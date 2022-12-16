
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.model.jaxb.xmla_empty.Emptyresult;
import org.eclipse.daanse.xmla.model.jaxb.xmla_mddataset.Mddataset;
import org.eclipse.daanse.xmla.model.jaxb.xmla_multipleresults.Results;
import org.eclipse.daanse.xmla.model.jaxb.xmla_rowset.Rowset;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for return complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="return"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{urn:schemas-microsoft-com:xml-analysis:mddataset}root"/&gt;
 *         &lt;element ref="{urn:schemas-microsoft-com:xml-analysis:rowset}root"/&gt;
 *         &lt;element ref="{urn:schemas-microsoft-com:xml-analysis:empty}root"/&gt;
 *         &lt;element ref="{http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults}results"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "return", propOrder = { "content" })
public class Return {

	@XmlElementRefs({
			@XmlElementRef(name = "root", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "root", namespace = "urn:schemas-microsoft-com:xml-analysis:rowset", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "root", namespace = "urn:schemas-microsoft-com:xml-analysis:empty", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "results", namespace = "http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults", type = Results.class, required = false) })
	protected List<java.lang.Object> content;

	/**
	 * Gets the rest of the content model.
	 * 
	 * <p>
	 * You are getting this "catch-all" property because of the following reason:
	 * The field name "Root" is used by two different parts of a schema. See: line
	 * 1483 of
	 * file:/home/stbischof/dev/git/org.eclipse.daanse.xmla/org.eclipse.daanse.xmla.definition/xmla.xsd
	 * line 1482 of
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
	 * }{@code <}{@link Emptyresult }{@code >} {@link JAXBElement
	 * }{@code <}{@link Mddataset }{@code >} {@link JAXBElement
	 * }{@code <}{@link Rowset }{@code >} {@link Results }
	 * 
	 * 
	 */
	public List<java.lang.Object> getContent() {
		if (content == null) {
			content = new ArrayList<java.lang.Object>();
		}
		return this.content;
	}

}
