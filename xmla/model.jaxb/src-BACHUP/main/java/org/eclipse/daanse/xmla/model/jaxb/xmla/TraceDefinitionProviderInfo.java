
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trace_Definition_ProviderInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trace_Definition_ProviderInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Data"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;all&gt;
 *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Version"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;all&gt;
 *                             &lt;element name="Major" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="Minor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="BuildNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                           &lt;/all&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trace_Definition_ProviderInfo", propOrder = {
    "data"
})
public class TraceDefinitionProviderInfo {

    @XmlElement(name = "Data", required = true)
    protected TraceDefinitionProviderInfo.Data data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link TraceDefinitionProviderInfo.Data }
     *     
     */
    public TraceDefinitionProviderInfo.Data getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link TraceDefinitionProviderInfo.Data }
     *     
     */
    public void setData(TraceDefinitionProviderInfo.Data value) {
        this.data = value;
    }

    public boolean isSetData() {
        return (this.data!= null);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;all&gt;
     *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Version"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;all&gt;
     *                   &lt;element name="Major" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="Minor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="BuildNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                 &lt;/all&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/all&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Data {

        @XmlElement(name = "Name", required = true)
        protected String name;
        @XmlElement(name = "Version", required = true)
        protected TraceDefinitionProviderInfo.Data.Version version;
        @XmlElement(name = "Type")
        protected String type;
        @XmlElement(name = "Description")
        protected String description;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        public boolean isSetName() {
            return (this.name!= null);
        }

        /**
         * Gets the value of the version property.
         * 
         * @return
         *     possible object is
         *     {@link TraceDefinitionProviderInfo.Data.Version }
         *     
         */
        public TraceDefinitionProviderInfo.Data.Version getVersion() {
            return version;
        }

        /**
         * Sets the value of the version property.
         * 
         * @param value
         *     allowed object is
         *     {@link TraceDefinitionProviderInfo.Data.Version }
         *     
         */
        public void setVersion(TraceDefinitionProviderInfo.Data.Version value) {
            this.version = value;
        }

        public boolean isSetVersion() {
            return (this.version!= null);
        }

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
        }

        public boolean isSetType() {
            return (this.type!= null);
        }

        /**
         * Gets the value of the description property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescription() {
            return description;
        }

        /**
         * Sets the value of the description property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescription(String value) {
            this.description = value;
        }

        public boolean isSetDescription() {
            return (this.description!= null);
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;all&gt;
         *         &lt;element name="Major" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="Minor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="BuildNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *       &lt;/all&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class Version {

            @XmlElement(name = "Major")
            protected String major;
            @XmlElement(name = "Minor")
            protected String minor;
            @XmlElement(name = "BuildNumber")
            protected String buildNumber;

            /**
             * Gets the value of the major property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMajor() {
                return major;
            }

            /**
             * Sets the value of the major property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMajor(String value) {
                this.major = value;
            }

            public boolean isSetMajor() {
                return (this.major!= null);
            }

            /**
             * Gets the value of the minor property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMinor() {
                return minor;
            }

            /**
             * Sets the value of the minor property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMinor(String value) {
                this.minor = value;
            }

            public boolean isSetMinor() {
                return (this.minor!= null);
            }

            /**
             * Gets the value of the buildNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBuildNumber() {
                return buildNumber;
            }

            /**
             * Sets the value of the buildNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBuildNumber(String value) {
                this.buildNumber = value;
            }

            public boolean isSetBuildNumber() {
                return (this.buildNumber!= null);
            }

        }

    }

}
