package org.eclipse.daanse.xmla.model.jaxb.ext;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "sspiHandshake" })
public class ReturnValue {

	@XmlElement(name = "SspiHandshake", required = true)
	protected byte[] sspiHandshake;

	public byte[] getSspiHandshake() {
		return sspiHandshake;
	}

	public void setSspiHandshake(byte[] value) {
		this.sspiHandshake = value;
	}
}