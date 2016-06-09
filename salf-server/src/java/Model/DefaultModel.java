package Model;

import Util.SalfException;
import Util.SalfExceptionUtil;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ruan
 */
public class DefaultModel {

    public static void executaUpdate(String sql) throws SalfException, SQLException, Exception {
        System.out.println("Sql de update: \n" + sql);

        try {
            //Registra o driver
            Class.forName("org.postgresql.Driver");
            //Solicita uma conexao
            Connection conn = ConnectionFactory.getConnection();

            //Executa a query
            java.sql.Statement st = conn.createStatement();
            st.executeUpdate(sql);
            if (st.getUpdateCount() == 0) {
                throw new SQLException("No updates were done.", SalfExceptionUtil.NO_AFFECTED_ROWS);
            }
            conn.close();
        } catch (SQLException ex) {
            String sqlState = ex.getSQLState();
            System.out.println("Exceção: " + ex.getSQLState() + ", " + ex);

            if (SalfExceptionUtil.tratavel(sqlState)) {
                throw ex;
            }

            throw new Exception(ex);
        } catch (Exception e) {
            throw e;
        }
    }

}
