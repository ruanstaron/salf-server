package Model;

import Value.ReservaValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ruan
 */
public class ReservaModel {

    public static ArrayList<ReservaValue> lista(ReservaValue reserva) {
        String sql = "select id_reserva\n"
                + "        , id_sala\n"
                + "        , data\n"
                + "        , hora\n"
                + "        , id_motivo\n"
                + "     from reserva \n";
        if (reserva.getId() != -1) {
            sql += "     where id_reserva = " + reserva.getId() + "\n";
        }
                sql += " order by id_reserva\n";
        System.out.println("Sql de lista: \n" + sql);

        ArrayList<ReservaValue> lista = new ArrayList<>();
        ReservaValue reservaAux;

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
                reservaAux = new ReservaValue(
                        rs.getInt("id_reserva"),
                        rs.getInt("id_sala"),
                        rs.getString("data"),
                        rs.getString("hora"),
                        rs.getInt("id_motivo")
                );
                lista.add(reservaAux);
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
