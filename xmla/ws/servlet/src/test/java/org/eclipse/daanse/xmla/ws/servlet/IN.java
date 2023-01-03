package org.eclipse.daanse.xmla.ws.servlet;

import jakarta.xml.bind.annotation.XmlAttribute;

public class IN {
    IN(int i) {
        this.i = i;
    }

    @XmlAttribute
    public int i;

}
