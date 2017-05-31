/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:Modulo4sResource
 * [/Modulo4]<br>
 * USAGE:
 * <pre>
 *        Modulo4Client client = new Modulo4Client();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Junior
 */
public class Modulo4Client {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FinUcabWebService/webresources";

    public Modulo4Client() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Modulo4");
    }

    public String getPruebaJson() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("prueba");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String buscarCategoria(String datosCategoria) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosCategoria != null) {
            resource = resource.queryParam("datosCategoria", datosCategoria);
        }
        resource = resource.path("buscarCategoria");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public Response postJson(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public String eliminarCategoria(String datosCategoria) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosCategoria != null) {
            resource = resource.queryParam("datosCategoria", datosCategoria);
        }
        resource = resource.path("eliminarCategoria");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getPruebaDataBase() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("pruebaDB");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String modificarCategoria(String datosCategoria) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosCategoria != null) {
            resource = resource.queryParam("datosCategoria", datosCategoria);
        }
        resource = resource.path("modificarCategoria");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String VisualizarCategoria(String datosCategoria) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosCategoria != null) {
            resource = resource.queryParam("datosCategoria", datosCategoria);
        }
        resource = resource.path("visualizarCategoria");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String registrarCategoria(String datosCategoria) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosCategoria != null) {
            resource = resource.queryParam("datosCategoria", datosCategoria);
        }
        resource = resource.path("registrarCategoria");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
