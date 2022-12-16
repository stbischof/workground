
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClrAssembly complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClrAssembly"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:schemas-microsoft-com:xml-analysis}Assembly"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Files"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="File" type="{urn:schemas-microsoft-com:xml-analysis}ClrAssemblyFile" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PermissionSet" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Safe"/&gt;
 *               &lt;enumeration value="ExternalAccess"/&gt;
 *               &lt;enumeration value="Unrestricted"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClrAssembly", propOrder = {
    "files",
    "permissionSet"
})
public class ClrAssembly
    extends Assembly
{

    @XmlElement(name = "Files", required = true)
    protected ClrAssembly.Files files;
    @XmlElement(name = "PermissionSet")
    protected String permissionSet;

    /**
     * Gets the value of the files property.
     * 
     * @return
     *     possible object is
     *     {@link ClrAssembly.Files }
     *     
     */
    public ClrAssembly.Files getFiles() {
        return files;
    }

    /**
     * Sets the value of the files property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClrAssembly.Files }
     *     
     */
    public void setFiles(ClrAssembly.Files value) {
        this.files = value;
    }

    public boolean isSetFiles() {
        return (this.files!= null);
    }

    /**
     * Gets the value of the permissionSet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermissionSet() {
        return permissionSet;
    }

    /**
     * Sets the value of the permissionSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermissionSet(String value) {
        this.permissionSet = value;
    }

    public boolean isSetPermissionSet() {
        return (this.permissionSet!= null);
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
     *       &lt;sequence&gt;
     *         &lt;element name="File" type="{urn:schemas-microsoft-com:xml-analysis}ClrAssemblyFile" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "file"
    })
    public static class Files {

        @XmlElement(name = "File", required = true)
        protected List<ClrAssemblyFile> file;

        /**
         * Gets the value of the file property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the file property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFile().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ClrAssemblyFile }
         * 
         * 
         */
        public List<ClrAssemblyFile> getFile() {
            if (file == null) {
                file = new ArrayList<ClrAssemblyFile>();
            }
            return this.file;
        }

        public boolean isSetFile() {
            return ((this.file!= null)&&(!this.file.isEmpty()));
        }

        public void unsetFile() {
            this.file = null;
        }

    }

}
