package Project;

import java.sql.*;

public class DBConnection {
    public static Connection getCon(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/library","root","1234");
            return con;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}