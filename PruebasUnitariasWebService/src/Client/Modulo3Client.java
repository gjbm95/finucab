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
 * Jersey REST client generated for REST resource:Modulo3sResource
 * [/Modulo3]<br>
 * USAGE:
 * <pre>
 *        Modulo3Client client = new Modulo3Client();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Junior
 */
public class Modulo3Client {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FinUcabWebService/webresources";

    public Modulo3Client() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Modulo3");
    }

    public String getObtenerPresupuesto(String nombrePresupuesto) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (nombrePresupuesto != null) {
            resource = resource.queryParam("nombrePresupuesto", nombrePresupuesto);
        }
        resource = resource.path("ObtenerPresupuesto");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public Response postJson(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public String modificarPresupuesto(String nombrePresupuesto, String usuarioid, String datosPresupuesto) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (nombrePresupuesto != null) {
            resource = resource.queryParam("nombrePresupuesto", nombrePresupuesto);
        }
        if (usuarioid != null) {
            resource = resource.queryParam("usuarioid", usuarioid);
        }
        if (datosPresupuesto != null) {
            resource = resource.queryParam("datosPresupuesto", datosPresupuesto);
        }
        resource = resource.path("ModificarPresupuesto");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String VerificarNombre(String nombrePresupuesto) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (nombrePresupuesto != null) {
            resource = resource.queryParam("nombrePresupuesto", nombrePresupuesto);
        }
        resource = resource.path("VerificarNombre");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getListaPresupuesto(String idUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idUsuario != null) {
            resource = resource.queryParam("idUsuario", idUsuario);
        }
        resource = resource.path("ListaPresupuesto");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String ObtenerSpinnerCategoria(String usuarioid) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (usuarioid != null) {
            resource = resource.queryParam("usuarioid", usuarioid);
        }
        resource = resource.path("ObtenerSpinnerCategoria");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getTodoslosPresupuestos(String idUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idUsuario != null) {
            resource = resource.queryParam("idUsuario", idUsuario);
        }
        resource = resource.path("ListaPresupuestoExportar");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String registrarPresupuesto(String usuarioid, String datosPresupuesto) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (usuarioid != null) {
            resource = resource.queryParam("usuarioid", usuarioid);
        }
        if (datosPresupuesto != null) {
            resource = resource.queryParam("datosPresupuesto", datosPresupuesto);
        }
        resource = resource.path("registrarPresupuesto");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getEliminarPresupuesto(String nombrePresupuesto, String idUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (nombrePresupuesto != null) {
            resource = resource.queryParam("nombrePresupuesto", nombrePresupuesto);
        }
        if (idUsuario != null) {
            resource = resource.queryParam("idUsuario", idUsuario);
        }
        resource = resource.path("EliminarPresupuesto");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
