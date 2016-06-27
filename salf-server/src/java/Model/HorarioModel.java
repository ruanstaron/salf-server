package Model;

import Value.HorarioValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Ruan
 */
public class HorarioModel {

    public static ArrayList<HorarioValue> lista(HorarioValue horario) throws Exception {
        String sql = "select s.*\n"
                + "     from horarios s\n";
        if (horario.getId() != -1) {
            sql += "   where s.id_horario = " + horario.getId() + "\n";
        }
        sql += "       order by s.id_horario\n";
        System.out.println("Sql de lista: \n" + sql);

        ArrayList<HorarioValue> lista = new ArrayList<>();
        HorarioValue horarioAux;

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
                horarioAux = new HorarioValue(
                        rs.getInt("id_horario"),
                        rs.getString("descricao")
                );
                lista.add(horarioAux);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Exceção ao listar horarios: " + e.getMessage());
            throw e;
        }

        return lista;
    }

}
