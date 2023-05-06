package ui;

import ui.dialog_windows_manager.calendar_window.CalendarWindow;
import ui.factory.TableWindowBuilder;
import ui.logger.WindowTrackingLogger;
import ui.ui_constants.ConstantUtils;
import ui.ui_constants.UiConstants;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Map;


//#TODO: Разработать ПК для администратора салона красоты.
//       В ПК должна храниться информация о клиентах салона, мастерах с указанием их специализации, услугах и их ценах.
//       Администратор может добавлять, изменять или удалять эту информацию. Ему могут понадобиться следующие сведения и возможности:
//          ·Просмотр расписания работы салона на выбранный день
//          ·Просмотр загруженности определённого мастера на день/неделю/месяц
//          ·Запись клиента на процедуру к определённому мастеру
//          ·Отчет о работе салона за неделю/месяц: сколько клиентов было обслужено, сколько денег пришло, какие мастера самые востребованные и какие – самые доходные
public class MainWindow extends JFrame {
    private final TableUIInterface clientTable;
    private final TableUIInterface mastersTable;
    private final TableUIInterface serviceTable;
    private final JTabbedPane tabbedPane;
    WindowTrackingLogger windowTrackingLogger = new WindowTrackingLogger();


    public MainWindow(EntityManagerFactory emf) {
        // Создание окон для таблицы клиентов и мастеров
        clientTable = createWindow(emf, "CLIENT");
        mastersTable = createWindow(emf, "MASTER");
        serviceTable = createWindow(emf, "SERVICE");

        // Создание вкладок и добавление их в объект JTabbedPane
        tabbedPane = new JTabbedPane();

        // Добавление объекта WindowTrackingLogger для отслеживания активных окон
        windowTrackingLogger.setTabbedLogger(tabbedPane);

        // Добавление вкладок в объект JTabbedPane
        addTabsToTabbedPane();
        getContentPane().add(tabbedPane);
        setSize(500, 300);
        setVisible(true);
    }

    private TableUIInterface createWindow(EntityManagerFactory emf, String title) {
        String tableTitle = title + "_TABLE";
        String buttonTitle = title + "_BUTTON";
        String labelTitle = title + "_LABEL";
        return new TableWindowBuilder(
                new JFrame(title),
                ConstantUtils.getValuesForWindow(UiConstants.values(), tableTitle),
                ConstantUtils.getValuesForWindow(UiConstants.values(), buttonTitle),
                ConstantUtils.getValuesForWindow(UiConstants.values(), labelTitle),
                emf
        );
    }

    private void addTabsToTabbedPane() {
        Map<String, TableUIInterface> myMap = Map.of(
                "Clients", clientTable,
                "Masters", mastersTable,
                "Services", serviceTable
        );

        myMap.forEach(this::addTab);
    }

    public void addTab(String title, TableUIInterface tableUI) {
        JScrollPane scrollPane = new JScrollPane(tableUI.getTable());
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(tableUI.getButtonPanel(), BorderLayout.SOUTH);

        tabbedPane.addTab(title, panel);
    }
}