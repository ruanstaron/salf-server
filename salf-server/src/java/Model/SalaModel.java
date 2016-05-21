package Model;

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

    public static ArrayList<SalaValue> listaSala(SalaValue sala) {
        String sql = "select s.*\n"
                + "     from sala s\n";
        if (sala.getId() != -1) {
            sql += "   where s.id_sala = " + sala.getId() + "\n";
        }
        sql += "       order by s.id_sala\n";
        System.out.println("Sql de lista: " + sql);

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
