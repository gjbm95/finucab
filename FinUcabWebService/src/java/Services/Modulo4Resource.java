/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author AlejandroNegrin
 */
public class Modulo4Resource {

    private String id;

    /**
     * Creates a new instance of Modulo4Resource
     */
    private Modulo4Resource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the Modulo4Resource
     */
    public static Modulo4Resource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of Modulo4Resource class.
        return new Modulo4Resource(id);
    }

    /**
     * Retrieves representation of an instance of Services.Modulo4Resource
     * @return an instance of javax.json.Json
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Json getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of Modulo4Resource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Json content) {
    }

    /**
     * DELETE method for resource Modulo4Resource
     */
    @DELETE
    public void delete() {
    }
}
