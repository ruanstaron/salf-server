package Control;

import Model.DefaultModel;
import Model.ProfessorModel;
import Util.SalfException;
import Util.SalfExceptionUtil;
import Value.ProfessorValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author cristhian
 */
public class ProfessorControl {

    public static String listar(int id) throws Exception {
        ProfessorValue professor = new ProfessorValue(id, null, null, null, -1);
        ArrayList<ProfessorValue> professores = ProfessorModel.lista(professor);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String professoresJson = ow.writeValueAsString(professores);

        return professoresJson;
    }

    public static void excluir(int id) throws SalfException, Exception {
        ProfessorValue professor = new ProfessorValue(id, null, null, null, -1);

        try {
            DefaultModel.executaUpdate(
                    "  delete from usuario u\n"
                    + " where u.id_usuario = " + professor.getId() + "\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.FOREIGN_KEY)) {
                throw new SalfException("Não é possível excluir este professor pois há reservas que o referenciam.");
            } else if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Professor não encontrado. Exclusão falhou.");
            }

            throw new Exception(e);
        }
    }

    public static void altera(int id, String json) throws SalfException, Exception {
        ProfessorValue professor = new ProfessorValue(json);
        professor.setId(id);

        try {
            DefaultModel.executaUpdate(
                    "  update usuario\n"
                    + "   set nome = '" + professor.getNome() + "'\n"
                    + "     , senha = '" + professor.getSenha() + "'\n"
                    + "     , email = '" + professor.getEmail() + "'\n"
                    + "     , id_departamento = " + professor.getId_departamento() + "\n"
                    + " where id_usuario = " + professor.getId() + "\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe um professor cadastrado com este nome.");
            } else if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Professor não encontrado. Alteração falhou.");
            }

            throw new Exception(e);
        }
    }

    public static void cadastra(String json) throws SalfException, Exception {
        ProfessorValue professor = new ProfessorValue(json);

        try {
            DefaultModel.executaUpdate(
                    "  insert into usuario\n"
                    + "       (nome, senha, email, id_departamento, tipo)\n"
                    + "values ('" + professor.getNome() + "', '" + professor.getSenha() + "', "
                    + "'" + professor.getEmail() + "', " + professor.getId_departamento() + ", "
                    + "false)\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe um professor cadastrado com este nome.");
            }

            throw new Exception(e);
        }
    }

}
