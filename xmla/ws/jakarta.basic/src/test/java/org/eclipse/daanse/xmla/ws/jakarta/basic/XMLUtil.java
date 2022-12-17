package org.eclipse.daanse.xmla.ws.jakarta.basic;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLUtil {

  public static String pretty(String xmlData) throws Exception {
    return pretty(xmlData, 2);
  }

  public static String pretty(String xmlData, int indent) throws Exception {
      System.out.println(xmlData);
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      transformerFactory.setAttribute("indent-number", indent);

      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      StringWriter stringWriter = new StringWriter();
      StreamResult xmlOutput = new StreamResult(stringWriter);

      Source xmlInput = new StreamSource(new StringReader(xmlData));
      transformer.transform(xmlInput, xmlOutput);

      String out = xmlOutput.getWriter()
              .toString();
      System.out.println(out);
      return out;
  }

  public static Document stringToDocument(String xmlStr) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);

    DocumentBuilder builder;
    try {
      builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
      return doc;
    } catch (Exception e) {
      fail(e);
    }
    return null;
  }
}