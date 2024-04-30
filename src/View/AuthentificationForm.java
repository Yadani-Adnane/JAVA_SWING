package View;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.sql.*;

public class AuthentificationForm extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AuthentificationForm() {
        initComponents();
        setResizable(false); 
        setLocationRelativeTo(null);
    }

    private void initComponents() {
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
        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        Left = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GRU");
        setPreferredSize(new java.awt.Dimension(900, 500));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel1.setLayout(null);

        Right.setBackground(new java.awt.Color(255, 255, 255));
        Right.setPreferredSize(new java.awt.Dimension(500, 500));
        Right.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("LOGIN");
        Right.add(jLabel1);
        jLabel1.setBounds(140, 50, 110, 48);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel2.setText("UserName");
        Right.add(jLabel2);
        jLabel2.setBounds(30, 140, 90, 20);

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        Right.add(jTextField1);
        jTextField1.setBounds(30, 170, 343, 40);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jLabel3.setText("Password");
        Right.add(jLabel3);
        jLabel3.setBounds(30, 240, 90, 20);

        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        Right.add(jPasswordField1);
        jPasswordField1.setBounds(30, 270, 343, 40);

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Login");
        
        Right.add(jButton1);
        jButton1.setBounds(60, 400, 120, 36);

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255)); // Set the checkbox background to match the panel
        jCheckBox1.setForeground(new java.awt.Color(0, 102, 102)); // Set the checkbox text color
        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jCheckBox1.setText("Show Password");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        Right.add(jCheckBox1);
        jCheckBox1.setBounds(30, 320, 130, 23);
        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Right.add(jButton2);
        jButton2.setBounds(240, 400, 120, 36);

        jPanel1.add(Right);
        Right.setBounds(500, 0, 500, 500);

        Left.setBackground(new java.awt.Color(0, 102, 102));
        Left.setPreferredSize(new java.awt.Dimension(500, 500));
        Left.setLayout(null);
        Icon icon = new ImageIcon("src/images/logo.png");
        
        jLabel5.setIcon(icon); 
        Left.add(jLabel5);
        jLabel5.setBounds(130, 130, 300, 200);

       


        jPanel1.add(Left);
        Left.setBounds(0, 0, 500, 500);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }

    
        
    


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        jTextField1.setText("");
        jPasswordField1.setText("");
    }

    
    
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        
        jPasswordField1.setEchoChar(jCheckBox1.isSelected() ? '\u0000' : '*');
    }
    public String getUsername() {
        return jTextField1.getText();
    }

    public String getPassword() {
        return new String(jPasswordField1.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
    	jButton1.addActionListener(listener);
    }

    

    private javax.swing.JPanel Left;
    private javax.swing.JPanel Right;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;

}
