package ui.dialog_windows_manager.dialog_windows;

import org.utils.Master;
import ui.dialog_windows_manager.AbstractTableManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MasterDialogWindow extends AbstractTableManager {
    @Override
    public void saveAction(List<String> actionData, EntityManager em) {
        if (actionData == null || actionData.size() < 2) {
            throw new IllegalArgumentException("Invalid action data");
        }
        String fullName = actionData.get(0);
        String specialization = actionData.get(1);

        Master master = new Master(fullName, specialization);

        em.getTransaction().begin();
        em.persist(master);
        em.getTransaction().commit();
    }

    @Override
    public List<?> getItems(EntityManager em) {
        Query query = em.createQuery("FROM Master ");
        return query.getResultList();
    }

    @Override
    public Object[] getRow(Object item) {
        Master entity = (Master) item;
        return new Object[]{entity.getId(), entity.getFullName(), entity.getSpecialization()};
    }


    @Override
    protected Class<?> getEntityClass() {
        return Master.class;
    }

    @Override
    public void updateData(int rowIndex, int columnIndex, String newValue, DefaultTableModel model, EntityManager em) {
        int id = (int) model.getValueAt(rowIndex, 0);

        Master master = em.find(Master.class, id);

        switch (columnIndex) {
            case 1 -> master.setFullName(newValue);
            case 2 -> master.setSpecialization(newValue);
        }

        em.getTransaction().begin();
        em.persist(master);
        em.getTransaction().commit();
    }
}
