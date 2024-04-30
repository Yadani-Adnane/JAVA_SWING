import Controller.AuthentificationController;
import Model.AuthentificationModel;
import View.AuthentificationForm;

public class App {
    public static void main(String[] args) {
        AuthentificationModel model = new AuthentificationModel();
        AuthentificationForm view = new AuthentificationForm();
        AuthentificationController controller = new AuthentificationController(view, model);
        view.setVisible(true);
    }
}
