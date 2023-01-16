package org.eclipse.daanse.ws.demo;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRegistry;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRegistry()
public class XML {

    @XmlRootElement
    public static class IN {
        public IN() {
        }

        IN(int i) {
            this.i = i;
        }

        @XmlAttribute
        public int i;

    }

    @XmlRootElement
    public static class OUT {
        public OUT() {
        }

        OUT(int i) {
            this.i = i;
        }

        @XmlAttribute
        public int i;

    }
}