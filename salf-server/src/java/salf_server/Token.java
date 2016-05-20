/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salf_server;

import Control.TokenControl;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Planejamento
 */
public class Token {
        
    public Token(){
        
    }

    public String geraToken(int id) throws KeyLengthException, JOSEException, SQLException{
        //gera segredo
        SecureRandom random = new SecureRandom();
        byte[] chave = new byte[32];
        random.nextBytes(chave);
        TokenControl.cadastraToken(id, chave);
        //Aplica encryptação HMAC
        JWSSigner signer = new MACSigner(chave);
        //cria o token
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        .subject(id)
        .expirationTime(new Date(1300819380 * 1000l))
        //.claim("http://example.com/is_root", true)
        .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        //Aplica a proteção HMAC
        signedJWT.sign(signer);
        //serializa
        String s = signedJWT.serialize();
        return s;
    }
    
    public boolean validaToken(String msg) throws JOSEException{
        SignedJWT token;
        int id;
        byte[] chave;
        try {
            //Pega a string e transforma em token
            token = SignedJWT.parse(msg);
            //recupera o id do usuario do token
            id = Integer.parseInt(token.getJWTClaimsSet().getSubject());
            //pede a chave que está cadastrada no banco para aquele id
            chave = TokenControl.getChave(id);
            //cria o objeto para verificação da chave
            JWSVerifier verifier = new MACVerifier(chave);
            //retorna se foi verificado com sucesso
            return token.verify(verifier);
        } catch (ParseException ex) {
            return false;
        }
    }
    
    public int getId(String msg) throws ParseException{
        int id;
        SignedJWT token;
        token = SignedJWT.parse(msg);
        id = Integer.parseInt(token.getJWTClaimsSet().getSubject());
        return id;
    }
}
