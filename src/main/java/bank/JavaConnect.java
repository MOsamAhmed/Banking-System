package bank;

import java.sql.*;
import javax.swing.JOptionPane;

public class JavaConnect {
    Connection conn = null;
    
    public static Connection connectDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:E:\\JAVA PROGRAMMING\\BRO CODE TUTORIAL\\Banking\\bank.sqlite");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
