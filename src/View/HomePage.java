package View;

import javax.swing.*;

import Controller.DashboardController;
import Controller.GestionDesBatimentsController;
import Controller.ReservationsController;
import Model.Bd;
import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;

public class HomePage extends JFrame {
    JButton gestionResidentButton;
    JButton gestionBatimentButton;
    JButton gestionRservation;
    private JPanel genderDistributionPanel;
    private JPanel ageDistributionPanel;
    private DashboardController dashboardController; // Add controller reference
    private JLabel activeLocalLabel;
    private JLabel inactiveLocalLabel;
    private JLabel activeForeignLabel;
    private JLabel inactiveForeignLabel;
    private JPanel residentContributionPanel;
    private JPanel statisticsPanel;
    public HomePage() {
        initComponents();
        dashboardController = new DashboardController();  
        updateDashboard(); 
        
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
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen mode
        setVisible(true);
        setTitle("Home Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a panel with BorderLayout to hold the menu and dashboard
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.DARK_GRAY); // Set background color

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
        gestionResidentButton = createStyledButton("Gestion des Résidents    ");
        
        gestionBatimentButton = createStyledButton("Gestion des Bâtiments    ");
        gestionRservation = createStyledButton("Gestion des réservations");
        gestionRservation.setSize(gestionBatimentButton.getWidth(), gestionResidentButton.getHeight());
     // Add action listeners to buttons
        gestionResidentButton.addActionListener(e -> openResidentManagementPage());
        gestionBatimentButton.addActionListener(e -> {
            try {
                openBatimentManagementPage();
            } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException
                    | IllegalAccessException | SQLException e1) {

                e1.printStackTrace();
            }
        });

