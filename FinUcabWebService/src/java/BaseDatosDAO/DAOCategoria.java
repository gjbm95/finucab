
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOCategoria;
import Dominio.Categoria;
import Dominio.Entidad;
import java.sql.CallableStatement;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import IndentityMap.FabricaIdentityMap;
import IndentityMap.IdentityMap;
import Registro.RegistroIdentityMap;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MariPerez
 */
public class DAOCategoria extends DAO implements IDAOCategoria { 
    
    private Connection conn = Conexion.conectarADb();
    
    public Entidad agregar(Entidad e) {
        
        Categoria categoria = (Categoria) e;            
        int idCategoria = 0;
        
        try {
            Statement st = conn.createStatement();
            CallableStatement cat = conn.prepareCall("{ call AgregarCategoria(?,?,?,?,?) }");
            cat.setInt(1, categoria.getIdUsario());
            cat.setString(2, categoria.getNombre());
            cat.setString(3, categoria.getDescripcion());
            cat.setBoolean(4, categoria.isIngreso());
            cat.setBoolean(5, categoria.isEstaHabilitado());
            cat.executeQuery();
            ResultSet rs = cat.getResultSet();
            rs.next();
            
            idCategoria = rs.getInt(1);
            
            categoria.setId(idCategoria);
            System.out.println(idCategoria+"id super");
            IdentityMap.getInstance().addEntidadEnLista(RegistroIdentityMap.categoria_listado, categoria);
            System.out.println(categoria.getId()+"id normal");
            } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
              catch (Exception ex) {

            return FabricaEntidad.obtenerSimpleResponseStatus(2);
       }
        return FabricaEntidad.obtenerSimpleResponse(idCategoria);
    }
                           
    @Override
    public Entidad modificar(Entidad e) {
        Categoria categoria = (Categoria) e;        
        try {
            CallableStatement cstmt;
            cstmt = conn.prepareCall("{ call ModificarCategoria(?,?,?,?,?) }");
            cstmt.setString(1,categoria.getNombre());
            cstmt.setString(2,categoria.getDescripcion());
            cstmt.setBoolean(3,categoria.isIngreso());
            cstmt.setBoolean(4,categoria.isEstaHabilitado());
            cstmt.setInt(5, categoria.getId());
            System.out.println(cstmt.toString());
            cstmt.execute();

            System.out.println("despues del stored");
            IdentityMap.getInstance().updateEntidadEnLista(RegistroIdentityMap.categoria_listado, categoria);
            
           } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categoria;
    } 
    
    @Override
    public Entidad consultar(int idcategoria) {
        
         Entidad categoria = FabricaIdentityMap.obtenerIdentityMap().getInstance().getEntidadEnLista(RegistroIdentityMap.categoria_listado, idcategoria);          

         if (categoria == null){
                            
                try {

                   Statement st = conn.createStatement();

                   CallableStatement a = conn.prepareCall("{ call ConsultarCategoria(?) }");
                   a.setInt(1, idcategoria);
                   a.executeQuery();

                   ResultSet rs = a.getResultSet();
                   while (rs.next()){
                       
                       categoria = new Categoria( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5), rs.getInt(6));
                   }

               } catch (SQLException ex) {
                   Logger.getLogger(DAOPago.class.getName()).log(Level.SEVERE, null, ex);
               }
         }
        
        return categoria;
        
    }
    
    @Override
    public ListaEntidad consultarTodos(int idUsuario) {
        
        ListaEntidad listaEntidad = FabricaIdentityMap.obtenerIdentityMap().getInstance().getListaEntidad(RegistroIdentityMap.categoria_listado);
        
        if (listaEntidad.getLista().isEmpty() ){
            
            try {
                ArrayList<Entidad> listaCategorias = new ArrayList<>();
                Statement st = conn.createStatement();
                CallableStatement a = conn.prepareCall("{ call ConsultarTodos(?) }");
                a.setInt(1, idUsuario);
                a.executeQuery();
                ResultSet rs = a.getResultSet();

                while (rs.next())
                {
                   Categoria ca = new Categoria( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5), rs.getInt(6));
                    listaCategorias.add(ca);
                }            
                
                listaEntidad = FabricaEntidad.obtenerListaEntidad(listaCategorias);
                FabricaIdentityMap.obtenerIdentityMap().getInstance().setListaEntidad(RegistroIdentityMap.categoria_listado, listaEntidad);
                
            } catch (SQLException ex) {
                Logger.getLogger(DAOPago.class.getName()).log(Level.SEVERE, null, ex);
            }
              catch(Exception e) {
                return null;
            }                
        }
        
        return listaEntidad;
    }
    
    @Override 
    public Entidad eliminarCategoria(int idCategoria){
        Entidad respuesta = null;
        try {
            Statement st = conn.createStatement();
            EliminarCategoria2(idCategoria, "presupuesto");
            EliminarCategoria2(idCategoria,"pago");
            CallableStatement cat = conn.prepareCall("{ call EliminarCategoria(?) }");
            cat.setInt(1,idCategoria);
            cat.executeQuery();
            ResultSet rs = cat.getResultSet();
            rs.next();
            if (rs.getInt(1)==1){
                respuesta = FabricaEntidad.obtenerSimpleResponse(1);
                
            }else{
                respuesta = FabricaEntidad.obtenerSimpleResponse(0);
            }
            
            FabricaIdentityMap.obtenerIdentityMap().getInstance().rmEntidadEnLista(RegistroIdentityMap.categoria_listado, idCategoria);
            
            } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;
    }        
    
    public boolean EliminarCategoria2 (int idcat, String tabla){
        boolean respuesta = false;
        try {
            CallableStatement cstmt;
            cstmt = conn.prepareCall("{ call EliminarCategoria2(?,?) }");
            cstmt.setInt(1, idcat);
            cstmt.setString(2, tabla);
            cstmt.execute();
            
           } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}
