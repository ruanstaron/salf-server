package Control;

import Model.SalaModel;
import Value.SalaValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author cristhian
 */
public class SalaControl {

    public static String listarSala(int id) throws ParseException, IOException {
        SalaValue sala = new SalaValue(id, null);
        ArrayList<SalaValue> salas = SalaModel.listaSala(sala);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String salasJson = ow.writeValueAsString(salas);

        return salasJson;
    }

    public static void excluirSala(String json) throws ParseException, IOException {
        SalaValue sala = new SalaValue(json);
        
        SalaModel.executaUpdate(
                "  delete from sala s\n"
                + " where s.id_sala = " + sala.getId() + "\n"
        );
    }

    public static void alteraSala(String json) throws ParseException, IOException {
        SalaValue sala = new SalaValue(json);

        SalaModel.executaUpdate(
                "  update sala\n"
                + "   set descricao = '" + sala.getDescricao() + "'\n"
                + " where id_sala = " + sala.getId() + "\n"
        );
    }

    public static void cadastraSala(String json) throws ParseException, IOException {
        SalaValue sala = new SalaValue(json);

        SalaModel.executaUpdate(
                "  insert into sala\n"
                + "       (descricao)\n"
                + "values ('" + sala.getDescricao() + "')\n"
        );
    }

}
