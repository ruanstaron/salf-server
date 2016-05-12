/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salf_server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelos.Motivo;

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

    @Path("/listaMotivos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listaMotivos() {
        Motivo motivo = new Motivo(1, "teste");
        List<Motivo> lista = new ArrayList<Motivo>();
        lista.add(motivo);
        
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(lista);
        
        System.out.println("json: " + json);

        return json;
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
