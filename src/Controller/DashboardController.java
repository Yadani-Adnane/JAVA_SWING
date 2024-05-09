package Controller;

import java.awt.Color;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import Model.DashboardModel;
import View.HistogramPanel;
import View.HomePage;
import View.PieChartPanel;
import java.sql.SQLException;

public class DashboardController {
    private DashboardModel dashboardModel;

    public DashboardController() {
        try {
            dashboardModel = new DashboardModel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public List<String> getStatisticsText() {
        List<String> statistics = new ArrayList<>();
        try {
            int localResidantCount = dashboardModel.getResidentLocalCount();
            int interResidantCount = dashboardModel.getResidentInterCount();
            int activeRoomCount = dashboardModel.getActiveRoomCount();
            int inactiveResidentCount = dashboardModel.getInactiveRoomCount();

            // Add each statistic to the list
            statistics.add("Residants Local: " + localResidantCount);
            statistics.add("Residant Internationl: " + interResidantCount);
            statistics.add("Chambre Active: " + activeRoomCount);
            statistics.add("Chambre Inactive: " + inactiveResidentCount);
        } catch (SQLException e) {
            e.printStackTrace();
            // If fetching fails, add a default message to the list
            statistics.add("Failed to fetch statistics");
        }
        return statistics;
    }

    public int[] getGenderCounts() {
        try {
            return dashboardModel.getGenderCounts();
        } catch (SQLException e) {
            e.printStackTrace();
            return new int[4]; // Return empty array if fetching fails
        }
    }

    public Map<String, Integer> getUniversityDistribution() {
        try {
            return dashboardModel.getUniversityDistribution();
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>(); // Return empty map if fetching fails
        }
    }
    public int getActiveLocalResidentCount() {
        try {
            return dashboardModel.getActiveLocalResidentCount();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getInactiveLocalResidentCount() {
        try {
            return dashboardModel.getInactiveLocalResidentCount();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getActiveForeignResidentCount() {
        try {
            return dashboardModel.getActiveForeignResidentCount();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getInactiveForeignResidentCount() {
        try {
            return dashboardModel.getInactiveForeignResidentCount();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    
    

    
}
