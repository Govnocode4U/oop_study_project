package ui.ui_constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConstantUtils {
    public static List<String> getValuesForWindow(UiTableConstants[] buttonTitles, String windowName) {
        return Arrays.stream(buttonTitles)
                .filter(b -> b.name().startsWith(windowName))
                .flatMap(b -> b.getValues().stream())
                .collect(Collectors.toList());
    }
}

