package View;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class GestionDesBatimentsView extends Component {
    JFrame frame = new JFrame("Gestion de bâtiments, étages et chambres ");
    JTabbedPane ongles = new JTabbedPane(SwingConstants.TOP);
    public JPanel ongle1 = new JPanel();
    public JButton ajouter = new JButton("Ajouter");
    public JButton ajouter2 = new JButton("Ajouter");
    public JButton ajouter3 = new JButton("Ajouter");
    JLabel table_label = new JLabel("liste des bâtiments :");
    public JButton chercher = new JButton("Chercher");
    JLabel table_label2 = new JLabel("liste des bâtiments :");
    public JButton chercher2 = new JButton("Chercher");
    JLabel table_label3 = new JLabel("liste des bâtiments :");
    public JButton chercher3 = new JButton("Chercher");
    JLabel id_label_bat = new JLabel("ID bâtiment: ");
    public JTextField id_tf = new JTextField(30);
    JLabel id_label_bat2 = new JLabel("ID bâtiment: ");
    public JComboBox id_bat1 = new JComboBox();
    JLabel id_label_bat3 = new JLabel("ID bâtiment: ");
    public JComboBox id_tf3 = new JComboBox();
    JLabel genre_label = new JLabel("Genre :");
    String[] genres = {"F", "G"};
    public JComboBox genre_list = new JComboBox(genres);
    JLabel etat_label = new JLabel("Etat :");
    String[] etats = {"Désactvé","Acivé"};
    public JComboBox etat_list = new JComboBox(etats);
    public JLabel numetage = new JLabel("N° étage :");
    public JTextField numetagetf = new JTextField();
    JLabel etat_label2 = new JLabel("Etat :");
    public JComboBox etat_list2 = new JComboBox(etats);
    String[] reservation = { "Non réservée", "Réservée"};
    JLabel reservation_label = new JLabel("Réservation:");
    public JComboBox reservation_list = new JComboBox(reservation);
    String[] etats2 = {"Désactvée","Acivée"};
    JLabel etat_label3 = new JLabel("Etat :");
    public JComboBox etat_list3 = new JComboBox(etats2);
    public JTextField cherche_tf = new JTextField(30);
    public JTextField cherche_tf2 = new JTextField(30);
    public JTextField cherche_tf3 = new JTextField(30);
    public JTable table = new JTable();
    public JTable table2= new JTable();
    public JTable table3 ;
    JPanel form_bat = new JPanel();
    JPanel form_et = new JPanel();
    JPanel form_ch = new JPanel();
    JPanel form_ch2 = new JPanel();
    JLabel id_etage_label = new JLabel("ID étage: ");
    public JTextField id_etage_tf = new JTextField(30);
    JLabel id_etage_label1 = new JLabel("ID étage: ");
    public JComboBox id_etage_tf1 = new JComboBox();
    public JPanel ongle2 = new JPanel();
    JLabel id_chmabre_lab = new JLabel("ID chambre:");
    public JTextField id_chmabre_tf = new JTextField(30);
    public JPanel ongle3 = new JPanel();
    JLabel chmbre_num_lab =new JLabel("N°:");
    public JTextField chambre_num_tf = new JTextField(30);
    public DefaultTableModel model = new DefaultTableModel();
    public JButton annuler1 = new JButton("Annuler");
    public JButton annuler2 = new JButton("Annuler");
    public JButton annuler3 = new JButton("Annuler");
    public DefaultTableModel modell = new DefaultTableModel();
    public DefaultTableModel modelll = new DefaultTableModel();

    public GestionDesBatimentsView() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthentificationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        frame.setResizable(false);
        frame.setSize(620, 465);
        ongle1.setBackground(new java.awt.Color(156, 190, 237, 255));
        ongle2.setBackground(new java.awt.Color(156, 190, 237, 255));
        ongle3.setBackground(new java.awt.Color(156, 190, 237, 255));
        ongle1.setPreferredSize(new Dimension(600,400));
        ongle1.setLayout(null);
        form_bat.setLayout(new GridLayout(1,7));
        form_bat.setBounds(20,10,450,30);
        form_bat.setBackground(new java.awt.Color(156, 190, 237, 255));
        id_label_bat.setHorizontalAlignment(SwingConstants.CENTER);
        form_bat.add(id_label_bat);
        id_tf.setEditable(false);
        form_bat.add(id_tf);
        genre_label.setHorizontalAlignment(SwingConstants.CENTER);
        form_bat.add(genre_label);
        form_bat.add(genre_list);
        etat_label.setHorizontalAlignment(SwingConstants.CENTER);
        form_bat.add(etat_label);
        form_bat.add(etat_list);
        ajouter.setBounds(480,10,100,30);
        ajouter.setBackground(new java.awt.Color(0, 102, 102));
        ajouter.setFont(new java.awt.Font("Segoe UI", 0, 14));
        ajouter.setForeground(new java.awt.Color(255, 255, 255));
        annuler1.setBackground(new java.awt.Color(0, 102, 102));
        annuler1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        annuler1.setForeground(new java.awt.Color(255, 255, 255));
        annuler2.setBackground(new java.awt.Color(0, 102, 102));
        annuler2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        annuler2.setForeground(new java.awt.Color(255, 255, 255));
        annuler3.setBackground(new java.awt.Color(0, 102, 102));
        annuler3.setFont(new java.awt.Font("Segoe UI", 0, 14));
        annuler3.setForeground(new java.awt.Color(255, 255, 255));
        ongle1.add(ajouter);
        ongle1.add(form_bat);
        table_label.setBounds(40,60,150,30);
        ongle1.add(table_label);
        cherche_tf.setText("Chercher par ID");
        cherche_tf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Effacer le texte lorsque le JTextField obtient le focus
                cherche_tf.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(cherche_tf.getText().equals("")){
                    cherche_tf.setText("Chercher par ID");
                }
            }
        });
        cherche_tf.setBounds(345,60,130,30);
        ongle1.add(cherche_tf);
        chercher.setBounds(480,60,100,30);
        chercher.setBackground(new java.awt.Color(0, 102, 102));
        chercher.setFont(new java.awt.Font("Segoe UI", 0, 14));
        chercher.setForeground(new java.awt.Color(255, 255, 255));
        ongle1.add(chercher);
        annuler1.setBounds(160,60,90,30);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(8, 100, 580, 260);
        table.setRowHeight(20);
        scrollPane.setViewportView(table);
        model.addColumn("ID_bâtiment");
        model.addColumn("Genre");
        model.addColumn("Etat");
        model.addColumn("Nombre d'etages");
        model.addColumn("Nbr chmabres");
        model.addColumn("Chambres dispo");
        table.setModel(model);
        ongle1.add(scrollPane);
        ongles.addTab("Bâtiments ", ongle1);
        ongle2.setLayout(null);
        form_et.setLayout(new GridLayout(3,2));
        form_et.setBounds(20,10,450,70);
        form_et.setBackground(new java.awt.Color(156, 190, 237, 255));
        id_label_bat2.setHorizontalAlignment(SwingConstants.CENTER);
        form_et.add(id_label_bat2);
        form_et.add(id_bat1);
        id_etage_label.setHorizontalAlignment(SwingConstants.CENTER);
        form_et.add(id_etage_label);
        id_etage_tf.setHorizontalAlignment(SwingConstants.CENTER);
        id_etage_tf.setEditable(false);
        form_et.add(id_etage_tf);
        form_et.add(new JLabel());
        form_et.add(new JLabel());
        form_et.add(new JLabel());
        form_et.add(new JLabel());
        numetage.setHorizontalAlignment(SwingConstants.CENTER);
        form_et.add(numetage);
        numetagetf.setEditable(false);
        form_et.add(numetagetf);
        etat_label2.setHorizontalAlignment(SwingConstants.CENTER);
        form_et.add(etat_label2);
        form_et.add(etat_list2);
        ongle2.add(form_et);
        ajouter2.setBounds(480,30,100,25);
        ajouter2.setBackground(new java.awt.Color(0, 102, 102));
        ajouter2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        ajouter2.setForeground(new java.awt.Color(255, 255, 255));
        table_label2.setBounds(40,100,150,30);
        ongle2.add(ajouter2);
        ongle2.add(table_label2);
        annuler2.setBounds(160,100,90,30);
        cherche_tf2.setText("Chercher par ID");
        cherche_tf2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Effacer le texte lorsque le JTextField obtient le focus
                cherche_tf2.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(cherche_tf2.getText().equals("")){
                    cherche_tf2.setText("Chercher par ID");
                }
            }
        });
        cherche_tf2.setBounds(355,100,115,25);
        ongle2.add(cherche_tf2);
        chercher2.setBounds(480,100,100,25);
        chercher2.setBackground(new java.awt.Color(0, 102, 102));
        chercher2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        chercher2.setForeground(new java.awt.Color(255, 255, 255));
        ongle2.add(chercher2);
        table2.setRowHeight(20);
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(8, 140, 580, 207);
        scrollPane2.setViewportView(table2);
        modell.addColumn("ID_bâtiment");
        modell.addColumn("ID_etage");
        modell.addColumn("Numéro");
        modell.addColumn("Etat");
        modell.addColumn("Nbr chambres");
        modell.addColumn("Chambres dispo");
        table2.setModel(modell);
        ongle2.add(scrollPane2);
        ongle2.setPreferredSize(new Dimension(600,400));
        ongles.addTab("Etages ", ongle2);
        ongle3.setPreferredSize(new Dimension(600,400));
        form_ch.setLayout(new GridLayout(1,6));
        form_ch.setBounds(20,10,475,30);
        form_ch.setBackground(new java.awt.Color(156, 190, 237, 255));
        form_ch2.setBounds(20,60,475,30);
        form_ch2.setBackground(new java.awt.Color(156, 190, 237, 255));
        id_label_bat3.setHorizontalAlignment(SwingConstants.CENTER);
        form_ch.add(id_label_bat3);
        form_ch.add(id_tf3);
        id_etage_label1.setHorizontalAlignment(SwingConstants.CENTER);
        form_ch.add(id_etage_label1);
        form_ch.add(id_etage_tf1);
        id_chmabre_lab.setHorizontalAlignment(SwingConstants.CENTER);
        form_ch.add(id_chmabre_lab);
        form_ch.add(id_chmabre_tf);
        id_chmabre_tf.setEditable(false);
        form_ch2.setLayout(new GridLayout(1,6));
        chmbre_num_lab.setHorizontalAlignment(SwingConstants.CENTER);
        form_ch2.add(chmbre_num_lab);
        form_ch2.add(chambre_num_tf);
        chambre_num_tf.setEditable(false);
        etat_label3.setHorizontalAlignment(SwingConstants.CENTER);
        form_ch2.add(etat_label3);
        form_ch2.add(etat_list3);
        reservation_label.setHorizontalAlignment(SwingConstants.CENTER);
        form_ch2.add(reservation_label);
        form_ch2.add(reservation_list);
        ongle3.setLayout(null);
        ongle3.add(form_ch);
        ongle3.add(form_ch2);
        ajouter3.setBounds(500,38,95,25);
        ajouter3.setBackground(new java.awt.Color(0, 102, 102));
        ajouter3.setFont(new java.awt.Font("Segoe UI", 0, 14));
        ajouter3.setForeground(new java.awt.Color(255, 255, 255));
        ongle3.add(ajouter3);
        table_label3.setBounds(40,100,150,30);
        annuler3.setBounds(160,100,90,30);
        ongle3.add(table_label3);
        annuler3.setBounds(160,100,150,30);
        cherche_tf3.setText("Chercher par ID");
        cherche_tf3.setBounds(340,100,155,25);
        cherche_tf3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Effacer le texte lorsque le JTextField obtient le focus
                cherche_tf3.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(cherche_tf3.getText().equals("")){
                    cherche_tf3.setText("Chercher par ID");
                }
            }
        });
        ongle3.add(cherche_tf3);
        chercher3.setBounds(500,100,95,25);
        chercher3.setBackground(new java.awt.Color(0, 102, 102));
        chercher3.setFont(new java.awt.Font("Segoe UI", 0, 14));
        chercher3.setForeground(new java.awt.Color(255, 255, 255));
        ongle3.add(chercher3);
        table3 = new JTable();
        table3.setRowHeight(20);
        JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setBounds(15, 140, 570, 207);
        scrollPane3.setViewportView(table3);
        modelll.addColumn("ID_bâtiment");
        modelll.addColumn("ID_etage");
        modelll.addColumn("ID_chambre");
        modelll.addColumn("Numéro");
        modelll.addColumn("Etat");
        modelll.addColumn("Réservation");
        modelll.addColumn("Résidant");
        table3.setModel(modelll);
        ongle3.add(scrollPane3);
        ongles.addTab("Chambres", ongle3);
        ongles.setOpaque(true);
        frame.getContentPane().add(ongles);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }



    public void addgenrelistlistner(ActionListener actionListener) {genre_list.addActionListener(actionListener);}
    public void addajouterlistner(ActionListener actionListener){ajouter.addActionListener(actionListener);}
    public void addtablelestner(ListSelectionListener actionListener){table.getSelectionModel().addListSelectionListener(actionListener);}
    public void addchercherlestner(ActionListener actionListener){chercher.addActionListener(actionListener);}
    public void addidbat1listner(ActionListener actionListener){id_bat1.addActionListener(actionListener);}
    public void addajouter2listner(ActionListener actionListener){ajouter2.addActionListener(actionListener);}
    public void addtable2lestner(ListSelectionListener actionListener){table2.getSelectionModel().addListSelectionListener(actionListener);}
    public void addchercher2lestner(ActionListener actionListener){chercher2.addActionListener(actionListener);}
    public void addidtf3listner(ActionListener actionListener){id_tf3.addActionListener(actionListener);}
    public void addtable3lestner(ListSelectionListener actionListener){table3.getSelectionModel().addListSelectionListener(actionListener);}
    public void addajouter3listner(ActionListener actionListener){ajouter3.addActionListener(actionListener);}
    public void addchercher3lestner(ActionListener actionListener){chercher3.addActionListener(actionListener);}
}
