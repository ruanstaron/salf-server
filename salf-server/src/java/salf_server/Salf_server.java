/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salf_server;

import Control.MotivoControl;
import Control.SalaControl;
import java.io.IOException;
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
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public String recebe(String data) {
        return data + "server";
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

    @GET
    @Path("/listaMotivos")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaMotivos() throws ParseException {
        return MotivoControl.listarMotivo();
    }

    @POST
    @Path("/excluiMotivo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void excluiMotivo(String data) throws ParseException, IOException {
        MotivoControl.excluirMotivo(data);
    }

    @POST
    @Path("/alteraMotivo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alteraMotivo(String data) throws ParseException, IOException {
        MotivoControl.alteraMotivo(data);
    }

    @POST
    @Path("/cadastraMotivo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cadastraMotivo(String data) throws ParseException, IOException {
        MotivoControl.cadastraMotivo(data);
    }

    @GET
    @Path("/listaSalas")
    @Produces(MediaType.APPLICATION_JSON)
    public String listaSalas() throws ParseException {
        return SalaControl.listarSala();
    }

    @POST
    @Path("/excluiSala")
    @Consumes(MediaType.APPLICATION_JSON)
    public void excluiSala(String data) throws ParseException, IOException {
        SalaControl.excluirSala(data);
    }

    @POST
    @Path("/alteraSala")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alteraSala(String data) throws ParseException, IOException {
        SalaControl.alteraSala(data);
    }

    @POST
    @Path("/cadastraSala")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cadastraSala(String data) throws ParseException, IOException {
        SalaControl.cadastraSala(data);
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
