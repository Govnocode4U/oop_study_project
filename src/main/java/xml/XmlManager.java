package xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static xml.ClientXmlWriter.writeDocument;

public class XmlManager {

    public XmlManager() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("src/main/resources/outputFiles/xmlFiles/clients.xml");

            ClientXmlWriter.addNewClient(document);
            writeDocument(document);
            FileReader.readDocument(document);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
        new XmlManager();
    }
}