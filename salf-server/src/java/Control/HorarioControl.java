package Control;

import Model.HorarioModel;
import Value.HorarioValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.ArrayList;

/**
 * @author Ruan
 */
public class HorarioControl {

    public static String listar(int id) throws Exception {
        HorarioValue horario = new HorarioValue(id, null);
        ArrayList<HorarioValue> horarios = HorarioModel.lista(horario);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String horariosJson = ow.writeValueAsString(horarios);

        return horariosJson;
    }
}
