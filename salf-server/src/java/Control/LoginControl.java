package Control;

import Model.LoginModel;
import Value.LoginValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Planejamento
 */
public class LoginControl {
    
    public static boolean realizaLogin(String json) throws IOException, SQLException{
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readValue(json, JsonNode.class);

        LoginValue login = new LoginValue(
                node.get("usuario").asText(),
                node.get("senha").asText()
        );
        return LoginModel.realizaLogin(login.getUsuario(), login.getSenha());
    }
    
    public static String geraLogin(String token){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(token);
        return json;
    }
    
    public static int getId_usuario(String json) throws IOException{
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readValue(json, JsonNode.class);

        LoginValue login = new LoginValue(
                node.get("usuario").asText(),
                node.get("senha").asText()
        );
        return LoginModel.getId_usuario(login.getUsuario());
    }
}
