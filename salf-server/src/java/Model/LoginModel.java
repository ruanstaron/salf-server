package Model;

import Value.ProfessorValue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author cristhian
 */
public class LoginModel {

    public static ArrayList<ProfessorValue> checaLogin(String user, String password) throws Exception {
        String sql = "select u.id_usuario\n"
                + "        , u.nome\n"
                + "        , u.senha\n"
                + "        , u.email\n"
                + "        , u.id_departamento\n"
                + "        , u.tipo\n"
                + "     from usuario u\n"
                + "    where 1 = 1\n"
                + "      and u.email = '" + user + "'\n"
                + "      and u.senha = '" + password + "'\n";
        System.out.println("Sql de login: \n" + sql);

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
                professorAux.adm = rs.getBoolean("tipo");
                lista.add(professorAux);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Exceção ao listar professores: " + e.getMessage());
            throw e;
        }

        return lista;
    }
    
}
