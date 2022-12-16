
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "requestType", "restrictions", "properties" })
@XmlRootElement(name = "Discover")
public class Discover {

	@XmlElement(name = "RequestType", required = true)
	public String requestType;

	@XmlElement(name = "Restrictions", required = true)
	protected Discover.Restrictions restrictions;
	@XmlElement(name = "Properties", required = true)
	protected Properties properties;

	public Discover() {
		System.out.println();

	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String value) {
		this.requestType = value;
	}

	public Discover.Restrictions getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(Discover.Restrictions value) {
		this.restrictions = value;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties value) {
		this.properties = value;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "restrictionList" })
	public static class Restrictions {

		@XmlElementRef(name = "RestrictionList", type = JAXBElement.class, required = false)
		protected JAXBElement<Discover.Restrictions.RestrictionList> restrictionList;

		/**
		 * Gets the value of the restrictionList property.
		 * 
		 * @return possible object is {@link JAXBElement
		 *         }{@code <}{@link Discover.Restrictions.RestrictionList }{@code >}
		 * 
		 */
		public JAXBElement<Discover.Restrictions.RestrictionList> getRestrictionList() {
			return restrictionList;
		}

		/**
		 * Sets the value of the restrictionList property.
		 * 
		 * @param value allowed object is {@link JAXBElement
		 *              }{@code <}{@link Discover.Restrictions.RestrictionList }{@code >}
		 * 
		 */
		public void setRestrictionList(JAXBElement<Discover.Restrictions.RestrictionList> value) {
			this.restrictionList = value;
		}

		public boolean isSetRestrictionList() {
			return (this.restrictionList != null);
		}

		/**
		 * <p>
		 * Java class for anonymous complex type.
		 * 
		 * <p>
		 * The following schema fragment specifies the expected content contained within
		 * this class.
		 * 
		 * <pre>
		 * &lt;complexType&gt;
		 *   &lt;complexContent&gt;
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
		 *       &lt;sequence&gt;
		 *         &lt;any maxOccurs="unbounded" minOccurs="0"/&gt;
		 *       &lt;/sequence&gt;
		 *     &lt;/restriction&gt;
		 *   &lt;/complexContent&gt;
		 * &lt;/complexType&gt;
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "any" })
		public static class RestrictionList {

			@XmlAnyElement(lax = true)
			protected List<java.lang.Object> any;

			/**
			 * Gets the value of the any property.
			 * 
			 * <p>
			 * This accessor method returns a reference to the live list, not a snapshot.
			 * Therefore any modification you make to the returned list will be present
			 * inside the Jakarta XML Binding object. This is why there is not a
			 * <CODE>set</CODE> method for the any property.
			 * 
			 * <p>
			 * For example, to add a new item, do as follows:
			 * 
			 * <pre>
			 * getAny().add(newItem);
			 * </pre>
			 * 
			 * 
			 * <p>
			 * Objects of the following type(s) are allowed in the list
			 * {@link java.lang.Object }
			 * 
			 * 
			 */
			public List<java.lang.Object> getAny() {
				if (any == null) {
					any = new ArrayList<java.lang.Object>();
				}
				return this.any;
			}

			public boolean isSetAny() {
				return ((this.any != null) && (!this.any.isEmpty()));
			}

			public void unsetAny() {
				this.any = null;
			}

		}

	}

}
