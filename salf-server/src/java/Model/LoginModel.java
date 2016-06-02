package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Planejamento
 */
public class LoginModel {
    
    public static boolean realizaLogin(String usuario, String senha) throws SQLException{
        String sql = "SELECT id_usuario FROM usuario WHERE nome = '"+usuario+"' "
                + "AND senha = '"+senha+"';";
        int id=0;
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
                id = rs.getInt("id_usuario");
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        if(id>0){
                return true;
        }else{
                return false;
        }
    }
    
    public static int getId_usuario(String usuario){
        String sql = "SELECT id_usuario FROM usuario WHERE nome = '"+usuario+"';";
        int id=0;
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
                id = rs.getInt("id_usuario");
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return id;
        }
        return id;
    }
}
