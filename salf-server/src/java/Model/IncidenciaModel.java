package Model;

import Value.IncidenciaValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Ruan
 */
public class IncidenciaModel {

    public static ArrayList<IncidenciaValue> lista(IncidenciaValue incidencia) throws Exception {
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
            conn.close();
        } catch (Exception e) {
            System.out.println("Exceção ao listar motivos: " + e.getMessage());
            throw e;
        }
        
        return lista;
    }
    
}
