package Model;

import Value.MotivoValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ruan
 */
public class MotivoModel {

    public static ArrayList<MotivoValue> lista(MotivoValue motivo) {
        String sql = "select m.*\n"
                + "     from motivo m\n"
                + "    where m.incidencia = false\n";
        if(motivo.getId() != -1) {
            sql += "     and m.id_motivo = " + motivo.getId() + "\n";
        }
        sql += "       order by m.id_motivo\n";
        System.out.println("Sql de lista: \n" + sql);
        
        ArrayList<MotivoValue> lista = new ArrayList<>();
        MotivoValue motivoAux;

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
                motivoAux = new MotivoValue(
                        rs.getInt("id_motivo"),
                        rs.getString("descricao")
                );
                lista.add(motivoAux);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MotivoModel.class.getName()).log(Level.SEVERE, null, ex);
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
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MotivoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
