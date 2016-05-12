package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruan
 */
public class Motivo {
    
    public static String listaMotivo(){
        String motivo = "erro";
        
        try {
            //Registra o driver
            Class.forName("org.postgresql.Driver");
            //Solicita uma conexao
            Connection conn;
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/salf", "postgres", "");

            //Executa a query
            java.sql.Statement st = conn.createStatement();
            st.executeQuery("select * from motivo where id_motivo=1");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                System.out.println(rs.getString("descricao"));
                motivo=rs.getString("descricao");
            }
            return motivo;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Motivo.class.getName()).log(Level.SEVERE, null, ex);
            return "fodeu";
        }
    }
}
