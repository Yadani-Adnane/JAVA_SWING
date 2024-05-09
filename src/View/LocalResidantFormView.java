package View;

import javax.swing.*;

import Controller.Chambre;
import Controller.Etage;
import Controller.ResidantController;
import Model.Bd;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class LocalResidantFormView extends JFrame {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField dateDeNaissanceField;
    private JTextField telField;
    private JTextField adresseField;
    private JTextField emailField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private ButtonGroup genderGroup;
    private JTextField dateEntreField;
    private JTextField dateSortieField;
    private JComboBox<String> etatComboBox;
    private JTextField universiteField;
    private JComboBox idChambreField;
    private JTextField telGarantField;
    private JComboBox<String> programmeComboBox;
    private JTextField reservationNonPayeesField;
    private JTextField CNEField;
    private JTextField cinField;
    private JButton saveButton;
    private JButton clearButton;
    private ResidantController controller;
    private GestionResidantsPage gestionResidantsPage;
    private HomePage homePage;

    public LocalResidantFormView(GestionResidantsPage gestionResidantsPage) {
        this.gestionResidantsPage = gestionResidantsPage;

        initComponents();
        controller = new ResidantController();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveLocalResidant();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }
    public void setGestionResidantsPage(GestionResidantsPage gestionResidantsPage) {
        this.gestionResidantsPage = gestionResidantsPage;
    }
    private void initComponents() {
        setTitle("Add Local Resident");
        setSize(550, 600);
        setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JPanel panel = new JPanel(new GridLayout(19, 2));
        panel.setBackground(new Color(255, 255, 255)); 
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font textFieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 14);

        
        panel.add(createStyledLabel("Nom:", labelFont));
        nomField = createStyledTextField();
        panel.add(nomField);

        panel.add(createStyledLabel("Prénom:", labelFont));
        prenomField = createStyledTextField();
        panel.add(prenomField);

        panel.add(createStyledLabel("Date de Naissance (YYYY-MM-DD):", labelFont));
        dateDeNaissanceField = createStyledTextField();
        panel.add(dateDeNaissanceField);

        panel.add(createStyledLabel("Téléphone:", labelFont));
        telField = createStyledTextField();
        panel.add(telField);
        panel.add(createStyledLabel("CNE:", labelFont));
        CNEField = createStyledTextField();
        panel.add(CNEField);

        panel.add(createStyledLabel("CIN:", labelFont));
        cinField = createStyledTextField();
        panel.add(cinField);

        panel.add(createStyledLabel("Adresse:", labelFont));
        adresseField = createStyledTextField();
        panel.add(adresseField);


        panel.add(createStyledLabel("Email:", labelFont));
        emailField = createStyledTextField();
        panel.add(emailField);

        panel.add(createStyledLabel("Genre:", labelFont));
        JPanel genderPanel = new JPanel();
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bd model = new Bd();
                List<Object> donnees= null;
                try {
                    donnees = model.select("*","Chambre","id_chambre like 'G%'");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                idChambreField.removeAllItems();
                for (int i = 0; i < donnees.size(); i++) {
                    Chambre c = (Chambre) donnees.get(i);
                    idChambreField.addItem(c.getId_chambre());
                }
            }
        });
        
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bd model = new Bd();
                List<Object> donnees= null;
                try {
                    donnees = model.select("*","Chambre","id_chambre like 'F%'");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                idChambreField.removeAllItems();
                for (int i = 0; i < donnees.size(); i++) {
                    Chambre c = (Chambre) donnees.get(i);
                    idChambreField.addItem(c.getId_chambre());
                }
            }
        });

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.setBackground(new java.awt.Color(255, 255, 255));
        panel.add(genderPanel);

        panel.add(createStyledLabel("Date d'Entrée (YYYY-MM-DD):", labelFont));
        dateEntreField = createStyledTextField();
        panel.add(dateEntreField);

        panel.add(createStyledLabel("Date de Sortie (YYYY-MM-DD):", labelFont));
        dateSortieField = createStyledTextField();
        panel.add(dateSortieField);

        panel.add(createStyledLabel("État:", labelFont));
        String[] etatOptions = {"Active", "Inactive"};
        etatComboBox = new JComboBox<>(etatOptions);
        etatComboBox.setFont(textFieldFont);
        panel.add(etatComboBox);

        panel.add(createStyledLabel("Université:", labelFont));
        universiteField = createStyledTextField();
        panel.add(universiteField);

        panel.add(createStyledLabel("ID Chambre:", labelFont));
        idChambreField = new JComboBox<>();
        panel.add(idChambreField);

        panel.add(createStyledLabel("Téléphone Garant:", labelFont));
        telGarantField = createStyledTextField();
        panel.add(telGarantField);

        panel.add(createStyledLabel("Programme d'Étude:", labelFont));
        String[] programmeOptions = {"Doctorat", "Master", "Licence"};
        programmeComboBox = new JComboBox<>(programmeOptions);
        programmeComboBox.setFont(textFieldFont);
        panel.add(programmeComboBox);

        panel.add(createStyledLabel("Réservations Non Payées:", labelFont));
        reservationNonPayeesField = createStyledTextField();
        panel.add(reservationNonPayeesField);


        saveButton = new JButton("Save");
        saveButton.setFont(buttonFont);
        
        saveButton.setBackground(new java.awt.Color(0, 102, 102));
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        panel.add(saveButton);
        clearButton = new JButton("Clear");
        clearButton.setFont(buttonFont);
        clearButton.setBackground(new java.awt.Color(0, 102, 102));
        clearButton.setForeground(new java.awt.Color(255, 255, 255));

        panel.add(clearButton);

        getContentPane().add(panel);
    }

    private JLabel createStyledLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Color.BLACK); 
        
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        return textField;
    }

    private void clearFields() {
        nomField.setText("");
        prenomField.setText("");
        dateDeNaissanceField.setText("");
        telField.setText("");
        adresseField.setText("");
        emailField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        dateEntreField.setText("");
        dateSortieField.setText("");
        etatComboBox.setSelectedIndex(0); 
        universiteField.setText("");
        idChambreField.setSelectedIndex(-1);
        telGarantField.setText("");
        programmeComboBox.setSelectedIndex(0); 
        reservationNonPayeesField.setText("");
        CNEField.setText("");
        cinField.setText("");
    }
    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }
    private void saveLocalResidant() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String dateDeNaissance = dateDeNaissanceField.getText();
        String tel = telField.getText();
        String adresse = adresseField.getText();
        String email = emailField.getText();
        String genre = maleRadioButton.isSelected() ? "M" : "F";
        String dateEntre = dateEntreField.getText();
        String dateSortie = dateSortieField.getText();
        String etat = (String) etatComboBox.getSelectedItem();
        String universite = universiteField.getText();
        String idChambre = idChambreField.getSelectedItem().toString();
        String telGarant = telGarantField.getText();
        String programmeDetude = (String) programmeComboBox.getSelectedItem();
        int reservationNonPayees = Integer.parseInt(reservationNonPayeesField.getText());
        String CNE = CNEField.getText();
        String cin = cinField.getText();

        controller.addLocalResidant(nom, prenom, dateDeNaissance, tel, adresse, cin, email, genre, dateEntre, dateSortie,
                etat, universite, idChambre, telGarant, programmeDetude, reservationNonPayees, CNE, cin);
        
        
        gestionResidantsPage.displayResidents();
        if (homePage != null) {
            homePage.updateDashboard();
        }
    }
    
}