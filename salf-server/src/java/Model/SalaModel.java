package Model;

import Value.MotivoValue;
import Value.SalaValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author cristhian
 */
public class SalaModel {

    public static ArrayList<SalaValue> listaSala() {
        String sql = "select s.*\n"
                + "     from sala s\n"
                + "    order by s.id_sala\n";
        System.out.println("Sql de lista: " + sql);
        
        ArrayList<SalaValue> lista = new ArrayList<>();
        SalaValue sala;

        try {
            //Registra o driver
            Class.forName("org.postgresql.Driver");
            //Solicita uma conexao
            Connection conn = ConnectionFactory.getConnection();

            //Executa a query
            java.sql.Statement st = conn.createStatement();
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                sala = new SalaValue(
                        rs.getInt("id_sala"),
                        rs.getString("descricao")
                );
                lista.add(sala);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MotivoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    public static void executaUpdate(String sql) {
        System.out.println("Sql de update: " + sql);
        
        try {
            //Registra o driver
            Class.forName("org.postgresql.Driver");
            //Solicita uma conexao
            Connection conn = ConnectionFactory.getConnection();

            //Executa a query
            java.sql.Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MotivoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
