package ui.factory;

import ui.TableUIInterface;
import ui.dialog_windows_manager.DialogWindowInterface;
import ui.logger.WindowTrackingLogger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TableWindowBuilder extends JFrame implements ActionListener, TableUIInterface, TableModelListener {
    EntityManagerFactory emf;
    EntityManager em;
    private final DefaultTableModel model;
    private final DetectParentFrame detectParentFrame = new DetectParentFrame();
    private final JTable table;
    private final JPanel buttonPanel = new JPanel();
    private final JFrame parentFrame;
    private final List<JButton> buttonsList = new ArrayList<>();
    private final List<String> labelsList;
    private final DialogWindow dialogWindow;
    Object oldValue;
    WindowTrackingLogger windowTrackingLogger = new WindowTrackingLogger();

    public TableWindowBuilder(JFrame frame, List<String> tableModelList, List<String> buttonNamesList,
                              List<String> labelsNamesList, EntityManagerFactory entityManagerFactory) {
        this.emf = entityManagerFactory;
        this.em = emf.createEntityManager();
        this.parentFrame = frame;
        this.labelsList = labelsNamesList;

        String[] columnNames = tableModelList.toArray(new String[0]);

        model = new DefaultTableModel(new Object[]{}, columnNames.length);
        model.addTableModelListener(this);

        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        table = new JTable(model);
        addTableListener();

        dialogWindow = new DialogWindow(getLabelsNamesList(), model, em);
        JScrollPane scrollPane = new JScrollPane(table);

        addButtonsToPanel(buttonNamesList);
        windowTrackingLogger.setButtonsLogger(buttonPanel);


        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        detectParentFrame.setParent(parentFrame.getTitle(), dialogWindow);

        switch (buttonsList.indexOf(e.getSource())) {
            case 0 -> dialogWindow.showWindow();
            case 1 -> dialogWindow.dialogWindowHandler.setModelRows(em, model);
            case 2 -> dialogWindow.dialogWindowHandler.deleteSelected(table, em, model);
        }
        windowTrackingLogger.printPressedButton(e.getActionCommand());

    }

    public List<String> getLabelsNamesList() {
        return labelsList;
    }

    public void addButtonsToPanel(List<String> buttonNamesList) {
        for (String nameButton : buttonNamesList) {
            JButton tmpButton = new JButton(nameButton);
            tmpButton.addActionListener(this);
            buttonPanel.add(tmpButton);

            buttonsList.add(tmpButton);

        }
    }

    @Override
    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    @Override
    public JTable getTable() {
        return table;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        if (row < 0 || column < 0) {
            return;
        }

        String newValue = (String) model.getValueAt(row, column);

        if (!newValue.equals(oldValue)) {
            if (confirmChange()) {
                updateData(row, column, newValue);
            } else {
                restoreOldValue(row, column);
            }
        }
    }

    private void updateData(int row, int column, String newValue) {
        DialogWindowInterface parentDialogWindow = detectParentFrame.setParent(parentFrame.getTitle(), dialogWindow);
        parentDialogWindow.updateData(row, column, newValue, model, em);
    }

    private void restoreOldValue(int row, int column) {
        model.setValueAt(oldValue, row, column);
    }

    private void addTableListener() {
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            int selectedColumn = table.getSelectedColumn();
            if (selectedRow >= 0) {
                oldValue = model.getValueAt(selectedRow, selectedColumn);
            }
        });
    }
}
