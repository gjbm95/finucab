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
 * Jersey REST client generated for REST resource:Modulo5sResource
 * [/Modulo5]<br>
 * USAGE:
 * <pre>
 *        Modulo5Client client = new Modulo5Client();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Junior
 */
public class Modulo5Client {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://192.168.0.107:8080/FinUcabWebService/webresources";

    public Modulo5Client() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Modulo5");
    }

    public String getPruebaJson() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("prueba");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public Response postJson(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public String modificarPago(String datosPago) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosPago != null) {
            resource = resource.queryParam("datosPago", datosPago);
        }
        resource = resource.path("modificarPago");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getPruebaDataBase() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("pruebaDB");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String registrarPago(String datosPago) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosPago != null) {
            resource = resource.queryParam("datosPago", datosPago);
        }
        resource = resource.path("registrarPago");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String consultarPago(String datosPago) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosPago != null) {
            resource = resource.queryParam("datosPago", datosPago);
        }
        resource = resource.path("consultarPago");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String visualizarPago(String datosPago) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosPago != null) {
            resource = resource.queryParam("datosPago", datosPago);
        }
        resource = resource.path("visualizarPago");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
