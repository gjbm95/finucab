
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOUsuario;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.Usuario;
import Exceptions.FabricaExcepcion;
import Exceptions.FinUCABException;
import Logica.Modulo1.ActualizarClaveException;
import Logica.Modulo1.IniciarSesionException;
import Logica.Modulo1.RecuperarClaveException;
import Logica.Modulo1.RegistrarIncorrectoException;
import Logica.Modulo1.VerificarUsuarioException;
import Registro.RegistroBaseDatos;
import Registro.RegistroError;
import Services.Modulo1sResource;
import java.net.URLDecoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
*Modulo 1 - Modulo de DaoUsuario.
*Desarrolladores:
*Mariangel Perez / Oswaldo Lopez / Aquiles Pulido
*Descripción de la clase:
*Clase encargada de contener todos los metodos que manejan la informacion del
*usuario.
*@Params
*
**/

public class DaoUsuario extends DAO implements IDAOUsuario{

    private Connection conn = Conexion.conectarADb();

    DaoUsuario() {
    }

    
    
    
     /**
     * Funcion encargada de agregar el usuario en la BD.
     *
     * @param e entidad que posee los datos a agregar en la BD.
     *
     * @return 0 de retorna esto no se puedo agregar correctamente el usuario,
     * 1 en este caso el usuario es agregado correctamente.
     * @throws Exceptions.FinUCABException
     */
    @Override
    public Entidad agregar(Entidad e) throws RegistrarIncorrectoException {
        Usuario usuario = (Usuario) e;
        int respuesta =0;
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            CallableStatement a = 
                    conn.prepareCall(RegistroBaseDatos.AGREGAR_USUARIO);
            a.setString(1, usuario.getUsuario());
            a.setString(2, usuario.getNombre());
            a.setString(3, usuario.getApellido());
            a.setString(4, usuario.getCorreo());
            a.setString(5, usuario.getPregunta());
            a.setString(6, usuario.getRespuesta());
            a.setString(7, usuario.getContrasena());
            if (a.execute()) {
                st.close();
                respuesta = 1;//Se agrego correctamente el usuario
            } else {
                st.close();
                respuesta = 0;//No se agrega el usuario
          
            }

        } catch (SQLException ex) {
            respuesta = 0;
            throw FabricaExcepcion.
                    instanciarRegistrarIncorrectoException(ex.getErrorCode(),
                            ex.getMessage());                
        }
        return FabricaEntidad.obtenerSimpleResponseStatus(respuesta);
    }
     /**
     * Funcion encargada de actualizar la clave del usuario en la BD por medio 
     * de la opcion olvido su contraseña.
     *
     * @param entidad entidad que posee los datos para modificar la clave del
     * usuario.
     *
     * @return 5 la clave de usuario se modifico correctamente, 6 no se logro
     * modificar la clave del usuario.
     */
    @Override
    public Entidad ActualizarClave(Entidad entidad) 
            throws ActualizarClaveException{
    Usuario usuario = (Usuario) entidad;
    int respuesta = 0;
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            CallableStatement a =
                    conn.prepareCall(RegistroBaseDatos.ACTUALIZAR_CLAVE);
            a.setString(1,usuario.getUsuario());
            a.setString(2,usuario.getContrasena());
            a.execute();
            ResultSet rs = a.getResultSet();
           
            while(rs.next()){
                if (rs.getString(1).equals("1")){
                    st.close();
                    respuesta =  5; //Se modifica correctamente la clave
                }else{ 
                   st.close();
                   respuesta =  6; //No se modifica la clave
                   throw FabricaExcepcion.
                           instanciarActualizarClaveException(201);
                }
            }
         
        } catch (SQLException ex) {
            respuesta = 0;
            throw FabricaExcepcion.
                    instanciarActualizarClaveException(ex.getErrorCode(),
                            ex.getMessage());                
        }
        
        return FabricaEntidad.obtenerSimpleResponseStatus(respuesta);
    }
    
    
     /**
     * Funcion encargada de verificar si el usuario existe en la BD.
     *
     * @param usuario string que posee los datos para verificar la clave del
     * usuario.
     *
     * @return 4 El usuario no se encuentra disponible, 5 el usuario se 
     * encuentra disponible.
     * @throws Logica.Modulo1.VerificarUsuarioException
     */
    @Override
    public Entidad verificarUsuario(String usuario) 
            throws VerificarUsuarioException{
        int respuesta = 0;
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            CallableStatement a =
                    conn.prepareCall(RegistroBaseDatos.VERIFICAR_USUARIO);
            
            a.setString(1, usuario);
            a.execute();
            
            ResultSet rs = a.getResultSet();
            while(rs.next()){
                if (rs.getString(1)!=null){
                    st.close();
                    respuesta =  4; //Usuario no disponible
                }else{     
                   st.close();
                   respuesta =  3; //Usuario Disponible
                   
                
                }
            }
        } catch (SQLException ex) {
             Logger.getLogger(Modulo1sResource.class.getName()).
                     log(Level.SEVERE, null, ex);
             throw  FabricaExcepcion.
                     instanciarVerificarUsuarioException(ex.getErrorCode(),
                             ex.getMessage());  
   
        } catch (Exception e) {
            respuesta =  2;//cambiar
        }
         return FabricaEntidad.obtenerSimpleResponseStatus(respuesta);
    }
    
    /**
     * Funcion encargada de verificar existencia de un usuario en el sistema 
     * y de existir verifica si el password recibido es igual al que está 
     * almacenado en la BD
     *
     * @param usuario entidad que posee los datos para verificar el inicio 
     * sesion del usuario.
     *
     * @return De validar correctamente el usuario y password retorna un JSON en
     * String con todos los datos del usuario,7 Se valido inicio de sesion del 
     * usuario correctamente,
     * @throws Logica.Modulo1.IniciarSesionException
     * 
     */
    @Override
    public Entidad obtenerInicioSesion(Entidad usuario) 
            throws IniciarSesionException{
        Usuario objeto = (Usuario) usuario;
        String respuesta="";
        int bandera=0;
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            
            CallableStatement a = 
                    conn.prepareCall(RegistroBaseDatos.INICIO_SESION);
            a.setString(1,  objeto.getUsuario());
            a.setString(2,  objeto.getContrasena());
            a.execute();
  
           ResultSet rs = a.getResultSet();
            while (rs.next()) {
                JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                usuarioBuilder.add("u_id", rs.getString("id"));
                usuarioBuilder.add("u_usuario", rs.getString("usuario"));
                usuarioBuilder.add("u_nombre", rs.getString("nombre"));
                usuarioBuilder.add("u_apellido", rs.getString("apellido"));
                usuarioBuilder.add("u_correo", rs.getString("correo"));
                usuarioBuilder.add("u_pregunta", rs.getString("pregunta"));
                usuarioBuilder.add("u_respuesta", rs.getString("respuesta"));
                usuarioBuilder.add("u_password", rs.getString("password"));
                JsonObject usuarioJsonObject = usuarioBuilder.build();
                respuesta= usuarioJsonObject.toString()+":-:iniciosesion";
                bandera=1;
            }
            if(bandera==0){
              st.close();
              respuesta= "7";
        }
            
        } catch (SQLException ex) {
            respuesta= "7";
            Logger.getLogger(Modulo1sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            throw FabricaExcepcion.
                    instanciarIniciarSesionException(ex.getErrorCode(),
                            ex.getMessage());  
        } 
        
    return FabricaEntidad.obtenerSimpleResponse(respuesta);
    }
    
      /**
     * Función que verifica existencia de un usuario en el sistema y de existir
     * verifica si el password recibido es igual al que está almacenado en la BD
     *
     * @param usuario string que posee los datos para verificar la clave del
     * usuario.
     *
     * @return De existir el usuario y la contraseña coincide retorna un JSON en
     * String con todos los datos del usuario. De lo contrario retorna el String
     * "ERROR"
     * @throws Logica.Modulo1.RecuperarClaveException
     */
    
    @Override
    public Entidad obtenerXRecuperarClave(String usuario) 
            throws RecuperarClaveException{
        String respuesta="";
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //String query = "SELECT * from recuperarclave('" + usuario+"');";
            CallableStatement a = 
                    conn.prepareCall(RegistroBaseDatos.RECUPERAR_CLAVE);
            a.setString(1, usuario);
            a.execute(); 
            ResultSet rs = a.getResultSet();
            Integer bandera = 0;
            while (rs.next()) {
                JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                usuarioBuilder.add("u_id", rs.getString("id"));
                usuarioBuilder.add("u_usuario", rs.getString("usuario"));
                usuarioBuilder.add("u_nombre", rs.getString("nombre"));
                usuarioBuilder.add("u_apellido", rs.getString("apellido"));
                usuarioBuilder.add("u_correo", rs.getString("correo"));
                usuarioBuilder.add("u_pregunta", rs.getString("pregunta"));
                usuarioBuilder.add("u_respuesta", rs.getString("respuesta"));
                usuarioBuilder.add("u_password", rs.getString("password"));
                JsonObject usuarioJsonObject = usuarioBuilder.build();
                respuesta = usuarioJsonObject.toString()+":-:recuperarclave";
                bandera = 1;
            }
            if(bandera == 0){
                st.close();
                respuesta = "ERROR";
               throw FabricaExcepcion.instanciarRecuperarClaveException(202);
                
            }
            
        } catch (SQLException ex) {
            respuesta = "ERROR";
            Logger.getLogger(Modulo1sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            throw FabricaExcepcion.
                    instanciarRecuperarClaveException(ex.getErrorCode(),
                            ex.getMessage());  
        }
        return FabricaEntidad.obtenerSimpleResponse(respuesta);
    }
    

    @Override
    public Entidad modificar(Entidad e) {
        Usuario obj = (Usuario) e;
        CallableStatement cstmt;
        try {
            cstmt = conn.prepareCall(RegistroBaseDatos.ACTUALIZAR_USUARIO);
            cstmt.setInt(1, obj.getId());
            cstmt.setString(2, obj.getUsuario());
            cstmt.setString(3, obj.getNombre());
            cstmt.setString(4, obj.getApellido());
            cstmt.setString(5, obj.getCorreo());
            cstmt.setString(6, obj.getPregunta());
            cstmt.setString(7, obj.getRespuesta());
            cstmt.setString(8, obj.getContrasena());
            cstmt.execute();   
        }catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).
                    log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(DaoUsuario.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public Entidad consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListaEntidad consultarTodos(int idUsuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
