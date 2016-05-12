package Control;

import Model.MotivoModel;
import Value.MotivoValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author Planejamento
 */
public class MotivoControl {
    
    public static String listarMotivo() throws ParseException{
        ArrayList<MotivoValue> motivos = MotivoModel.listaMotivo();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(motivos);
        
        return json;
    }
    
    public static void excluirMotivo(String json) throws ParseException, IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readValue(json, JsonNode.class);
        
        MotivoValue motivo = new MotivoValue(
                node.get("id_motivo").asInt(),
                null
        );
        
        MotivoModel.excluiMotivo(motivo);
    }
    
}
