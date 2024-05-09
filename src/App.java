import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JFrame;

import Controller.AuthentificationController;
import Model.AuthentificationModel;

import View.AuthentificationForm;
import View.HomePage;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        AuthentificationModel model = new AuthentificationModel();
        AuthentificationForm view = new AuthentificationForm();
        AuthentificationController controller = new AuthentificationController(view, model);
        view.setVisible(true);
       }
}
