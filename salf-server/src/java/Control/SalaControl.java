package Control;

import Model.SalaModel;
import Value.SalaValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author cristhian
 */
public class SalaControl {

    public static String listarSala() throws ParseException {
        ArrayList<SalaValue> salas = SalaModel.listaSala();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(salas);

        return json;
    }

    public static void excluirSala(String json) throws ParseException, IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readValue(json, JsonNode.class);

        SalaValue sala = new SalaValue(
                node.get("id_sala").asInt(),
                null
        );

        SalaModel.executaUpdate(
                "  delete from sala s\n"
                + " where s.id_sala = " + sala.getId_sala() + "\n"
        );
    }

    public static void alteraSala(String json) throws ParseException, IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readValue(json, JsonNode.class);

        SalaValue sala = new SalaValue(
                node.get("id_sala").asInt(),
                node.get("descricao").asText()
        );

        SalaModel.executaUpdate(
                "  update sala\n"
                + "   set descricao = '" + sala.getDescricao() + "'\n"
                + " where id_sala = " + sala.getId_sala() + "\n"
        );
    }

    public static void cadastraSala(String json) throws ParseException, IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readValue(json, JsonNode.class);

        SalaValue sala = new SalaValue(
                -1,
                node.get("descricao").asText()
        );

        SalaModel.executaUpdate(
                "  insert into sala\n"
                + "       (descricao)\n"
                + "values ('" + sala.getDescricao() + "')\n"
        );
    }

}
