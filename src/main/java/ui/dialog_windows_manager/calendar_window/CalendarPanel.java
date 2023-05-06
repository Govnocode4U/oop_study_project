package ui.dialog_windows_manager.calendar_window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.Serial;
import java.util.Calendar;

class CalendarPanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Calendar calendar;
    private final JLabel monthLabel;
    private final JTable table;
    private static final String[] WEEKDAYS = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};


    public CalendarPanel(Calendar calendar) {
        super(new BorderLayout());
        this.calendar = calendar;
        monthLabel = createMonthLabel();
        String[][] days = createDays();
        table = createTable(days);
        table.setDefaultEditor(Object.class, null);
        addComponentsToPanel();
    }
    private JLabel createMonthLabel() {
        System.out.println("ya tut");
        return new JLabel(getMonthName() + " " + calendar.get(Calendar.YEAR), SwingConstants.CENTER);
    }
    private String[][] createDays() {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        if (dayOfWeek == -1) {
            dayOfWeek = 6;
        }
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeksInMonth = (int) Math.ceil((daysInMonth + dayOfWeek - 1) / 7.0);
        String[][] days = new String[weeksInMonth][7];
        for (int row = 0; row < weeksInMonth; row++) {
            for (int col = 0; col < 7; col++) {
                int day = row * 7 + col - dayOfWeek + 2;
                if (day < 1 || day > daysInMonth) {
                    days[row][col] = "";
                } else {
                    days[row][col] = String.valueOf(day);
                }
            }
        }
        return days;
    }


    private JTable createTable(String[][] days) {
        JTable table = new JTable(days, WEEKDAYS);
        table.setColumnSelectionAllowed(true);
        table.setRowHeight(30);
        table.setFont(table.getFont().deriveFont(20f));
        return table;
    }
    private void addComponentsToPanel() {
        add(monthLabel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    public void prevMonth() {
        calendar.add(Calendar.MONTH, -1); // переключаем календарь на предыдущий месяц
        updateTableData();
    }

    public void nextMonth() {
        calendar.add(Calendar.MONTH, 1); // переключаем календарь на следующий месяц
        updateTableData();
    }

    private void updateTableData() {
        String[][] days = createDays();

        DefaultTableModel model = new DefaultTableModel(days, WEEKDAYS);
        table.setModel(model);
        monthLabel.setText(getMonthName() + " " + calendar.get(Calendar.YEAR));
        revalidate(); // перерисовываем окно
        repaint();
    }

    public JTable getTable() {
        return table;
    }

    private String getMonthName() {
        return MONTHS[calendar.get(Calendar.MONTH)];
    }
}
