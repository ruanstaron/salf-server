package Control;

import Model.DefaultModel;
import Model.ReservaModel;
import Util.SalfException;
import Util.SalfExceptionUtil;
import Value.ReservaValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Ruan
 */
public class ReservaControl {

    public static void cadastra(String json) throws SalfException, Exception {
        ReservaValue reserva = new ReservaValue(json);

        try {
            DefaultModel.executaUpdate(
                    "  insert into reserva\n"
                    + "       (id_sala, id_usuario, data, id_horario, id_motivo)\n"
                    + "values ('" + reserva.getSala() + "', '" + reserva.getId_usuario() + "', "
                    + "'" + reserva.getData() + "', " + reserva.getId_horario() + ", "
                    + "'" + reserva.getMotivo() + "')\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe uma reserva cadastrada nesta data.");
            }

            throw new Exception(e);
        }
    }

    public static void altera(int id, String json) throws SalfException, Exception {
        ReservaValue reserva = new ReservaValue(json);
        reserva.setId(id);

        try {
            DefaultModel.executaUpdate(
                    "  update reserva\n"
                    + "   set id_sala = '" + reserva.getSala() + "'\n"
                    + "     , data = '" + reserva.getData() + "'\n"
                    + "     , id_horario = " + reserva.getId_horario() + "\n"
                    + "     , id_motivo = " + reserva.getMotivo() + "\n"
                    + " where id_reserva = " + reserva.getId() + "\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.UNIQUE_KEY)) {
                throw new SalfException("Já existe uma reserva cadastrada nesta data.");
            } else if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Reserva não encontrada. Alteração falhou.");
            }

            throw new Exception(e);
        }
    }

    public static void excluir(int id) throws SalfException, Exception {
        ReservaValue reserva = new ReservaValue(id);

        try {
            DefaultModel.executaUpdate(
                    "  delete from reserva \n"
                    + " where id_reserva = " + reserva.getId() + "\n"
            );
        } catch (SQLException e) {
            String sqlState = e.getSQLState();

            if (sqlState.equals(SalfExceptionUtil.NO_AFFECTED_ROWS)) {
                throw new SalfException("Reserva não encontrada. Exclusão falhou.");
            }

            throw new Exception(e);
        }
    }

    public static String listar(int id) throws Exception {
        ReservaValue reserva = new ReservaValue(id);
        ArrayList<ReservaValue> reservas = ReservaModel.lista(reserva);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String reservaJson = ow.writeValueAsString(reservas);

        return reservaJson;
    }

}
