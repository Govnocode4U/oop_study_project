package ui.logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class WindowTrackingLogger implements ChangeListener {
    JPanel buttonPanel;
    static String currentTab;
    private static final Logger logger = LogManager.getLogger(WindowTrackingLogger.class);
    public void setTabbedLogger(JTabbedPane tabbedPane) {
        tabbedPane.addChangeListener(this);
    }
    public void setButtonsLogger(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }
    @Override
    public void stateChanged(ChangeEvent e) {

        // Получаем индекс текущей выбранной вкладки
        JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        int selectedIndex = tabbedPane.getSelectedIndex();

        // Получаем название текущей выбранной вкладки по индексу
        currentTab = tabbedPane.getTitleAt(selectedIndex);

        // Выводим в консоль название текущей выбранной вкладки
        logger.info("Selected tab changed: " + currentTab);
    }

    public void printPressedButton(String pressedButton) {
        logger.info("Button Pressed:" + pressedButton);
    }
}
