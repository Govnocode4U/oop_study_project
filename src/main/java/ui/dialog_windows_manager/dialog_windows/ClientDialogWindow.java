package ui.dialog_windows_manager.dialog_windows;

import org.utils.Client;
import ui.dialog_windows_manager.AbstractTableManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClientDialogWindow extends AbstractTableManager {

    @Override
    public void saveAction(List<String> actionData, EntityManager em) {
        Client client = new Client(actionData.get(0), actionData.get(1), actionData.get(2));

        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }

    public List<?> getItems(EntityManager em) {
        Query query = em.createQuery("FROM Client ");
        return query.getResultList();
    }


    public Object[] getRow(Object item) {
        Client entity = (Client) item;
        return new Object[]{entity.getId(), entity.getFullName(), entity.getPhone(), entity.getEmail()};
    }

    @Override
    protected Class<?> getEntityClass() {
        return Client.class;
    }

    @Override
    public void updateData(int rowIndex, int columnIndex, String newValue, DefaultTableModel model, EntityManager em) {
        int id = (int) model.getValueAt(rowIndex, 0);

        Client client = em.find(Client.class, id);

        switch (columnIndex) {
            case 1 -> client.setFullName(newValue);
            case 2 -> client.setPhone(newValue);
            case 3 -> client.setEmail(newValue);
        }

        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }
}
