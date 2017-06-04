package app;

import java.sql.*;

/**
 * Created by xontik on 31/05/2017.
 */
public class BDD {
    private static Connection c = null;

    public static Connection getInstance(){

        if(c == null) {
            try {
                c = DriverManager.getConnection("jdbc:oracle:thin:@134.214.112.67:1521:orcl", "p1502484", "240617");
                Statement stmt = c.createStatement();
                /*
                ResultSet rs = stmt.executeQuery("SELECT * FROM PC");
                while (rs.next()) {
                    System.out.println(rs.getObject(1) + " " + rs.getObject(2) + " " + rs.getObject(3) + " ");
                }*/

            } catch (SQLException e) {
                e.printStackTrace();
                c = null;
            }

        }
        return c;
    }

    public static ResultSet query(String q) throws SQLException{
        getInstance();
        ResultSet rs;

        Statement stmt = c.createStatement();
        rs = stmt.executeQuery(q);

        return rs;

    }
}
