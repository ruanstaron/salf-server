package Model;

import Value.DepartamentoValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author cristhian
 */
public class DepartamentoModel {

    public static ArrayList<DepartamentoValue> lista(DepartamentoValue departamento) {
        String sql = "select d.*\n"
                + "     from departamento d\n";
        if (departamento.getId() != -1) {
            sql += "   where d.id_departamento = " + departamento.getId() + "\n";
        }
        sql += "       order by d.id_departamento\n";
        System.out.println("Sql de lista: \n" + sql);

        ArrayList<DepartamentoValue> lista = new ArrayList<>();
        DepartamentoValue departamentoAux;

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
                departamentoAux = new DepartamentoValue(
                        rs.getInt("id_departamento"),
                        rs.getString("descricao")
                );
                lista.add(departamentoAux);
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
