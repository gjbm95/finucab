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
 * Jersey REST client generated for REST resource:Modulo6sResource
 * [/Modulo6]<br>
 * USAGE:
 * <pre>
 *        Modulo6Client client = new Modulo6Client();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Junior
 */
public class Modulo6Client {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FinUcabWebService/webresources";

    public Modulo6Client() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Modulo6");
    }

    public String getPruebaJson() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("prueba");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String registarPlanificacion(Object requestEntity, String id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("registrarPlanificacion/{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String registrarPlanificacion(String datosPlanificacion) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosPlanificacion != null) {
            resource = resource.queryParam("datosPlanificacion", datosPlanificacion);
        }
        resource = resource.path("registrarPlanificacion");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public Response postJson(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public <T> T modificarPlanificacion(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("modificarPlanificacion").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public String getPruebaDataBase() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("pruebaDB");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String listarPlanificacion(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("listarPlanificacion/{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String VisualizarPlanificacion(String datosPlanificacion) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosPlanificacion != null) {
            resource = resource.queryParam("datosPlanificacion", datosPlanificacion);
        }
        resource = resource.path("visualizarPlanificacion");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
