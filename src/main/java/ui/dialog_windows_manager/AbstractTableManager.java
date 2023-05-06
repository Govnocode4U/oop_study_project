package ui.dialog_windows_manager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class AbstractTableManager implements DialogWindowInterface {
    protected List<?> items;
    public abstract List<?> getItems(EntityManager em);

    public abstract Object[] getRow(Object item);

    public void printHello() {
        System.out.println("HELLO");
    }
    @Override
    public void setModelRows(EntityManager em, DefaultTableModel model) {
        items = getItems(em);
        model.setRowCount(0);
        items.stream()
                .map(this::getRow)
                .forEach(model::addRow);
        model.fireTableDataChanged();
    }

    @Override
    public void deleteSelected(JTable table, EntityManager em, DefaultTableModel model) {
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(null, "Please select at least one row to delete.");
            return;
        }
        em.getTransaction().begin();
        for (int row : selectedRows) {
            int itemId = (int) model.getValueAt(row, 0);
            Query query = em.createQuery(String.format("DELETE FROM %s where id = :id", getEntityClass().getSimpleName()));
            query.setParameter("id", itemId);
            query.executeUpdate();
        }
        em.getTransaction().commit();
        setModelRows(em, model);
    }


    protected abstract Class<?> getEntityClass();
}

