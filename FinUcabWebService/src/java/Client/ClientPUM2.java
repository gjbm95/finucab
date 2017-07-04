/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:Modulo2sResource
 * [/Modulo2]<br>
 * USAGE:
 * <pre>
 *        ClientPUM2 client = new ClientPUM2();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Alejandro Negrin
 */
public class ClientPUM2 {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FinUcabWebService/webresources";

    public ClientPUM2() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = (WebTarget) client.target(BASE_URI).path("Modulo2");
    }

    public String agregarTDC(String datosTDC) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosTDC != null) {
            resource = resource.queryParam("datosTDC", datosTDC);
        }
        resource = resource.path("agregarTDC");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String actualizarTDC(String datosTDC) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosTDC != null) {
            resource = resource.queryParam("datosTDC", datosTDC);
        }
        resource = resource.path("actualizarTDC");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String agregarCuentaBancaria(String datosCuenta) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosCuenta != null) {
            resource = resource.queryParam("datosCuenta", datosCuenta);
        }
        resource = resource.path("agregarCuentaBancaria");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String eliminarCuentaBancaria(String idCuenta) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idCuenta != null) {
            resource = resource.queryParam("idCuenta", idCuenta);
        }
        resource = resource.path("eliminarCuentaBancaria");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String eliminarTDC(String idtdc) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idtdc != null) {
            resource = resource.queryParam("idtdc", idtdc);
        }
        resource = resource.path("eliminarTDC");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String consultarTDC(String idUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idUsuario != null) {
            resource = resource.queryParam("idUsuario", idUsuario);
        }
        resource = resource.path("consultarTDC");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String consultarCuentas(String idUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idUsuario != null) {
            resource = resource.queryParam("idUsuario", idUsuario);
        }
        resource = resource.path("consultarCuentas");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String actualizarDatosUsuario(String datosUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosUsuario != null) {
            resource = resource.queryParam("datosUsuario", datosUsuario);
        }
        resource = resource.path("actualizarDatosUsuario");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String actualizarCuentaBancaria(String datosCuenta) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (datosCuenta != null) {
            resource = resource.queryParam("datosCuenta", datosCuenta);
        }
        resource = resource.path("actualizarCuentaBancaria");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String consultarEstadisticas(String idUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idUsuario != null) {
            resource = resource.queryParam("idUsuario", idUsuario);
        }
        resource = resource.path("consultarEstadisticas");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
