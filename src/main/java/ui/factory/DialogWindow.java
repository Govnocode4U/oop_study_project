package ui.factory;

import ui.dialog_windows_manager.DialogWindowInterface;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DialogWindow extends JFrame implements ActionListener {
    EntityManager em;
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");
    JDialog addWindowDialog = new JDialog(this, "Add Dialog Window", true);
    JPanel inputPanel;
    DialogWindowInterface dialogWindowHandler;
    DefaultTableModel model;
    List<String> dataAction = new ArrayList<>();

    public DialogWindow(List<String> labelsList, DefaultTableModel model, EntityManager em) {
        this.model = model;
        this.em = em;

        addWindowDialog.setSize(300, 200);

        inputPanel = new JPanel(new GridLayout(3, 2));

        for (String label : labelsList) {
            JLabel tmpLabel = new JLabel(label);
            JTextField textField = new JTextField();

            inputPanel.add(tmpLabel);
            inputPanel.add(textField);
        }

        cancelButton.addActionListener(this);
        saveButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        addWindowDialog.add(inputPanel, BorderLayout.CENTER);

        addWindowDialog.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == saveButton) {
            getTextFields();
            dialogWindowHandler.saveAction(dataAction, em);
            dialogWindowHandler.setModelRows(em, model);
        }
        else if (e.getSource() == cancelButton) {
            addWindowDialog.dispose();
        }
        dialogWindowHandler.clearFields(addWindowDialog.getContentPane());
        dataAction.clear();
        addWindowDialog.dispose();

    }

    public void showWindow() {
        addWindowDialog.setVisible(true);
    }

    public void setDialogWindowHandler(DialogWindowInterface dialogWindowHandler) {
        this.dialogWindowHandler = dialogWindowHandler;
    }

    public void getTextFields() {
        Component[] components = inputPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField textField) {
                String text = textField.getText();
                dataAction.add(text);
            }
        }
    }
}
