package xml;

import org.utils.Client;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ClientXmlWriter {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("salon_persistence");
    static EntityManager em = emf.createEntityManager();

    static void addNewClient(Document document) throws TransformerFactoryConfigurationError, DOMException {
        em.getTransaction().begin();
        em.getTransaction().commit();
        List<Client> clients = em.createQuery("FROM Client ").getResultList();

        Node root = document.getDocumentElement();

        for (Client client : clients) {

            Element clientXML = document.createElement("Client");

            Element idClient = document.createElement("Id");
            idClient.setTextContent(String.valueOf(client.getId()));

            Element nameClient = document.createElement("Name");
            nameClient.setTextContent(client.getFullName());

            Element phoneClient = document.createElement("Phone");
            phoneClient.setTextContent(client.getPhone());

            Element emailClient = document.createElement("Email");
            emailClient.setTextContent(client.getEmail());

            clientXML.appendChild(idClient);
            clientXML.appendChild(nameClient);
            clientXML.appendChild(phoneClient);
            clientXML.appendChild(emailClient);

            root.appendChild(clientXML);
        }
    }

    static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();

            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/main/resources/outputFiles/xmlFiles/clients.xml")));
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1");


        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
