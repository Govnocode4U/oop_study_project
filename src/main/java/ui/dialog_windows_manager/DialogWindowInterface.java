package ui.dialog_windows_manager;


import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;



public interface DialogWindowInterface {
    void saveAction(List<String> actionData, EntityManager em);
    void setModelRows(EntityManager em, DefaultTableModel model);
    void deleteSelected(JTable table, EntityManager em, DefaultTableModel model);

    void updateData(int rowIndex, int columnIndex, String newValue, DefaultTableModel model, EntityManager em);

    List<?> getItems(EntityManager em);

    Object[] getRow(Object item);

    default void clearFields(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JComboBox) {
                ((JComboBox) component).setSelectedIndex(0);
            } else if (component instanceof Container) {
                clearFields((Container) component);
            }
        }
    }

}
