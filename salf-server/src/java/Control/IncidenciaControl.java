package Control;

import Model.DefaultModel;
import Model.IncidenciaModel;
import Util.SalfException;
import Util.SalfExceptionUtil;
import Value.IncidenciaValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Planejamento
 */
public class IncidenciaControl {

    public static String listar(int id) throws Exception {
        IncidenciaValue incidencia = new IncidenciaValue(id, null);
        ArrayList<IncidenciaValue> incidencias = IncidenciaModel.lista(incidencia);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String incidenciasJson = ow.writeValueAsString(incidencias);

        return incidenciasJson;
    }

    public static void excluir(int id) throws SalfException, Exception {
        IncidenciaValue incidencia = new IncidenciaValue(id, null);

        try {
            DefaultModel.executaUpdate(
                    "  delete from motivo m\n"
                    + " where m.id_motivo = " + incidencia.getId() + "\n"
                    + "   and m.incidencia = true\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.FOREIGN_KEY)) {
                throw new SalfException("Não é possível excluir esta incidência pois há reservas que a referenciam.");
            } else if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Incidência não encontrada. Exclusão falhou.");
            }

            throw new Exception(e);
        }
    }

    public static void altera(int id, String json) throws SalfException, Exception {
        IncidenciaValue incidencia = new IncidenciaValue(json);
        incidencia.setId(id);

        try {
            DefaultModel.executaUpdate(
                    "  update motivo\n"
                    + "   set descricao = '" + incidencia.getDescricao() + "'\n"
                    + " where id_motivo = " + incidencia.getId() + "\n"
                    + "   and incidencia = true\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe uma incidência cadastrada com esta descrição.");
            } else if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Incidência não encontrada. Alteração falhou.");
            }

            throw new Exception(e);
        }
    }

    public static void cadastra(String json) throws SalfException, Exception {
        IncidenciaValue incidencia = new IncidenciaValue(json);

        try {
            DefaultModel.executaUpdate(
                    "  insert into motivo\n"
                    + "       (descricao, incidencia)\n"
                    + "values ('" + incidencia.getDescricao() + "', true)\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe uma incidência cadastrada com esta descrição.");
            }

            throw new Exception(e);
        }
    }

}
