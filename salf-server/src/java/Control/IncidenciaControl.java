package Control;

import Model.IncidenciaModel;
import Model.MotivoModel;
import Value.IncidenciaValue;
import Value.MotivoValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author Planejamento
 */
public class IncidenciaControl {

    public static String listar(int id) throws ParseException {
        IncidenciaValue incidencia = new IncidenciaValue(id, null);
        ArrayList<IncidenciaValue> incidencias = IncidenciaModel.lista(incidencia);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String incidenciasJson = ow.writeValueAsString(incidencias);

        return incidenciasJson;
    }

    public static void excluir(int id) throws ParseException, IOException {
        IncidenciaValue incidencia = new IncidenciaValue(id, null);

        IncidenciaModel.executaUpdate(
                "  delete from motivo m\n"
                + " where m.id_motivo = " + incidencia.getId() + "\n"
                + "   and m.incidencia = true\n"
        );
    }

    public static void altera(int id, String json) throws ParseException, IOException {
        IncidenciaValue incidencia = new IncidenciaValue(json);
        incidencia.setId(id);

        IncidenciaModel.executaUpdate(
                "  update motivo\n"
                + "   set descricao = '" + incidencia.getDescricao() + "'\n"
                + " where id_motivo = " + incidencia.getId() + "\n"
                + "   and incidencia = true\n"
        );
    }

    public static void cadastra(String json) throws ParseException, IOException {
        IncidenciaValue incidencia = new IncidenciaValue(json);

        IncidenciaModel.executaUpdate(
                "  insert into motivo\n"
                + "       (descricao, incidencia)\n"
                + "values ('" + incidencia.getDescricao() + "', true)\n"
        );
    }

}
