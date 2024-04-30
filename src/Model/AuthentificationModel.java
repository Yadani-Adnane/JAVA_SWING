package Model;
import java.sql.*;

public class AuthentificationModel {
    public boolean authenticate(String username, String password) {
        String SUrl, SUser, SPass;
        String query, fname = null, passDb = null;
        SUrl = "jdbc:MySQL://localhost:3306/bd_residance";
        SUser = "root";
        SPass = "";
        int notFound = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            query = "SELECT * FROM admin WHERE nom_utilisateur= '"+username+"'";

            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                passDb = rs.getString("mot_de_passe");
                fname = rs.getString("nom_utilisateur");
                notFound = 1;
            }
            if(notFound == 1 && password.equals(passDb)){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Error!" + e.getMessage());
            return false;
        }
    }
}
