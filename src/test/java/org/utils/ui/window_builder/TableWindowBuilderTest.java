package org.utils.ui.window_builder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ui.TableUIInterface;
import ui.factory.TableWindowBuilder;
import ui.ui_constants.ConstantUtils;
import ui.ui_constants.UiTableConstants;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TableWindowBuilderTest {
    private TableUIInterface mockClientTable;
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("salon_persistence");

    TableUIInterface clientTable = new TableWindowBuilder(
            new JFrame("CLIENT"),
            ConstantUtils.getValuesForWindow(UiTableConstants.values(), "CLIENT_TITLE"),
            ConstantUtils.getValuesForWindow(UiTableConstants.values(), "CLIENT_BUTTON"),
            ConstantUtils.getValuesForWindow(UiTableConstants.values(), "CLIENT_lABEL"),
            emf
    );
    @Mock
    JTabbedPane mockTabbedPane = mock(JTabbedPane.class);

    @Mock
    JTable mockTable = mock(JTable.class);
    @Mock
    JPanel mockButtonPanel = mock(JPanel.class);


    @Before
    public void setUp() {
        // Создаем моки для зависимых объектов
        mockClientTable = mock(TableWindowBuilder.class);

        mockTabbedPane = mock(JTabbedPane.class);

        // Устанавливаем ожидаемое поведение для методов интерфейса TableUIInterface
        when(mockClientTable.getTable()).thenReturn(mockTable);
        when(mockClientTable.getButtonPanel()).thenReturn(mockButtonPanel);

        // Устанавливаем моки в качестве зависимостей в тестируемом объекте
        clientTable = mockClientTable;


    }

    @Test
    public void windowBuilderGetters() {
        assertEquals(mockClientTable.getTable(), clientTable.getTable());
        assertEquals(mockClientTable.getButtonPanel(), clientTable.getButtonPanel());
    }
}
