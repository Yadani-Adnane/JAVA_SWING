package View;

import javax.swing.*;

import Controller.Chambre;
import Controller.ResidantController;
import Model.Bd;
import Model.ResidantLocal;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModifyLocalResidentForm extends JFrame {
	 private JTextField nomFieldLocal;
	    private JTextField prenomFieldLocal;
	    private JTextField dateDeNaissanceFieldLocal;
	    private JTextField telFieldLocal;
	    private JTextField adresseFieldLocal;
	    private JTextField idFieldLocal;
	    private JTextField emailFieldLocal;
	    private JRadioButton maleRadioButtonLocal;
	    private JRadioButton femaleRadioButtonLocal;
	    private ButtonGroup genderGroupLocal;
	    private JTextField dateEntreFieldLocal;
	    private JTextField dateSortieFieldLocal;
	    private JComboBox<String> etatComboBoxLocal;
	    private JTextField universiteFieldLocal;
	    private JComboBox idChambreFieldLocal;
	    private JTextField telGarantFieldLocal;
	    private JComboBox<String> programmeComboBoxLocal;
	    private JTextField reservationNonPayeesFieldLocal;
	    private JTextField CNEFieldLocal;
	    private JTextField cinFieldLocal;
	    private JButton saveButton;
	    private JButton clearButton;
	    private ResidantController controller;
	    private ResidantLocal selectedLocalResident;
	    private GestionResidantsPage gestionResidantsPage;

    public ModifyLocalResidentForm(GestionResidantsPage gestionResidantsPage) {
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
        panel.add(createStyledLabel("Nom:", labelFont));
        nomFieldLocal = createStyledTextField();
        panel.add(nomFieldLocal);
        panel.add(createStyledLabel("Prénom:", labelFont));
        prenomFieldLocal = createStyledTextField();
        panel.add(prenomFieldLocal);


        
        panel.add(createStyledLabel("Date de Naissance:",labelFont));
        dateDeNaissanceFieldLocal = createStyledTextField();
        panel.add(dateDeNaissanceFieldLocal);

        panel.add(createStyledLabel("Téléphone:", labelFont));
        telFieldLocal = createStyledTextField();
        panel.add(telFieldLocal);

        panel.add(createStyledLabel("Adresse:", labelFont));
        adresseFieldLocal = createStyledTextField();
        panel.add(adresseFieldLocal);

        panel.add(createStyledLabel("CNE:", labelFont));
        CNEFieldLocal = createStyledTextField();
        panel.add(CNEFieldLocal);

        panel.add(createStyledLabel("CIN:", labelFont));
        cinFieldLocal = createStyledTextField();
        panel.add(cinFieldLocal);

        panel.add(createStyledLabel("Email:", labelFont));
        emailFieldLocal = createStyledTextField();
        panel.add(emailFieldLocal);

        panel.add(createStyledLabel("Genre:", labelFont));
        JPanel genderPanel = new JPanel();
        maleRadioButtonLocal = new JRadioButton("M");
        femaleRadioButtonLocal = new JRadioButton("F");
        genderGroupLocal = new ButtonGroup();
        genderGroupLocal.add(maleRadioButtonLocal);
        genderGroupLocal.add(femaleRadioButtonLocal);
        genderPanel.add(maleRadioButtonLocal );
        genderPanel.add(femaleRadioButtonLocal );
        genderPanel.setBackground(new java.awt.Color(255, 255, 255));

        panel.add(genderPanel);
        

        panel.add(createStyledLabel("Date d'Entrée:", labelFont));
        dateEntreFieldLocal = createStyledTextField();
        panel.add(dateEntreFieldLocal);

        panel.add(createStyledLabel("Date de Sortie:", labelFont));
        dateSortieFieldLocal = createStyledTextField();
        panel.add(dateSortieFieldLocal);

        panel.add(createStyledLabel("État:", labelFont));
        String[] etats = {"Actif", "Inactif"};
        etatComboBoxLocal = new JComboBox<>(etats);
        panel.add(etatComboBoxLocal);

        panel.add(createStyledLabel("Université:", labelFont));
        universiteFieldLocal = createStyledTextField();
        panel.add(universiteFieldLocal);

        panel.add(createStyledLabel("ID Chambre:", labelFont));
        idChambreFieldLocal = new JComboBox<>();
        panel.add(idChambreFieldLocal);

        panel.add(createStyledLabel("Téléphone Garant:", labelFont));
        telGarantFieldLocal = createStyledTextField();
        panel.add(telGarantFieldLocal);

        panel.add(createStyledLabel("Programme d'Étude:", labelFont));
        String[] programmes = {"Doctorat", "Master", "Licence"};
        programmeComboBoxLocal = new JComboBox<>(programmes);
        panel.add(programmeComboBoxLocal);

        panel.add(createStyledLabel("Réservations Non Payées:", labelFont));
        reservationNonPayeesFieldLocal = createStyledTextField();
        panel.add(reservationNonPayeesFieldLocal);


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

    public void populateFields(ResidantLocal resident) {
    	selectedLocalResident = resident;
    	nomFieldLocal.setText(resident.getNom());
        prenomFieldLocal.setText(resident.getPrenom());
        dateDeNaissanceFieldLocal.setText(resident.getDateDeNaissance());
        telFieldLocal.setText(resident.getTel());
        adresseFieldLocal.setText(resident.getAdresse());
        emailFieldLocal.setText(resident.getEmail());
        dateEntreFieldLocal.setText(resident.getDateEntre());
        dateSortieFieldLocal.setText(resident.getDateSortie());
        etatComboBoxLocal.setSelectedItem(resident.getEtat());
        universiteFieldLocal.setText(resident.getUniversite());
        idChambreFieldLocal.setSelectedItem(resident.getIdChambre());
        telGarantFieldLocal.setText(resident.getTelGarant());
        programmeComboBoxLocal.setSelectedItem(resident.getProgrammeDetude());
        reservationNonPayeesFieldLocal.setText(String.valueOf(resident.getReservationNonPayees()));
        CNEFieldLocal.setText(resident.getCNE());
        cinFieldLocal.setText(resident.getCin());
        if (resident.getGenre().equals("M")) {
            maleRadioButtonLocal.setSelected(true);
            Bd model = new Bd();
            List<Object> donnees;
            try {
                donnees = model.select("*","Chambre","id_chambre like 'G%'");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            idChambreFieldLocal.removeAllItems();
            for (int i = 0; i < donnees.size(); i++) {
                Chambre c = (Chambre) donnees.get(i);
                idChambreFieldLocal.addItem(c.getId_chambre());
            }
            idChambreFieldLocal.setSelectedItem(resident.getIdChambre());
        } else {
            femaleRadioButtonLocal.setSelected(true);
            Bd model = new Bd();
            List<Object> donnees;
            try {
                donnees = model.select("*","Chambre","id_chambre like 'F%'");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            idChambreFieldLocal.removeAllItems();
            for (int i = 0; i < donnees.size(); i++) {
                Chambre c = (Chambre) donnees.get(i);
                idChambreFieldLocal.addItem(c.getId_chambre());
            }
            idChambreFieldLocal.setSelectedItem(resident.getIdChambre());
        }
    }
    private void saveModifiedResident() {
    	 if (selectedLocalResident != null) {
             String nomLocal = nomFieldLocal.getText();
             String prenomLocal = prenomFieldLocal.getText();
             String dateDeNaissanceLocal = dateDeNaissanceFieldLocal.getText();
             String telLocal = telFieldLocal.getText();
             String adresseLocal = adresseFieldLocal.getText();
             String emailLocal = emailFieldLocal.getText();
             String genreLocal = maleRadioButtonLocal.isSelected() ? "M" : "F";
             String dateEntreLocal = dateEntreFieldLocal.getText();
             String dateSortieLocal = dateSortieFieldLocal.getText();
             String etatLocal = etatComboBoxLocal.getSelectedItem().toString();
             String universiteLocal = universiteFieldLocal.getText();
             String idChambreLocal = idChambreFieldLocal.getSelectedItem().toString();
             String telGarantLocal = telGarantFieldLocal.getText();
             String programmeDetudeLocal = programmeComboBoxLocal.getSelectedItem().toString();
             int reservationNonPayeesLocal = Integer.parseInt(reservationNonPayeesFieldLocal.getText());
             String CNELocal = CNEFieldLocal.getText();
             String cinLocal = cinFieldLocal.getText();

             controller.modifyLocalResidant(cinLocal, nomLocal, prenomLocal, dateDeNaissanceLocal, telLocal, adresseLocal, emailLocal,
                     genreLocal, dateEntreLocal, dateSortieLocal, etatLocal,
                     universiteLocal, idChambreLocal, telGarantLocal, programmeDetudeLocal,
                     reservationNonPayeesLocal, CNELocal, cinLocal);
             gestionResidantsPage.displayResidents(); 
    }
    	 dispose();
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
    private void clearFields() {
        nomFieldLocal.setText("");
        prenomFieldLocal.setText("");
        dateDeNaissanceFieldLocal.setText("");
        telFieldLocal.setText("");
        adresseFieldLocal.setText("");
        emailFieldLocal.setText("");
        maleRadioButtonLocal.setSelected(false);
        femaleRadioButtonLocal.setSelected(false);
        dateEntreFieldLocal.setText("");
        dateSortieFieldLocal.setText("");
        etatComboBoxLocal.setSelectedIndex(0); // Set to "Active"
        universiteFieldLocal.setText("");
        idChambreFieldLocal.setSelectedIndex(-1);
        telGarantFieldLocal.setText("");
        programmeComboBoxLocal.setSelectedIndex(0); // Set to first option
        reservationNonPayeesFieldLocal.setText("");
        CNEFieldLocal.setText("");
        cinFieldLocal.setText("");
    }}
