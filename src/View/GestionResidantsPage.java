package View;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controller.ResidantController;
import Model.ResidantEtranger;
import Model.ResidantLocal;
import java.util.List;
import java.util.function.Consumer;
import java.awt.*;
import java.awt.event.*;
public class GestionResidantsPage extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel localResidentsPanel;
    private JPanel internationalResidentsPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton returnToOriginalButton;
    private JTable localResidentsTable;
    private JTable internationalResidentsTable;
    private ResidantController controller;
    private List<ResidantLocal> originalLocalResidents;
    private List<ResidantEtranger> originalInternationalResidents;
    private JButton exportToExcelButton;
    private HomePage homePage;

    public GestionResidantsPage() {
        controller = new ResidantController(); 
        
        initComponents();
        displayResidents();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected tab index
                int tabIndex = tabbedPane.getSelectedIndex();
                
              
                if (tabIndex == 0) {
                    // Display form for adding local resident
                    showLocalResidantForm();
                    
                } else {
                    // Display form for adding international resident
                    showInternationalResidantForm();
                }
                displayResidents();
                if (homePage != null) {
                    homePage.updateDashboard();
                }
            }
            
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected tab index
                int tabIndex = tabbedPane.getSelectedIndex();
                
                if (tabIndex == 0) {
                    // Delete local resident
                    int selectedRow = localResidentsTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String id = localResidentsTable.getValueAt(selectedRow, 5).toString();
                        controller.deleteLocalResidant(id);
                        // Refresh the table after deletion
                        displayResidents();
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez sélectionner un résidant local à supprimer.");
                    }
                    
                } else {
                    // Delete international resident
                    int selectedRow = internationalResidentsTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String id = internationalResidentsTable.getValueAt(selectedRow, 5).toString();
                        controller.deleteInternationalResidant(id);                        
                        displayResidents();
                       
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez sélectionner un résidant international à supprimer.");
                    }
                }
                if (homePage != null) {
                    homePage.updateDashboard();
                }
            }
        });
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tabIndex = tabbedPane.getSelectedIndex();
                
                if (tabIndex == 0) {
                    int selectedRow = localResidentsTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String id = localResidentsTable.getValueAt(selectedRow, 5).toString();
                        ResidantLocal resident = controller.getLocalResidantById(id);
                        if (resident != null) {
                            showModifyFormLocal(resident);
                        
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez sélectionner un résidant local à modifier.");
                    }
                } else {
                    int selectedRow = internationalResidentsTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String id = internationalResidentsTable.getValueAt(selectedRow, 5).toString();
                        ResidantEtranger resident = controller.getInternationalResidantById(id);
                        if (resident != null) {
                            showModifyFormInter(resident);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez sélectionner un résidant international à modifier.");
                        
                    }
                }
                if (homePage != null) {
                    homePage.updateDashboard();
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText().trim();
                if (!keyword.isEmpty()) {
                    searchAndDisplayResidents(keyword);
                }
            }
        });
        returnToOriginalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetToOriginalState();
            }
        });
        

        
    
    }
    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }
    private void initComponents() {
        setTitle("Gestion des Résidents");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Initialize components
        tabbedPane = new JTabbedPane();
        localResidentsPanel = createResidentsPanel("Locaux");
        internationalResidentsPanel = createResidentsPanel("Internationaux");
        addButton = new JButton("Ajouter");
        deleteButton = new JButton("Supprimer");
        
        modifyButton = new JButton("Modifier");
        searchField = new JTextField(20);
        searchButton = new JButton("Rechercher");
        returnToOriginalButton = new JButton("Retourner");
        returnToOriginalButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        returnToOriginalButton.setBackground(Color.GRAY);
        returnToOriginalButton.setForeground(Color.WHITE);

        // Add action listener to the button
       
        // Set layouts
        localResidentsPanel.setLayout(new BorderLayout());
        internationalResidentsPanel.setLayout(new BorderLayout());

        // Add panels to the tabbedPane
        tabbedPane.addTab("Résidents Locaux", localResidentsPanel);
        tabbedPane.addTab("Résidents Internationaux", internationalResidentsPanel);
        tabbedPane.setForeground(Color.WHITE);
        // Set button styles
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        modifyButton.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 14)); 

        // Set search field style
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Set font

        // Set button colors
        addButton.setBackground(Color.decode("#1CB5E0")); // Set background color
        deleteButton.setBackground(Color.decode("#1CB5E0")); // Set background color
        modifyButton.setBackground(Color.decode("#1CB5E0")); // Set background color
        searchButton.setBackground(Color.decode("#1CB5E0")); // Set background color

        // Set button text color
        addButton.setForeground(Color.WHITE); 
        deleteButton.setForeground(Color.WHITE); 
        modifyButton.setForeground(Color.WHITE); 
        searchButton.setForeground(Color.WHITE); 
        exportToExcelButton = new JButton("Exporter vers Excel");
        exportToExcelButton.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        exportToExcelButton.setBackground(Color.decode("#1CB5E0")); 
        exportToExcelButton.setForeground(Color.WHITE); 
        exportToExcelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToExcel();
            }
        });
        // Add buttons and search field to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new java.awt.Color(255, 255, 255));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(exportToExcelButton);
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new java.awt.Color(255, 255, 255));

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(returnToOriginalButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    private JPanel createResidentsPanel(String type) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new java.awt.Color(255, 255, 255));

        // Add a label to display the type of residents
        JLabel label = new JLabel("Liste des Résidents " + type);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(Color.BLUE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        // Add a reservation panel
        JPanel reservationPanel = createReservationPanel();
        panel.add(reservationPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createReservationPanel() {
        JPanel reservationPanel = new JPanel(new BorderLayout());
        reservationPanel.setBackground(new java.awt.Color(255, 255, 255));

        // Add a label for the reservation section
        JLabel reservationLabel = new JLabel("Gestion des Réservations");
        reservationLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        reservationLabel.setForeground(Color.BLUE);
        reservationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        reservationPanel.add(reservationLabel, BorderLayout.NORTH);

        JPanel reservationButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addReservationButton = new JButton("Ajouter Réservation");
        JButton deleteReservationButton = new JButton("Supprimer Réservation");
        JButton modifyReservationButton = new JButton("Modifier Réservation");
        JButton searchReservationButton = new JButton("Rechercher Réservation");
        reservationButtonPanel.add(addReservationButton);
        reservationButtonPanel.add(deleteReservationButton);
        reservationButtonPanel.add(modifyReservationButton);
        reservationButtonPanel.add(searchReservationButton);
        reservationButtonPanel.setBackground(new java.awt.Color(255, 255, 255));
        reservationPanel.add(reservationButtonPanel, BorderLayout.SOUTH);

        return reservationPanel;
    }
    public void showLocalResidantForm() {
        LocalResidantFormView localForm = new LocalResidantFormView(this);
        
        localForm.setVisible(true);
        
        
    }

    public void showInternationalResidantForm() {
        InternationalResidantFormView internationalForm = new InternationalResidantFormView(this);
        internationalForm.setVisible(true);
    }
    public void displayResidents() {
        
        DefaultTableModel localTableModel = new DefaultTableModel();
        localTableModel.setColumnIdentifiers(new String[]{"Nom", "Prénom", "Date de Naissance", "Téléphone", "Adresse", "ID", "Email", "Genre", "Date d'Entrée", "Date de Sortie", "État", "Université", "ID Chambre", "Téléphone Garant", "Programme d'Étude", "Réservations Non Payées", "CNE", "CIN"});
       
        // Populate tables
        List<ResidantLocal> localResidents = controller.getLocalResidents();
        originalLocalResidents = localResidents;
        for (ResidantLocal resident : localResidents) {
            localTableModel.addRow(new Object[]{resident.getNom(), resident.getPrenom(), resident.getDateDeNaissance(),
                    resident.getTel(), resident.getAdresse(), resident.getId(), resident.getEmail(),
                    resident.getGenre(), resident.getDateEntre(), resident.getDateSortie(), resident.getEtat(),
                    resident.getUniversite(), resident.getIdChambre(), resident.getTelGarant(),
                    resident.getProgrammeDetude(), resident.getReservationNonPayees(), resident.getCNE(),
                    resident.getCin()});
        }
        localResidentsTable = new JTable(localTableModel);
        JScrollPane localScrollPane = new JScrollPane(localResidentsTable);
        localResidentsPanel.removeAll();
        localResidentsPanel.add(localScrollPane, BorderLayout.CENTER);
        localResidentsPanel.revalidate(); 

     // International Residents
        DefaultTableModel internationalTableModel = new DefaultTableModel();
        internationalTableModel.setColumnIdentifiers(new String[]{"Nom", "Prénom", "Date de Naissance", "Téléphone", "Adresse", "ID", "Email", "Genre", "Date d'Entrée", "Date de Sortie", "État", "Université", "ID Chambre", "Téléphone Garant", "Programme d'Étude", "Réservations Non Payées", "Numéro de Passport", "Pays"});
        
        List<ResidantEtranger> internationalResidents = controller.getInternationalResidents();
        originalInternationalResidents = internationalResidents;
        for (ResidantEtranger resident : internationalResidents) {
            internationalTableModel.addRow(new Object[]{resident.getNom(), resident.getPrenom(), resident.getDateDeNaissance(),
                    resident.getTel(), resident.getAdresse(), resident.getId(), resident.getEmail(),
                    resident.getGenre(), resident.getDateEntre(), resident.getDateSortie(), resident.getEtat(),
                    resident.getUniversite(), resident.getIdChambre(), resident.getTelGarant(),
                    resident.getProgrammeDetude(), resident.getReservationNonPayees(), resident.getNumPassport(),
                    resident.getPays()});
        }
        internationalResidentsTable = new JTable(internationalTableModel);
        JScrollPane internationalScrollPane = new JScrollPane(internationalResidentsTable);
        internationalResidentsPanel.removeAll(); 
        internationalResidentsPanel.add(internationalScrollPane, BorderLayout.CENTER);
        internationalResidentsPanel.revalidate(); 
    }

    public void showModifyFormLocal(ResidantLocal resident) {
    	ModifyLocalResidentForm modifyForm = new ModifyLocalResidentForm(this);
        modifyForm.populateFields(resident);
        modifyForm.setVisible(true);
    }

    public void showModifyFormInter(ResidantEtranger resident) {
    	ModifyInternationalResidentForm modifyForm = new ModifyInternationalResidentForm(this);
        modifyForm.populateFieldsInternational(resident);
        modifyForm.setVisible(true);
    }
    private void searchAndDisplayResidents(String keyword) {
        // Search local residents
        List<ResidantLocal> matchingLocalResidents = controller.searchLocalResidents(keyword);
        displayLocalResidents(matchingLocalResidents);

        // Search international residents
        List<ResidantEtranger> matchingInternationalResidents = controller.searchInternationalResidents(keyword);
        if (matchingLocalResidents.isEmpty() && matchingInternationalResidents.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun résident trouvé avec ce mot-clé.");
        } else {
            // Display search results
            displayLocalResidents(matchingLocalResidents);
            displayInternationalResidents(matchingInternationalResidents);
        }
        }

    private void displayLocalResidents(List<ResidantLocal> residents) {
        DefaultTableModel localTableModel = (DefaultTableModel) localResidentsTable.getModel();
        localTableModel.setRowCount(0); 
        for (ResidantLocal resident : residents) {
            localTableModel.addRow(new Object[]{resident.getNom(), resident.getPrenom(), resident.getDateDeNaissance(),
                    resident.getTel(), resident.getAdresse(), resident.getId(), resident.getEmail(),
                    resident.getGenre(), resident.getDateEntre(), resident.getDateSortie(), resident.getEtat(),
                    resident.getUniversite(), resident.getIdChambre(), resident.getTelGarant(),
                    resident.getProgrammeDetude(), resident.getReservationNonPayees(), resident.getCNE(),
                    resident.getCin()});
        }
    }
    private void displayInternationalResidents(List<ResidantEtranger> residents) {
        DefaultTableModel internationalTableModel = (DefaultTableModel) internationalResidentsTable.getModel();
        internationalTableModel.setRowCount(0); 
        for (ResidantEtranger resident : residents) {
            internationalTableModel.addRow(new Object[]{resident.getNom(), resident.getPrenom(), resident.getDateDeNaissance(),
                    resident.getTel(), resident.getAdresse(), resident.getId(), resident.getEmail(),
                    resident.getGenre(), resident.getDateEntre(), resident.getDateSortie(), resident.getEtat(),
                    resident.getUniversite(), resident.getIdChambre(), resident.getTelGarant(),
                    resident.getProgrammeDetude(), resident.getReservationNonPayees(), resident.getNumPassport(),
                    resident.getPays()});
        }
    }
    private void resetToOriginalState() {
        // Reset local residents table
        displayLocalResidents(originalLocalResidents);

        // Reset international residents table
        displayInternationalResidents(originalInternationalResidents);
    }
    private void exportToExcel() {
        try {
            // Get the table model
            DefaultTableModel model = (DefaultTableModel) localResidentsTable.getModel();

            // Choose the file to save
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Exporter vers Excel");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath() + ".csv";

                // Write data to CSV file
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                for (int i = 0; i < model.getColumnCount(); i++) {
                    writer.write(model.getColumnName(i));
                    if (i < model.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();

                for (int row = 0; row < model.getRowCount(); row++) {
                    for (int column = 0; column < model.getColumnCount(); column++) {
                        writer.write(model.getValueAt(row, column).toString());
                        if (column < model.getColumnCount() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.newLine();
                }
                writer.close();

                JOptionPane.showMessageDialog(this, "Tableau exporté vers Excel avec succès.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'exportation vers Excel: " + e.getMessage());
        }
    }

}