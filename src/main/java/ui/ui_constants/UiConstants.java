package ui.ui_constants;

import java.util.Arrays;
import java.util.List;

public enum UiConstants {
    CLIENT_TABLE("ID", "Name", "Phone", "Email"),
    MASTER_TABLE("ID", "Name", "Specialization"),
    SERVICE_TABLE("ID", "Name", "Price"),

    CLIENT_LABEL("Name:", "Phone:", "Email:"),
    MASTER_LABEL("Name:", "Specialization:"),
    SERVICE_LABEL("Name:", "Price:"),

    MASTER_BUTTON("Add Master", "Refresh", "Delete", "Get PDF"),
    CLIENT_BUTTON("Add Client", "Refresh", "Delete", "Get PDF"),
    SERVICE_BUTTON("Add Service", "Refresh", "Delete", "Get PDF");

    private final List<String> values;

    UiConstants(String... values) {
        this.values = Arrays.asList(values);
    }

    public List<String> getValues() {
        return values;
    }

}
