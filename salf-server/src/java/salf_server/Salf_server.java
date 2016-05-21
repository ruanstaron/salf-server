package salf_server;

import Control.LoginControl;
import Control.MotivoControl;
import Control.SalaControl;
import com.nimbusds.jose.JOSEException;
import java.io.IOException;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author Planejamento
 */
@Path("salf_server")
public class Salf_server {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Salf_server
     */
    public Salf_server() {
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public String efetuaLogin(String data) throws IOException, SQLException, JOSEException {
        if(LoginControl.realizaLogin(data)){
            Token token = new Token();
            String s = token.geraToken(LoginControl.getId_usuario(data));
            return LoginControl.geraLogin(s);
        }
        else{
            return null;
        }
    }

    /**
     * Retrieves representation of an instance of salf_server.Salf_server
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Salf-Server";
    }

    // ------- ------------ - ------
    // MÉTODOS RELACIONADOS A MOTIVO
    // ------- ------------ - ------
    @GET
    @Path("/motivoListar")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaMotivos(@DefaultValue("-1") @QueryParam("id") int id) throws ParseException {
        return MotivoControl.listarMotivo(id);
    }

    @POST
    @Path("/motivoDeletar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void excluiMotivo(String data) throws ParseException, IOException {
        MotivoControl.excluirMotivo(data);
    }

    @POST
    @Path("/motivoAlterar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alteraMotivo(String data) throws ParseException, IOException {
        MotivoControl.alteraMotivo(data);
    }

    @POST
    @Path("/motivoCadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cadastraMotivo(String data) throws ParseException, IOException {
        MotivoControl.cadastraMotivo(data);
    }

    // ------- ------------ - ----
    // MÉTODOS RELACIONADOS A SALA
    // ------- ------------ - ----
    @GET
    @Path("/salaListar")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaSalas(@DefaultValue("-1") @QueryParam("id") int id) throws ParseException, IOException {
        return SalaControl.listarSala(id);
    }

    @POST
    @Path("/salaDeletar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void excluiSala(String json) throws ParseException, IOException {
        SalaControl.excluirSala(json);
    }

    @POST
    @Path("/salaCadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cadastraSala(String json) throws ParseException, IOException {
        SalaControl.cadastraSala(json);
    }

    @POST
    @Path("/salaAlterar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alteraSala(String json) throws ParseException, IOException {
        SalaControl.alteraSala(json);
    }

    /**
     * PUT method for updating or creating an instance of Salf_server
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
