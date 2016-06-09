package Control;

import Model.DefaultModel;
import Model.DepartamentoModel;
import Util.SalfException;
import Util.SalfExceptionUtil;
import Value.DepartamentoValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author cristhian
 */
public class DepartamentoControl {

    public static String listar(int id) throws Exception {
        DepartamentoValue departamento = new DepartamentoValue(id, null);
        ArrayList<DepartamentoValue> departamentos = DepartamentoModel.lista(departamento);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String departamentosJson = ow.writeValueAsString(departamentos);

        return departamentosJson;
    }

    public static void excluir(int id) throws SalfException, Exception {
        DepartamentoValue departamento = new DepartamentoValue(id, null);

        try {
            DefaultModel.executaUpdate(
                    "  delete from departamento d\n"
                    + " where d.id_departamento = " + departamento.getId() + "\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.FOREIGN_KEY)) {
                throw new SalfException("Não é possível excluir este departamento pois há professores que o referenciam.");
            } else if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Departamento não encontrado. Exclusão falhou.");
            }

            throw new Exception(e);
        }
    }

    public static void altera(int id, String json) throws SalfException, Exception {
        DepartamentoValue departamento = new DepartamentoValue(json);
        departamento.setId(id);

        try {
            DefaultModel.executaUpdate(
                    "  update departamento\n"
                    + "   set descricao = '" + departamento.getDescricao() + "'\n"
                    + " where id_departamento = " + departamento.getId() + "\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe um departamento cadastrado com esta descrição.");
            } else if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Departamento não encontrado. Alteração falhou.");
            }

            throw new Exception(e);
        }
    }

    public static void cadastra(String json) throws SalfException, Exception {
        DepartamentoValue departamento = new DepartamentoValue(json);

        try {
            DefaultModel.executaUpdate(
                    "  insert into departamento\n"
                    + "       (descricao)\n"
                    + "values ('" + departamento.getDescricao() + "')\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe um departamento cadastrado com esta descrição.");
            }

            throw new Exception(e);
        }
    }

}
