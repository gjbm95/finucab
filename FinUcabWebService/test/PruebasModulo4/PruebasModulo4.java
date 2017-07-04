/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasModulo4;

import BaseDatosDAO.DAOCategoria;
import BaseDatosDAO.FabricaDAO;
import Dominio.Categoria;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.SimpleResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeffrey
 */
public class PruebasModulo4 {
    
    public PruebasModulo4() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     /*@Test
     public void TestAgregar() {
     
         Entidad categoria = FabricaEntidad.obtenerCategoria(1,"juan","suedo del mes",true,true);
         DAOCategoria dao = FabricaDAO.instanciasDaoCategoria();
         SimpleResponse respuesta = (SimpleResponse)dao.agregar(categoria);
         Categoria con = (Categoria)dao.consultar(respuesta.getId());
         assertEquals(respuesta.getId(),con.getId());
         
     }*/
}