        gestionRservation.addActionListener(e -> {
            try {
                openReservationspage();
            } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException
                     | IllegalAccessException | SQLException e1) {

                e1.printStackTrace();
            }
        });
        
        menuPanel.add(gestionResidentButton);
        menuPanel.add(gestionBatimentButton);
        menuPanel.add(gestionRservation);
     
        
        // Initialize gender distribution panel
        genderDistributionPanel = new JPanel();
        genderDistributionPanel.setBackground(Color.DARK_GRAY);
        genderDistributionPanel.setLayout(new BorderLayout());

        // Initialize age distribution panel
        ageDistributionPanel = new JPanel();
        ageDistributionPanel.setBackground(Color.DARK_GRAY);
        ageDistributionPanel.setLayout(new BorderLayout());

        // Add statistics label, gender distribution panel, and age distribution panel to contentPane

        // Dashboard Panel
        JLabel dashboardLabel = new JLabel("Dashboard");
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dashboardLabel.setForeground(Color.WHITE); // Set text color
        JPanel dashboardLabelPanel = new JPanel();
        dashboardLabelPanel.setBackground(Color.DARK_GRAY); // Set background color
        dashboardLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        dashboardLabelPanel.add(dashboardLabel);
        JPanel dashboardComponentsPanel = new JPanel(new GridLayout(2, 2));
        dashboardComponentsPanel.setBackground(Color.DARK_GRAY);
        statisticsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        statisticsPanel.setBackground(Color.DARK_GRAY);
        
        
        JLabel statTitleLabel = new JLabel("Statistiques",JLabel.CENTER);
        statTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statTitleLabel.setForeground(Color.decode("#1CB5E0"));
        
        JPanel statOveriviewPanel = new JPanel(new BorderLayout());
        statOveriviewPanel.add(statTitleLabel, BorderLayout.NORTH);
        statOveriviewPanel.add(statisticsPanel, BorderLayout.CENTER);
      
        statOveriviewPanel.setBackground(Color.DARK_GRAY);
        statOveriviewPanel.setBorder(BorderFactory.createTitledBorder(""));

        JPanel genderPanel = new JPanel(new BorderLayout());
        genderPanel.add(genderDistributionPanel, BorderLayout.CENTER);
        JLabel genderTitleLabel = new JLabel("Gender Distribution" ,JLabel.CENTER);
        genderTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        genderTitleLabel.setForeground(Color.decode("#1CB5E0"));
        genderPanel.add(genderTitleLabel, BorderLayout.NORTH);
        genderPanel.setBackground(Color.DARK_GRAY);
        genderPanel.setBorder(BorderFactory.createTitledBorder(""));

        JPanel agePanel = new JPanel(new BorderLayout());
        agePanel.add(ageDistributionPanel, BorderLayout.CENTER);
        JLabel ageTitleLabel = new JLabel("Université Distribution",JLabel.CENTER);
        ageTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ageTitleLabel.setForeground(Color.decode("#1CB5E0"));
        agePanel.add(ageTitleLabel, BorderLayout.NORTH);
        agePanel.setBackground(Color.DARK_GRAY);
        agePanel.setBorder(BorderFactory.createTitledBorder(""));
        
        
        
        
        residentContributionPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        residentContributionPanel.setBackground(Color.DARK_GRAY);
        JLabel resTitleLabel = new JLabel("Etat Residants",JLabel.CENTER);
        resTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resTitleLabel.setForeground(Color.decode("#1CB5E0"));
        
        JPanel resOveriviewPanel = new JPanel(new BorderLayout());
        resOveriviewPanel.add(resTitleLabel, BorderLayout.NORTH);
        resOveriviewPanel.add(residentContributionPanel, BorderLayout.CENTER);
      
        resOveriviewPanel.setBackground(Color.DARK_GRAY);
        resOveriviewPanel.setBorder(BorderFactory.createTitledBorder(""));
        
       
        
        
        dashboardComponentsPanel.add(statOveriviewPanel);
        dashboardComponentsPanel.add(genderPanel);
        dashboardComponentsPanel.add(agePanel);
        dashboardComponentsPanel.add(resOveriviewPanel);

        
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(Color.DARK_GRAY); // Set background color
        dashboardPanel.setPreferredSize(new Dimension(600, 600));
        
        dashboardPanel.add(dashboardLabelPanel, BorderLayout.NORTH);
        dashboardPanel.add(dashboardComponentsPanel, BorderLayout.CENTER);

        // Add menuPanel to the left (WEST) region and dashboardPanel to the center region
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(mlogoPanel,BorderLayout.NORTH);
        leftPanel.add(menuPanel,BorderLayout.CENTER);

        contentPane.add(leftPanel, BorderLayout.WEST);
        contentPane.add(dashboardPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 

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

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action performed
            }
        });

        return button;
    }
    
   
    
    public void updateDashboard() {
        // Update statistics label with fetched statistics text
    	statisticsPanel.removeAll();

        // Get the list of statistics from the controller
        List<String> statistics = dashboardController.getStatisticsText();

        // Create a JLabel for each statistic and add it to the statistics panel
        for (String statistic : statistics) {
            JLabel statisticLabel = new JLabel(statistic);
            statisticLabel.setFont(new Font("Arial", Font.BOLD, 16));
            statisticLabel.setForeground(Color.WHITE);
            statisticsPanel.add(statisticLabel);
        }
        // Update gender distribution panel with fetched gender counts
        int[] genderCounts = dashboardController.getGenderCounts();
        PieChartPanel genderPieChart = new PieChartPanel(genderCounts, new String[]{"Male(Local)", "Female(Local)", "Male(International)", "Female(International)"}, new Color[]{Color.BLUE, Color.PINK, Color.CYAN, Color.ORANGE});
        genderDistributionPanel.add(genderPieChart);

        Map<String, Integer> universityDistribution = dashboardController.getUniversityDistribution();
		HistogramPanel universityHistogram = new HistogramPanel(universityDistribution);
		ageDistributionPanel.removeAll(); // Clear previous content
		ageDistributionPanel.add(universityHistogram);
        int activeLocalResidentCount = dashboardController.getActiveLocalResidentCount();
        int inactiveLocalResidentCount = dashboardController.getInactiveLocalResidentCount();
        int activeForeignResidentCount = dashboardController.getActiveForeignResidentCount();
        int inactiveForeignResidentCount = dashboardController.getInactiveForeignResidentCount();
        
        activeLocalLabel = new JLabel("Active Local Residents: " + activeLocalResidentCount);
        inactiveLocalLabel = new JLabel("Inactive Local Residents: " + inactiveLocalResidentCount);
        activeForeignLabel = new JLabel("Active International Residents: " + activeForeignResidentCount);
        inactiveForeignLabel = new JLabel("Inactive Iternational Residents: " + inactiveForeignResidentCount);
       
        activeLocalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        activeLocalLabel.setForeground(Color.WHITE);
        inactiveLocalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inactiveLocalLabel.setForeground(Color.WHITE);
        activeForeignLabel.setFont(new Font("Arial", Font.BOLD, 16));
        activeForeignLabel.setForeground(Color.WHITE);
        inactiveForeignLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inactiveForeignLabel.setForeground(Color.WHITE);
        
        residentContributionPanel.add(activeLocalLabel);
        residentContributionPanel.add(inactiveLocalLabel);
        residentContributionPanel.add(activeForeignLabel);
        residentContributionPanel.add(inactiveForeignLabel);
        
        
        
        genderDistributionPanel.revalidate();
        genderDistributionPanel.repaint();
        ageDistributionPanel.revalidate();
        ageDistributionPanel.repaint();
        residentContributionPanel.revalidate();
        residentContributionPanel.repaint();
    }
    private void openResidentManagementPage() {
        GestionResidantsPage residentManagementPage = new GestionResidantsPage();
        residentManagementPage.setVisible(true);
    }

    private void openBatimentManagementPage() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        GestionDesBatimentsController controller = new GestionDesBatimentsController(new GestionDesBatimentsView(), new Bd());
    }
    private void openReservationspage() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        ReservationsController controller = new ReservationsController(new Reservations(), new Bd());
    }
    
}
