package Control;

import Model.DefaultModel;
import Model.MotivoModel;
import Util.SalfException;
import Util.SalfExceptionUtil;
import Value.MotivoValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Planejamento
 */
public class MotivoControl {

    public static String listar(int id) throws Exception {
        MotivoValue motivo = new MotivoValue(id, null);
        ArrayList<MotivoValue> motivos = MotivoModel.lista(motivo);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String motivosJson = ow.writeValueAsString(motivos);

        return motivosJson;
    }

    public static void excluir(int id) throws SalfException, Exception {
        MotivoValue motivo = new MotivoValue(id, null);

        try {
            DefaultModel.executaUpdate(
                    "  delete from motivo m\n"
                    + " where m.id_motivo = " + motivo.getId() + "\n"
                    + "   and m.incidencia = false\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.FOREIGN_KEY)) {
                throw new SalfException("Não é possível excluir este motivo pois há reservas que o referenciam.");
            } else if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Motivo não encontrado. Exclusão falhou.");
            }

            throw new Exception(e);
        }
    }

    public static void altera(int id, String json) throws SalfException, Exception {
        MotivoValue motivo = new MotivoValue(json);
        motivo.setId(id);

        try {
            DefaultModel.executaUpdate(
                    "  update motivo\n"
                    + "   set descricao = '" + motivo.getDescricao() + "'\n"
                    + " where id_motivo = " + motivo.getId() + "\n"
                    + "   and incidencia = false\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe um motivo cadastrado com esta descrição.");
            } else if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Motivo não encontrado. Alteração falhou.");
            }

            throw new Exception(e);
        }
    }

    public static void cadastra(String json) throws SalfException, Exception {
        MotivoValue motivo = new MotivoValue(json);

        try {
            DefaultModel.executaUpdate(
                    "  insert into motivo\n"
                    + "       (descricao, incidencia)\n"
                    + "values ('" + motivo.getDescricao() + "', false)\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe um motivo cadastrado com esta descrição.");
            }

            throw new Exception(e);
        }
    }

}
