package salf_server;

import Control.DepartamentoControl;
import Control.LoginControl;
import Control.MotivoControl;
import Control.ProfessorControl;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;

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
    @Path("/motivo")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaMotivos() throws ParseException {
        return MotivoControl.listar(-1);
    }
    
    @GET
    @Path("/motivo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaMotivos(@PathParam("id") int id) throws ParseException {
        return MotivoControl.listar(id);
    }

    @DELETE
    @Path("/motivo/{id}")
    public void excluiMotivo(@PathParam("id") int id) throws ParseException, IOException {
        MotivoControl.excluir(id);
    }

    @PUT
    @Path("/motivo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alteraMotivo(@PathParam("id") int id, String data) throws ParseException, IOException {
        MotivoControl.altera(id, data);
    }

    @POST
    @Path("/motivo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cadastraMotivo(String data) throws ParseException, IOException {
        MotivoControl.cadastra(data);
    }

    // ------- ------------ - ----
    // MÉTODOS RELACIONADOS A SALA
    // ------- ------------ - ----
    @GET
    @Path("/sala")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaSalas() throws ParseException, IOException {
        return SalaControl.listar(-1);
    }
    
    @GET
    @Path("/sala/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaSalas(@PathParam("id") int id) throws ParseException, IOException {
        return SalaControl.listar(id);
    }

    @DELETE
    @Path("/sala/{id}")
    public void excluiSala(@PathParam("id") int id) throws ParseException, IOException {
        SalaControl.excluir(id);
    }

    @POST
    @Path("/sala")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cadastraSala(String json) throws ParseException, IOException {
        SalaControl.cadastra(json);
    }

    @PUT
    @Path("/sala/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alteraSala(@PathParam("id") int id, String json) throws ParseException, IOException {
        SalaControl.altera(id, json);
    }

    // ------- ------------ - ------------
    // MÉTODOS RELACIONADOS A DEPARTAMENTO
    // ------- ------------ - ------------
    @GET
    @Path("/departamento")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaDepartamentos() throws ParseException, IOException {
        return DepartamentoControl.listar(-1);
    }
    
    @GET
    @Path("/departamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaDepartamentos(@PathParam("id") int id) throws ParseException, IOException {
        return DepartamentoControl.listar(id);
    }

    @DELETE
    @Path("/departamento/{id}")
    public void excluiDepartamento(@PathParam("id") int id) throws ParseException, IOException {
        DepartamentoControl.excluir(id);
    }

    @POST
    @Path("/departamento")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cadastraDepartamento(String json) throws ParseException, IOException {
        DepartamentoControl.cadastra(json);
    }

    @PUT
    @Path("/departamento/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alteraDepartamento(@PathParam("id") int id, String json) throws ParseException, IOException {
        DepartamentoControl.altera(id, json);
    }
    
    // ------- ------------ - -----------
    // MÉTODOS RELACIONADOS A PROFESSORES
    // ------- ------------ - -----------
    @GET
    @Path("/professor")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaProfessores() throws ParseException, IOException {
        return ProfessorControl.listar(-1);
    }
    
    @GET
    @Path("/professor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaProfessores(@PathParam("id") int id) throws ParseException, IOException {
        return ProfessorControl.listar(id);
    }

    @DELETE
    @Path("/professor/{id}")
    public void excluiProfessor(@PathParam("id") int id) throws ParseException, IOException {
        ProfessorControl.excluir(id);
    }

    @POST
    @Path("/professor")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cadastraProfessor(String json) throws ParseException, IOException {
        ProfessorControl.cadastra(json);
    }

    @PUT
    @Path("/professor/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alteraProfessor(@PathParam("id") int id, String json) throws ParseException, IOException {
        ProfessorControl.altera(id, json);
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
