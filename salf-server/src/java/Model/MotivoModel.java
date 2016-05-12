package Model;

import Value.MotivoValue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ruan
 */
public class MotivoModel {

    public static ArrayList<MotivoValue> listaMotivo() {
        String sql = "select *\n"
                + "     from motivo\n"
                + "    where incidencia = '0'\n";
        
        ArrayList<MotivoValue> lista = new ArrayList<>();
        MotivoValue motivo;

        try {
            //Registra o driver
            Class.forName("org.postgresql.Driver");
            //Solicita uma conexao
            Connection conn;
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/salf", "postgres", "postgres");

            //Executa a query
            java.sql.Statement st = conn.createStatement();
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                motivo = new MotivoValue(
                        rs.getInt("id_motivo"),
                        rs.getString("descricao")
                );
                lista.add(motivo);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MotivoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
}
