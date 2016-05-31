package Model;

import Value.IncidenciaValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ruan
 */
public class IncidenciaModel {

    public static ArrayList<IncidenciaValue> lista(IncidenciaValue incidencia) {
        String sql = "select m.*\n"
                + "     from motivo m\n"
                + "    where m.incidencia = true\n";
        if(incidencia.getId() != -1) {
            sql += "     and m.id_motivo = " + incidencia.getId() + "\n";
        }
        sql += "       order by m.id_motivo\n";
        System.out.println("Sql de lista: \n" + sql);
        
        ArrayList<IncidenciaValue> lista = new ArrayList<>();
        IncidenciaValue incidenciaAux;

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
                incidenciaAux = new IncidenciaValue(
                        rs.getInt("id_motivo"),
                        rs.getString("descricao")
                );
                lista.add(incidenciaAux);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IncidenciaModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    public static void executaUpdate(String sql) {
        System.out.println("Sql de update: \n" + sql);
        
        try {
            //Registra o driver
            Class.forName("org.postgresql.Driver");
            //Solicita uma conexao
            Connection conn = ConnectionFactory.getConnection();

            //Executa a query
            java.sql.Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IncidenciaModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
