package Control;

import Model.DefaultModel;
import Model.SalaModel;
import Util.SalfException;
import Util.SalfExceptionUtil;
import Value.SalaValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author cristhian
 */
public class SalaControl {

    public static String listar(int id) throws Exception {
        SalaValue sala = new SalaValue(id, null);
        ArrayList<SalaValue> salas = SalaModel.lista(sala);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String salasJson = ow.writeValueAsString(salas);

        return salasJson;
    }

    public static void excluir(int id) throws SalfException, Exception {
        SalaValue sala = new SalaValue(id, null);

        try {
            DefaultModel.executaUpdate(
                    "  delete from sala s\n"
                    + " where s.id_sala = " + sala.getId() + "\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.FOREIGN_KEY)) {
                throw new SalfException("Não é possível excluir esta sala pois há reservas que a referenciam.");
            } else if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Sala não encontrado. Exclusão falhou.");
            }

            throw new Exception(e);
        }
    }

    public static void altera(int id, String json) throws SalfException, Exception {
        SalaValue sala = new SalaValue(json);
        sala.setId(id);

        try {
            DefaultModel.executaUpdate(
                    "  update sala\n"
                    + "   set descricao = '" + sala.getDescricao() + "'\n"
                    + " where id_sala = " + sala.getId() + "\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe uma sala cadastrada com esta descrição.");
            } else if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Sala não encontrada. Alteração falhou.");
            }

            throw new Exception(e);
        }
    }

    public static void cadastra(String json) throws SalfException, Exception {
        SalaValue sala = new SalaValue(json);

        try {
            DefaultModel.executaUpdate(
                    "  insert into sala\n"
                    + "       (descricao)\n"
                    + "values ('" + sala.getDescricao() + "')\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe uma sala cadastrada com esta descrição.");
            }

            throw new Exception(e);
        }
    }

}
