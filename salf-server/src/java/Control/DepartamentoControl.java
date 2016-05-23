package Control;

import Model.DepartamentoModel;
import Value.DepartamentoValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author cristhian
 */
public class DepartamentoControl {

    public static String listar(int id) throws ParseException, IOException {
        DepartamentoValue departamento = new DepartamentoValue(id, null);
        ArrayList<DepartamentoValue> departamentos = DepartamentoModel.lista(departamento);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String departamentosJson = ow.writeValueAsString(departamentos);

        return departamentosJson;
    }

    public static void excluir(int id) throws ParseException, IOException {
        DepartamentoValue departamento = new DepartamentoValue(id, null);
        
        DepartamentoModel.executaUpdate(
                "  delete from departamento d\n"
                + " where d.id_departamento = " + departamento.getId() + "\n"
        );
    }

    public static void altera(int id, String json) throws ParseException, IOException {
        DepartamentoValue departamento = new DepartamentoValue(json);
        departamento.setId(id);

        DepartamentoModel.executaUpdate(
                "  update departamento\n"
                + "   set descricao = '" + departamento.getDescricao() + "'\n"
                + " where id_departamento = " + departamento.getId() + "\n"
        );
    }

    public static void cadastra(String json) throws ParseException, IOException {
        DepartamentoValue departamento = new DepartamentoValue(json);

        DepartamentoModel.executaUpdate(
                "  insert into departamento\n"
                + "       (descricao)\n"
                + "values ('" + departamento.getDescricao() + "')\n"
        );
    }

}
