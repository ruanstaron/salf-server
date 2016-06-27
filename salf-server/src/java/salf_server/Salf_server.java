package salf_server;

import Control.DepartamentoControl;
import Control.HorarioControl;
import Control.IncidenciaControl;
import static Control.LoginControl.checaLogin;
import Control.MotivoControl;
import Control.ProfessorControl;
import Control.ReservaControl;
import Control.SalaControl;
import Util.SalfExceptionUtil;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
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
                .header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
        return rb.build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response efetuaLogin(@HeaderParam("user") String user, @HeaderParam("password") String password) {
        try {
            boolean adm = checaLogin(user, password);
            return makeCors(Response.ok().entity(
                    "{\"adm\": " + (adm ? "true" : "false") + "}"
            ));
        } catch(Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
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
    public Response listaMotivos(@HeaderParam("user") String user, @HeaderParam("password") String password) {
        try {
            checaLogin(user, password);
            return makeCors(Response.ok().entity(
                    MotivoControl.listar(-1)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @GET
    @Path("/motivo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaMotivos(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password);
            return makeCors(Response.ok().entity(
                    MotivoControl.listar(id)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @DELETE
    @Path("/motivo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluiMotivo(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password, true);
            MotivoControl.excluir(id);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @PUT
    @Path("/motivo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraMotivo(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id, String data) {
        try {
            checaLogin(user, password);
            MotivoControl.altera(id, data);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @POST
    @Path("/motivo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraMotivo(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            String data) {
        try {
            checaLogin(user, password);
            MotivoControl.cadastra(data);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    // ------- ------------ - ----
    // MÉTODOS RELACIONADOS A SALA
    // ------- ------------ - ----
    @GET
    @Path("/sala")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaSalas(@HeaderParam("user") String user, @HeaderParam("password") String password) {
        try {
            checaLogin(user, password);
            return makeCors(Response.ok().entity(
                    SalaControl.listar(-1)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @GET
    @Path("/sala/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaSalas(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password);
            return makeCors(Response.ok().entity(
                    SalaControl.listar(id)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @DELETE
    @Path("/sala/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluiSala(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password, true);
            SalaControl.excluir(id);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @POST
    @Path("/sala")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraSala(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            String json) {
        try {
            checaLogin(user, password, true);
            SalaControl.cadastra(json);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @PUT
    @Path("/sala/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraSala(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id, String json) {
        try {
            checaLogin(user, password, true);
            SalaControl.altera(id, json);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    // ------- ------------ - ------------
    // MÉTODOS RELACIONADOS A DEPARTAMENTO
    // ------- ------------ - ------------
    @GET
    @Path("/departamento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaDepartamentos(@HeaderParam("user") String user, @HeaderParam("password") String password) {
        try {
            checaLogin(user, password);
            return makeCors(Response.ok().entity(
                    DepartamentoControl.listar(-1)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @GET
    @Path("/departamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaDepartamentos(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password);
            return makeCors(Response.ok().entity(
                    DepartamentoControl.listar(id)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @DELETE
    @Path("/departamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluiDepartamento(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password, true);
            DepartamentoControl.excluir(id);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @POST
    @Path("/departamento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraDepartamento(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            String json) {
        try {
            checaLogin(user, password, true);
            DepartamentoControl.cadastra(json);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @PUT
    @Path("/departamento/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraDepartamento(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id, String json) {
        try {
            checaLogin(user, password, true);
            DepartamentoControl.altera(id, json);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    // ------- ------------ - -----------
    // MÉTODOS RELACIONADOS A PROFESSORES
    // ------- ------------ - -----------
    @GET
    @Path("/professor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProfessores(@HeaderParam("user") String user, @HeaderParam("password") String password) {
        try {
            checaLogin(user, password, true);
            return makeCors(Response.ok().entity(
                    ProfessorControl.listar(-1)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @GET
    @Path("/professor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProfessores(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password, true);
            return makeCors(Response.ok().entity(
                    ProfessorControl.listar(id)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @DELETE
    @Path("/professor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluiProfessor(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password, true);
            ProfessorControl.excluir(id);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @POST
    @Path("/professor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraProfessor(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            String json) {
        try {
            checaLogin(user, password, true);
            ProfessorControl.cadastra(json);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @PUT
    @Path("/professor/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraProfessor(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id, String json) {
        try {
            checaLogin(user, password, true);
            ProfessorControl.altera(id, json);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    // ------- ------------ - ----------
    // MÉTODOS RELACIONADOS A INCIDENCIA
    // ------- ------------ - ----------
    @GET
    @Path("/incidencia")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaIncidencias(@HeaderParam("user") String user, @HeaderParam("password") String password) {
        try {
            checaLogin(user, password, true);
            return makeCors(Response.ok().entity(
                    IncidenciaControl.listar(-1)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @GET
    @Path("/incidencia/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaIncidencias(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password, true);
            return makeCors(Response.ok().entity(
                    IncidenciaControl.listar(id)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @DELETE
    @Path("/incidencia/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluiIncidencia(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password, true);
            IncidenciaControl.excluir(id);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @PUT
    @Path("/incidencia/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraIncidencia(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id, String data) {
        try {
            checaLogin(user, password, true);
            IncidenciaControl.altera(id, data);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @POST
    @Path("/incidencia")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraIncidencia(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            String data) {
        try {
            checaLogin(user, password, true);
            IncidenciaControl.cadastra(data);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    // ------- ------------ - -------
    // MÉTODOS RELACIONADOS A RESERVA
    // ------- ------------ - -------
    @POST
    @Path("/reserva")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraReserva(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            String json) {
        try {
            checaLogin(user, password);
            ReservaControl.cadastra(json);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @PUT
    @Path("/reserva/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraReserva(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id, String data) {
        try {
            checaLogin(user, password);
            ReservaControl.altera(id, data);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @DELETE
    @Path("/reserva/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluiReserva(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password);
            ReservaControl.excluir(id);
            return makeCors(Response.ok());
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @GET
    @Path("/reserva")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaReserva(@HeaderParam("user") String user, @HeaderParam("password") String password) {
        try {
            checaLogin(user, password);
            return makeCors(Response.ok().entity(
                    ReservaControl.listar(-1)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @GET
    @Path("/reserva/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaReserva(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password);
            return makeCors(Response.ok().entity(
                    ReservaControl.listar(id)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }
    
    // ------- ------------ - -------
    // MÉTODOS RELACIONADOS A HORÁRIO
    // ------- ------------ - -------
    
    @GET
    @Path("/horarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaHorarios(@HeaderParam("user") String user, @HeaderParam("password") String password) {
        try {
            checaLogin(user, password);
            return makeCors(Response.ok().entity(
                    HorarioControl.listar(-1)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
    }

    @GET
    @Path("/horarios/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaHorarios(@HeaderParam("user") String user, @HeaderParam("password") String password, 
            @PathParam("id") int id) {
        try {
            checaLogin(user, password);
            return makeCors(Response.ok().entity(
                    HorarioControl.listar(id)
            ));
        } catch (Exception e) {
            return makeCors(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(SalfExceptionUtil.toJson(e.getMessage()
                            .replace(Character.toChars(10)[0], ' ')
                            .replace("\"", "\'")
                    )));
        }
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
