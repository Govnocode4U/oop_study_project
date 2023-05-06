package ui;

import javax.swing.*;

public interface TableUIInterface {

    JPanel getButtonPanel();
    JTable getTable();
    default boolean confirmChange() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to change this value?");
        return confirm == JOptionPane.YES_OPTION;
    }
}
