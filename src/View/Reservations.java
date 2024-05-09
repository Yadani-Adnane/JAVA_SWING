package View;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Reservations extends Component {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel form = new JPanel();
    public JPanel form1 = new JPanel();
    JLabel label = new JLabel("ID résident :");
    public JTextField id = new JTextField(10);
    JLabel label1 = new JLabel("ID chambre :");
    public JTextField id1 = new JTextField(10);
    JLabel label2 = new JLabel("Prix de réservation :");
    public JTextField prix = new JTextField(10);
    JButton payer = new JButton("Payer");
    JLabel label3 = new JLabel("Liste des réservations :");
    public JButton annuler = new JButton("Annuler");
    JButton chercher = new JButton("Chercher");
    public JTextField chercher1 = new JTextField(10);
    public JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel();
    JScrollPane scrollPane = new JScrollPane();

    public Reservations() {
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
        frame.setSize(800,450);
        panel.setLayout(null);
        form.setLayout(new GridLayout(1,7));
        form.add(label);
        form.add(id);
        form.add(label1);
        form.add(id1);
        form.add(label2);
        form.add(prix);
        form.add(payer);
        form.setBounds(10, 10, 750, 30);
        panel.add(form);
        form1.setLayout(new GridLayout(1,4));
        form1.add(label3);
        form1.add(annuler);
        annuler.setVisible(false);
        form1.revalidate();
        form1.repaint();
        chercher1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Effacer le texte lorsque le JTextField obtient le focus
                chercher1.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(chercher1.getText().equals("")){
                    chercher1.setText("Chercher par ID");
                }
            }
        });
        form1.add(chercher1);
        form1.add(chercher);
        form1.setBounds(10, 60, 750, 30);
        panel.add(form1);
        scrollPane.setBounds(8, 120, 750, 300);
        table.setRowHeight(20);
        scrollPane.setViewportView(table);
        model.addColumn("ID réservation");
        model.addColumn("Date");
        model.addColumn("Résident");
        model.addColumn("Chambre");
        model.addColumn("Prix");
        model.addColumn("Payée");
        table.setModel(model);
        panel.add(scrollPane);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void addpayeractionlestner(ActionListener a){ payer.addActionListener(a); }
    public void addchercheractionlestner(ActionListener a){ chercher.addActionListener(a); }
    public void addtableactionlestner(ListSelectionListener a){ table.getSelectionModel().addListSelectionListener(a); }
}
