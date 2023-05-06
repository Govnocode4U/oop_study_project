
import ui.MainWindow;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("salon_persistence");
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(emf);
    }
}
