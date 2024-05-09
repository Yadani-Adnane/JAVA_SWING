package View;

import javax.swing.*;

import Controller.Chambre;
import Controller.ResidantController;
import Model.Bd;
import Model.ResidantEtranger;
import Model.ResidantLocal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModifyInternationalResidentForm extends JFrame {
    

    private JTextField nomFieldInternational;
    private JTextField prenomFieldInternational;
    private JTextField dateDeNaissanceFieldInternational;
    private JTextField telFieldInternational;
    private JTextField adresseFieldInternational;
    private JTextField emailFieldInternational;
    private JRadioButton maleRadioButtonInternational;
    private JRadioButton femaleRadioButtonInternational;
    private ButtonGroup genderGroupInternational;
    private JTextField dateEntreFieldInternational;
    private JTextField dateSortieFieldInternational;
    private JComboBox<String> etatComboBoxInternational;
    private JTextField universiteFieldInternational;
    private JComboBox idChambreFieldInternational;
    private JTextField telGarantFieldInternational;
    private JComboBox<String> programmeComboBoxInternational;
    private JTextField reservationNonPayeesFieldInternational;
    private JTextField numPassportFieldInternational;
    private JTextField paysFieldInternational;
    private ResidantController controller;
    private JButton saveButton;
    private JButton clearButton;
    private ResidantLocal selectedLocalResident;
    private ResidantEtranger selectedInternationalResident;
    private GestionResidantsPage gestionResidantsPage;

    public ModifyInternationalResidentForm(GestionResidantsPage gestionResidantsPage) {
        this.gestionResidantsPage = gestionResidantsPage;
        controller = new ResidantController(); 

    	initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setTitle("Modify Resident");
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(19, 2));
        panel.setBackground(Color.WHITE);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font textFieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 14);
    
        panel.add(createStyledLabel("Nom:",labelFont));
        nomFieldInternational = createStyledTextField();
        panel.add(nomFieldInternational);

        panel.add(createStyledLabel("Prénom:",labelFont));
        prenomFieldInternational = createStyledTextField();
        panel.add(prenomFieldInternational);

        panel.add(createStyledLabel("Date de Naissance:",labelFont));
        dateDeNaissanceFieldInternational = createStyledTextField();
        panel.add(dateDeNaissanceFieldInternational);

        panel.add(createStyledLabel("Téléphone:",labelFont));
        telFieldInternational = createStyledTextField();
        panel.add(telFieldInternational);

        panel.add(createStyledLabel("Adresse (International):",labelFont));
        adresseFieldInternational = createStyledTextField();
        panel.add(adresseFieldInternational);

        panel.add(createStyledLabel("Numéro de Passport:",labelFont));
        numPassportFieldInternational = createStyledTextField();
        panel.add(numPassportFieldInternational);

        panel.add(createStyledLabel("Email:",labelFont));
        emailFieldInternational = createStyledTextField();
        panel.add(emailFieldInternational);

        panel.add(createStyledLabel("Genre:",labelFont));
        JPanel genderPanel = new JPanel();
        maleRadioButtonInternational = new JRadioButton("M");
        maleRadioButtonInternational.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bd model = new Bd();
                List<Object> donnees= null;
                try {
                    donnees = model.select("*","Chambre","id_chambre like 'G%'");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                idChambreFieldInternational.removeAllItems();
                for (int i = 0; i < donnees.size(); i++) {
                    Chambre c = (Chambre) donnees.get(i);
                    idChambreFieldInternational.addItem(c.getId_chambre());
                }
            }
        });
        femaleRadioButtonInternational = new JRadioButton("F");
        femaleRadioButtonInternational.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bd model = new Bd();
                List<Object> donnees= null;
                try {
                    donnees = model.select("*","Chambre","id_chambre like 'F%'");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                idChambreFieldInternational.removeAllItems();
                for (int i = 0; i < donnees.size(); i++) {
                    Chambre c = (Chambre) donnees.get(i);
                    idChambreFieldInternational.addItem(c.getId_chambre());
                }
            }
        });
        genderGroupInternational = new ButtonGroup();
        genderGroupInternational.add(maleRadioButtonInternational);
        genderGroupInternational.add(femaleRadioButtonInternational);
        genderPanel.add(maleRadioButtonInternational );
        genderPanel.add(femaleRadioButtonInternational );
        genderPanel.setBackground(new java.awt.Color(255, 255, 255));

        panel.add(genderPanel);

        panel.add(createStyledLabel("Date d'Entrée:",labelFont));
        dateEntreFieldInternational = createStyledTextField();
        panel.add(dateEntreFieldInternational);

        panel.add(createStyledLabel("Date de Sortie:",labelFont));
        dateSortieFieldInternational = createStyledTextField();
        panel.add(dateSortieFieldInternational);

        panel.add(createStyledLabel("État:",labelFont));
        String[] etatsInternational = {"Actif", "Inactif"};
        etatComboBoxInternational = new JComboBox<>(etatsInternational);
        panel.add(etatComboBoxInternational);

        panel.add(createStyledLabel("Université:",labelFont));
        universiteFieldInternational = createStyledTextField();
        panel.add(universiteFieldInternational);

        panel.add(createStyledLabel("ID Chambre:",labelFont));
        idChambreFieldInternational = new JComboBox<>();
        panel.add(idChambreFieldInternational);

        panel.add(createStyledLabel("Téléphone Garant:",labelFont));
        telGarantFieldInternational = createStyledTextField();
        panel.add(telGarantFieldInternational);

        panel.add(createStyledLabel("Programme d'Étude:",labelFont));
        String[] programmesInternational = {"Doctorat", "Master", "Licence"};
        programmeComboBoxInternational = new JComboBox<>(programmesInternational);
        panel.add(programmeComboBoxInternational);

        panel.add(createStyledLabel("Réservations Non Payées:",labelFont));
        reservationNonPayeesFieldInternational = createStyledTextField();
        panel.add(reservationNonPayeesFieldInternational);


        panel.add(createStyledLabel("Pays:",labelFont));
        paysFieldInternational = createStyledTextField();
        panel.add(paysFieldInternational);

        // Add Save button
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        saveButton.setBackground(new Color(0, 102, 102));
        saveButton.setForeground(Color.WHITE);
        panel.add(saveButton);
        clearButton = new JButton("Clear");
        clearButton.setFont(buttonFont);
        clearButton.setBackground(new java.awt.Color(0, 102, 102));
        clearButton.setForeground(new java.awt.Color(255, 255, 255));

        panel.add(clearButton);
        getContentPane().add(panel);

        // Add ActionListener for Save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveModifiedResident();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }
    private JLabel createStyledLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Color.BLACK); // Set label text color
        
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Set text field font
        return textField;
    }

    
    

    public void populateFieldsInternational(ResidantEtranger resident) {
    	selectedInternationalResident = resident;
    	nomFieldInternational.setText(resident.getNom());
        prenomFieldInternational.setText(resident.getPrenom());
        dateDeNaissanceFieldInternational.setText(resident.getDateDeNaissance());
        telFieldInternational.setText(resident.getTel());
        adresseFieldInternational.setText(resident.getAdresse());
        emailFieldInternational.setText(resident.getEmail());
        
        dateEntreFieldInternational.setText(resident.getDateEntre());
        dateSortieFieldInternational.setText(resident.getDateSortie());
        etatComboBoxInternational.setSelectedItem(resident.getEtat());
        universiteFieldInternational.setText(resident.getUniversite());
        idChambreFieldInternational.setSelectedItem(resident.getIdChambre());
        telGarantFieldInternational.setText(resident.getTelGarant());
        programmeComboBoxInternational.setSelectedItem(resident.getProgrammeDetude());
        reservationNonPayeesFieldInternational.setText(String.valueOf(resident.getReservationNonPayees()));
        numPassportFieldInternational.setText(resident.getNumPassport());
        paysFieldInternational.setText(resident.getPays());
        if (resident.getGenre().equals("M")) {
            maleRadioButtonInternational.setSelected(true);
            Bd model = new Bd();
            List<Object> donnees;
            try {
                donnees = model.select("*","Chambre","id_chambre like 'G%'");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            idChambreFieldInternational.removeAllItems();
            for (int i = 0; i < donnees.size(); i++) {
                Chambre c = (Chambre) donnees.get(i);
                idChambreFieldInternational.addItem(c.getId_chambre());
            }
            idChambreFieldInternational.setSelectedItem(resident.getIdChambre());
        } else {
            femaleRadioButtonInternational.setSelected(true);
            Bd model = new Bd();
            List<Object> donnees;
            try {
                donnees = model.select("*","Chambre","id_chambre like 'F%'");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            idChambreFieldInternational.removeAllItems();
            for (int i = 0; i < donnees.size(); i++) {
                Chambre c = (Chambre) donnees.get(i);
                idChambreFieldInternational.addItem(c.getId_chambre());
            }
            idChambreFieldInternational.setSelectedItem(resident.getIdChambre());
        }
    }

    private void saveModifiedResident() {
       if (selectedInternationalResident != null) {
            String idInternational = numPassportFieldInternational.getText();
            String nomInternational = nomFieldInternational.getText();
            String prenomInternational = prenomFieldInternational.getText();
            String dateDeNaissanceInternational = dateDeNaissanceFieldInternational.getText();
            String telInternational = telFieldInternational.getText();
            String adresseInternational = adresseFieldInternational.getText();
            String emailInternational = emailFieldInternational.getText();
            String genreInternational = maleRadioButtonInternational.isSelected() ? "M" : "F";
            String dateEntreInternational = dateEntreFieldInternational.getText();
            String dateSortieInternational = dateSortieFieldInternational.getText();
            String etatInternational = etatComboBoxInternational.getSelectedItem().toString();
            String universiteInternational = universiteFieldInternational.getText();
            String idChambreInternational = idChambreFieldInternational.getSelectedItem().toString();
            String telGarantInternational = telGarantFieldInternational.getText();
            String programmeDetudeInternational = programmeComboBoxInternational.getSelectedItem().toString();
            int reservationNonPayeesInternational = Integer.parseInt(reservationNonPayeesFieldInternational.getText());
            String numPassportInternational = numPassportFieldInternational.getText();
            String paysInternational = paysFieldInternational.getText();

            controller.modifyInternationalResidant(numPassportInternational, nomInternational, prenomInternational,
                    dateDeNaissanceInternational, telInternational, adresseInternational, emailInternational,
                    genreInternational, dateEntreInternational, dateSortieInternational, etatInternational,
                    universiteInternational, idChambreInternational, telGarantInternational, programmeDetudeInternational,
                    reservationNonPayeesInternational, numPassportInternational, paysInternational);
            gestionResidantsPage.displayResidents(); 

       }
        dispose();
    }
    private void clearFields() {
        nomFieldInternational.setText("");
        prenomFieldInternational.setText("");
        dateDeNaissanceFieldInternational.setText("");
        telFieldInternational.setText("");
        adresseFieldInternational.setText("");
        emailFieldInternational.setText("");
        maleRadioButtonInternational.setSelected(false);
        femaleRadioButtonInternational.setSelected(false);
        dateEntreFieldInternational.setText("");
        dateSortieFieldInternational.setText("");
        etatComboBoxInternational.setSelectedIndex(0); // Set to "Active"
        universiteFieldInternational.setText("");
        idChambreFieldInternational.setSelectedItem(-1);
        telGarantFieldInternational.setText("");
        programmeComboBoxInternational.setSelectedIndex(0); // Set to first option
        reservationNonPayeesFieldInternational.setText("");
        numPassportFieldInternational.setText("");
        paysFieldInternational.setText("");
    }
   
}
