package Control;

import Model.LoginModel;
import Value.ProfessorValue;
import java.util.ArrayList;

/**
 * @author cristhian
 */
public class LoginControl {
    
    private static final boolean IGNORA_VALIDACAO = false;
    private static final boolean IGNORA_VALIDACAO_ADM = false;
    
    public static boolean checaLogin(String user, String password, boolean adm) throws Exception {
        if(IGNORA_VALIDACAO) return true;
        if(user == null || password == null) {
            throw new Exception("É preciso estar logado para realizar esta operação.");
        }
        
        ArrayList<ProfessorValue> logins = LoginModel.checaLogin(user, password);
        
        if(logins.isEmpty()) {
            throw new Exception("Usuário ou senha incorretos.");
        }
        if(!IGNORA_VALIDACAO_ADM && (adm && logins.get(0).adm == false)) {
            throw new Exception("É preciso ter privilégios de administrador para realizar esta operação.");
        }
        
        return logins.get(0).adm;
    }
    
    public static boolean checaLogin(String user, String password) throws Exception {
        return checaLogin(user, password, false);
    }
    
}
