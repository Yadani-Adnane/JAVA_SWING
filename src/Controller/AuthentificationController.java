package Controller;

import Model.AuthentificationModel;
import View.AuthentificationForm;
import View.HomePage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AuthentificationController {
    private AuthentificationForm view;
    private AuthentificationModel model;

    public AuthentificationController(AuthentificationForm view, AuthentificationModel model) {
        this.view = view;
        this.model = model;
        this.view.addLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();
            boolean isAuthenticated = model.authenticate(username, password);
            if (isAuthenticated) {
                
            	System.out.println("Authentication successful!");
            	openHomePage();
            	
            } else {
                JOptionPane.showMessageDialog(view, "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
                
            }
        }
        private void openHomePage() {
            HomePage homePage = new HomePage();
            homePage.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            homePage.setVisible(true);
            view.dispose();
        }
        
    }
    
    
}
