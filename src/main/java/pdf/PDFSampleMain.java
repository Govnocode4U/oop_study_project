package pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.utils.Client;

import javax.persistence.EntityManager;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class PDFSampleMain {

    public void makePdf(EntityManager em, File FILE, int numColumns) {

        try {
            Desktop desktop = Desktop.getDesktop();
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(FILE));

            document.open();

            PdfPTable table = new PdfPTable(numColumns);
            addTableHeader(table);
            addRows(table, em);

            document.add(table);
            document.close();

            desktop.open(new File(FILE.toURI()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("ID", "Full Name", "Phone", "Email")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table, EntityManager em) {
        em.getTransaction().begin();
        em.getTransaction().commit();
        List<Client> clients = em.createQuery("FROM Client ").getResultList();

        for (Client client : clients) {
            table.addCell(String.valueOf(client.getId()));
            table.addCell(String.valueOf(client.getFullName()));
            table.addCell(String.valueOf(client.getPhone()));
            table.addCell(String.valueOf(client.getEmail()));
        }
    }

}