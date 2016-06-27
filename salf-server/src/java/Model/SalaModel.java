package Model;

import Value.SalaValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author cristhian
 */
public class SalaModel {

    public static ArrayList<SalaValue> lista(SalaValue sala) throws Exception {
        String sql = "select s.*\n"
                + "     from sala s\n";
        if (sala.getId() != -1) {
            sql += "   where s.id_sala = " + sala.getId() + "\n";
        }
        sql += "       order by s.id_sala\n";
        System.out.println("Sql de lista: \n" + sql);

        ArrayList<SalaValue> lista = new ArrayList<>();
        SalaValue salaAux;

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
                salaAux = new SalaValue(
                        rs.getInt("id_sala"),
                        rs.getString("descricao")
                );
                lista.add(salaAux);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Exceção ao listar salas: " + e.getMessage());
            throw e;
        }

        return lista;
    }

}
