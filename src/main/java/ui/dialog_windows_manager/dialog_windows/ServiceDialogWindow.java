package ui.dialog_windows_manager.dialog_windows;

import org.utils.Service;
import ui.dialog_windows_manager.AbstractTableManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ServiceDialogWindow extends AbstractTableManager {
    @Override
    public void saveAction(List<String> actionData, EntityManager em) {
        Service service = new Service(actionData.get(0), actionData.get(1));

        em.getTransaction().begin();
        em.persist(service);
        em.getTransaction().commit();
    }

    public List<?> getItems(EntityManager em) {
        Query query = em.createQuery("FROM Service ");
        return query.getResultList();
    }


    public Object[] getRow(Object item) {
        Service entity = (Service) item;
        return new Object[]{entity.getId(), entity.getFullName(), entity.getPrice()};
    }

    @Override
    protected Class<?> getEntityClass() {
        return Service.class;
    }

    @Override
    public void updateData(int rowIndex, int columnIndex, String newValue, DefaultTableModel model, EntityManager em) {
        int id = (int) model.getValueAt(rowIndex, 0);

        Service service = em.find(Service.class, id);

        switch (columnIndex) {
            case 1 -> service.setFullName(newValue);
            case 2 -> service.setPrice(newValue);
        }

        em.getTransaction().begin();
        em.persist(service);
        em.getTransaction().commit();
    }
}