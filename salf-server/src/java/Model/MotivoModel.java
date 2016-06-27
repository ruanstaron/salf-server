package Model;

import Value.MotivoValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Ruan
 */
public class MotivoModel {

    public static ArrayList<MotivoValue> lista(MotivoValue motivo) throws Exception {
        String sql = "select m.*\n"
                + "     from motivo m\n"
                + "    where m.incidencia = false\n";
        if (motivo.getId() != -1) {
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
        } catch (Exception e) {
            System.out.println("Exceção ao listar motivos: " + e.getMessage());
            throw e;
        }

        return lista;
    }

}
