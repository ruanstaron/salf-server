package Model;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConnectionFactory {

    ConnectionFactory(String jdbcpostgresqllocalhost5432salf, String postgres, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/salf", "postgres", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}