package ui.dialog_windows_manager.calendar_window;

import ui.TableUIInterface;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.Calendar;


public class CalendarWindow extends JFrame implements TableUIInterface {
    private static final String[] BUTTONS = {"<", ">"};
    @Serial
    private static final long serialVersionUID = 1L;
    private final JPanel buttonPanel;
    private final CalendarPanel calendarPanel;
    private final JTable calendarTable;

    public CalendarWindow(Calendar calendar) {
        super("Calendar Window");

        // создаем панель с календарем и кнопками управления
        calendarPanel = new CalendarPanel(calendar);
        buttonPanel = createButtonPanel(calendarPanel);
        this.calendarTable = calendarPanel.getTable();

        // добавляем панель с календарем и кнопками на окно
        add(calendarPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // устанавливаем размер и центрируем окно
        setSize(500, 300);
        setLocationRelativeTo(null);

    }


    private JPanel createButtonPanel(CalendarPanel calendarPanel) {
        JPanel buttonPanel = new JPanel();
        for (String label : CalendarWindow.BUTTONS) {
            JButton button = createButton(label, calendarPanel);
            buttonPanel.add(button);
        }
        return buttonPanel;
    }
    private JButton createButton(String label, CalendarPanel calendarPanel) {
        JButton button = new JButton(label);
        switch (label) {
            case "<" -> button.addActionListener(e -> calendarPanel.prevMonth());
            case ">" -> button.addActionListener(e -> calendarPanel.nextMonth());
        }
        return button;
    }

    @Override
    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    @Override
    public JTable getTable() {
        return calendarTable;
    }

    @Override
    public boolean confirmChange() {
        return TableUIInterface.super.confirmChange();
    }
}



