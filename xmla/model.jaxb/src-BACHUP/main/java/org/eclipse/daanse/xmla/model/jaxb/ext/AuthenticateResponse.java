
package org.eclipse.daanse.xmla.model.jaxb.ext;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "returnValue" })
@XmlRootElement(name = "AuthenticateResponse")
public class AuthenticateResponse {

	@XmlElement(name = "return", required = true)
	protected ReturnValue returnValue;

	public ReturnValue getReturn() {
		return returnValue;
	}

	public void setReturn(ReturnValue value) {
		this.returnValue = value;
	}

}
