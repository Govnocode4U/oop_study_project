package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.TransformerFactoryConfigurationError;

public class FileReader {
    static NodeList readDocument(Document document) throws TransformerFactoryConfigurationError {
        Element rootElement = document.getDocumentElement();
        NodeList nodeList = rootElement.getElementsByTagName("Client");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id = element.getElementsByTagName("Id").item(0).getTextContent();
                String name = element.getElementsByTagName("Name").item(0).getTextContent();
                String phone = element.getElementsByTagName("Phone").item(0).getTextContent();
                String email = element.getElementsByTagName("Email").item(0).getTextContent();

                System.out.println(id + " " + name + " " + phone + " " + email);
            }
        }

        return nodeList;
    }
}
