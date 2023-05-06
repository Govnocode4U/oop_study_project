package ui.factory;

import ui.dialog_windows_manager.dialog_windows.ClientDialogWindow;
import ui.dialog_windows_manager.DialogWindowInterface;
import ui.dialog_windows_manager.dialog_windows.MasterDialogWindow;
import ui.dialog_windows_manager.dialog_windows.ServiceDialogWindow;

import java.util.HashMap;
import java.util.Map;


public class DetectParentFrame {
    private Map<String, DialogWindowInterface> handlers = new HashMap<>();

    public DetectParentFrame() {
        handlers.put("CLIENT", new ClientDialogWindow());
        handlers.put("MASTER", new MasterDialogWindow());
        handlers.put("SERVICE", new ServiceDialogWindow());
    }

    public DialogWindowInterface setParent(String frame, DialogWindow dialogWindow) {
        DialogWindowInterface handler = handlers.get(frame);
        if (handler != null) {
            dialogWindow.setDialogWindowHandler(handler);
        }
        return handler;
    }
}

