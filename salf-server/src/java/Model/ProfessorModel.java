package Model;

import Value.ProfessorValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author cristhian
 */
public class ProfessorModel {

    public static ArrayList<ProfessorValue> lista(ProfessorValue professor) {
        String sql = "select u.id_usuario\n"
                + "        , u.nome\n"
                + "        , u.senha\n"
                + "        , u.email\n"
                + "        , u.id_departamento\n"
                + "        , d.descricao as departamento\n"
                + "        , u.tipo\n"
                + "     from usuario u\n"
                + "     left join departamento d\n"
                + "       on d.id_departamento = u.id_departamento\n"
                + "    where u.tipo = false\n";
        if (professor.getId() != -1) {
            sql += "     and u.id_usuario = " + professor.getId() + "\n";
        }
        sql += "       order by u.id_usuario\n";
        System.out.println("Sql de lista: \n" + sql);

        ArrayList<ProfessorValue> lista = new ArrayList<>();
        ProfessorValue professorAux;

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
                professorAux = new ProfessorValue(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        rs.getInt("id_departamento")
                );
                professorAux.setDepartamento(rs.getString("departamento"));
                lista.add(professorAux);
            }
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MotivoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
