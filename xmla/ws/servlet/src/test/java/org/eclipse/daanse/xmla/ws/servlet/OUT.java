package org.eclipse.daanse.xmla.ws.servlet;

import jakarta.xml.bind.annotation.XmlAttribute;

public class OUT {
    OUT(int i) {
        this.i = i;
    }

    @XmlAttribute
    public int i;

}
