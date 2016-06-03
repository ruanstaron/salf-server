package Control;

import Model.ReservaModel;
import Value.ReservaValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author Ruan
 */
public class ReservaControl {

    public static void cadastra(String json) throws ParseException, IOException {
        ReservaValue reserva = new ReservaValue(json);

        ReservaModel.executaUpdate(
                "  insert into reserva\n"
                + "       (id_sala, id_usuario, data, hora, id_motivo)\n"
                + "values ('" + reserva.getSala() + "', '" + reserva.getId_usuario() + "', " +
                        "'" + reserva.getData() + "', '" + reserva.getHora() + "', " +
                        "'" + reserva.getMotivo() + "')\n"
        );
    }
    
    public static void altera(int id, String json) throws ParseException, IOException {
        ReservaValue reserva = new ReservaValue(json);
        reserva.setId(id);

        ReservaModel.executaUpdate(
                "  update reserva\n"
                + "   set id_sala = '" + reserva.getSala() + "'\n"
                + "     , data = '" + reserva.getData() + "'\n"
                + "     , hora = '" + reserva.getHora() + "'\n"
                + "     , id_motivo = " + reserva.getMotivo()+ "\n"
                + " where id_reserva = " + reserva.getId() + "\n"
        );
    }
    
    public static void excluir(int id) throws ParseException, IOException {
        ReservaValue reserva = new ReservaValue(id);
        
        ReservaModel.executaUpdate(
                "  delete from reserva \n"
                + " where id_reserva = " + reserva.getId() + "\n"
        );
    }
    
    public static String listar(int id) throws ParseException, IOException {
        ReservaValue reserva = new ReservaValue(id);
        ArrayList<ReservaValue> reservas = ReservaModel.lista(reserva);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String reservaJson = ow.writeValueAsString(reservas);

        return reservaJson;
    }

}
