package Control;

import Model.MotivoModel;
import Value.MotivoValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author Planejamento
 */
public class MotivoControl {

    public static String listar(int id) throws ParseException {
        MotivoValue motivo = new MotivoValue(id, null);
        ArrayList<MotivoValue> motivos = MotivoModel.lista(motivo);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String motivosJson = ow.writeValueAsString(motivos);

        return motivosJson;
    }

    public static void excluir(int id) throws ParseException, IOException {
        MotivoValue motivo = new MotivoValue(id, null);

        MotivoModel.executaUpdate(
                "  delete from motivo m\n"
                + " where m.id_motivo = " + motivo.getId() + "\n"
                + "   and m.incidencia = false\n"
        );
    }

    public static void altera(int id, String json) throws ParseException, IOException {
        MotivoValue motivo = new MotivoValue(json);
        motivo.setId(id);

        MotivoModel.executaUpdate(
                "  update motivo\n"
                + "   set descricao = '" + motivo.getDescricao() + "'\n"
                + " where id_motivo = " + motivo.getId() + "\n"
                + "   and incidencia = false\n"
        );
    }

    public static void cadastra(String json) throws ParseException, IOException {
        MotivoValue motivo = new MotivoValue(json);

        MotivoModel.executaUpdate(
                "  insert into motivo\n"
                + "       (descricao, incidencia)\n"
                + "values ('" + motivo.getDescricao() + "', false)\n"
        );
    }

}
