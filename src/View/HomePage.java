package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame {
    public HomePage() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Home Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a panel with BorderLayout to hold the menu and dashboard
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.WHITE); // Set background color

        // Menu Panel
        JPanel mlogoPanel = new JPanel(new GridLayout());
        mlogoPanel.setBackground(Color.decode("#1CB5E0"));
        //mlogoPanel.setPreferredSize(new Dimension(600, 600));

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // Set background color
        menuPanel.setBackground(Color.decode("#1CB5E0"));
        // Create constraints for the logo label
        
        Icon logo = new ImageIcon("src/images/lg.png");
        JLabel jLabel = new JLabel(logo);
        mlogoPanel.add(jLabel);
        
        // Create styled buttons with hover effects
        JButton gestionResidentButton = createStyledButton("Gestion des Résidents");
        JButton gestionBatimentButton = createStyledButton("Gestion des Bâtiments");
        JButton gestionEmployeButton = createStyledButton("Gestion des Employés");

        menuPanel.add(gestionResidentButton);
       
        menuPanel.add(gestionBatimentButton);
     
        menuPanel.add(gestionEmployeButton);

        // Dashboard Panel
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setBackground(Color.DARK_GRAY); // Set background color
        dashboardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Center components in the dashboard
        dashboardPanel.setPreferredSize(new Dimension(600, 600));
        JLabel dashboardLabel = new JLabel("Dashboard");
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dashboardLabel.setForeground(Color.WHITE); // Set text color
        dashboardPanel.add(dashboardLabel);

        // Add menuPanel to the left (WEST) region and dashboardPanel to the center region
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(mlogoPanel,BorderLayout.NORTH);
        
        leftPanel.add(menuPanel,BorderLayout.CENTER);

        contentPane.add(leftPanel, BorderLayout.WEST);
        contentPane.add(dashboardPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE); // Set text color
        button.setBackground(Color.decode("#1CB5E0")); // Set background color
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding
        button.setFocusPainted(false); // Remove focus border
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 144, 255)); // Lighter background color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#1CB5E0")); // Restore original background color
            }
        });

        // Add action listener for button click
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
            }
        });

        return button;
    }

    public static void main(String[] args) {
    	
    	
    	
        SwingUtilities.invokeLater(() -> {
            
            HomePage homePage = new HomePage();
            homePage.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen mode
            homePage.setVisible(true);
        });
    }
}
