/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.TokenModel;
import java.sql.SQLException;

/**
 *
 * @author Planejamento
 */
public class TokenControl {
    
    public static void cadastraToken(int id, byte[] chave) throws SQLException{
        String s = new String(chave);
        TokenModel.cadastraToken(id, s);
    }
    
    public static byte[] getChave(int id){
        String s = TokenModel.getChave(id);
        byte[] chave = s.getBytes();
        return chave;
    }
}
