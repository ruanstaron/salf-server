package Control;

import Model.ProfessorModel;
import Value.ProfessorValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author cristhian
 */
public class ProfessorControl {

    public static String listar(int id) throws ParseException, IOException {
        ProfessorValue professor = new ProfessorValue(id, null, null, null, -1);
        ArrayList<ProfessorValue> professores = ProfessorModel.lista(professor);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String professoresJson = ow.writeValueAsString(professores);

        return professoresJson;
    }

    public static void excluir(int id) throws ParseException, IOException {
        ProfessorValue professor = new ProfessorValue(id, null, null, null, -1);
        
        ProfessorModel.executaUpdate(
                "  delete from usuario u\n"
                + " where u.id_usuario = " + professor.getId() + "\n"
        );
    }

    public static void altera(int id, String json) throws ParseException, IOException {
        ProfessorValue professor = new ProfessorValue(json);
        professor.setId(id);

        ProfessorModel.executaUpdate(
                "  update usuario\n"
                + "   set nome = '" + professor.getNome() + "'\n"
                + "     , senha = '" + professor.getSenha() + "'\n"
                + "     , email = '" + professor.getEmail() + "'\n"
                + "     , id_departamento = " + professor.getId_departamento()+ "\n"
                + " where id_usuario = " + professor.getId() + "\n"
        );
    }

    public static void cadastra(String json) throws ParseException, IOException {
        ProfessorValue professor = new ProfessorValue(json);

        ProfessorModel.executaUpdate(
                "  insert into usuario\n"
                + "       (nome, senha, email, id_departamento, tipo)\n"
                + "values ('" + professor.getNome() + "', '" + professor.getSenha() + "', " +
                        "'" + professor.getEmail() + "', " + professor.getId_departamento() + ", " +
                        "false)\n"
        );
    }

}
