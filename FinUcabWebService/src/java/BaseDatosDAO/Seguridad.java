
package BaseDatosDAO;
import static Registro.RegistroBaseDatos.url;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
* Modulo 2 - Modulo de Pefil y Gestion de Cuentas. 
* Desarrolladores:
* Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
* Descripcion de la clase: 
* Se encarga de gestionar los datos de la conexion a la base de datos. 
*@Params
*
**/
public class Seguridad {
  
private static Seguridad instancia;
public static String data = "gRvpGYTBY/T9L47kChbEeFRwmuf6Usa46P42uTu"
            + "ozRG7rndXL1tGFl29jo97SBK+wgfKTkUQA86d70hZky2ayw==";

private Seguridad () {}

/**
 * Fabrica de la clase Seguridad.
 * @return Devuelve la instanciacion de la clase. 
 */
public static Seguridad obtenerInstancia(){
  if (instancia==null)
      instancia = new Seguridad(); 
  return instancia; 
}
    



/**
 * Metodo que devuelve los datos desencriptados de la conexion a la base de 
 * datos.
 * @return
 */
private String obtenerDatos(){
  String respuesta = "";
        try {       
         
                            
            respuesta = this.Desencriptar(data);
 
        } catch (Exception ex) {
            Logger.getLogger(Seguridad.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        
        return respuesta; 
}

/**
 * Metodo que devuelve el nombre de la base de datos. 
 */
public String obtenerUsuarioDB(){
  String [] data = this.obtenerDatos().split("%");
  return data[0];  
}

/**
 * Metodo que devuelve la contrasena de la base de datos.
 * @return 
 */
public String obtenerContrasenaDB(){
  String [] data = this.obtenerDatos().split("%");
  return data[1];  
}

/**
 * Metodo que devuelve la direccion de servidor de la base de datos. 
 * @return 
 */
public String obtenerServerDB(){
  String [] data = this.obtenerDatos().split("%");
  return data[2];  
}


    /**
     * Metodo encargado de Encriptar datos de texto 
     * @param texto Es el texto a encriptar
     * @return 
     */
    public String Encriptar(String texto) {
 
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
    

    /**
     * Metodo encargado de Desencriptar datos de texto 
     * @param texto Es el texto a Desencriptar
     * @return 
     */
    public String Desencriptar(String textoEncriptado) {

        String secretKey = "qualityinfosolutions"; //llave para desenciptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }


}
