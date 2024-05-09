package Model;

import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DashboardModel {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/bd_residance";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    public DashboardModel() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public int getActiveRoomCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM chambre WHERE etat = 'active'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    public int getInactiveRoomCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM chambre WHERE etat = 'inactive'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    public int getResidentLocalCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM residantlocal";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }
    public int getResidentInterCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM residantEtranger";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    public int getReservationCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM reservation";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    public int[] getGenderCounts() throws SQLException {
        int[] counts = new int[4]; // Male(Local), Female(Local), Male(International), Female(International)
        String[] genders = {"M", "F"};
        String[] statuses = {"residantlocal", "residantetranger"};

        for (int i = 0; i < statuses.length; i++) {
            for (int j = 0; j < genders.length; j++) {
                String query = "SELECT COUNT(*) FROM personne " +
                               "JOIN " + statuses[i] + " ON personne.id_personne = " + statuses[i] + ".id " +
                               "WHERE personne.genre = ?";

                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, genders[j]);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        counts[i * 2 + j] = rs.getInt(1);
                    }
                }
            }
        }
        return counts;
    }

    public Map<String, Integer> getUniversityDistribution() throws SQLException {
        Map<String, Integer> universityMap = new HashMap<>();
        String query = "SELECT universite, COUNT(*) AS count FROM residant GROUP BY universite";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String universite = rs.getString("universite");
                int count = rs.getInt("count");
                universityMap.put(universite, count);
            }
        }
        return universityMap;
    }
    
    public int getActiveLocalResidentCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM residantlocal rl JOIN residant r ON rl.id = r.id_residant WHERE r.etat = 'active'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    public int getInactiveLocalResidentCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM residantlocal rl JOIN residant r ON rl.id = r.id_residant WHERE r.etat = 'inactive'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    public int getActiveForeignResidentCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM residantetranger re JOIN residant r ON re.id = r.id_residant WHERE r.etat = 'active'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    public int getInactiveForeignResidentCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM residantetranger re JOIN residant r ON re.id = r.id_residant WHERE r.etat = 'inactive'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }
    
    
    }
    



