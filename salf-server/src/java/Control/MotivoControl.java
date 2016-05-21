package Control;

import Model.MotivoModel;
import Value.MotivoValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import static javax.ws.rs.client.Entity.json;

/**
 * @author Planejamento
 */
public class MotivoControl {

    public static String listarMotivo(int id) throws ParseException {
        MotivoValue motivo = new MotivoValue(id, null);
        ArrayList<MotivoValue> motivos = MotivoModel.listaMotivo(motivo);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String motivosJson = ow.writeValueAsString(motivos);

        System.out.println("retorno: " + motivosJson);
        return motivosJson;
    }

    public static void excluirMotivo(String json) throws ParseException, IOException {
        MotivoValue motivo = new MotivoValue(json);

        MotivoModel.executaUpdate(
                "  delete from motivo m\n"
                + " where m.id_motivo = " + motivo.getId() + "\n"
                + "   and m.incidencia is null\n"
        );
    }

    public static void alteraMotivo(String json) throws ParseException, IOException {
        MotivoValue motivo = new MotivoValue(json);

        MotivoModel.executaUpdate(
                "  update motivo\n"
                + "   set descricao = '" + motivo.getDescricao() + "'\n"
                + " where id_motivo = " + motivo.getId() + "\n"
                + "   and incidencia is null\n"
        );
    }

    public static void cadastraMotivo(String json) throws ParseException, IOException {
        MotivoValue motivo = new MotivoValue(json);

        MotivoModel.executaUpdate(
                "  insert into motivo\n"
                + "       (descricao, incidencia)\n"
                + "values ('" + motivo.getDescricao() + "', null)\n"
        );
    }

}
