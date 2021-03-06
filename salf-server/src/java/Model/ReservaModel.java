package Model;

import Value.ReservaValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Ruan
 */
public class ReservaModel {

    public static ArrayList<ReservaValue> lista(ReservaValue reserva, int idUsuario) throws Exception {
        String sql = "select id_reserva\n"
                + "        , id_sala\n"
                + "        , data\n"
                + "        , id_horario\n"
                + "        , id_motivo\n"
                + "     from reserva \n"
                + "    where 1 = 1\n";
        if (reserva.getId() != -1) {
            sql += "     and id_reserva = " + reserva.getId() + "\n";
        }
        if(idUsuario != -1) {
            sql += "     and id_usuario = " + idUsuario + "\n";
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
                        rs.getInt("id_horario"),
                        rs.getInt("id_motivo")
                );
                lista.add(reservaAux);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Exceção ao listar motivos: " + e.getMessage());
            throw e;
        }

        return lista;
    }

}
