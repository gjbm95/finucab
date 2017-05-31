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
 * Jersey REST client generated for REST resource:Modulo1sResource
 * [/Modulo1]<br>
 * USAGE:
 * <pre>
 *        Modulo1Client client = new Modulo1Client();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author AlejandroNegrin
 */
public class Modulo1Client {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FinUcabWebService/webresources";

    public Modulo1Client() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Modulo1");
    }

    public String getPruebaJson() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("prueba");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String registrarUsuario(String datosUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosUsuario != null) {
            resource = resource.queryParam("datosUsuario", datosUsuario);
        }
        resource = resource.path("registrarUsuario");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String recuperarClave(String datosUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosUsuario != null) {
            resource = resource.queryParam("datosUsuario", datosUsuario);
        }
        resource = resource.path("recuperarClave");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public Response postJson(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public String actualizarClave(String datosUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosUsuario != null) {
            resource = resource.queryParam("datosUsuario", datosUsuario);
        }
        resource = resource.path("actualizarClave");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getPruebaDataBase() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("pruebaDB");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String iniciarSesion(String datosUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosUsuario != null) {
            resource = resource.queryParam("datosUsuario", datosUsuario);
        }
        resource = resource.path("iniciarSesion");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getData(Object requestEntity) throws ClientErrorException {
        return webTarget.path("pruebaData").request(javax.ws.rs.core.MediaType.TEXT_PLAIN).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_PLAIN), String.class);
    }

    public String verificarUsuario(String nombreUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (nombreUsuario != null) {
            resource = resource.queryParam("nombreUsuario", nombreUsuario);
        }
        resource = resource.path("verificarUsuario");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
