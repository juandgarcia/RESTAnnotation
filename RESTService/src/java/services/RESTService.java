package services;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Servicio de prueba para el demo de la anotación RESTAnnotation
 *
 * @author Juan David García Manrique
 */
@Stateless
@Path("/MyService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RESTService {

    @GET
    @Path("/myGetMethod/{id}")
    public String myGetMethod(@PathParam("id") int id) {
        return "[{\"respuesta\":\"Recibido el ID: " + id + " por GET.\"}]";
    }
}
