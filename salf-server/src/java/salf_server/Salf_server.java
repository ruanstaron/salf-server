package salf_server;

import Control.DepartamentoControl;
import Control.IncidenciaControl;
import Control.LoginControl;
import Control.MotivoControl;
import Control.ProfessorControl;
import Control.ReservaControl;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

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
    
    private Response makeCors(ResponseBuilder req) {
        ResponseBuilder rb = req
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");;
        return rb.build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public String efetuaLogin(String data) throws IOException, SQLException, JOSEException {
        if (LoginControl.realizaLogin(data)) {
            Token token = new Token();
            String s = token.geraToken(LoginControl.getId_usuario(data));
            return LoginControl.geraLogin(s);
        } else {
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
    public Response listaMotivos() throws ParseException {
        return makeCors(Response.ok().entity(
                MotivoControl.listar(-1)
        ));
    }

    @GET
    @Path("/motivo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaMotivos(@PathParam("id") int id) throws ParseException {
        return makeCors(Response.ok().entity(
                MotivoControl.listar(id)
        ));
    }

    @DELETE
    @Path("/motivo/{id}")
    public Response excluiMotivo(@PathParam("id") int id) throws ParseException, IOException {
        MotivoControl.excluir(id);
        return makeCors(Response.ok());
    }

    @PUT
    @Path("/motivo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alteraMotivo(@PathParam("id") int id, String data) throws ParseException, IOException {
        MotivoControl.altera(id, data);
        return makeCors(Response.ok());
    }

    @POST
    @Path("/motivo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastraMotivo(String data) throws ParseException, IOException {
        MotivoControl.cadastra(data);
        return makeCors(Response.ok());
    }

    // ------- ------------ - ----
    // MÉTODOS RELACIONADOS A SALA
    // ------- ------------ - ----
    @GET
    @Path("/sala")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaSalas() throws ParseException, IOException {
        return makeCors(Response.ok().entity(
                SalaControl.listar(-1)
        ));
    }

    @GET
    @Path("/sala/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaSalas(@PathParam("id") int id) throws ParseException, IOException {
        return makeCors(Response.ok().entity(
                SalaControl.listar(id)
        ));
    }

    @DELETE
    @Path("/sala/{id}")
    public Response excluiSala(@PathParam("id") int id) throws ParseException, IOException {
        SalaControl.excluir(id);
        return makeCors(Response.ok());
    }

    @POST
    @Path("/sala")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastraSala(String json) throws ParseException, IOException {
        SalaControl.cadastra(json);
        return makeCors(Response.ok());
    }

    @PUT
    @Path("/sala/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alteraSala(@PathParam("id") int id, String json) throws ParseException, IOException {
        SalaControl.altera(id, json);
        return makeCors(Response.ok());
    }

    // ------- ------------ - ------------
    // MÉTODOS RELACIONADOS A DEPARTAMENTO
    // ------- ------------ - ------------
    @GET
    @Path("/departamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaDepartamentos() throws ParseException, IOException {
        return makeCors(Response.ok().entity(
                DepartamentoControl.listar(-1)
        ));
    }

    @GET
    @Path("/departamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaDepartamentos(@PathParam("id") int id) throws ParseException, IOException {
        return makeCors(Response.ok().entity(
                DepartamentoControl.listar(id)
        ));
    }

    @DELETE
    @Path("/departamento/{id}")
    public Response excluiDepartamento(@PathParam("id") int id) throws ParseException, IOException {
        DepartamentoControl.excluir(id);
        return makeCors(Response.ok());
    }

    @POST
    @Path("/departamento")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastraDepartamento(String json) throws ParseException, IOException {
        DepartamentoControl.cadastra(json);
        return makeCors(Response.ok());
    }

    @PUT
    @Path("/departamento/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alteraDepartamento(@PathParam("id") int id, String json) throws ParseException, IOException {
        DepartamentoControl.altera(id, json);
        return makeCors(Response.ok());
    }

    // ------- ------------ - -----------
    // MÉTODOS RELACIONADOS A PROFESSORES
    // ------- ------------ - -----------
    @GET
    @Path("/professor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProfessores() throws ParseException, IOException {
        return makeCors(Response.ok().entity(
                ProfessorControl.listar(-1)
        ));
    }

    @GET
    @Path("/professor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProfessores(@PathParam("id") int id) throws ParseException, IOException {
        return makeCors(Response.ok().entity(
                ProfessorControl.listar(id)
        ));
    }

    @DELETE
    @Path("/professor/{id}")
    public Response excluiProfessor(@PathParam("id") int id) throws ParseException, IOException {
        ProfessorControl.excluir(id);
        return makeCors(Response.ok());
    }

    @POST
    @Path("/professor")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastraProfessor(String json) throws ParseException, IOException {
        ProfessorControl.cadastra(json);
        return makeCors(Response.ok());
    }

    @PUT
    @Path("/professor/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alteraProfessor(@PathParam("id") int id, String json) throws ParseException, IOException {
        ProfessorControl.altera(id, json);
        return makeCors(Response.ok());
    }

    // ------- ------------ - ----------
    // MÉTODOS RELACIONADOS A INCIDENCIA
    // ------- ------------ - ----------
    @GET
    @Path("/incidencia")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaIncidencias() throws ParseException {
        return makeCors(Response.ok().entity(
                IncidenciaControl.listar(-1)
        ));
    }

    @GET
    @Path("/incidencia/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaIncidencias(@PathParam("id") int id) throws ParseException {
        return makeCors(Response.ok().entity(
                IncidenciaControl.listar(id)
        ));
    }

    @DELETE
    @Path("/incidencia/{id}")
    public Response excluiIncidencia(@PathParam("id") int id) throws ParseException, IOException {
        IncidenciaControl.excluir(id);
        return makeCors(Response.ok());
    }

    @PUT
    @Path("/incidencia/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alteraIncidencia(@PathParam("id") int id, String data) throws ParseException, IOException {
        IncidenciaControl.altera(id, data);
        return makeCors(Response.ok());
    }

    @POST
    @Path("/incidencia")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastraIncidencia(String data) throws ParseException, IOException {
        IncidenciaControl.cadastra(data);
        return makeCors(Response.ok());
    }
    
    // ------- ------------ - -------
    // MÉTODOS RELACIONADOS A RESERVA
    // ------- ------------ - -------
    @POST
    @Path("/reserva")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastraReserva(String json) throws ParseException, IOException {
        ReservaControl.cadastra(json);
        return makeCors(Response.ok());
    }
    
    @PUT
    @Path("/reserva/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alteraReserva(@PathParam("id") int id, String data) throws ParseException, IOException {
        ReservaControl.altera(id, data);
        return makeCors(Response.ok());
    }
    
    @DELETE
    @Path("/reserva/{id}")
    public Response excluiReserva(@PathParam("id") int id) throws ParseException, IOException {
        ReservaControl.excluir(id);
        return makeCors(Response.ok());
    }
    
    @GET
    @Path("/reserva")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaReserva() throws ParseException, IOException {
        return makeCors(Response.ok().entity(
                ReservaControl.listar(-1)
        ));
    }

    @GET
    @Path("/reserva/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaReserva(@PathParam("id") int id) throws ParseException, IOException {
        return makeCors(Response.ok().entity(
                ReservaControl.listar(id)
        ));
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
